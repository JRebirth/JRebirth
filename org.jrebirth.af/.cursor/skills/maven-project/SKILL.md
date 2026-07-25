---
name: maven-project
description: >-
  Create a Maven project or module that uses JRebirth AF, JavaFX, JPMS,
  generated ModuleStarter, and the standard Application/Model/View/Controller
  skeleton. Use when bootstrapping a new standalone app, sample, showcase, or
  repo submodule.
---
<!-- gen from .mdh -->

# Create JRebirth Maven Project

Scaffold Maven project boots JRebirth cleanly keeps MVC roles separated

## Decide The Project Shape

- For repository module inherit nearest JRebirth parent POM use `${project.version}` JRebirth artifacts
- For standalone app use explicit `jrebirth.version`, JavaFX dependencies compiler configuration JavaFX Maven plugin
- For showcase module follow real examples `showcase/demo` `showcase/todos`.

## Standard Files

- `pom.xml`
- `src/main/java/module-info.java`
- `src/main/java/<pkg>/<App>.java`
- `src/main/java/<pkg>/ui/MainModel.java`
- `src/main/java/<pkg>/ui/MainView.java`
- `src/main/java/<pkg>/ui/MainController.java`
- optional `src/main/java/<pkg>/resources/<App>Styles.java`
- optional CSS/resource files under matching package/resource path

Templates live `templates/`.

## Required Dependencies

- `org.jrebirth.af:api`
- `org.jrebirth.af:core`
- `org.jrebirth.af:preloader` only when using JRebirth preloader startup
- `org.jrebirth.af:processor` provided dependency or annotation processor when `ModuleStarter` generation needed
- `javafx.controls`; add `javafx.fxml`, `javafx.web`, `javafx.media`, or `javafx.swing` only when app uses them
- runtime logging such `ch.qos.logback:logback-classic` standalone apps

## JPMS Rules

- Use `open module ...` normal app/showcase path It simple matches showcase modules
- Export public app UI, resource packages needed
- Require `org.jrebirth.af.api` `org.jrebirth.af.core`.
- Add `provides org.jrebirth.af.api.module.ModuleStarter with <pkg>.<Name>ModuleStarter;` generated starter modules
- Do not hand-write `<Name>ModuleStarter`; annotation processor generates it

## MVC Scaffold

- Application extends `DefaultApplication<StackPane>`.
- `firstModelClass()` returns `MainModel.class`.
- Model extends `DefaultModel<MainModel, MainView>` owns state bindings orchestration
- View extends `DefaultView<MainModel, BorderPane, MainController>` builds JavaFX nodes
- Controller extends `DefaultController<MainModel, MainView>` wires events model calls commands services or waves

## Checks

- Keep business logic out View Controller
- Do not use raw threads or `Platform.runLater` architecture
- Keep package names module name resource base path generated starter name consistent
- In repository verify `mvn clean install -Dmaven.test.skip=true`; standalone generated project use `mvn clean package`.
- Launch standalone JavaFX application `mvn javafx:run`.
- Launch repository module `mvn javafx:run -pl <module-path> -Djavafx.mainClass=<module>/<appClass>`.
- After creating application tell user exact build command exact launch command generated project

Use `reference.md` project variants `templates/` scaffold files
