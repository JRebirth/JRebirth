---
name: maven-project
description: >-
  Create a Maven project or module that uses JRebirth AF, JavaFX, JPMS,
  generated ModuleStarter, and the standard Application/Model/View/Controller
  skeleton. Use when bootstrapping a new standalone app, sample, showcase, or
  repo submodule.
---
<!-- gen .mdh -->

# Create JRebirth Maven Project

Scaffold Maven project boots JRebirth cleanly keeps MVC roles separated integrates correctly JPMS

## Decide project shape

Before creating any files choose right shape:

- Repository module: inherit nearest JRebirth parent POM use `${project.version}` JRebirth artifacts Do not hard-code versions
- Standalone app: use explicit `jrebirth.version`, add JavaFX dependencies explicitly configure Maven compiler plugin add JavaFX Maven plugin
- Showcase module: follow real examples `showcase/demo` `showcase/todos` exact structure copy

## Standard files

Every new JRebirth module or application includes:

- `pom.xml`
- `src/main/java/module-info.java`
- `src/main/java/<pkg>/<App>.java`
- `src/main/java/<pkg>/ui/MainModel.java`
- `src/main/java/<pkg>/ui/MainView.java`
- `src/main/java/<pkg>/ui/MainController.java`
- Optional: `src/main/java/<pkg>/resources/<App>Styles.java`
- Optional: CSS resource files under matching package or resource path

Templates live `templates/`.

## Required dependencies

- `org.jrebirth.af:api`
- `org.jrebirth.af:core`
- `org.jrebirth.af:preloader` — only when using JRebirth preloader startup
- `org.jrebirth.af:processor` — provided dependency or annotation processor when `ModuleStarter` generation needed
- `javafx.controls` — always required Add `javafx.fxml`, `javafx.web`, `javafx.media`, or `javafx.swing` only when application actually uses them
- A runtime logger such `ch.qos.logback:logback-classic` standalone apps

## JPMS rules

- Use `open module ...` normal app or showcase path It simplest approach matches showcase modules
- Export public application UI, resource packages needed
- Require `org.jrebirth.af.api` `org.jrebirth.af.core`.
- Add `provides org.jrebirth.af.api.module.ModuleStarter with <pkg>.<Name>ModuleStarter;` generated starter Do not hand-write `<Name>ModuleStarter` — annotation processor generates it

## MVC scaffold

- Application extends `DefaultApplication<StackPane>`.
- `firstModelClass()` returns `MainModel.class`.
- Model extends `DefaultModel<MainModel, MainView>` owns state bindings orchestration
- View extends `DefaultView<MainModel, BorderPane, MainController>` builds JavaFX nodes
- Controller extends `DefaultController<MainModel, MainView>` wires events model calls commands services or waves

## Checks before finishing

- Keep business logic out View Controller
- Do not use raw threads or `Platform.runLater` architecture
- Keep package names module name resource base path generated starter name consistent each other
- In repository verify build `mvn clean install -Dmaven.test.skip=true`.
- For standalone generated project verify `mvn clean package`.
- Launch standalone app `mvn javafx:run`.
- Launch repository module `mvn javafx:run -pl <module-path> -Djavafx.mainClass=<module>/<appClass>`.
- After creating application always tell user exact build command exact launch command generated project correct module name class filled in

Use `reference.md` project variants `templates/` scaffold files
