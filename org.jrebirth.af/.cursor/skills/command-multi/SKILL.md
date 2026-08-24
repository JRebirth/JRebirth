---
name: command-multi
description: >-
  Create a multi command that runs a named, ordered sequence of smaller JRebirth
  commands. Use when one user action must trigger several existing commands in a
  known order.
---
<!-- gen .mdh -->

# Create Multi Command

A multi command named sequence smaller commands It coordinates ordered chain existing commands without adding logic own

## Use when

One user action must trigger several existing commands fixed known order Examples: "save publish" (SaveCommand → PublishCommand), "reset reload" (ClearCommand → LoadCommand)

## Good case

Composing two or more already-written commands single named chain The multi command itself contains no business logic — only declares order

## Bad case

Using multi command dumping ground ad-hoc logic If find yourself adding `if` statements or data transformation inside multi command split logic smaller single commands first

## Key API

- Extend multi command base class declare ordered list command classes run
- Pass shared data between commands through wave or `WaveBean` attached triggering wave

Template: `templates/MultiCommandTemplate.java.txt`.
