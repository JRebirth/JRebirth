# Create Single Command


Single command = one clear action.

Use for:
- save
- refresh
- load one thing
- navigate one thing

## Invocation

From any JRebirth component (`Model`, `Controller`, etc.), trigger a command with **`callCommand`** — never **`getCommand(...).run(...)`**, which bypasses the normal wave pipeline.

- **Single payload**: define a **`WaveItem<T>`** on the command (or a shared waves class), then `callCommand(FooCommand.class, WBuilder.waveData(FooCommand.ITEM, value))`. Avoid a dedicated `WaveBean` for one field.
- **Multiple fields**: use a **`WaveBean`** with a static factory **`FooWaveBean.of()`** and fluent setters, then `callCommand(FooCommand.class, bean)`.
