package com.patchoulibutton.mod.network

import com.patchoulibutton.mod.PatchouliButtonMod
import com.patchoulibutton.mod.book.ExternalGuides
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.neoforged.neoforge.network.handling.IPayloadContext

class OpenExternalGuidePayload(val id: String) : CustomPacketPayload {
    override fun type(): CustomPacketPayload.Type<OpenExternalGuidePayload> = TYPE

    companion object {
        val TYPE: CustomPacketPayload.Type<OpenExternalGuidePayload> = CustomPacketPayload.Type(
            ResourceLocation.fromNamespaceAndPath(PatchouliButtonMod.MOD_ID, "open_guide"),
        )
        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, OpenExternalGuidePayload> = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            OpenExternalGuidePayload::id,
            ::OpenExternalGuidePayload,
        )

        fun handle(payload: OpenExternalGuidePayload, context: IPayloadContext) {
            context.enqueueWork {
                val player = context.player() as? ServerPlayer ?: return@enqueueWork
                ExternalGuides.open(player, payload.id)
            }
        }
    }
}
