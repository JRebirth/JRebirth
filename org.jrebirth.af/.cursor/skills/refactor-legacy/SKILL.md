---
name: refactor-legacy
description: >-
  Migrate legacy or messy JavaFX code into the JRebirth role split incrementally.
  Use before editing many files in an old JavaFX feature, or when a class mixes
  state, nodes, events, and actions.
---
<!-- gen .mdh -->

# Refactor Legacy JavaFX JRebirth

Use skill before touching class mixes responsibilities — state node construction event handling backend calls all one place Jumping straight edits without plan leads larger messier code

## When use skill

- A class has fields both UI nodes application state
- A class handles events also calls REST endpoint or database
- A class uses `Platform.runLater` or raw `Thread` routine work
- A feature has no clear Model, View, Controller split

## Migration order

Work through steps order Complete each step before moving next one

1. Extract UI state Model Move all application-level fields (lists, selections form values flags) new or existing Model JavaFX properties Bind UI controls properties
2. Isolate node construction View Move scene graph construction layout View class The View should only build nodes expose controls Controller needs
3. Move event hookup Controller Wire button clicks key handlers selection listeners Controller Each handler should one or two lines delegate Model or Command
4. Extract explicit actions Commands Each named action (save, delete navigate import) becomes own Command class Replace inline logic `callCommand(...)`.
5. Extract slow or external work Services Any call touches database REST endpoint file or runs more than few milliseconds belongs Service
6. Introduce Waves cross-feature communication Replace direct component-to-component calls Waves only where decoupling genuinely useful

## Safety rule

Refactor incrementally Complete verify one step before starting next Each step should produce working testable state Do not rewrite entire class one pass — risk introducing bugs much higher changes become too large review

See `anti-patterns` skill recognize specific problems before start
