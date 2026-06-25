# JRebirth Maven project reference

<!-- gen .mdh -->

## Repo module POM

Use parent inheritance inside this repo.

```xml
<parent>
    <groupId>org.jrebirth.af</groupId>
    <artifactId>showcase</artifactId>
    <version>12.0.0-SNAPSHOT</version>
    <relativePath>..</relativePath>
</parent>
```

Minimum dependencies:

```xml
<dependency>
    <groupId>org.jrebirth.af</groupId>
    <artifactId>processor</artifactId>
    <version>${project.version}</version>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>org.jrebirth.af</groupId>
    <artifactId>core</artifactId>
    <version>${project.version}</version>
</dependency>
```

## Standalone POM shape

Use explicit versions and JavaFX plugin.

```xml
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.release>21</maven.compiler.release>
    <jrebirth.version>12.0.0-SNAPSHOT</jrebirth.version>
    <javafx.version>25</javafx.version>
    <main.module>com.example.app</main.module>
    <main.class>com.example.app/com.example.app.ExampleApplication</main.class>
</properties>
```

Add `org.jrebirth.af:api`, `core`, optional `preloader`, `processor` provided, `org.openjfx:javafx-controls`, and `logback-classic`.

## Build and launch

Standalone app:

```bash
mvn clean package
mvn javafx:run
```

Repo module:

```bash
mvn clean install -Dmaven.test.skip=true
mvn javafx:run -pl <module-path> -Djavafx.mainClass=<module>/<appClass>
```

Showcase demo example:

```bash
mvn javafx:run -pl showcase/demo -Djavafx.mainClass=org.jrebirth.af.showcase.demo/org.jrebirth.af.showcase.demo.JRebirthDemo
```

On Linux/Cloud with Xvfb, prefix launch with `DISPLAY=:1`. On Windows PowerShell, no `DISPLAY` prefix.

After scaffolding, report exact commands with placeholders resolved.

## module-info.java

```java
open module com.example.app {

    exports com.example.app;
    exports com.example.app.ui;
    exports com.example.app.resources;

    provides org.jrebirth.af.api.module.ModuleStarter with com.example.app.ExampleModuleStarter;

    requires javafx.controls;
    requires org.jrebirth.af.api;
    requires org.jrebirth.af.core;
}
```

## Application

```java
public final class ExampleApplication extends DefaultApplication<StackPane> {

    public static void main(final String... args) {
        preloadAndLaunch(args);
    }

    @Override
    public Class<? extends Model> firstModelClass() {
        return MainModel.class;
    }

    @Override
    protected String applicationTitle() {
        return "Example";
    }

    @Override
    protected void customizeScene(final Scene scene) {
        super.customizeScene(scene);
        addCSS(scene, ExampleStyles.DEFAULT);
    }
}
```

## MVC skeleton

```java
public final class MainModel extends DefaultModel<MainModel, MainView> {
    @Override
    protected void initModel() {
        // state setup
    }
}
```

```java
public final class MainView extends DefaultView<MainModel, BorderPane, MainController> {
    public MainView(final MainModel model) throws CoreException {
        super(model);
    }

    @Override
    protected void initView() {
        node().setPrefSize(800, 600);
    }
}
```

```java
public final class MainController extends DefaultController<MainModel, MainView> {
    public MainController(final MainView view) throws CoreException {
        super(view);
    }
}
```

## Resources

```java
public enum ExampleStyles implements StyleSheetEnum {
    DEFAULT {{
        ss("Example");
    }};
}
```

Real examples:

- `showcase/demo/src/main/java/org/jrebirth/af/showcase/demo/JRebirthDemo.java`
- `showcase/demo/src/main/java/org/jrebirth/af/showcase/demo/ui/MainModel.java`
- `showcase/demo/src/main/java/org/jrebirth/af/showcase/demo/ui/MainView.java`
- `showcase/demo/src/main/java/org/jrebirth/af/showcase/demo/ui/MainController.java`
- `showcase/demo/src/main/java/module-info.java`
- `showcase/todos/src/main/java/module-info.java`
