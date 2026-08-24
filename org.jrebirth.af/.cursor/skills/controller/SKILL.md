---
name: controller
description: >-
  Create a JRebirth Controller that wires user gestures to model, commands, or
  services. Use when adding button, key, selection, or menu handlers.
---
<!-- gen .mdh -->

# Create Controller

The Controller translates raw user gestures intent It receives UI events decides what should happen delegates Model, Command, or Service It does not contain business logic

## Good uses

- Wiring button click `callCommand(...)` or `model().doSomething(...)` call
- Registering keyboard shortcuts mapping them commands
- Listening table row selection updating Model's selected item property
- Wiring menu action Command

## Bad uses

- Business rules or validation logic (move Model or Command)
- Persistent feature state (move Model)
- Long methods grow past few lines — extract them named Command instead

## Key API

- Extend `DefaultController<M, V>` `(V view)` constructor
- Register event adapters `initEventAdapters()`.
- Bridge node event straight Service `linkService(node, eventType, Service.class, WAVE, filter, waveData)`.
- For everything else delegate `model()` or trigger command `callCommand(...)`.

Template: `templates/ControllerTemplate.java.txt`.
