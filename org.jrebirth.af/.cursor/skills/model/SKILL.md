---
name: model
description: >-
  Create or extend a JRebirth Model (state, bindings, orchestration).
  Use when a feature needs properties, selection, validation state, or when it
  triggers commands and services.
---
<!-- gen .mdh -->

# Create Model

The Model brain feature It owns state maintains bindings orchestrates interactions between View, Commands, Services, Waves

## Use when

A feature needs any following:

- Observable state View can bind to
- Selection, form or filter state shared between components
- Validation flags or computed derived values
- Triggering Commands or Services response user intent or incoming waves

## What belongs Model

- JavaFX properties (`SimpleStringProperty`, `SimpleListProperty`, etc.)
- Computed bindings derived properties
- Selection, form filter state
- Validation flags
- Logic calls Commands (`callCommand(...)`) or Services
- Wave listeners annotated `@OnWave`.

## What does not belong Model

- Raw scene graph node construction (belongs View)
- Heavy backend logic or I/O (belongs Service)
- Low-level UI event boilerplate (belongs Controller)

## Key API

- Extend `DefaultModel<M, V>`, or `DefaultFXMLModel<M>` when view loaded FXML
- Link sibling models `@Link` annotation
- Set up bindings `bind()`.
- React incoming messages `@OnWave(SomeWaves.WT)`.
- Emit messages `sendWave(...)`.
- Run actions `callCommand(SomeCommand.class, WBuilder.waveData(ITEM, value))`.

Template: `templates/ModelTemplate.java.txt`.
