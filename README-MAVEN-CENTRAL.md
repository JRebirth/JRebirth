# Publish `org.jrebirth:af` (libraries) to Maven Central

Same secrets pattern as [JRebirth-Organization-POM](https://github.com/JRebirth/JRebirth-Organization-POM/blob/master/README-MAVEN-CENTRAL.md).

## Not published to Central

These modules set `skipPublishing=true` (and CI also uses `-pl` exclusions):

- `sample`
- `distribution`
- `showcase` and all showcase apps (`demo`, `todos`, `fxml`, …)

## Secrets (repo `JRebirth/JRebirth` Actions)

`CENTRAL_USERNAME`, `CENTRAL_TOKEN`, `GPG_PRIVATE_KEY`, `GPG_PASSPHRASE`

Public key on https://keys.openpgp.org (email confirmed).

**Prerequisite:** `org.jrebirth:organization` release already on Central (AF parent).

## Local

```bash
mvn -f org.jrebirth.af/pom.xml -B -Pdeploy-central clean deploy \
  -pl '!sample,!distribution,!showcase,!showcase/demo,!showcase/analyzer,!showcase/undoredo,!showcase/fxml,!showcase/wave,!showcase/todos,!showcase/workbench,!showcase/fonticon'
```

## Development vs release

- Branch `12.x-dev` uses a **`-SNAPSHOT`** version (e.g. `12.0.1-SNAPSHOT`); pushes deploy to **GitHub Packages** (`snapshot.yml`).
- **Maven Central** publish requires an explicit maintainer decision. Do not tag or run the Central workflow without approval.
- After Central release `X.Y.Z`, bump `12.x-dev` to the next snapshot (e.g. `12.0.2-SNAPSHOT`).

Runbook: `org.jrebirth.af/.cursor/skills/release-version/SKILL.md`.

**12.0.1** republishes `jrebirth-maven-plugin` with valid `text2fx` / `ecore2fx` goals (Central **12.0.0** plugin descriptor was empty).

## GitHub Actions

- Tag: `jrebirth-X.Y.Z` (e.g. `jrebirth-12.0.1`)
- Or workflow_dispatch with version `X.Y.Z` (no `SNAPSHOT`)
