---
name: behavior
description: >-
  Create a JRebirth Behavior for a tiny, genuinely shared cross-cutting helper.
  Use only when small reusable interaction logic appears in several places.
---
<!-- gen .mdh -->

# Create Behavior

A Behavior is a tiny reusable piece of cross-cutting logic.

## Use when

A very small reusable piece of logic appears in several places.

## Good uses

- repeated formatting helper tied to feature interaction
- tiny reusable interaction helper

## Avoid

- using behavior as a dump zone
- putting business workflow in behavior
- faking service or command as behavior

If logic grows, promote it to a Service or Command instead.

## Key API

- expose a behavior contract extending `Behavior<${Name}BehaviorData, Model>`.
- register one implementation with `@Register(value = ${Name}Behavior.class)`.
- keep setup in `initBehavior()` and read configuration from `data()`.
- create a data class extending `BehaviorDataBase` and annotate it with `@BehaviorDataFor(${Name}Behavior.class)`.
- attach the behavior by passing the data to a model key or behaviored component: `modelKey.optionalData().add(${Name}BehaviorData.create()...)`.

Templates:

- `templates/BehaviorTemplate.java.txt`
- `templates/BehaviorImplTemplate.java.txt`
- `templates/BehaviorDataTemplate.java.txt`
