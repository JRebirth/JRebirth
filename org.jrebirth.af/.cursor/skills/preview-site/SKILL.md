---
name: preview-site
description: >-
  Generate the JRebirth Maven marketing/docs site quickly and open a local
  HTTP preview. Use when the user asks to build, regenerate, or preview
  www.jrebirth.org pages (home, AI-Kit, doc), or after editing src/site.
---
<!-- gen from .mdh -->

# Preview JRebirth site (fast)

Framework-only skill Do not put `jrebirth-ai-kit` (app consumers do not need it)

## Goal

Regenerate `org.jrebirth.af/target/site` serve locally so agent (or user) can check HTML/CSS without deploying

## Fast path (default)

From repo root `JRebirth/`:

```powershell
powershell -File org.jrebirth.af/.cursor/skills/preview-site/scripts/preview-site.ps1
```

What script does:

1. Ensures site skin installed (`JRebirth-Skin` → local `.m2`) when missing
2. Runs non-recursive site build AF aggregator only (skips submodule sites + `distribution`).
3. Starts local static server `http://127.0.0.1:4173/` via `py -3 -m http.server` (override `-Port`).
4. Prints URLs home + AI kit

On Windows, prefer `py` launcher — Microsoft Store `python` stub often exits 9009

Flags:

| Flag | Effect |
|------|--------|
| `-SkipBuild` | Only serve an existing `target/site` |
| `-BuildOnly` | Generate site, do not start the server |
| `-FullReactor` | Slower: `mvn site -pl '!distribution'` (all module sites) |
| `-Port 4173` | HTTP port |
| `-NoBrowser` | Do not call `Start-Process` on the home URL |

## Manual Maven (same fast build)

```powershell
# once, if skin missing
mvn -f D:\DEV\GIT\JRebirth-Skin\org.jrebirth.site.jrebirth-skin\pom.xml install -DskipTests

cd D:\DEV\GIT\JRebirth\org.jrebirth.af
mvn site -N
```

Output: `org.jrebirth.af/target/site/`
Key pages: `index.html`, `doc/AI-Kit.html`, `doc/Toc.html`.

## Agent preview checklist

After generate:

1. Confirm `target/site/index.html` `target/site/doc/AI-Kit.html` exist
2. Serve script (or `python -m http.server` `target/site`).
3. Open home → section `#ai-kit` → link `doc/AI-Kit.html`.
4. Open AI-Kit page → TOC pager + install commands readable
5. Fix content under `src/site/` (xdoc / markdown), then re-run script (omit `-SkipBuild`).

## Related

- Content authoring: `update-site-doc`
- DMZ publish: Hosting skill `publish-static-jrebirth` (only when user asks deploy)
