package com.patchoulibutton.mod.client

import com.patchoulibutton.mod.book.CompendiumBook
import com.patchoulibutton.mod.config.ClientConfig
import com.patchoulibutton.mod.mixin.ContainerScreenAccessor
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screens.inventory.InventoryScreen
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack
import vazkii.patchouli.common.item.ItemModBook
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.client.event.ScreenEvent
import vazkii.patchouli.api.PatchouliAPI

object InventoryBookButton {
    private const val SIZE = 18

    @SubscribeEvent
    fun onRender(event: ScreenEvent.Render.Post) {
        if (!ClientConfig.CONFIG.showBookButton.get()) return
        val screen = event.screen as? InventoryScreen ?: return
        val accessor = screen as ContainerScreenAccessor
        val x = accessor.getScreenLeft() + ClientConfig.CONFIG.buttonX.get()
        val y = accessor.getScreenTop() + ClientConfig.CONFIG.buttonY.get()
        val graphics = event.guiGraphics
        val hovered = hit(event.mouseX, event.mouseY, x, y)
        if (hovered) {
            graphics.fill(x, y, x + SIZE, y + SIZE, 0x80FFFFFF.toInt())
        }
        graphics.renderItem(bookStack(), x + 1, y + 1)
        if (hovered) {
            graphics.renderTooltip(
                Minecraft.getInstance().font,
                Component.translatable("patchoulibutton.screen.button"),
                event.mouseX,
                event.mouseY,
            )
        }
    }

    @SubscribeEvent
    fun onClick(event: ScreenEvent.MouseButtonPressed.Pre) {
        if (!ClientConfig.CONFIG.showBookButton.get()) return
        val screen = event.screen as? InventoryScreen ?: return
        if (event.button != 0) return
        val accessor = screen as ContainerScreenAccessor
        val x = accessor.getScreenLeft() + ClientConfig.CONFIG.buttonX.get()
        val y = accessor.getScreenTop() + ClientConfig.CONFIG.buttonY.get()
        if (!hit(event.mouseX.toInt(), event.mouseY.toInt(), x, y)) return
        PatchouliAPI.get().openBookGUI(CompendiumBook.ID)
        event.isCanceled = true
    }

    private fun bookStack(): ItemStack {
        val stack = ItemModBook.forBook(CompendiumBook.ID)
        return if (stack.isEmpty) net.minecraft.world.item.Items.BOOK.defaultInstance else stack
    }

    private fun hit(mouseX: Int, mouseY: Int, x: Int, y: Int): Boolean {
        return mouseX >= x && mouseX < x + SIZE && mouseY >= y && mouseY < y + SIZE
    }
}
