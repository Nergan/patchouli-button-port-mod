package com.patchoulibutton.mod.config

import net.neoforged.neoforge.common.ModConfigSpec

/**
 * Клиентский конфиг: кнопка в инвентаре. Файл `config/patchoulibutton-client.toml`.
 */
class ClientConfig(builder: ModConfigSpec.Builder) {
    val showBookButton: ModConfigSpec.BooleanValue
    val buttonX: ModConfigSpec.IntValue
    val buttonY: ModConfigSpec.IntValue

    init {
        builder.push("button")
        showBookButton = builder
            .comment(
                "Show the compendium button in the survival inventory.",
                "Отображать кнопку книги сборки в инвентаре.",
            )
            .translation("$PREFIX.button.show_book_button")
            .define("show_book_button", false)
        buttonX = builder
            .comment(
                "Button X inside the survival inventory, counted from the panel's top-left corner.",
                "Координата X кнопки в инвентаре выживания, от левого верхнего угла панели.",
            )
            .translation("$PREFIX.button.button_x")
            .defineInRange("button_x", 127, -40, 200)
        buttonY = builder
            .comment(
                "Button Y inside the survival inventory, counted from the panel's top-left corner.",
                "Координата Y кнопки в инвентаре выживания, от левого верхнего угла панели.",
            )
            .translation("$PREFIX.button.button_y")
            .defineInRange("button_y", 61, -40, 200)
        builder.pop()
    }

    companion object {
        private const val PREFIX = "patchoulibutton.configuration"

        val SPEC: ModConfigSpec
        val CONFIG: ClientConfig

        init {
            val pair = ModConfigSpec.Builder().configure(::ClientConfig)
            CONFIG = pair.getLeft()
            SPEC = pair.getRight()
        }
    }
}
