---
name: jrebirth-invoke-command
description: >-
  Invoke JRebirth Commands with callCommand / linkCommand / sendWave(WBuilder.callCommand),
  never getCommand(...).run(...). Use when triggering a Command from Model, Controller,
  Service, or when reviewing command invocation code.
---
# Invoke Commands correctly

Never run a Command by retrieving it then calling `run` yourself. That bypasses the wave pipeline (notifier, thread routing, return waves, lifecycle).

## Forbidden

```java
getCommand(FooCommand.class).run(...);
getCommand(FooCommand.class, key).run(wave);
component.getCommand(FooCommand.class).run(WBuilder.callCommand(FooCommand.class)...);
```

## Prefer

| Intent | API |
|--------|-----|
| Named command, WaveData payload | `callCommand(FooCommand.class, WBuilder.waveData(ITEM, value))` |
| Named command, WaveBean | `callCommand(FooCommand.class, FooWaveBean.of()...)` |
| Prebuilt CALL_COMMAND wave (extra flags, several beans) | `sendWave(WBuilder.callCommand(FooCommand.class).waveBean(...).addDatas(...))` |
| Controller → command | `linkCommand(...)` or `callCommand(...)` on the controller/model |

## Recipes

Concrete call sites: `references/recipes.md`.

## Why

- `callCommand` / `sendWave(WBuilder.callCommand(...))` go through the notifier
- Command thread type (UI / JIT / pool) is respected
- Same path as MultiCommand orchestration and return extras

## Related

- `jrebirth-create-command` / `jrebirth-create-multi-command` — write the Command class
- `jrebirth-threading` — choose UI vs JIT vs pool Command instead of `runInto*`
- `jrebirth-anti-patterns` — plain-JavaFX leaks

