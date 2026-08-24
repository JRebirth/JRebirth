---
name: anti-patterns
description: >-
  Spot and fix JRebirth anti-patterns (runLater glue, raw threads, logic in
  controllers, state in views, global facade abuse). Use when reviewing or
  cleaning up JRebirth code that smells like plain JavaFX.
---
<!-- gen from .mdh -->

# Avoid anti-patterns

## Smells

- `Platform.runLater` or `JRebirth.runInto*` used architecture glue
- `getCommand(...).run(...)` instead `callCommand` / `sendWave(WBuilder.callCommand(...))`
- raw thread creation routine flows
- heavy logic controllers
- business state hidden views
- `globalFacade` access everywhere

## Fix path

Find missing JRebirth role then move code it For thread hops use `threading` skill For Command triggering use `command-invoke` skill See also jrebirth-core refactor-legacy
