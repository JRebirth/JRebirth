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

Scaffold a Maven project that boots JRebirth cleanly and keeps MVC roles separated.

## Decide shape

- repo module: inherit nearest JRebirth parent POM and use `${project.version}`.
- standalone app: use explicit `jrebirth.version`, JavaFX deps, compiler config, and JavaFX Maven plugin.
- showcase module: follow `showcase/demo` and `showcase/todos` patterns.

## Standard files

- `pom.xml`
- `src/main/java/module-info.java`
- `src/main/java/<pkg>/<App>.java`
- `src/main/java/<pkg>/ui/MainModel.java`
- `src/main/java/<pkg>/ui/MainView.java`
- `src/main/java/<pkg>/ui/MainController.java`
- optional `src/main/java/<pkg>/resources/<App>Styles.java`
- optional CSS/resource files under matching package/resource path

Templates live in `templates/`.

## Required dependencies

- `org.jrebirth.af:api`
- `org.jrebirth.af:core`
- `org.jrebirth.af:preloader` only when using JRebirth preloader startup.
- `org.jrebirth.af:processor` as provided/annotation processor when ModuleStarter generation is needed.
- `javafx.controls`; add `javafx.fxml`, `javafx.web`, `javafx.media`, `javafx.swing` only when used.
- runtime logging such as `ch.qos.logback:logback-classic` for standalone apps.

## JPMS rules

- easiest app/showcase path: `open module ...`.
- export public app/UI/resource packages as needed.
- require `org.jrebirth.af.api` and `org.jrebirth.af.core`.
- add `provides org.jrebirth.af.api.module.ModuleStarter with <pkg>.<Name>ModuleStarter;` for generated starter modules.
- do not hand-write `<Name>ModuleStarter`; annotation processor generates it.

## MVC scaffold

- Application extends `DefaultApplication<StackPane>`.
- `firstModelClass()` returns `MainModel.class`.
- Model extends `DefaultModel<MainModel, MainView>` and owns state/bindings.
- View extends `DefaultView<MainModel, BorderPane, MainController>` and builds nodes.
- Controller extends `DefaultController<MainModel, MainView>` and wires events.

## Checks

- no business logic in View/Controller.
- no raw threads or `Platform.runLater` for architecture.
- package names, module name, resource base path, and generated starter name match.
- build with `mvn clean install -Dmaven.test.skip=true` for this repo; standalone use `mvn clean package`.
- launch standalone JavaFX app with `mvn javafx:run`.
- launch repo module with `mvn javafx:run -pl <module-path> -Djavafx.mainClass=<module>/<appClass>`.
- after creating app, tell user exact build and launch commands.

Use `reference.md` for project variants and `templates/` for scaffold files.
