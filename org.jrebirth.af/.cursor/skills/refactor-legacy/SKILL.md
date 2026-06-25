---
name: refactor-legacy
description: >-
  Migrate legacy or messy JavaFX code into the JRebirth role split incrementally.
  Use before editing many files in an old JavaFX feature, or when a class mixes
  state, nodes, events, and actions.
---
<!-- gen .mdh -->

# Refactor legacy JavaFX JRebirth

## Migration order

1. Identify UI state move Model
2. Isolate node construction View
3. Move event hookup Controller
4. Extract explicit actions Commands
5. Extract slow or external work Services
6. Introduce Waves only where decoupling useful

## Safety rule

Refactor incrementally not rewriting everything blindly
