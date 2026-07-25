---
name: view
description: >-
  Build a JRebirth View (scene graph, controls, bindings, view-local CSS).
  Use when composing nodes for a feature, or attaching a stylesheet to a view.
---
<!-- gen from .mdh -->

# Create View

View builds nodes composes scene graph cleanly

## Put View

- panes controls labels tables forms
- visual structure
- simple binding hookup model properties
- styling hooks

## Keep out

- business rules
- persistent or complex state
- persistence backend calls
- heavy imperative workflow

## Key API

- extend `DefaultView<M, Pane, C>`; build nodes `initView()`; expose controls package accessors
- annotate action controls `@OnAction` so Controller receives events
- attach view-local stylesheets `addCSS(StyleSheetItem)` view (adds `pane().getStylesheets()`).
- build item `Resources.create(new StyleSheet("basename"))`, plus `.module(...)` when needed This one-argument form `addCSS(scene, ...)` form stays Application

Template: `templates/ViewTemplate.java.txt`.
