package com.patchoulibutton.mod.util

import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack

object PatchouliGuideItems {
    private val guideBookId: ResourceLocation = ResourceLocation.fromNamespaceAndPath("patchouli", "guide_book")
    private val bookComponentId: ResourceLocation = ResourceLocation.fromNamespaceAndPath("patchouli", "book")

    fun isGuideBook(stack: ItemStack): Boolean {
        if (stack.isEmpty) return false
        return BuiltInRegistries.ITEM.getKey(stack.item) == guideBookId
    }

    fun bookKey(stack: ItemStack): String? {
        if (!isGuideBook(stack)) return null
        val id = bookId(stack)
        return id?.toString() ?: guideBookId.toString()
    }

    fun counts(player: Player): Map<String, Int> {
        val counts = LinkedHashMap<String, Int>()
        forEachGuide(player) { key, stack ->
            counts[key] = (counts[key] ?: 0) + stack.count
        }
        return counts
    }

    fun removeExcess(player: Player, excess: Map<String, Int>) {
        if (excess.isEmpty()) return
        val left = HashMap(excess)
        forEachGuide(player) { key, stack ->
            val remaining = left[key] ?: return@forEachGuide
            if (remaining <= 0) return@forEachGuide
            val take = minOf(remaining, stack.count)
            stack.shrink(take)
            left[key] = remaining - take
        }
        player.containerMenu.broadcastChanges()
    }

    private fun forEachGuide(player: Player, action: (String, ItemStack) -> Unit) {
        val inventory: Inventory = player.inventory
        for (slot in 0 until inventory.containerSize) {
            val stack = inventory.getItem(slot)
            val key = bookKey(stack) ?: continue
            action(key, stack)
        }
        val carried = player.containerMenu.carried
        val carriedKey = bookKey(carried)
        if (carriedKey != null) action(carriedKey, carried)
    }

    @Suppress("UNCHECKED_CAST")
    private fun bookId(stack: ItemStack): ResourceLocation? {
        val type = BuiltInRegistries.DATA_COMPONENT_TYPE.get(bookComponentId) as? DataComponentType<ResourceLocation>
            ?: return null
        return stack.get(type)
    }
}
