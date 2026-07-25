---
name: command-multi
description: >-
  Create a multi command that runs a named, ordered sequence of smaller JRebirth
  commands. Use when one user action must trigger several existing commands in a
  known order.
---
<!-- gen from .mdh -->

# Create Multi Command

A multi command named sequence smaller commands

## Use when

A feature needs one user action trigger several existing commands known order

## Good case

Compose existing commands ordered chain

## Bad case

Using multi command giant dumping ground everything

## Key API

- extend multi command base declare chain command classes run order
- pass shared data through wave or `WaveBean`.

Template: `templates/MultiCommandTemplate.java.txt`.
