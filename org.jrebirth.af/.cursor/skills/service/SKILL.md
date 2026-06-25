---
name: service
description: >-
  Create a JRebirth Service for slow, external, backend, or integration work
  (REST, DB gateway, file import/export, heavy computation). Use to keep long
  work out of Model, View, and Controller.
---
<!-- gen .mdh -->

# Create Service

Service does long-running or external work off UI logic

## Use when

Work slow external backend-facing, or integration-heavy:

- REST client call
- Spring bridge call
- file import or export
- report generation
- expensive computation

## Keep out

- visual node building
- trivial UI event handling

## Key API

- split interface plus `DefaultService` implementation registered `@Register(value = FooService.class)`.
- expose `WaveItem<T>` `WaveType` constants interface typed payloads
- name action methods `doXxx(payload, Wave)` so bind waves
- return results or send result wave never touch scene graph directly

Template: `templates/ServiceTemplate.java.txt`.
