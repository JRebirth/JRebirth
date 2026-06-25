# JRebirth wB-CSMVC Reference

`wB-CSMVC` is the JRebirth role split around classic MVC:

- `w`: Waves for decoupled messages and typed payloads.
- `B`: Behaviors for tiny reusable cross-cutting interaction logic.
- `C`: Commands for explicit named actions.
- `S`: Services for slow, external, backend, or integration work.
- `MVC`: Model, View, Controller for a self-contained UI feature.

## Design Diagram

Open `wb-csmvc-diagram.svg` directly when Mermaid rendering is not available.

```mermaid
flowchart LR
    User([User gesture])
    External[(Backend, file system,<br/>integration boundary)]

    subgraph Component[JRebirth Component]
        Controller[Controller<br/>event hookup]
        View[View<br/>scene graph and controls]
        Model[Model<br/>state, properties, bindings,<br/>UI orchestration]
        Command[Command<br/>named action]
        Service[Service<br/>slow or external work]
        Wave[[Wave<br/>typed decoupled message]]
        Behavior[Behavior<br/>tiny reusable helper]
    end

    User --> Controller
    Controller -->|delegates intent| Model
    Controller -->|may trigger action| Command

    View -->|annotated controls| Controller
    View <-->|binds and observes| Model

    Model -->|callCommand| Command
    Command -->|uses when work is slow or external| Service
    Service --> External
    Service -->|result wave| Wave

    Model -->|sendWave| Wave
    Command -->|sendWave| Wave
    Wave -->|OnWave| Model
    Wave -->|OnWave| Service
    Wave -->|OnWave| Command

    Behavior -. attaches via behavior data .-> Model
    Behavior -. augments small shared interaction .-> View

    style Component fill:#ecfeff,stroke:#0891b2,stroke-width:2px,color:#0e7490
```

## Direction Rules

- Keep persistent feature state in the Model.
- Keep JavaFX node construction in the View.
- Keep user event translation in the Controller.
- Use Commands for named actions instead of long Controller methods.
- Use Services when work crosses an external boundary or should not live in UI logic.
- Use Waves only when communication must be decoupled across components.
- Use Behaviors only for small reusable interaction helpers; promote growing logic to a Command or Service.

## Feature Creation Order

1. Define the Model contract, state, bindings, and orchestration points.
2. Build the View scene graph and expose only the controls the Controller needs.
3. Wire gestures in the Controller and delegate to the Model or Commands.
4. Add Commands for explicit actions.
5. Add Services for external or long-running work.
6. Add Waves for cross-feature notification or decoupled orchestration.
7. Add Behaviors only when the same small interaction helper appears in multiple places.
