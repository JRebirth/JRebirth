---
name: waves
description: >-
  Define and use JRebirth Waves for decoupled messaging between components.
  Use for cross-feature notifications, app-wide refresh triggers, or decoupled
  orchestration, not for plain local method calls.
---
<!-- gen .mdh -->

# Use Waves

Waves carry typed messages between components should not know about each other directly Use them decouple communication across feature boundaries

## Good uses

- Cross-feature notifications (e.g., "an item deleted" broadcast multiple listeners)
- App-wide refresh triggers when several components need react same event
- Decoupled orchestration where sender must not depend receiver

## Bad uses

- Replacing every direct collaboration message passing If component A always only communicates component B, direct call or shared Model property simpler clearer
- Using Wave local call could normal method invocation within same component

## Key API

- Declare `@Preload` interface hold wave contract This makes wave types discoverable
- Declare `String` name constant then create wave type: `WaveType NAME_WT = WBuilder.waveType(NAME)`.
- Declare typed payload keys `WaveItem<T>` constants same interface
- Emit wave any component `sendWave(NAME_WT)` or `sendWave(NAME_WT, WBuilder.waveData(KEY, value))`.
- Receive wave any component `@OnWave(NAME_WT)` method

Template: `templates/WavesTemplate.java.txt`.
