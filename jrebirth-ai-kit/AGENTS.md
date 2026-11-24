# AGENTS.md — JRebirth Application Framework

Rules for any AI agent working on a **JRebirth AF** application. Follow these before writing Java/JavaFX code.

## Skill first

Before creating or refactoring JRebirth code, open the matching skill under `skills/jrebirth-*/SKILL.md` (or `.cursor/skills/…` after install). Do not invent plain-JavaFX patterns when a JRebirth role exists.

## Role split (wB-CSMvc)

Keep one responsibility per role. Never mix them in the same class.

| Role | Owns | Does not own |
|------|------|----------------|
| **Application** | Boot, stage/scene shell, first model | Business logic, feature orchestration |
| **Model** | UI state, JavaFX properties, bindings, orchestration | Node construction, raw backend I/O |
| **View** | Scene graph, controls, view-local CSS | Business rules, persistent state |
| **Controller** | Gesture → Model/Command/Service wiring | Business rules, long methods |
| **Command** | One named discrete action | Scene-graph building |
| **Service** | Slow / external / backend work | UI nodes, trivial click handling |
| **Wave** | Decoupled cross-component messages | Replacing every local method call |
| **Behavior** | Tiny shared cross-cutting helper | Workflow dumps, fake services |

## Threading (absolute)

- Model wave handlers run on the **JavaFX Application Thread (JAT)**. Update UI there directly.
- Service wave handlers run on the **JRebirth Internal Thread (JIT)**.
- Commands run on JIT unless you use a UI or pool Command superclass.
- **Forbidden by default:** `new Thread`, `Platform.runLater`, and `JRebirth.runInto*` as app architecture.
- Prefer **refresh Waves** or **Commands** (`DefaultUICommand` / `DefaultCommand` / `DefaultPoolCommand`) for thread hops.
- Long I/O → Service (`returnData`) or pool Command — never block JAT.

## Waves

- Use Waves for cross-feature notifications, app-wide refresh, and decoupled orchestration.
- Declare contracts on a `@Preload` holder: `String` id + `WaveType` + typed `WaveItem`s.
- Emit with `sendWave(...)`; receive with `@OnWave` / `listen`.
- Do not use Waves for a plain local method call inside one feature.

## Commands — invoke correctly

- Trigger with `callCommand(...)`, `linkCommand(...)`, or `sendWave(WBuilder.callCommand(...))`.
- **Never** `getCommand(...).run(...)` — it bypasses the wave pipeline (thread routing, lifecycle, return waves).

## Naming

- Feature types: `FooModel`, `FooView`, `FooController`, `FooCommand`, `FooService` / `FooServiceImpl`.
- Wave holders: `FooWaves` with `DO_X` string + `DO_X_WT` `WaveType`.
- Packages: `…ui` (MVC), `…command`, `…service`, `…wave`, `…resources`.

## Hard bans

- No entity / JPA types in UI code; network stays in Service (or gateway).
- No heavy logic in View or Controller; no feature state owned by View.
- No `localFacade().globalFacade()` except rare hard integration cases.
- Prefer framework mechanisms over ad-hoc shortcuts.

## When unsure

Stop. Re-read `jrebirth-core`, then the role skill (`jrebirth-create-model`, `…-view`, `…-command`, …). For smells, use `jrebirth-anti-patterns` and `jrebirth-threading`.
