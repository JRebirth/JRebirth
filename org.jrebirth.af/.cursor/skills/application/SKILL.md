---
name: application
description: >-
  Create the JRebirth/JavaFX application entry point and shell configuration.
  Use when bootstrapping a new app, choosing the first model, or customizing the
  stage, scene, and preloaded resources.
---
<!-- gen from .mdh -->

# Create Application

The Application entry point only It boots configures shell

## Keep here

- bootstrap launch flow
- first model selection
- stage title icons shell configuration
- scene customization global CSS

## Keep out

- business logic
- feature orchestration
- controller-style event code
- backend access

## Key API

- extend `DefaultApplication<StackPane>` (or chosen root pane type)
- choose entry model `firstModelClass()`.
- set title `applicationTitle()`.
- add global stylesheets `customizeScene(scene)` `addCSS(scene, SomeStyles.DEFAULT)`.
- tune window `customizeStage(stage)`.
- list preloaded resources `getResourceToPreload()`.

Template: `templates/ApplicationTemplate.java.txt`.
