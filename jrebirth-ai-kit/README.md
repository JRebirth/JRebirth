# JRebirth AI Kit

Agent-ready instructions so Cursor, Claude Code, Codex, Copilot, and similar tools can **vibecode** JavaFX apps on [JRebirth AF](https://www.jrebirth.org) without inventing plain-JavaFX shortcuts.

This kit is the **single source of truth** for JRebirth application skills. Install it into your app repo; do not fork and rewrite the rules locally unless you contribute upstream.

## What you get

| Path | Purpose |
|------|---------|
| `AGENTS.md` | Strict architecture rules (MVC, JAT, Waves, naming) |
| `CLAUDE.md` | Pointer for Claude Code → `@AGENTS.md` |
| `.cursor/rules/jrebirth-core.mdc` | Cursor rule (globs `*.java`, `*.fxml`) |
| `skills/jrebirth-*/` | Step-by-step skills ([Agent Skills](https://agentskills.io)-style `SKILL.md` + `references/`) |
| `install.ps1` / `install.sh` | Copy/link into a target project for multi-agent layouts |
| `install-interactive.ps1` / `install-interactive.sh` | Prompts for target path, symlink, and force — then runs the installer |

## Download (no Git clone)

Grab **jrebirth-ai-kit-&lt;version&gt;.zip** from [www.jrebirth.org](https://www.jrebirth.org) (`downloads/jrebirth-ai-kit-12.0.0.zip` for the current release), unzip, then run `install-interactive.ps1` or `install-interactive.sh`.

## Install into an existing project

### Interactive (recommended)

**Windows**

```powershell
cd path\to\jrebirth-ai-kit
.\install-interactive.ps1
```

**Linux / macOS**

```bash
cd path/to/jrebirth-ai-kit
chmod +x install-interactive.sh install.sh
./install-interactive.sh
```

### Windows (PowerShell)

```powershell
cd path\to\jrebirth-ai-kit
.\install.ps1 -Target C:\path\to\your-app
# optional: -Force  (backup+replace AGENTS/CLAUDE)
# optional: -Link   (junction skills folders to this kit)
```

### Linux / macOS

```bash
cd path/to/jrebirth-ai-kit
chmod +x install.sh
./install.sh /path/to/your-app
# optional: ./install.sh /path/to/your-app --force --link
```

The installer:

1. Copies `AGENTS.md` and `CLAUDE.md` to the project root (asks before overwrite, or appends a pointer).
2. Installs `.cursor/rules/jrebirth-core.mdc`.
3. Copies or links `skills/` into:
   - `.cursor/skills/`
   - `.claude/skills/`
   - `.codex/skills/`
   - `.agents/skills/`

### Other options

- **Clone / submodule** this folder into your monorepo and run the installer once.
- **`npx skills add`** (when published to a skills registry): point at this kit’s `skills/` directory or the GitHub path once available.
- **Manual**: copy `AGENTS.md`, `CLAUDE.md`, the Cursor rule, and the `skills/` tree into the locations above.

## Skills catalog

| Skill | When to use | Example prompt |
|-------|-------------|----------------|
| `jrebirth-core` | Role unclear; starting any JRebirth work | “Where should this selection state live?” |
| `jrebirth-create-mvc` | New screen / panel / editor | “Scaffold a Membership list screen with JRebirth MVC.” |
| `jrebirth-create-model` | State, bindings, orchestration | “Add filter properties and selection to the model.” |
| `jrebirth-create-view` | Scene graph / CSS on a view | “Build the form layout in the View.” |
| `jrebirth-create-controller` | Buttons, keys, menus | “Wire the Save button to a command.” |
| `jrebirth-create-command` | One named action | “Create a SaveMembershipCommand.” |
| `jrebirth-create-multi-command` | Ordered command chain | “Run validate then save then refresh.” |
| `jrebirth-invoke-command` | Triggering commands safely | “Fix getCommand().run(…) usage.” |
| `jrebirth-create-service` | REST, files, heavy work | “Load members from the API in a Service.” |
| `jrebirth-create-wave` | Decoupled messaging | “Add a DO_REFRESH_LIST wave.” |
| `jrebirth-create-behavior` | Tiny shared helper | “Share this hover helper as a Behavior.” |
| `jrebirth-create-resources` | Images, CSS, fonts enums | “Add a stylesheet resource enum.” |
| `jrebirth-create-application` | App entry / stage / scene | “Bootstrap DefaultApplication and first model.” |
| `jrebirth-maven-project` | New Maven module / app | “Create a standalone JRebirth Maven app.” |
| `jrebirth-jpms-module` | `module-info`, opens, ModuleStarter | “Fix IllegalAccessException on Model.” |
| `jrebirth-annotations` | `@OnWave`, `@OnAction`, `@Preload`… | “Listen to this wave with @OnWave.” |
| `jrebirth-processor-modules` | Generated ModuleStarter / `@Register` | “ModuleStarter class not found.” |
| `jrebirth-threading` | JAT / JIT / pool without `runInto*` | “Refresh the table after async load.” |
| `jrebirth-anti-patterns` | Code smells review | “Review this class for JRebirth anti-patterns.” |
| `jrebirth-refactor-legacy` | Migrate messy JavaFX | “Split this god class into MVC roles.” |
| `jrebirth-spring-bridge` | Spring API / DTO boundaries | “Call the Spring REST API from a Service.” |
| `jrebirth-javafx-css` | Validate JavaFX CSS | “Check this stylesheet against OpenJFX CSS ref.” |

## After install — how agents should behave

1. Read `AGENTS.md`.
2. Open the matching `jrebirth-*` skill before generating code.
3. Use templates under each skill’s `references/` folder instead of inventing APIs.

## Docs

- Website: [AI kit / vibecoding](https://www.jrebirth.org/doc/AI-Kit.html) (after site publish)
- Framework docs: [www.jrebirth.org/doc/Toc.html](https://www.jrebirth.org/doc/Toc.html)

## Contributing

Improve skills in this kit only (no second copy under `org.jrebirth.af/.cursor/skills` for app patterns). Framework-only docs (Maven Site) stay in the main module’s Cursor tree.
