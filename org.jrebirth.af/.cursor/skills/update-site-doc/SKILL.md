---
name: update-site-doc
description: >-
  Update JRebirth Maven Site documentation when framework code changes.
  Use when adding or modifying API, commands, services, models, modules,
  annotations, or any public-facing JRebirth feature, or when asked to write or
  fix documentation pages.
---
<!-- gen .mdh -->

# Update JRebirth Site Documentation

Keep Maven Site documentation sync whenever framework code changes Documentation drifts code confuses contributors users

## Location

All documentation pages live under `org.jrebirth.af/src/site/markdown/doc/`.

The format Markdown Velocity (`.md.vm`). Velocity macros control navigation table contents live code snippet includes

## Sync checklist (run through when code changes)

1. Identify documentation page covers changed module or feature Use module-to-page map `reference.md`.
2. If no page covers change create new one
3. Update or add `MACRO{include}` snippets pointing changed source files
4. Verify snippet selectors still match Method or class renames break `aj:` selectors — update them
5. Update prose reflect new behavior new parameters or changed annotations
6. If new module added also update `TocList.vm`, `site.xml`, `jrebirth-book.xml`.

## Style rules

- Write clear direct prose Avoid filler phrases like "it important note that" or "please aware"
- Use `**bold**` class or annotation name first mention section
- Keep code snippets sourced real files via `MACRO{include}` rather than hand-written This ensures examples stay accurate when code changes
- Place body content inside `#[[ ]]#` block prevent Velocity parsing it

For page template snippet selector syntax new-page steps module-to-page map see `reference.md`.
