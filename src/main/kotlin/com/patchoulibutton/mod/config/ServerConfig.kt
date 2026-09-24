package com.patchoulibutton.mod.config

import net.neoforged.neoforge.common.ModConfigSpec

/**
 * Конфиг типа SERVER. Файл мира, на выделенном сервере его задаёт сервер.
 * Экран NeoForge на чужом сервере показывает значения только для чтения.
 */
class ServerConfig(builder: ModConfigSpec.Builder) {
    val clearStartingBooks: ModConfigSpec.BooleanValue

    init {
        builder.push("books")
        clearStartingBooks = builder
            .comment(
                "Remove Patchouli guide books that appear in a player's inventory during the first 5 seconds after they join.",
                "Books already in the inventory at the moment of joining are left alone.",
                "Убирать книги-гайды Patchouli, которые появляются в инвентаре в первые 5 секунд после входа.",
                "Книги, которые уже лежали в инвентаре в момент входа, не трогаются.",
            )
            .translation("$PREFIX.books.clear_starting_books")
            .define("clear_starting_books", true)
        builder.pop()
    }

    companion object {
        private const val PREFIX = "patchoulibutton.configuration"

        val SPEC: ModConfigSpec
        val CONFIG: ServerConfig

        init {
            val pair = ModConfigSpec.Builder().configure(::ServerConfig)
            CONFIG = pair.getLeft()
            SPEC = pair.getRight()
        }
    }
}
