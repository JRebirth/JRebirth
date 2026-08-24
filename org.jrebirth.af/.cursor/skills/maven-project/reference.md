<!-- gen .mdh -->

# JRebirth Maven Project Reference

This reference gives exact shapes use when creating Maven project or module uses JRebirth AF

## Repository Module POM

Inside repository prefer parent inheritance `${project.version}` instead hard-coded JRebirth versions

```xml
<parent>
    <groupId>org.jrebirth.af</groupId>
    <artifactId>showcase</artifactId>
    <version>12.0.0-SNAPSHOT</version>
    <relativePath>..</relativePath>
</parent>
```

Minimum dependencies JRebirth module:

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

## Standalone POM Shape

For standalone project use explicit versions configure JavaFX Maven plugin

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

Add `org.jrebirth.af:api`, `org.jrebirth.af:core`, optional `org.jrebirth.af:preloader`, `org.jrebirth.af:processor` provided scope `org.openjfx:javafx-controls`, runtime logger such `logback-classic`.

## Build And Launch

For standalone generated application:

```bash
mvn clean package
mvn javafx:run
```

For application module inside repository:

```bash
mvn clean install -Dmaven.test.skip=true
mvn javafx:run -pl <module-path> -Djavafx.mainClass=<module>/<appClass>
```

For example showcase demo can launched with:

```bash
mvn javafx:run -pl showcase/demo -Djavafx.mainClass=org.jrebirth.af.showcase.demo/org.jrebirth.af.showcase.demo.JRebirthDemo
```

On Linux or Cloud Agent VM Xvfb, prefix launch command `DISPLAY=:1`. On Windows PowerShell, do not use `DISPLAY` prefix

After scaffolding project agent should always report exact build command exact launch command generated module name module path application class filled in

## module-info.java

Use `open module` ordinary application case so JRebirth can instantiate components reflectively

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

`ExampleModuleStarter` generated annotation processor Do not hand-write it

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

## MVC Skeleton

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

Real examples inspect before changing similar project:

- `showcase/demo/src/main/java/org/jrebirth/af/showcase/demo/JRebirthDemo.java`
- `showcase/demo/src/main/java/org/jrebirth/af/showcase/demo/ui/MainModel.java`
- `showcase/demo/src/main/java/org/jrebirth/af/showcase/demo/ui/MainView.java`
- `showcase/demo/src/main/java/org/jrebirth/af/showcase/demo/ui/MainController.java`
- `showcase/demo/src/main/java/module-info.java`
- `showcase/todos/src/main/java/module-info.java`
