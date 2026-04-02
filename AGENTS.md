# AGENTS.md

## Cursor Cloud specific instructions

### Project overview

JRebirth is a JavaFX application framework (multi-module Maven project). The source lives under `org.jrebirth.af/` with many submodules (api, core, component, showcase, tooling, etc.). Active development on branch `12.x-dev` targets **Java 25** with JPMS (`module-info.java` in library and showcase modules).

### Versioning

- **Release line**: 12.x; Maven coordinates use version **`12.0.0-SNAPSHOT`** until a release is cut.
- **Parent POM**: `org.jrebirth:organization` is defined in this repo at [`org.jrebirth/pom.xml`](org.jrebirth/pom.xml). The framework aggregator [`org.jrebirth.af/pom.xml`](org.jrebirth.af/pom.xml) sets `<relativePath>../org.jrebirth/pom.xml</relativePath>` — the parent is resolved from the workspace, not from an external BOM version alone.
- **Java / JavaFX**: Compiler release and Enforcer expect **Java 25** (`java.version` / `maven.compiler.release`). OpenJFX aligns with **`openjfx.version`** (currently **26**) in the root `org.jrebirth.af` POM.

### Prerequisites

- **JDK 25** (the build uses `--release 25`; `maven-enforcer-plugin` fails the build below Java 25). If your environment only has JDK 21, install JDK 25 before running a full compile.
- **Apache Maven 3.9+** (the POM pins `maven.version` **3.9.11** for documentation/tooling consistency; install via `sudo apt-get install -y maven` or equivalent).

### Build

```
cd /workspace/org.jrebirth.af
mvn clean install -Dmaven.test.skip=true
```

Use `-Dmaven.test.skip=true` to skip both test compilation and execution when you only need artifacts. For a normal test run from the reactor root, omit that flag (or use `-DskipTests` if you only want to skip execution).

### Running tests

```
cd /workspace/org.jrebirth.af
mvn test -pl core
```

Core module tests use JUnit 5, TestFX, and Monocle for headless JavaFX. Run this **after** dependencies are built (e.g. following `mvn install` from the reactor root, or use `mvn test` from `org.jrebirth.af` without `-pl` if resolution errors appear). Exact skip/fail counts can change with the 12.x line; treat any widespread JPMS-related failures as framework issues to investigate in context.

### Running a showcase app

```
cd /workspace/org.jrebirth.af/showcase/demo
DISPLAY=:1 mvn javafx:run -Djavafx.mainClass=org.jrebirth.af.showcase.demo/org.jrebirth.af.showcase.demo.JRebirthDemo
```

Requires a running X display (`:1` via Xvfb is available in the Cloud Agent VM). The Demo app bundles showcase modules such as Wave, Undo Redo, Todos, FXML, FontIcon, and Workbench.

### Gotchas

- No Maven wrapper (`mvnw`) exists in the repo; Maven must be installed system-wide.
- There is no lint command or standalone linter configured — code quality is checked via SonarQube/JaCoCo during the Maven build.
- First-time builds still need **network access** to resolve dependencies from Maven Central (and other configured repositories); the organization parent itself is local under `org.jrebirth/`.
