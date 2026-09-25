package com.patchoulibutton.mod.event

import com.patchoulibutton.mod.book.CompendiumBook
import com.patchoulibutton.mod.config.ServerConfig
import com.patchoulibutton.mod.util.PatchouliGuideItems
import net.minecraft.server.level.ServerPlayer
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.event.entity.player.PlayerEvent
import vazkii.patchouli.common.item.ItemModBook

object CompendiumGift {
    @SubscribeEvent
    fun onLogin(event: PlayerEvent.PlayerLoggedInEvent) {
        val player = event.entity as? ServerPlayer ?: return
        if (!ServerConfig.CONFIG.giveCompendium.get()) return
        val data = player.persistentData
        if (data.getBoolean(CompendiumBook.GIVEN_FLAG)) return
        if (!PatchouliGuideItems.hasCompendium(player)) {
            val stack = ItemModBook.forBook(CompendiumBook.ID)
            if (stack.isEmpty) return
            if (!player.inventory.add(stack)) {
                player.drop(stack, false)
            }
        }
        data.putBoolean(CompendiumBook.GIVEN_FLAG, true)
    }

    @SubscribeEvent
    fun onClone(event: PlayerEvent.Clone) {
        if (!event.original.persistentData.getBoolean(CompendiumBook.GIVEN_FLAG)) return
        event.entity.persistentData.putBoolean(CompendiumBook.GIVEN_FLAG, true)
    }
}
