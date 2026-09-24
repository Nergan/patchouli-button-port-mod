package com.patchoulibutton.mod.config

import net.neoforged.neoforge.common.ModConfigSpec

/**
 * Клиентский конфиг: кнопка в инвентаре. Файл `config/patchoulibutton-client.toml`.
 */
class ClientConfig(builder: ModConfigSpec.Builder) {
    val openAllBooks: ModConfigSpec.BooleanValue
    val bookId: ModConfigSpec.ConfigValue<String>
    val buttonX: ModConfigSpec.IntValue
    val buttonY: ModConfigSpec.IntValue

    init {
        builder.push("button")
        openAllBooks = builder
            .comment(
                "If true, the inventory button opens the list of every loaded Patchouli book.",
                "If false, it opens the single book from book_id.",
                "Если да, кнопка открывает список всех загруженных книг Patchouli.",
                "Если нет, открывает одну книгу из book_id.",
            )
            .translation("$PREFIX.button.open_all_books")
            .define("open_all_books", true)
        bookId = builder
            .comment(
                "Book id opened when open_all_books is false, for example 'modid:book_name'.",
                "Идентификатор книги, если open_all_books выключен, например 'modid:book_name'.",
            )
            .translation("$PREFIX.button.book_id")
            .define("book_id", "")
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
