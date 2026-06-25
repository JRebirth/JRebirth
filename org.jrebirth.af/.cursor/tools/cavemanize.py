#!/usr/bin/env python3
"""cavemanize.py - compile human .mdh sources into token-minimal caveman files.

Source of truth = the human-readable *.mdh files (markdown, normal English).
Cursor reads only the generated siblings:
  - any *.mdh under  .cursor/rules/  -> *.mdc   (rule, frontmatter kept verbatim)
  - any other *.mdh                  -> *.md    (skill / reference / doc)

The body prose is squeezed to "caveman" style (drop articles/copulas/fillers,
lowercase plain words, strip sentence punctuation). Anything that carries meaning
is preserved verbatim: YAML frontmatter, fenced code, inline `code`, links,
tables, Identifiers/CamelCase/ALLCAPS, symbols, and negations (no/not/never...).

Usage:
  py .cursor/tools/cavemanize.py            # generate every sibling from *.mdh
  py .cursor/tools/cavemanize.py --check    # exit 1 if any output is stale
  py .cursor/tools/cavemanize.py --file PATH.mdh
"""
from __future__ import annotations

import argparse
import re
import sys
from pathlib import Path

# Root = the .cursor directory that contains this tools/ folder.
CURSOR_ROOT = Path(__file__).resolve().parent.parent

# Filler words safe to drop. Negations and logic/imperative words are NOT here
# on purpose (no, not, never, if, then, when, use, make, put, keep, move, ...).
STOP = {
    "a", "an", "the",
    "is", "are", "am", "was", "were", "be", "been", "being",
    "of", "to", "into", "for", "with", "on", "at", "in", "from", "by", "as",
    "and", "that", "which", "this", "these", "those", "it", "its",
    "you", "your", "we", "they", "i", "our", "us",
    "will", "shall", "would",
}

_INLINE_CODE = re.compile(r"`[^`]*`")
_LINK = re.compile(r"\[[^\]]*\]\([^)]*\)")
_EMPHASIS = re.compile(r"\*\*|__")
_TRAIL_PUNCT = re.compile(r"[.,;:?!]+$")
# A token is "protected" (kept verbatim) when it looks technical.
_PROTECTED = re.compile(r"[A-Z0-9`._/():@#<>{}=\[\]\"'\\|*+-]")


def _out_path(src: Path) -> Path:
    """Map a .mdh source to its generated sibling path."""
    rel = src.resolve().relative_to(CURSOR_ROOT)
    ext = ".mdc" if rel.parts and rel.parts[0] == "rules" else ".md"
    return src.with_suffix(ext)


def _squeeze_token(tok: str) -> str | None:
    """Return caveman token, or None to drop it."""
    if _PROTECTED.search(tok):
        # technical token: keep verbatim but drop a single trailing period
        return tok[:-1] if tok.endswith(".") and not tok.endswith("..") else tok
    m = _TRAIL_PUNCT.search(tok)
    trail = m.group(0) if m else ""
    core = tok[: -len(trail)] if trail else tok
    if core.lower() in STOP:
        return None
    trail = trail.replace(".", "").replace(",", "").replace(";", "")
    return core.lower() + trail


def _squeeze_prose(text: str) -> str:
    """Caveman-squeeze a single prose string (no leading markdown marker)."""
    holes: list[str] = []

    def _stash(m: re.Match) -> str:
        holes.append(m.group(0))
        return f"\x00{len(holes) - 1}\x00"

    text = _INLINE_CODE.sub(_stash, text)
    text = _LINK.sub(_stash, text)
    text = _EMPHASIS.sub("", text)

    out: list[str] = []
    for tok in text.split():
        if "\x00" in tok:
            out.append(tok)
            continue
        sq = _squeeze_token(tok)
        if sq:
            out.append(sq)
    result = " ".join(out)

    def _restore(m: re.Match) -> str:
        return holes[int(m.group(1))]

    return re.sub(r"\x00(\d+)\x00", _restore, result)


def _is_verbatim_line(line: str) -> bool:
    s = line.lstrip()
    return "|" in line or s.startswith(("<!--", "<", "|"))


def _squeeze_line(line: str) -> str:
    if not line.strip():
        return ""
    if _is_verbatim_line(line):
        return line.rstrip()
    # heading
    m = re.match(r"^(#{1,6})\s+(.*)$", line)
    if m:
        body = _squeeze_prose(m.group(2))
        return f"{m.group(1)} {body}".rstrip()
    # list item (-, *, +, or "1.")
    m = re.match(r"^(\s*(?:[-*+]|\d+\.)\s+)(.*)$", line)
    if m:
        return (m.group(1) + _squeeze_prose(m.group(2))).rstrip()
    return _squeeze_prose(line).rstrip()


def cavemanize(text: str, src_name: str) -> str:
    lines = text.replace("\r\n", "\n").split("\n")
    i = 0
    front: list[str] = []
    # frontmatter: copy verbatim
    if lines and lines[0].strip() == "---":
        front.append(lines[0])
        i = 1
        while i < len(lines):
            front.append(lines[i])
            if lines[i].strip() == "---":
                i += 1
                break
            i += 1

    out: list[str] = []
    in_fence = False
    blank = False
    for line in lines[i:]:
        fence = line.lstrip().startswith("```")
        if fence:
            in_fence = not in_fence
            out.append(line.rstrip())
            blank = False
            continue
        if in_fence:
            out.append(line)
            blank = False
            continue
        sq = _squeeze_line(line)
        if not sq:
            if not blank:
                out.append("")
            blank = True
            continue
        out.append(sq)
        blank = False

    marker = "<!-- gen .mdh -->"
    parts: list[str] = []
    if front:
        parts.extend(front)
    parts.append(marker)
    body = "\n".join(out).strip("\n")
    if body:
        parts.append("")
        parts.append(body)
    return "\n".join(parts).rstrip("\n") + "\n"


def iter_sources(root: Path):
    return sorted(p for p in root.rglob("*.mdh"))


def main(argv: list[str]) -> int:
    ap = argparse.ArgumentParser(description="Compile .mdh sources to caveman files.")
    ap.add_argument("--check", action="store_true", help="fail if any output is stale")
    ap.add_argument("--file", type=Path, help="process a single .mdh source")
    args = ap.parse_args(argv)

    sources = [args.file] if args.file else iter_sources(CURSOR_ROOT)
    stale: list[Path] = []
    wrote = 0
    for src in sources:
        src = src.resolve()
        if src.suffix != ".mdh":
            print(f"skip (not .mdh): {src}", file=sys.stderr)
            continue
        text = src.read_text(encoding="utf-8")
        result = cavemanize(text, src.name)
        out = _out_path(src)
        current = out.read_text(encoding="utf-8") if out.exists() else None
        if args.check:
            if current != result:
                stale.append(out)
        elif current != result:
            out.write_bytes(result.encode("utf-8"))
            wrote += 1
            print(f"wrote {out.relative_to(CURSOR_ROOT)}")

    if args.check:
        if stale:
            print("STALE caveman outputs (run cavemanize.py):", file=sys.stderr)
            for p in stale:
                print(f"  {p.relative_to(CURSOR_ROOT)}", file=sys.stderr)
            return 1
        print("ok: all caveman outputs in sync")
        return 0
    print(f"done: {wrote} file(s) updated, {len(sources)} source(s) scanned")
    return 0


if __name__ == "__main__":
    raise SystemExit(main(sys.argv[1:]))
