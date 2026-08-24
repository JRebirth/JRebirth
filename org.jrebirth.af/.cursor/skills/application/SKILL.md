---
name: application
description: >-
  Create the JRebirth/JavaFX application entry point and shell configuration.
  Use when bootstrapping a new app, choosing the first model, or customizing the
  stage, scene, and preloaded resources.
---
<!-- gen .mdh -->

# Create Application

The Application class entry point nothing more It boots JRebirth runtime configures shell (stage, scene global CSS), hands control first Model All feature logic lives elsewhere

## What belongs here

- Bootstrap launch flow (`preloadAndLaunch(args)` `main`).
- The choice first Model class returned `firstModelClass()`.
- Stage title window icon configuration
- Scene customization global CSS stylesheets
- The list resources preload before UI appears

## What does not belong here

- Business logic any kind
- Feature orchestration or state management
- Controller-style event handling
- Direct backend or service access

If find yourself adding any move them appropriate role (Model, Command, or Service)

## Key API

- Extend `DefaultApplication<StackPane>` (or whichever root pane type shell uses)
- Return first Model class `firstModelClass()`.
- Set window title `applicationTitle()`.
- Add global stylesheets `customizeScene(scene)` `addCSS(scene, SomeStyles.DEFAULT)`.
- Tune window `customizeStage(stage)`.
- List resources preload `getResourceToPreload()`.

Template: `templates/ApplicationTemplate.java.txt`.
