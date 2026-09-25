package com.patchoulibutton.mod.book

import com.patchoulibutton.mod.PatchouliButtonMod
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Entity
import net.minecraft.world.level.LevelAccessor
import net.neoforged.fml.ModList

/**
 * Гайды, которые не являются книгами Patchouli, но должны попасть в книгу сборки
 * и в зачистку стартовых книг.
 */
object ExternalGuides {
    val CNC_FIELD_GUIDE: ResourceLocation = ResourceLocation.fromNamespaceAndPath("cnc", "field_guide")

    fun isExternalGuide(itemId: ResourceLocation): Boolean = itemId == CNC_FIELD_GUIDE && cncLoaded()

    fun open(player: ServerPlayer, id: String) {
        if (id == CNC_FIELD_GUIDE.toString()) {
            openCnc(player)
        }
    }

    private fun cncLoaded(): Boolean = ModList.get().isLoaded("cnc")

    private fun openCnc(player: ServerPlayer) {
        if (!cncLoaded()) return
        try {
            val method = Class.forName("net.imasillylittleguy.cnc.procedures.FieldGuideOpenProcedure")
                .getMethod(
                    "execute",
                    LevelAccessor::class.java,
                    Double::class.javaPrimitiveType,
                    Double::class.javaPrimitiveType,
                    Double::class.javaPrimitiveType,
                    Entity::class.java,
                )
            method.invoke(null, player.level(), player.x, player.y, player.z, player)
        } catch (exception: ReflectiveOperationException) {
            PatchouliButtonMod.LOGGER.warn("Could not open the Critters and Crawlers field guide", exception)
        }
    }
}
