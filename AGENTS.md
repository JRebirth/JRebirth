# AGENTS.md

## Cursor Cloud specific instructions

### Project overview

JRebirth is a JavaFX application framework (multi-module Maven project). The source lives under `org.jrebirth.af/` with 18+ submodules (api, core, component, showcase, etc.). Branch `11.x-jpms` targets Java 11+ with JPMS module-info files.

### Prerequisites

- **JDK 21** (pre-installed; compiles for `--release 11`)
- **Apache Maven 3.5.4+** (installed via `sudo apt-get install -y maven`)

### Build

```
cd /workspace/org.jrebirth.af
mvn clean install -Dmaven.test.skip=true
```

Use `-Dmaven.test.skip=true` to skip both test compilation and execution. The `ecore2fx` tooling submodule has a pre-existing missing JUnit 4 dependency that breaks test compilation; `-DskipTests` alone is insufficient.

### Running tests

```
cd /workspace/org.jrebirth.af
mvn test -pl core
```

Core module tests use JUnit 5, TestFX, and Monocle for headless JavaFX. On this branch (11.x-jpms), 18 of 51 core tests fail with a pre-existing JPMS `Module.getName()` NPE — this is a known framework-level issue, not an environment problem. The remaining 29 tests pass (25 pass + 4 skip).

### Running a showcase app

```
cd /workspace/org.jrebirth.af/showcase/demo
DISPLAY=:1 mvn javafx:run -Djavafx.mainClass=org.jrebirth.af.showcase.demo/org.jrebirth.af.showcase.demo.JRebirthDemo
```

Requires a running X display (`:1` via Xvfb is available in the Cloud Agent VM). The Demo app bundles all showcase modules: Wave, Undo Redo, Todos, FXML, FontIcon, Workbench.

### Gotchas

- No Maven wrapper (`mvnw`) exists in the repo; Maven must be installed system-wide.
- There is no lint command or standalone linter configured — code quality is checked via SonarQube/JaCoCo during the Maven build.
- The parent POM `org.jrebirth:organization:2.1.1` is fetched from Maven Central on first build; internet access is required.
