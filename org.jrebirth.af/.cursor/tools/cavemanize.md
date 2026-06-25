<!-- gen .mdh -->

# Caveman skills convention

This `.cursor` tree keeps two layers every rule skill document

## Layers

- `*.mdh` (markdown-human) source truth Write normal readable English here
- The Cursor-facing file generated token-minimal ("caveman") Never edit hand

## Generation map

The generator picks output extension location source:

- `*.mdh` under `.cursor/rules/` compiles `*.mdc` (a Cursor rule)
- any other `*.mdh` compiles `*.md` (`SKILL.md`, `reference.md`, doc etc.)

## What preserved

The squeeze only touches plain prose These kept verbatim:

- YAML frontmatter (so rule `description`/`globs` skill `name`/`description` still match)
- fenced code blocks inline `code`.
- markdown links tables
- identifiers CamelCase, ALLCAPS, symbols numbers
- negations logic words: no not never if then when use make put keep move

Only articles copulas filler prepositions dropped plain words lowercased

## Workflow

1. Edit relevant `*.mdh` source
2. Run generator repo so siblings refresh
3. Commit both `*.mdh` source generated file together

```bash
py .cursor/tools/cavemanize.py
```

Verify nothing stale (good pre-commit or CI check):

```bash
py .cursor/tools/cavemanize.py --check
```

Process single source:

```bash
py .cursor/tools/cavemanize.py --file .cursor/skills/model/SKILL.mdh
```
