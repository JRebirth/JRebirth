---
name: anti-patterns
description: >-
  Spot and fix JRebirth anti-patterns (runLater glue, raw threads, logic in
  controllers, state in views, global facade abuse). Use when reviewing or
  cleaning up JRebirth code that smells like plain JavaFX.
---
<!-- gen .mdh -->

# Avoid anti-patterns

## Smells

- `Platform.runLater` used architecture glue
- raw thread creation routine flows
- heavy logic controllers
- business state hidden views
- `globalFacade` access everywhere

## Fix path

Find missing JRebirth role then move code it See jrebirth-core refactor-legacy skills
