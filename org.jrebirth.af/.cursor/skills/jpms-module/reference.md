<!-- gen from .mdh -->

# JRebirth JPMS module-info reference

Detailed reference jpms-module skill Real examples come JRebirth showcase multi-module ezDojo client

## Anatomy directive

- `requires X;` compile run against module X
- `requires transitive X;` re-export X anyone requires module Use when exported type exposes X (a public method returning `javafx.controls` node Jackson type etc.)
- `requires static X;` compile-only (annotation processors optional deps)
- `exports pkg;` make public API pkg readable compile run time
- `exports pkg to M;` qualified export module M only
- `opens pkg;` allow deep reflection (and resource access) pkg run time
- `opens pkg to M;` qualified open module M only
- `uses S;` module consumes ServiceLoader service S
- `provides S with Impl;` module supplies S
- `open module name { ... }` opens every package then never write `opens`.

## JRebirth-specific rules

1. Reflection: core builds Model, View, Controller, Command, Service, resource enums reflectively Their packages must open `org.jrebirth.af.core`. Whole-module `open module` easy path per-package `opens ... to org.jrebirth.af.core` strict path
2. FXML: `DefaultFXMLModel` controller package must open `javafx.fxml` too `open module` covers otherwise add `opens controllerPkg to javafx.fxml;` `requires javafx.fxml;`.
3. ModuleStarter: processor generates `XModuleStarter` per module declare exactly one `provides org.jrebirth.af.api.module.ModuleStarter with X;`. Missing provider means module not started JRebirth
4. JRebirth `@Register` / `@RegistrationPoint` services resolved JRebirth internally Do NOT map them JPMS `provides`/`uses`.

## JavaFX requires matrix

| Need | requires |
|------|----------|
| controls, layout, scene | `javafx.controls` (pulls graphics, base) |
| FXML loading | `javafx.fxml` |
| WebView / HTMLEditor | `javafx.web` |
| SwingNode / BufferedImage bridge | `javafx.swing` |
| media playback | `javafx.media` |

`javafx.graphics` `javafx.base` usually arrive transitively through `javafx.controls` through `org.jrebirth.af.core`.

## Resources (images, css i18n)

Typed JRebirth resource enums load via module If resource package reflected or read across module boundaries keep open (covered `open module`). Prefer `client-resources` skill resource-loading rules skill only covers module-info side

## Real examples (read before editing similar module)

- showcase feature fully open: `org.jrebirth.af/showcase/todos/src/main/java/module-info.java` — `open module`, `requires api + core`, `provides ModuleStarter`.
- minimal feature: `org.jrebirth.af/showcase/wave/src/main/java/module-info.java`.
- complex app shell: `ezDojo/client/app/src/main/java/module-info.java` — many requires `requires transitive` shared common broad exports commented per-package opens kept documentation
- shared common library: `ezDojo/client/common/src/main/java/module-info.java` — `requires transitive` leaked JRebirth JavaFX types
- strict feature (not open): `ezDojo/client/club/src/main/java/module-info.java` — plain `module` explicit `opens ui.* to org.jrebirth.af.core` `provides ModuleStarter`.
- ServiceLoader SPI: `ezDojo/client/launcher/src/main/java/module-info.java` — `uses`/`provides` `UIProvider`, `opens` JAXB runtime
- plain DTO/shared module (no JRebirth, no UI): `ezDojo/shared/src/main/java/module-info.java`.

## Troubleshooting

| Symptom | Cause | Fix |
|---------|-------|-----|
| `IllegalAccessException` / `cannot access ... module does not open` at startup | component package not open to core | `open module`, or `opens pkg to org.jrebirth.af.core;` |
| FXML `LoadException` reflective access | controller package not open to `javafx.fxml` | open it to `javafx.fxml` (or whole module) |
| `XModuleStarter` does not resolve | annotation processor did not run | put the JRebirth processor on the annotation-processor path, rebuild |
| Module loads but JRebirth never starts it | no `provides ModuleStarter` | add the provides line |
| `ServiceConfigurationError` | SPI declared in `uses` but `provides` missing or impl not public no-arg | add/fix `provides S with Impl;` |
| split package error | same package in two modules | merge or rename the package |
| `module not found: javafx.*` | JavaFX module not required or not on module path | add the `requires javafx.*` and the JavaFX modules to the run config |
