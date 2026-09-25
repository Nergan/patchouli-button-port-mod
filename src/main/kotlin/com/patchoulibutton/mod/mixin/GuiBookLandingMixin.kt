package com.patchoulibutton.mod.mixin

import com.patchoulibutton.mod.book.CompendiumBook
import com.patchoulibutton.mod.client.CompendiumIcons
import com.patchoulibutton.mod.client.ownsCompendium
import net.minecraft.client.resources.language.I18n
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.Shadow
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.Redirect
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo
import vazkii.patchouli.client.book.gui.GuiBook
import vazkii.patchouli.client.book.gui.GuiBookLanding
import vazkii.patchouli.common.book.Book

@Mixin(GuiBookLanding::class)
abstract class GuiBookLandingMixin {
    @Shadow
    lateinit var book: Book

    @Shadow
    var spread: Int

    @Shadow
    var maxSpreads: Int

    @Inject(method = ["init"], at = [At("TAIL")])
    private fun installCompendiumIcons(callback: CallbackInfo) {
        if (!book.ownsCompendium()) return
        maxSpreads = CompendiumIcons.pageCount()
        if (spread >= maxSpreads) spread = maxSpreads - 1
        CompendiumIcons.install(this as GuiBook, spread)
    }

    @Inject(method = ["onPageChanged"], at = [At("HEAD")], cancellable = true)
    private fun turnCompendiumPage(callback: CallbackInfo) {
        if (!book.ownsCompendium()) return
        maxSpreads = CompendiumIcons.pageCount()
        CompendiumIcons.install(this as GuiBook, spread)
        callback.cancel()
    }

    @Redirect(
        method = ["drawForegroundElements"],
        at = At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/resources/language/I18n;get(Ljava/lang/String;)Ljava/lang/String;",
        ),
    )
    private fun compendiumHeader(key: String): String {
        if (book.id == CompendiumBook.ID && key == "patchouli.gui.lexicon.categories") {
            return I18n.get("patchoulibutton.book.guides")
        }
        return I18n.get(key)
    }
}
