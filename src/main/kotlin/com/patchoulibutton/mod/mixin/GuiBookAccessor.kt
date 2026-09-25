package com.patchoulibutton.mod.mixin

import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.gen.Accessor
import vazkii.patchouli.client.book.gui.GuiBook

@Mixin(GuiBook::class)
interface GuiBookAccessor {
    @Accessor("spread")
    fun patchouliButtonSpread(): Int

    @Accessor("spread")
    fun patchouliButtonSetSpread(value: Int)

    @Accessor("maxSpreads")
    fun patchouliButtonMaxSpreads(): Int

    @Accessor("maxSpreads")
    fun patchouliButtonSetMaxSpreads(value: Int)
}
