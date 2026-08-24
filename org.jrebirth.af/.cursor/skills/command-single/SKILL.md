---
name: command-single
description: >-
  Create a single JRebirth Command for one explicit, named action (save,
  refresh, load one thing, navigate). Use when a discrete action must be run and
  named clearly.
---
<!-- gen .mdh -->

# Create Single Command

A single command encapsulates one clear named action It stateless short-lived, does exactly one thing

## Use when

An explicit action must run named clearly:

- Save editor content
- Refresh ranking list
- Load single item ID
- Open detail screen
- Import file disk

If action has clear name non-developer could understand belongs command

## Key API

- Extend `DefaultCommand` do work `perform(Wave)`.
- Use `DefaultUICommand` when action must run JavaFX thread (e.g., updates UI node directly)
- Use `DefaultUndoableCommand` when action must support undo/redo
- Read payload wave `wave.get(SomeWaves.ITEM)`.

## Invocation

Always trigger command `callCommand`, never `getCommand(...).run(...)` — direct invocation bypasses wave pipeline breaks threading guarantees

- Single payload: define `WaveItem<T>` command class or shared waves class then call `callCommand(FooCommand.class, WBuilder.waveData(FooCommand.ITEM, value))`. Avoid dedicated `WaveBean` single field
- Multiple fields: define `WaveBean` static factory `FooWaveBean.of()` fluent setters then call `callCommand(FooCommand.class, bean)`.

## Keep small

A single command should do one thing If starts orchestrating several async steps or direct backend concerns pair Service or split multi command sequence

Template: `templates/SingleCommandTemplate.java.txt`.
