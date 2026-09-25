package com.patchoulibutton.mod

import com.patchoulibutton.mod.config.ClientConfig
import com.patchoulibutton.mod.config.ServerConfig
import com.patchoulibutton.mod.event.CompendiumGift
import com.patchoulibutton.mod.event.ModSetup
import com.patchoulibutton.mod.event.StartingBookCleaner
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.Mod
import net.neoforged.fml.config.ModConfig
import net.neoforged.neoforge.common.NeoForge
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@Mod(PatchouliButtonMod.MOD_ID)
class PatchouliButtonMod(modEventBus: IEventBus, modContainer: ModContainer) {

    companion object {
        const val MOD_ID = "patchoulibutton"

        @JvmField
        val LOGGER: Logger = LogManager.getLogger(MOD_ID)
    }

    init {
        modContainer.registerConfig(ModConfig.Type.SERVER, ServerConfig.SPEC)
        modContainer.registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC)
        ModSetup.init(modEventBus, modContainer)
        NeoForge.EVENT_BUS.register(StartingBookCleaner)
        NeoForge.EVENT_BUS.register(CompendiumGift)
    }
}
