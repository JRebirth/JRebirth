---
name: mvc-feature
description: >-
  Scaffold a complete JRebirth MVC feature (screen, editor, dashboard, panel).
  Use when a new self-contained UI unit needs Model, View, Controller, and
  optionally Commands, Services, and Waves.
---
<!-- gen .mdh -->

# Create Full MVC Feature

A new screen editor dashboard or panel self-contained JRebirth unit Build coherent structure start so stays maintainable grows

## Standard pack

Every feature needs minimum:

- Model — owns state bindings orchestration
- View — builds scene graph nodes exposes controls Controller
- Controller — wires user gestures Model calls Commands, or Services

Add only when feature genuinely needs them:

- Commands — one per named explicit action (save, delete navigate)
- Services — one per external slow or integration boundary
- Waves — only when communication another feature must decoupled

## Creation order

Always define roles order so each layer can built one before it:

1. Define Model contract: what state does feature own? What properties bindings does expose?
2. Build View scene graph skeleton expose only controls Controller need
3. Wire gestures Controller delegate Model or Commands
4. Add Commands each explicit action
5. Add Services external or long-running work
6. Add Waves only where cross-feature, decoupled communication necessary

## Per-role skills

Use dedicated skill each role creating:

- Model → `model` skill
- View → `view` skill
- Controller → `controller` skill
- Single command → `command-single` skill
- Ordered command chain → `command-multi` skill
- Service → `service` skill
- Waves → `waves` skill

## Reference

`reference.md` contains wB-CSMVC flow diagram direction rules Open `wb-csmvc-diagram.svg` when Mermaid rendering not available
