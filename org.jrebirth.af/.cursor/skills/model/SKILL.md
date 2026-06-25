---
name: model
description: >-
  Create or extend a JRebirth Model (state, bindings, orchestration).
  Use when a feature needs properties, selection, validation state, or when it
  triggers commands and services.
---
<!-- gen .mdh -->

# Create Model

Model owns state bindings interaction logic It brain feature

## Use when

A feature needs state bindings selection validation state or UI orchestration

## Put Model

- JavaFX properties
- computed bindings
- selection form filter state
- validation flags
- command or service triggering

## Keep out

- raw node construction
- large backend logic
- low-level controller event boilerplate

## Key API

- extend `DefaultModel<M, V>`, or `DefaultFXMLModel<M>` when view FXML
- link sibling models `@Link` annotation
- set up bindings `bind()`.
- react messages `@OnWave(SomeWaves.WT)`; emit `sendWave(...)`.
- run actions `callCommand(SomeCommand.class, WBuilder.waveData(ITEM, value))`.

Template: `templates/ModelTemplate.java.txt`.
