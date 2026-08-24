---
name: behavior
description: >-
  Create a JRebirth Behavior for a tiny, genuinely shared cross-cutting helper.
  Use only when small reusable interaction logic appears in several places.
---
<!-- gen .mdh -->

# Create Behavior

A Behavior tiny self-contained piece cross-cutting interaction logic can attached multiple components Think reusable mixin Model

## Use when

The same small piece interaction logic appears several unrelated components extracting helper avoids duplicating same boilerplate each time

## Good uses

- A repeated formatting helper tied user interaction (e.g., auto-select-all focus)
- A tiny reusable drag-and-drop or tooltip interaction applied multiple views
- A small keyboard shortcut helper used across multiple panels

## Avoid

- Using Behavior dump zone miscellaneous utilities
- Putting business workflow or orchestration logic Behavior Those belong Command or Service
- Creating Behavior acts like Service or Command but called Behavior

If logic grows beyond few lines or starts interacting external systems promote Service or Command instead

## Key API

- Expose behavior contract extending `Behavior<${Name}BehaviorData, Model>`.
- Register one implementation `@Register(value = ${Name}Behavior.class)`.
- Keep setup `initBehavior()` read configuration `data()`.
- Create data class extending `BehaviorDataBase` annotate `@BehaviorDataFor(${Name}Behavior.class)`.
- Attach behavior passing data model key or behaviored component: `modelKey.optionalData().add(${Name}BehaviorData.create()...)`.

Templates:

- `templates/BehaviorTemplate.java.txt`
- `templates/BehaviorImplTemplate.java.txt`
- `templates/BehaviorDataTemplate.java.txt`
