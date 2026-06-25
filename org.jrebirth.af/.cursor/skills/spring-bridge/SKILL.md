---
name: spring-bridge
description: >-
  Keep a JRebirth client clean while consuming Spring-side services or APIs.
  Use when wiring backend calls, mapping DTO and FXO, or deciding where network
  and entity code may live.
---
<!-- gen .mdh -->

# Bridge Spring

Keep JRebirth client clean while consuming Spring-side services or APIs

## Rules

- UI layer never manipulates JPA entities
- network backend calls stay dedicated Service or gateway
- DTOs stay plain
- FXO stays client-side only
- map DTO FXO cleanly across boundary
