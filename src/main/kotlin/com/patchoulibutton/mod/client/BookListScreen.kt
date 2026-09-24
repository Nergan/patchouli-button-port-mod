package com.patchoulibutton.mod.client

import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.components.Button
import net.minecraft.client.gui.screens.Screen
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import vazkii.patchouli.api.PatchouliAPI
import vazkii.patchouli.common.book.Book
import vazkii.patchouli.common.book.BookRegistry

class BookListScreen(private val parent: Screen) : Screen(TITLE) {
    private val books: List<Book> = BookRegistry.INSTANCE.books.values.sortedBy { it.id.toString() }
    private var page = 0

    override fun init() {
        clearWidgets()
        val x = width / 2 - 100
        var y = 36
        val from = page * PAGE_SIZE
        val until = minOf(from + PAGE_SIZE, books.size)
        for (index in from until until) {
            val book = books[index]
            addRenderableWidget(
                Button.builder(book.displayName()) {
                    PatchouliAPI.get().openBookGUI(book.id)
                }.bounds(x, y, 200, 20).build(),
            )
            y += 22
        }
        if (page > 0) {
            addRenderableWidget(
                Button.builder(Component.translatable("patchoulibutton.screen.prev")) { page--; rebuildWidgets() }
                    .bounds(width / 2 - 100, height - 28, 98, 20)
                    .build(),
            )
        }
        if (until < books.size) {
            addRenderableWidget(
                Button.builder(Component.translatable("patchoulibutton.screen.next")) { page++; rebuildWidgets() }
                    .bounds(width / 2 + 2, height - 28, 98, 20)
                    .build(),
            )
        }
    }

    override fun render(graphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
        renderBackground(graphics, mouseX, mouseY, partialTick)
        super.render(graphics, mouseX, mouseY, partialTick)
        graphics.drawCenteredString(font, title, width / 2, 14, 0xFFFFFF)
        if (books.isEmpty()) {
            graphics.drawCenteredString(
                font,
                Component.translatable("patchoulibutton.screen.empty"),
                width / 2,
                48,
                0xA0A0A0,
            )
        }
    }

    override fun onClose() {
        minecraft?.setScreen(parent)
    }

    override fun isPauseScreen(): Boolean = false

    companion object {
        private const val PAGE_SIZE = 8
        private val TITLE: Component = Component.translatable("patchoulibutton.screen.title")

        fun openConfigured(parent: Screen) {
            val minecraft = parent.minecraft ?: return
            if (com.patchoulibutton.mod.config.ClientConfig.CONFIG.openAllBooks.get()) {
                minecraft.setScreen(BookListScreen(parent))
                return
            }
            val raw = com.patchoulibutton.mod.config.ClientConfig.CONFIG.bookId.get()
            val id = ResourceLocation.tryParse(raw)
            if (id == null) {
                minecraft.player?.displayClientMessage(
                    Component.translatable("patchoulibutton.screen.bad_book", raw),
                    true,
                )
                return
            }
            PatchouliAPI.get().openBookGUI(id)
        }
    }
}

private fun Book.displayName(): Component = Component.translatable(name)
