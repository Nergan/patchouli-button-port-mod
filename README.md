# Patchouli Button

**[English](README.md)** · **[Русский](README.ru.md)**

A **Minecraft 1.21.1** NeoForge port of [PatchouliButton](https://modrinth.com/mod/patchoulibutton) by Globox_Z. Written in Kotlin with [Kotlin for Forge](https://modrinth.com/mod/kotlin-for-forge).

The inventory button and the config screen are available in English and Russian.

## Downloads

Jars are published to [GitHub Releases](https://github.com/Nergan/patchouli-button-neoforge-port-mod/releases/latest) and [Modrinth](https://modrinth.com/project/patchouli-button-neoforge-port). A push to `main` updates the current version’s release.

| File | Required | What it is |
| --- | --- | --- |
| `patchoulibutton-1.0.0.jar` | Yes | this mod |
| Kotlin for Forge 5.8+ | Yes | [Kotlin for Forge](https://modrinth.com/mod/kotlin-for-forge) |
| Patchouli 1.21.1-93+ | Yes | [Patchouli](https://modrinth.com/mod/patchouli) |

Do not install `*-sources.jar`.

## What it does

- Adds a book button to the survival inventory. It lists every Patchouli book loaded by the current mods and opens the one you pick.
- The list comes from Patchouli’s book registry, not from the items in your inventory. A book is listed even if nobody gave you the item.
- With **Clear starter Patchouli books** on (the default), `patchouli:guide_book` items that show up during the first five seconds after you join are removed. Books that were already in the inventory at the moment of joining stay there.

## Requirements

| Component | Version |
| --- | --- |
| Minecraft | 1.21.1 |
| NeoForge | 21.1.209 (any 21.1.x should work) |
| Kotlin for Forge | 5.8.0, **NeoForge** build |
| Patchouli | 1.21.1-93 or newer for NeoForge |
| Java | 21 |

The mod is required on both client and server.

## Configuration

In-game: Mods → Patchouli Button → Config.

| File | What it controls |
| --- | --- |
| `config/patchoulibutton-client.toml` | button position, and whether it opens the full list or one book |
| `saves/<world>/serverconfig/patchoulibutton-server.toml` | whether starter books are cleared |

On a dedicated server the server file is `world/serverconfig/patchoulibutton-server.toml`. `clear_starting_books` is a `SERVER` option: the server owns it and syncs it to clients.

| Option | Default | Meaning |
| --- | --- | --- |
| `clear_starting_books` | `true` | remove Patchouli guide books gained in the first 5 seconds after joining |
| `open_all_books` | `true` | open the list; if `false`, open `book_id` |
| `book_id` | empty | book to open when the list is disabled, such as `modid:book_name` |
| `button_x` | `127` | button position inside the survival inventory |
| `button_y` | `61` | button position inside the survival inventory |

## License

[MIT](LICENSE). This is a port of PatchouliButton by Globox_Z, which is also MIT.
