# Patchouli Button

**[English](README.md)** · **[Русский](README.ru.md)**

Порт [PatchouliButton](https://modrinth.com/mod/patchoulibutton) от Globox_Z на **Minecraft 1.21.1** и NeoForge. Написан на Kotlin через [Kotlin for Forge](https://modrinth.com/mod/kotlin-for-forge).

Кнопка в инвентаре и экран настроек есть на английском и русском.

## Загрузки

Jar публикуется в [GitHub Releases](https://github.com/Nergan/patchouli-button-neoforge-port-mod/releases/latest) и на [Modrinth](https://modrinth.com/project/patchouli-button-neoforge-port). Пуш в `main` обновляет релиз текущей версии.

| Файл | Нужен | Что это |
| --- | --- | --- |
| `patchoulibutton-1.0.0.jar` | Да | этот мод |
| Kotlin for Forge 5.8+ | Да | [Kotlin for Forge](https://modrinth.com/mod/kotlin-for-forge) |
| Patchouli 1.21.1-93+ | Да | [Patchouli](https://modrinth.com/mod/patchouli) |

`*-sources.jar` ставить не нужно.

## Что делает

- В инвентаре выживания появляется кнопка с книгой. Она показывает все книги Patchouli, которые загрузили установленные моды, и открывает выбранную.
- Список берётся из реестра Patchouli, а не из предметов в инвентаре. Книга есть в списке, даже если предмет вам не выдавали.
- Настройка **Убирать стартовые книги Patchouli** включена по умолчанию. Предметы `patchouli:guide_book`, которые появляются в первые пять секунд после входа, удаляются. Книги, которые уже лежали в инвентаре в момент входа, остаются.

## Требования

| Компонент | Версия |
| --- | --- |
| Minecraft | 1.21.1 |
| NeoForge | 21.1.209 (подойдёт любой 21.1.x) |
| Kotlin for Forge | 5.8.0, сборка **NeoForge** |
| Patchouli | 1.21.1-93 или новее для NeoForge |
| Java | 21 |

Мод нужен и на клиенте, и на сервере.

## Настройки

В игре: Mods → Patchouli Button → Config.

| Файл | Что задаёт |
| --- | --- |
| `config/patchoulibutton-client.toml` | положение кнопки и открывать ли весь список или одну книгу |
| `saves/<мир>/serverconfig/patchoulibutton-server.toml` | убирать ли стартовые книги |

На выделенном сервере файл лежит в `world/serverconfig/patchoulibutton-server.toml`. `clear_starting_books` — настройка типа `SERVER`: её задаёт сервер и рассылает клиентам.

| Параметр | По умолчанию | Смысл |
| --- | --- | --- |
| `clear_starting_books` | `true` | убирать книги Patchouli, появившиеся в первые 5 секунд после входа |
| `open_all_books` | `true` | открывать список; если `false`, открывается `book_id` |
| `book_id` | пусто | книга, если список выключен, например `modid:book_name` |
| `button_x` | `127` | положение кнопки в инвентаре выживания |
| `button_y` | `61` | положение кнопки в инвентаре выживания |

## Лицензия

[MIT](LICENSE). Это порт PatchouliButton от Globox_Z, у которого тоже лицензия MIT.
