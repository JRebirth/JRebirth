# JRebirth skills (framework tree)

Application / vibecoding skills live in the **single source of truth**:

`jrebirth-ai-kit/skills/` at the repository root (`D:\DEV\GIT\JRebirth\jrebirth-ai-kit\skills` on a typical clone).

This folder keeps **framework-only** skills:

- `update-site-doc` — author Maven Site documentation pages
- `preview-site` — fast `mvn site -N` + local HTTP preview (`scripts/preview-site.ps1`)
- `release-version` — SNAPSHOT bumps, Central release gate, site downloads (AI kit + framework ZIP), upload script

Install the kit into an app with `jrebirth-ai-kit/install.ps1` or `install.sh`.
