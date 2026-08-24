---
name: spring-bridge
description: >-
  Keep a JRebirth client clean while consuming Spring-side services or APIs.
  Use when wiring backend calls, mapping DTO and FXO, or deciding where network
  and entity code may live.
---
<!-- gen .mdh -->

# Bridge Spring

When JRebirth UI client consumes Spring backend keep two worlds cleanly separated The client should never depend JPA entities Spring beans or database infrastructure

## The boundary

The Spring side owns persistence business rules entities The JRebirth client owns UI state user interaction presentation logic Data crosses boundary plain DTOs (Data Transfer Objects) one direction FXOs (JavaFX-friendly observable objects) client side

## Rules

- The UI layer never manipulates JPA entities Entities persistence objects must not cross boundary Map them DTOs Spring side before sending them client
- Network backend calls stay dedicated JRebirth Service or gateway The Model Controller must not call REST endpoints or databases directly
- DTOs stay plain They simple Java objects no behavior no JavaFX bindings no Spring annotations
- FXOs stay client-side only They observable counterpart DTOs JRebirth side The Model holds FXOs binds View controls them
- Map DTO FXO cleanly across boundary The mapping happens inside Service or dedicated mapper called Service Do not scatter mapping logic across Models Controllers

## Typical data flow

1. The user triggers action → Controller calls Command
2. The Command tells Service load or send data
3. The Service makes HTTP/RPC call receives DTO, maps FXO
4. The Service sends result Wave carrying FXO
5. The Model receives Wave, stores FXO property View updates through bindings
