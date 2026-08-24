<!-- gen .mdh -->

# Caveman Skills Convention

This `.cursor` tree keeps two layers every rule skill document This convention lets humans maintain readable well-explained source files while agent works compact token-minimal copies

## Layers

- `*.mdh` (markdown-human) source truth Write normal readable English here This file edit when want update rules or skills
- The Cursor-facing file generated token-minimal ("caveman") Never edit hand — run generator instead

## Generation map

The generator picks output extension based location source file:

- A `*.mdh` under `.cursor/rules/` compiles `*.mdc` — Cursor rule file YAML frontmatter header
- Any other `*.mdh` compiles `*.md` — skill file reference file or tool documentation file (e.g., `SKILL.md`, `reference.md`).

## What preserved verbatim

The squeeze only touches plain prose These kept exactly written:

- YAML frontmatter so rule `description`/`globs` skill `name`/`description` remain correct
- Fenced code blocks inline `code` spans
- Markdown links tables
- Identifiers, CamelCase names ALLCAPS constants symbols numbers
- Negations logic words: no not never if then when use make put keep move

Only articles copulas filler prepositions dropped Plain prose words lowercased

## Workflow

1. Edit relevant `*.mdh` source file
2. Run generator repository root so all siblings refreshed once
3. Commit both `*.mdh` source generated file together

```bash
py .cursor/tools/cavemanize.py
```

Verify no output stale (useful pre-commit check or CI):

```bash
py .cursor/tools/cavemanize.py --check
```

Process single source file:

```bash
py .cursor/tools/cavemanize.py --file .cursor/skills/model/SKILL.mdh
```
