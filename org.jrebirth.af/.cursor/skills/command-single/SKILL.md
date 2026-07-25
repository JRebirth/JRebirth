---
name: command-single
description: >-
  Create a single JRebirth Command for one explicit, named action (save,
  refresh, load one thing, navigate). Use when a discrete action must be run and
  named clearly.
---
<!-- gen from .mdh -->

# Create Single Command

A single command one clear named action

## Use when

One explicit action must run named clearly:

- save editor
- refresh ranking
- load one thing
- open detail screen
- import file

## Key API

- extend `DefaultCommand`; do work `perform(Wave)`.
- use `DefaultUICommand` when action must run JavaFX thread `DefaultUndoableCommand` when undoable
- read payload `wave.get(SomeWaves.ITEM)`.

## Invocation

Trigger any component (Model, Controller) `callCommand`, never `getCommand(...).run(...)`, bypasses wave pipeline

- Single payload: define `WaveItem<T>` command or shared waves class then `callCommand(FooCommand.class, WBuilder.waveData(FooCommand.ITEM, value))`. Avoid dedicated `WaveBean` one field
- Multiple fields: use `WaveBean` static factory `FooWaveBean.of()` fluent setters then `callCommand(FooCommand.class, bean)`.

## Keep small

If starts orchestrating many async steps or backend concerns pair service

Template: `templates/SingleCommandTemplate.java.txt`.
