---
name: release-version
description: >-
  JRebirth AF snapshot and release versioning: bump POMs on 12.x-dev, validate
  jrebirth-maven-plugin descriptors (text2fx/ecore2fx), build site downloads
  (AI kit + framework ZIP), tag jrebirth-X.Y.Z for Maven Central, and publish
  the website. Use when preparing SNAPSHOT lines, releases, tags, or site packaging.
---
<!-- gen from .mdh -->

# JRebirth AF — snapshot release versions

Framework-only skill Covers JRebirth repository (`org.jrebirth.af/`), not application consumers

## Hard rules

1. Never publish Maven Central (tag `jrebirth-*` or `workflow_dispatch` release) unless maintainer explicitly approves release version
2. While waiting approval keep `12.x-dev` `-SNAPSHOT` (e.g `12.0.1-SNAPSHOT`).
3. Before any tooling release `PluginDescriptorGoalsTest` must pass (`text2fx` `ecore2fx` `plugin.xml`, no empty `<mojos/>`).
4. Maven Central 12.0.0 `jrebirth-maven-plugin` broken — republish under 12.0.1+ only do not reuse 12.0.0 coordinates

## Version layout

| Branch | POM version | Where artifacts go |
|--------|-------------|-------------------|
| `12.x-dev` | `X.Y.Z-SNAPSHOT` | GitHub Packages (`-Pgithub-packages`, workflow `snapshot.yml`) |
| Tag `jrebirth-X.Y.Z` | `X.Y.Z` (CI `versions:set`) | Maven Central (`-Pdeploy-central`, workflow `release-maven-central.yml`) |

Parent: `org.jrebirth:organization:6.0.0` (Organization-POM Central)

## Bump dev version (local)

PowerShell (quote version — dots break unquoted `-D` args):

```powershell
mvn -f org.jrebirth.af/pom.xml -B "org.codehaus.mojo:versions-maven-plugin:2.18.0:set" "-DnewVersion=12.0.1-SNAPSHOT" -DprocessAllModules=true -DgenerateBackupPoms=false
```

Helper:

```powershell
powershell -File org.jrebirth.af/.cursor/skills/release-version/scripts/bump-af-version.ps1 -NewVersion 12.0.1-SNAPSHOT
```

After bump: fix orphan POMs outside reactor if any still reference old parent version (`form/`, `tooling/genmodel/`, `showcase/data/`).

## Validate tooling (mandatory before release)

```powershell
mvn -f org.jrebirth.af/pom.xml -pl tooling/jrebirth-maven-plugin test
```

Site wiring tests:

```powershell
mvn -f org.jrebirth.af/pom.xml test -pl core -Dtest=AiKitAssemblyDescriptorTest,FrameworkBundleAssemblyDescriptorTest
```

## Site downloads (`pre-site`)

Built `org.jrebirth.af/target/site/downloads/`:

| File | Assembly |
|------|----------|
| `jrebirth-ai-kit-${project.version}.zip` | `src/assembly/ai-kit.xml` |
| `jrebirth-${project.version}.zip` | `src/assembly/framework-bundle.xml` (library JARs + sources) |

Requires prior `mvn install` so module `target/*.jar` exist before `mvn site`.

Fast site (aggregator only): see `preview-site` skill

```powershell
mvn -f org.jrebirth.af/pom.xml clean install -Dmaven.test.skip=true
mvn -f org.jrebirth.af/pom.xml site -N -Dmaven.javadoc.skip=true
```

Public site path: `sites/org.jrebirth-${version}/` (hosted `https://sites.jrebirth.org/org.jrebirth-${version}/`).

## Showcase apps (DMZ `apps.jrebirth.org`, never git)

Portable app ZIPs not repository Site templates link `https://apps.jrebirth.org/{app}/{version}/{file}.zip` (POM property `appsDownloadBase`) — same pattern EZDojo desktop artefacts `downloads.ezdojo.net`.

1. Build portable ZIPs (`showcase` `-Ppackage-portable` or external demo repos)
2. Stage locally (gitignored):

```powershell
powershell -File tools/stage-jrebirth-apps.ps1
```

3. Publish binaries DMZ (`publish-jrebirth-to-dmz.ps1` Hosting repository):

```powershell
powershell -File D:\DEV\GIT\Hosting\.cursor\skills\hosting\publish-static-jrebirth\scripts\publish-jrebirth-to-dmz.ps1 `
  -AppsOnly -RemoteSiteVersion /var/www/sites/jrebirth.org/sites/org.jrebirth-12.0.1 -Apply
```

Docroot DMZ: `/var/www/sites/jrebirth.org/apps/` → public `apps.jrebirth.org`.

## Site download ZIPs (AI kit + framework)

Built Maven `pre-site` → `org.jrebirth.af/target/site/downloads/` (`jrebirth-ai-kit-*.zip`, `jrebirth-*.zip`). Ship Maven site `www` / `sites.jrebirth.org` (relative `downloads/` links), not via `apps.jrebirth.org`.

## Upload site (DMZ preferred)

```powershell
powershell -File tools/stage-jrebirth-apps.ps1
# Hosting publish: site + apps (see publish-jrebirth-to-dmz.ps1 -BuildSite if needed)
```

Legacy IONOS SCP: `tools/upload-site-and-apps.ps1` — avoid new releases use DMZ static vhosts instead

## Release checklist (Central — gated)

Stop here until maintainer approves

1. All apps depend JRebirth aligned release version build green (local SNAPSHOT or GitHub Packages)
2. `PluginDescriptorGoalsTest` green commit release
3. Maintainer approves Central publish `X.Y.Z`.
4. Tag `jrebirth-X.Y.Z` push or run GitHub Action Release Maven Central version `X.Y.Z` (no `SNAPSHOT`).
5. Verify Central `org.jrebirth.af.tooling:jrebirth-maven-plugin:X.Y.Z` lists `text2fx` `ecore2fx`.
6. Build site + upload (`tools/upload-site-and-apps.ps1 -Version X.Y.Z`).
7. On `12.x-dev`, bump next snapshot (e.g `12.0.2-SNAPSHOT`) commit

Central exclusions (unchanged): `sample`, `distribution`, `showcase/*` — see `README-MAVEN-CENTRAL.md`.

## Related skills

- `preview-site` — local HTML preview
- `update-site-doc` — doc pages download links when version or assemblies change
