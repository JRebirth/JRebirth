---
name: update-site-doc
description: >-
  Update JRebirth Maven Site documentation when framework code changes.
  Use when adding or modifying API, commands, services, models, modules,
  annotations, or any public-facing JRebirth feature, or when asked to write or
  fix documentation pages.
---
<!-- gen from .mdh -->

# Update JRebirth Site Documentation

Keep Maven Site docs sync framework code

## Location

All doc pages live under `org.jrebirth.af/src/site/markdown/doc/`.
Format: Markdown plus Velocity (`.md.vm`).

## Sync checklist (when code changes)

1. Identify doc page covers changed module or feature
2. If no page exists create one
3. Update or add `MACRO{include}` snippets pointing changed source files
4. Verify snippet selectors still match method or class renames break `aj:` selectors
5. Update prose reflect new behavior parameters or annotations
6. If new module added also update `TocList.vm`, `site.xml`, `jrebirth-book.xml`.
7. For AI / vibecoding docs keep `doc/AI-Kit.md.vm` home `#ai-kit` section sync `jrebirth-ai-kit/README.md` (skills live only kit — do not duplicate them under `.cursor/skills`). Site downloads `pre-site`: `jrebirth-ai-kit-${project.version}.zip` (`src/assembly/ai-kit.xml`) `jrebirth-${project.version}.zip` (`src/assembly/framework-bundle.xml`). Link both `xdoc/index.xml.vm` when download strip changes

## Style rules

- write clear direct prose avoid filler
- use `**bold**` class or annotation name first mention
- keep code snippets real source via `MACRO{include}`, not hand-written
- body content goes inside `#[[ ]]#` block prevent Velocity parsing

For page template snippet selector syntax new-page steps module-to-page map see `reference.md`.
