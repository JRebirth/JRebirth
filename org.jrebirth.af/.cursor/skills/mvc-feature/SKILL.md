---
name: mvc-feature
description: >-
  Scaffold a complete JRebirth MVC feature (screen, editor, dashboard, panel).
  Use when a new self-contained UI unit needs Model, View, Controller, and
  optionally Commands, Services, and Waves.
---
<!-- gen .mdh -->

# Create full MVC feature

A new screen editor dashboard or panel needs own coherent JRebirth structure

## Standard pack

- Model
- View
- Controller
- one or more Commands if named actions exist
- one or more Services if external or slow work exists
- Waves only if decoupled messaging needed

## Order

Start Model contract then View skeleton then Controller wiring then Commands Services

Use per-role skills (model, view controller command-single, command-multi, service waves) each part

Reference: `reference.md` contains wB-CSMVC diagram role direction rules Use `wb-csmvc-diagram.svg` when Mermaid rendering not available
