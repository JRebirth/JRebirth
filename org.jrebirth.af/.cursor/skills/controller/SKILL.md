---
name: controller
description: >-
  Create a JRebirth Controller that wires user gestures to model, commands, or
  services. Use when adding button, key, selection, or menu handlers.
---
<!-- gen .mdh -->

# Create Controller

Controller translates user gestures calls toward model commands or services

## Good uses

- button click wiring
- key handlers
- table selection listeners when not purely declarative
- menu action hookup

## Bad uses

- business rules
- persistent feature state
- long methods should become commands

## Key API

- extend `DefaultController<M, V>` `(V view)` constructor
- register adapters `initEventAdapters()`.
- bridge node event straight service `linkService(node, eventType, Service.class, WAVE, filter, waveData)`.
- otherwise delegate `model()` or trigger command

Template: `templates/ControllerTemplate.java.txt`.
