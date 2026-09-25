package com.patchoulibutton.mod.client

import net.minecraft.client.gui.screens.Screen
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModContainer
import net.neoforged.neoforge.client.gui.ConfigurationScreen
import net.neoforged.neoforge.client.gui.IConfigScreenFactory
import net.neoforged.neoforge.common.NeoForge

object ClientModEvents {
    fun init(@Suppress("UNUSED_PARAMETER") modBus: IEventBus, modContainer: ModContainer) {
        modContainer.registerExtensionPoint(
            IConfigScreenFactory::class.java,
            IConfigScreenFactory { container, parent: Screen -> ConfigurationScreen(container, parent) },
        )
        NeoForge.EVENT_BUS.register(InventoryBookButton)
        NeoForge.EVENT_BUS.register(CompendiumReturn)
    }
}
