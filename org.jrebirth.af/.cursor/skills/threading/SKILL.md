---
name: threading
description: >-
  Avoid JRebirth.runInto* and Platform.runLater for app logic. Prefer refresh
  waves for JAT UI updates and Commands by thread (UI / JIT / pool) for hops.
  Use when hopping threads, refreshing UI after async work, or reviewing
  runIntoJAT / runIntoJIT / runIntoJTP usage.
---
<!-- gen from .mdh -->

# Threading — avoid runInto*

Do not hop threads raw `JRebirth.runInto*` or `Platform.runLater` ordinary application work Prefer Waves Commands so ownership thread defaults stay clear

## Forbidden default

- `JRebirth.runIntoJAT(...)` / `runIntoJATSync(...)`
- `JRebirth.runIntoJIT(...)` / `runIntoJITSync(...)`
- `JRebirth.runIntoJTP(...)` / `runIntoJTPSync(...)`
- `JRebirth.run(RunType.…, …)` / `runSync(RunType.…, …)` used ad-hoc hops
- Any other `JRebirth.runInto*` helper
- `Platform.runLater(...)` app UI refresh or orchestration

Do not replace one hop another (for example `runIntoJTP` instead `runIntoJAT`).

## Thread defaults (reminder)

- Model wave handlers → JAT
- Service wave handlers → JIT
- Command wave handlers → JIT unless using UI or pool superclass
- Long I/O / network → Service (`returnData`) or pool Command

Direct `getCommand(...)` / `getService(...)` / `getModel(...)` runs current thread Prefer Waves Commands over unsafe cross-thread direct calls

## Replacement matrix

| Instead of | Prefer | API |
|------------|--------|-----|
| `runIntoJAT` / `Platform.runLater` (UI refresh) | Refresh **wave** if a Model owns the UI; else **UI Command** | Model `listen` / `doXxxDone` / `@OnWave` (already JAT); `DefaultUICommand` / `DefaultUIBeanCommand` |
| `runIntoJIT` | **Command** (first choice) | `DefaultCommand` / `DefaultBeanCommand` via `callCommand` / `linkCommand` |
| `runIntoJTP` (pool work) | **Pool Command** or **Service** | `DefaultPoolCommand` / `DefaultPoolBeanCommand`; long I/O → `returnData(Service…)` |

JAT nuance: inside Model wave handler (`doXxxDone`, `@OnWave`, `processWave`) update UI directly — already JAT Do not wrap again

JIT / JTP: prefer Commands over inventing waves solely hop threads Waves remain fine Model-owned refresh contracts (`DO_REFRESH_X`).

## Recipes

### Already JAT (Model return handler)

```java
public void doLoadItemsDone(final List<Item> items, final Wave wave) {
    rows().setAll(items != null ? items : List.of());
    // already JAT — no runIntoJAT
}
```

### Refresh wave (JAT UI refresh elsewhere)

```java
// emit
sendWave(FeatureWaves.DO_REFRESH_LIST);

// Model
listen(FeatureWaves.DO_REFRESH_LIST);
public void doRefreshList(final Wave wave) {
    refreshList();
}
```

### UI Command (named JAT action)

```java
public final class RefreshListCommand extends DefaultUICommand {
    @Override
    protected void perform(final Wave wave) {
        getModel(ItemsModel.class).refreshList();
    }
}
// callCommand(RefreshListCommand.class);
```

### JIT / pool Command

```java
// JIT messaging / short sync work
public final class ApplyFilterCommand extends DefaultCommand { … }

// JTP heavy local work
public final class ParseImportCommand extends DefaultPoolCommand { … }
```

## Escape hatch (narrow)

Framework or toolkit/stage plumbing no owning Model or Command path may still use `runInto*` inside framework itself Application feature code must not: never CRUD refresh dashboard rewire locale reload property listeners or `*Done` handlers

## Related skills

- `command-single` / `command-multi` — choose Command superclass thread
- `waves` — refresh decoupled notifications
- `service` — long-running / backend work
- `model` — JAT handlers UI state
- `anti-patterns` — `runLater` raw-thread smells
