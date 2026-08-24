---
name: anti-patterns
description: >-
  Spot and fix JRebirth anti-patterns (runLater glue, raw threads, logic in
  controllers, state in views, global facade abuse). Use when reviewing or
  cleaning up JRebirth code that smells like plain JavaFX.
---
<!-- gen .mdh -->

# Avoid Anti-Patterns

This skill helps recognize eliminate most common JRebirth anti-patterns Each one signals class has drifted outside intended role

## Smells their fixes

### `Platform.runLater` used architecture glue

What looks like: `Platform.runLater(() -> someModel.update(...))` scattered across services background threads or event handlers

Why wrong: JRebirth manages thread transitions internally through Service Wave mechanisms Sprinkling `runLater` everywhere means caller manually compensating structural problem instead using framework correctly

Fix: Let Service return result wave The Model listens `@OnWave` updates properties correct thread automatically Remove all `runLater` calls compensate direct Service or thread results

### Raw thread creation routine flows

What looks like: `new Thread(() -> { ... }).start()` inside Model, View, or Controller

Why wrong: Raw threads unmanaged They bypass JRebirth's internal thread pool have no lifecycle can mutate state any thread any time

Fix: Extract slow work Service JRebirth routes Service calls background thread brings results back through waves without any manual thread management

### Heavy logic Controller

What looks like: A Controller method validates input applies business rules or orchestrates several operations

Why wrong: Controllers translate user gestures intent Business rules orchestration belong Model (for state decisions) or Commands (for explicit actions) A long Controller method sign intent not delegated

Fix: Extract business logic Model or Command The Controller should call `model().doSomething(...)` or `callCommand(SomeCommand.class, ...)` nothing more

### Business state hidden View

What looks like: A View field holds list items selected value or validation flag other components might need

Why wrong: Views transient State View invisible Model, cannot bound other components lost when View rebuilt

Fix: Move state Model JavaFX property Bind View control `model().itemsProperty()` instead storing locally

### `globalFacade` access everywhere

What looks like: `localFacade().globalFacade().getXxx()` called multiple components reach shared service or model

Why wrong: This creates tight coupling between components bypasses wave-based communication keeps components independent

Fix: Use Waves communicate across component boundaries Send wave originating component let target component subscribe `@OnWave`. Reserve direct facade access rare hard integration cases where waves impractical

## General fix path

1. Find missing JRebirth role (Model, Command, Service, Wave)
2. Move misplaced code role
3. Wire communication through correct mechanism (wave, command call or model binding)

See `jrebirth-core` `refactor-legacy` skills complete role map migration order
