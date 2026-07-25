---
name: jrebirth-core
description: >-
  Decide which JRebirth role a piece of code belongs to before writing it.
  Use when starting any JRebirth work, when roles feel blurred, or when plain
  JavaFX habits (runLater, raw threads, state in views) start leaking in.
---
<!-- gen from .mdh -->

# JRebirth core

Use stable role split so codebase stays predictable easy evolve

## Main rule

Before writing code ask: Application, Model, View, Controller, Command, Service, Wave, or Behavior?

## Ownership

- Application: startup shell stage scene customization
- Model: app state JavaFX properties bindings orchestration UI interactions
- View: node construction visual composition
- Controller: MVC event hookup
- Command: explicit action execution
- Service: long-running, backend or integration work
- Wave: decoupled communication
- Behavior: tiny reusable cross-cutting logic

## Anti-drift checks

- If View holds business rules move them Model
- If Controller owns persistent state move Model
- If Model performs backend I/O directly move Service
- If `runLater` shows up everywhere architecture leaking

Read matching role skill before creating or refactoring role

For complete wB-CSMVC role map see `../mvc-feature/reference.md`.
