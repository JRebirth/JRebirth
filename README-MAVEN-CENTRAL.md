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

## GitHub Actions

- Tag: `af-12.0.0`
- Or workflow_dispatch with version `12.0.0`
