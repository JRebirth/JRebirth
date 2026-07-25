---
name: waves
description: >-
  Define and use JRebirth Waves for decoupled messaging between components.
  Use for cross-feature notifications, app-wide refresh triggers, or decoupled
  orchestration, not for plain local method calls.
---
<!-- gen from .mdh -->

# Use Waves

Waves send messages between decoupled parts without tight direct dependencies

## Good uses

- cross-feature notifications
- app-wide refresh triggers
- decoupled orchestration

## Bad uses

- local call could normal method
- replacing every direct collaboration message passing

## Key API

- declare `@Preload` interface holding wave contract
- `String` name constant then `WaveType NAME_WT = WBuilder.waveType(NAME)`.
- typed payload keys `WaveItem<T>` constants
- emit `sendWave(NAME_WT)`; receive `@OnWave(NAME)`.

Template: `templates/WavesTemplate.java.txt`.
