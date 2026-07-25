---
name: behavior
description: >-
  Create a JRebirth Behavior for a tiny, genuinely shared cross-cutting helper.
  Use only when small reusable interaction logic appears in several places.
---
<!-- gen from .mdh -->

# Create Behavior

A Behavior tiny reusable piece cross-cutting logic

## Use when

A very small reusable piece logic appears several places

## Good uses

- repeated formatting helper tied feature interaction
- tiny reusable interaction helper

## Avoid

- using behavior dump zone
- putting business workflow behavior
- faking service or command behavior

If logic grows promote Service or Command instead

## Key API

- expose behavior contract extending `Behavior<${Name}BehaviorData, Model>`.
- register one implementation `@Register(value = ${Name}Behavior.class)`.
- keep setup `initBehavior()` read configuration `data()`.
- create data class extending `BehaviorDataBase` annotate `@BehaviorDataFor(${Name}Behavior.class)`.
- attach behavior passing data model key or behaviored component: `modelKey.optionalData().add(${Name}BehaviorData.create()...)`.

Templates:

- `templates/BehaviorTemplate.java.txt`
- `templates/BehaviorImplTemplate.java.txt`
- `templates/BehaviorDataTemplate.java.txt`
