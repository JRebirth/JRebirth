---
name: view
description: >-
  Build a JRebirth View (scene graph, controls, bindings, view-local CSS).
  Use when composing nodes for a feature, or attaching a stylesheet to a view.
---
<!-- gen .mdh -->

# Create View

The View builds scene graph composes visual structure feature It pure rendering role: constructs nodes binds controls Model properties exposes action controls Controller wire

## What belongs View

- Panes, controls labels tables forms other JavaFX nodes
- Visual structure layout composition
- Simple binding hookup Model properties (e.g., `label.textProperty().bind(model().nameProperty())`).
- Styling hooks such CSS class assignments

## What does not belong View

- Business rules or validation logic (belongs Model)
- Persistent or complex state (belongs Model)
- Persistence backend calls (belong Service)
- Heavy imperative workflow or multi-step orchestration (belongs Command)

## Key API

- Extend `DefaultView<M, Pane, C>`. Build all nodes `initView()`. Expose controls via package-private accessors so Controller can wire them
- Annotate action controls `@OnAction` so Controller receives events automatically
- Attach view-local stylesheet `addCSS(StyleSheetItem)` inside View This adds stylesheet `pane().getStylesheets()`.
- Build `StyleSheetItem` `Resources.create(new StyleSheet("basename"))`. Add `.module(...)` when resource different module Use one-argument `addCSS(StyleSheetItem)` form here two-argument `addCSS(scene, ...)` form Application only

Template: `templates/ViewTemplate.java.txt`.
