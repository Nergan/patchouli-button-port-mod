package com.patchoulibutton.mod.client

import com.patchoulibutton.mod.config.ClientConfig
import com.patchoulibutton.mod.mixin.ContainerScreenAccessor
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screens.inventory.InventoryScreen
import net.minecraft.network.chat.Component
import net.minecraft.world.item.Items
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.client.event.ScreenEvent

object InventoryBookButton {
    private const val SIZE = 18

    @SubscribeEvent
    fun onRender(event: ScreenEvent.Render.Post) {
        val screen = event.screen as? InventoryScreen ?: return
        val accessor = screen as ContainerScreenAccessor
        val x = accessor.getScreenLeft() + ClientConfig.CONFIG.buttonX.get()
        val y = accessor.getScreenTop() + ClientConfig.CONFIG.buttonY.get()
        val graphics = event.guiGraphics
        val hovered = hit(event.mouseX, event.mouseY, x, y)
        if (hovered) {
            graphics.fill(x, y, x + SIZE, y + SIZE, 0x80FFFFFF.toInt())
        }
        graphics.renderItem(Items.BOOK.defaultInstance, x + 1, y + 1)
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
        val screen = event.screen as? InventoryScreen ?: return
        if (event.button != 0) return
        val accessor = screen as ContainerScreenAccessor
        val x = accessor.getScreenLeft() + ClientConfig.CONFIG.buttonX.get()
        val y = accessor.getScreenTop() + ClientConfig.CONFIG.buttonY.get()
        if (!hit(event.mouseX.toInt(), event.mouseY.toInt(), x, y)) return
        BookListScreen.openConfigured(screen)
        event.isCanceled = true
    }

    private fun hit(mouseX: Int, mouseY: Int, x: Int, y: Int): Boolean {
        return mouseX >= x && mouseX < x + SIZE && mouseY >= y && mouseY < y + SIZE
    }
}
