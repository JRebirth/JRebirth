---
name: jrebirth-threading
description: >-
  Avoid JRebirth.runInto* and Platform.runLater for app logic. Prefer refresh
  waves for JAT UI updates and Commands by thread (UI / JIT / pool) for hops.
  Use when hopping threads, refreshing UI after async work, or reviewing
  runIntoJAT / runIntoJIT / runIntoJTP usage.
---
# Threading — avoid runInto*

Do not hop threads with raw `JRebirth.runInto*` or `Platform.runLater` for ordinary application work. Prefer Waves and Commands so ownership and thread defaults stay clear.

## Forbidden by default

- `JRebirth.runIntoJAT(...)` / `runIntoJATSync(...)`
- `JRebirth.runIntoJIT(...)` / `runIntoJITSync(...)`
- `JRebirth.runIntoJTP(...)` / `runIntoJTPSync(...)`
- `JRebirth.run(RunType.…, …)` / `runSync(RunType.…, …)` used as ad-hoc hops
- Any other `JRebirth.runInto*` helper
- `Platform.runLater(...)` for app UI refresh or orchestration

Do not replace one hop with another (for example `runIntoJTP` instead of `runIntoJAT`).

## Thread defaults (reminder)

- Model wave handlers → JAT
- Service wave handlers → JIT
- Command wave handlers → JIT unless using a UI or pool superclass
- Long I/O / network → Service (`returnData`) or pool Command

Direct `getCommand(...)` / `getService(...)` / `getModel(...)` runs on the **current** thread. Prefer Waves and Commands over unsafe cross-thread direct calls.

## Replacement matrix

| Instead of | Prefer | API |
|------------|--------|-----|
| `runIntoJAT` / `Platform.runLater` (UI refresh) | Refresh **wave** if a Model owns the UI; else **UI Command** | Model `listen` / `doXxxDone` / `@OnWave` (already JAT); `DefaultUICommand` / `DefaultUIBeanCommand` |
| `runIntoJIT` | **Command** (first choice) | `DefaultCommand` / `DefaultBeanCommand` via `callCommand` / `linkCommand` |
| `runIntoJTP` (pool work) | **Pool Command** or **Service** | `DefaultPoolCommand` / `DefaultPoolBeanCommand`; long I/O → `returnData(Service…)` |

**JAT nuance:** inside a Model wave handler (`doXxxDone`, `@OnWave`, `processWave`) update the UI **directly** — already on JAT. Do not wrap again.

**JIT / JTP:** prefer Commands over inventing waves solely to hop threads. Waves remain fine for Model-owned refresh contracts (`DO_REFRESH_X`).

## Recipes

Concrete code samples: `references/recipes.md`.

## Escape hatch (narrow)

Framework or toolkit/stage plumbing with **no** owning Model or Command path may still use `runInto*` inside the framework itself. Application feature code must not: never for CRUD refresh, dashboard rewire, locale reload, property listeners, or `*Done` handlers.

## Related skills

- `jrebirth-create-command` / `jrebirth-create-multi-command` — choose Command superclass by thread
- `jrebirth-create-wave` — refresh and decoupled notifications
- `jrebirth-create-service` — long-running / backend work
- `jrebirth-create-model` — JAT handlers and UI state
- `jrebirth-anti-patterns` — `runLater` and raw-thread smells

