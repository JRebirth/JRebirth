---
name: service
description: >-
  Create a JRebirth Service for slow, external, backend, or integration work
  (REST, DB gateway, file import/export, heavy computation). Use to keep long
  work out of Model, View, and Controller.
---
<!-- gen .mdh -->

# Create Service

The Service handles work slow external or crosses system boundary It runs off UI thread so application stays responsive Never put kind work Model, View, or Controller

## Use when

The work any following:

- A REST client call or HTTP request
- A Spring backend bridge call or RPC
- A file import or export operation
- A report or document generation task
- An expensive in-process computation

## What does not belong Service

- Scene graph node building (belongs View)
- Trivial UI event handling (belongs Controller)
- Business orchestration calls other commands sequence (use multi command instead)

## Key API

- Split interface `DefaultService` implementation registered `@Register(value = FooService.class)`.
- Expose typed `WaveItem<T>` `WaveType` constants interface payload declaration
- Name action methods `doXxx(payload, Wave)` so bind wave pipeline automatically
- Return results sending result wave Never touch scene graph directly Service

Template: `templates/ServiceTemplate.java.txt`.
