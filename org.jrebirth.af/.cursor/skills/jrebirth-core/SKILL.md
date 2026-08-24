---
name: jrebirth-core
description: >-
  Decide which JRebirth role a piece of code belongs to before writing it.
  Use when starting any JRebirth work, when roles feel blurred, or when plain
  JavaFX habits (runLater, raw threads, state in views) start leaking in.
---
<!-- gen .mdh -->

# JRebirth Core

Use stable role split so codebase stays predictable easy evolve Every piece code belongs exactly one role

## Main rule

Before writing any code ask: Application, Model, View, Controller, Command, Service, Wave, or Behavior?

## Role ownership

- Application: startup shell stage scene customization
- Model: application state JavaFX properties bindings orchestration UI interactions
- View: scene graph node construction visual composition
- Controller: MVC event hookup — translating user gestures intent
- Command: one explicit named action execution
- Service: long-running, backend or integration work
- Wave: decoupled typed communication between components
- Behavior: tiny genuinely reusable cross-cutting interaction logic

## Anti-drift checks

Run checks before committing code JRebirth project:

- If View holds business rules or validation logic move them Model
- If Controller owns persistent state move Model
- If Model performs backend I/O directly move Service
- If `Platform.runLater` appears more than once feature code architecture leaking — restructure through waves services

## Next step

Read matching role skill before creating or refactoring role Each skill contains key API, templates examples role

For complete wB-CSMVC role map flow diagram see `../mvc-feature/reference.md`.
