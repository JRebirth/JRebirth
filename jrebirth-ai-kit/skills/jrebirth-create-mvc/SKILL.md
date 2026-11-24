---
name: jrebirth-create-mvc
description: >-
  Scaffold a complete JRebirth MVC feature (screen, editor, dashboard, panel).
  Use when a new self-contained UI unit needs Model, View, Controller, and
  optionally Commands, Services, and Waves.
---
# Create full MVC feature

A new screen, editor, dashboard, or panel needs its own coherent JRebirth structure.

## Standard pack

- Model
- View
- Controller
- one or more Commands if named actions exist
- one or more Services if external or slow work exists
- Waves only if decoupled messaging is needed

## Order

Start with the Model contract, then the View skeleton, then Controller wiring, then Commands and Services.

Use the per-role skills (`jrebirth-create-model`, `jrebirth-create-view`, `jrebirth-create-controller`, `jrebirth-create-command`, `jrebirth-create-multi-command`, `jrebirth-create-service`, `jrebirth-create-wave`) for each part.

Reference: `references/wb-csmvc.md` and `references/wb-csmvc-diagram.svg`.
