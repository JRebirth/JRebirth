#!/usr/bin/env python3
"""Migrate innermost JavaFX Builder chains to constructor + setters."""
from __future__ import annotations

import re
import sys
from pathlib import Path

SKIP = {"ExpressionBuilder"}

COLLECTION = frozenset(
    {"children", "items", "keyFrames", "points", "stops", "transforms"}
)
SINGLE_ADD = frozenset({"styleClass"})

_ctor_first = {
    "Label": "text",
    "Button": "text",
    "Hyperlink": "text",
    "CheckBox": "text",
    "Text": "text",
    "Circle": "radius",
    "Stop": None,  # special
}

_counter = 0


def cap(s: str) -> str:
    return s[0].upper() + s[1:] if s else s


def is_fx_builder(name: str) -> bool:
    return name.endswith("Builder") and name not in SKIP


def jtype(name: str) -> str:
    return name[: -len("Builder")]


def paren_end(s: str, i: int) -> int:
    depth = 0
    while i < len(s):
        c = s[i]
        if c == "(":
            depth += 1
        elif c == ")":
            depth -= 1
            if depth == 0:
                return i
        elif c in "\"'":
            q = c
            i += 1
            while i < len(s) and s[i] != q:
                if s[i] == "\\":
                    i += 1
                i += 1
        i += 1
    return -1


def parse_chain(s: str, pos: int):
    m = re.match(r"(\w+Builder)\.create\(\)", s[pos:])
    if not m:
        return None
    bname = m.group(1)
    if not is_fx_builder(bname):
        return None
    p = pos + m.end()
    methods = []
    while True:
        while p < len(s) and s[p] in " \t\r\n":
            p += 1
        if p >= len(s):
            return None
        if s.startswith(".build()", p):
            end = p + len(".build()")
            suffix = ""
            q = end
            while q < len(s) and s[q] in " \t":
                q += 1
            sm = re.match(r"\.(\w+)\(\)", s[q:])
            if sm:
                suffix = s[q : q + sm.end()]
            return (end - pos) + len(suffix), bname, methods, suffix
        if s[p] != ".":
            return None
        p += 1
        mm = re.match(r"(\w+)\(", s[p:])
        if not mm:
            return None
        meth = mm.group(1)
        a0 = p + mm.end() - 1
        a1 = paren_end(s, a0)
        if a1 < 0:
            return None
        methods.append((meth, s[a0 + 1 : a1].strip()))
        p = a1 + 1


def nested_builder_inside(segment: str) -> bool:
    return bool(re.search(r"\w+Builder\.create\(\)", segment))


def find_innermost(s: str):
    best = None
    for m in re.finditer(r"\w+Builder\.create\(\)", s):
        st = m.start()
        parsed = parse_chain(s, st)
        if not parsed:
            continue
        span, bname, methods, suffix = parsed
        end = st + span
        inner = s[st + m.end() : end - len(".build()" + suffix)]
        if nested_builder_inside(inner):
            continue
        cand = (st, end, bname, methods, suffix)
        if best is None or (end - st) < (best[1] - best[0]):
            best = cand
    return best


def emit(bname: str, methods: list[tuple[str, str]], vname: str) -> list[str]:
    cls = jtype(bname)
    lines: list[str] = []
    rem = list(methods)
    ctor_args: list[str] = []
    if cls == "Stop":
        off = col = None
        for meth, args in rem:
            if meth == "offset":
                off = args
            elif meth == "color":
                col = args
        if off is not None and col is not None:
            lines.append(f"final {cls} {vname} = new {cls}({off}, {col});")
            rem = [(m, a) for m, a in rem if m not in ("offset", "color")]
        else:
            lines.append(f"final {cls} {vname} = new {cls}();")
    elif cls in _ctor_first and _ctor_first[cls]:
        key = _ctor_first[cls]
        for i, (meth, args) in enumerate(rem):
            if meth == key:
                ctor_args = [args]
                rem.pop(i)
                break
        if ctor_args:
            lines.append(f"final {cls} {vname} = new {cls}({', '.join(ctor_args)});")
        else:
            lines.append(f"final {cls} {vname} = new {cls}();")
    else:
        lines.append(f"final {cls} {vname} = new {cls}();")

    for meth, args in rem:
        if meth in COLLECTION:
            call = f"{vname}.get{cap(meth)}().addAll"
            lines.append(f"{call}({args});" if args else f"{call}();")
        elif meth in SINGLE_ADD:
            lines.append(f"{vname}.getStyleClass().add({args});")
        elif meth == "content" and cls == "SVGPath":
            lines.append(f"{vname}.setContent({args});")
        elif args == "":
            lines.append(f"{vname}.{meth}();")
        else:
            lines.append(f"{vname}.set{cap(meth)}({args});")
    return lines


def line_indent(s: str, pos: int) -> str:
    ls = s.rfind("\n", 0, pos) + 1
    return re.match(r"[ \t]*", s[ls:pos]).group(0)


def migrate_content(content: str) -> tuple[str, int]:
    global _counter
    n = 0
    while True:
        hit = find_innermost(content)
        if not hit:
            break
        st, end, bname, methods, suffix = hit
        indent = line_indent(content, st)
        vname = f"_b{next_counter()}"
        stmt_lines = emit(bname, methods, vname)
        for i, ln in enumerate(stmt_lines):
            stmt_lines[i] = indent + ln.lstrip()

        ls = content.rfind("\n", 0, st) + 1
        prefix = content[ls:st]
        assign = re.match(
            r"(\s*)(?:final\s+)?([\w.<>,\[\]]+)\s+(\w+)\s*=\s*$",
            prefix,
        )
        after = content[end:]
        trailing = ""
        if after.lstrip().startswith(";"):
            trailing = ";"
        if assign:
            ind, typ, var = assign.groups()
            stmt_lines[0] = f"{ind}final {typ} {var} = new {jtype(bname)}();"
            # re-emit setters on var
            stmt_lines = [stmt_lines[0]]
            for ln in emit(bname, methods, var)[1:]:
                stmt_lines.append(indent + ln.split(None, 2)[-1] if ln.startswith("final") else indent + ln)
            # fix emit for assign - redo properly
            stmt_lines = []
            rem = list(methods)
            ctor = f"{ind}final {typ} {var} = "
            cls = jtype(bname)
            if cls == "Label" and rem and rem[0][0] == "text":
                stmt_lines.append(f"{ctor}new {cls}({rem[0][1]});")
                rem = rem[1:]
            elif cls == "Button" and rem and rem[0][0] == "text":
                stmt_lines.append(f"{ctor}new {cls}({rem[0][1]});")
                rem = rem[1:]
            else:
                stmt_lines.append(f"{ctor}new {cls}();")
            for meth, args in rem:
                if meth in COLLECTION:
                    stmt_lines.append(f"{ind}{var}.get{cap(meth)}().addAll({args});")
                elif meth in SINGLE_ADD:
                    stmt_lines.append(f"{ind}{var}.getStyleClass().add({args});")
                elif meth == "content" and cls == "SVGPath":
                    stmt_lines.append(f"{ind}{var}.setContent({args});")
                elif args == "":
                    stmt_lines.append(f"{ind}{var}.{meth}();")
                else:
                    stmt_lines.append(f"{ind}{var}.set{cap(meth)}({args});")
            block = "\n".join(stmt_lines) + suffix + trailing
            content = content[:ls] + block + after.lstrip(";")
        else:
            decl = "\n".join(stmt_lines) + "\n"
            repl = vname + suffix + trailing
            content = content[:ls] + decl + content[ls:st] + repl + after.lstrip(";")
        n += 1
        if n > 5000:
            break
        if n > 5000:
            break
    return content, n


def next_counter() -> int:
    global _counter
    _counter += 1
    return _counter


def fix_imports(text: str) -> str:
    lines = text.splitlines()
    out: list[str] = []
    add: set[str] = set()
    for line in lines:
        m = re.match(r"import (javafx\.[\w.]+)\.(\w+Builder);", line)
        if m and is_fx_builder(m.group(2)):
            cls = jtype(m.group(2))
            pkg = m.group(1)
            add.add(f"import {pkg}.{cls};")
            continue
        out.append(line)
    if not add:
        return text
    ins = 0
    for i, line in enumerate(out):
        if line.startswith("import javafx."):
            ins = i + 1
    for imp in sorted(add):
        if imp not in out:
            out.insert(ins, imp)
    return "\n".join(out) + ("\n" if text.endswith("\n") else "")


def process_file(path: Path) -> int:
    global _counter
    _counter = 0
    raw = path.read_text(encoding="utf-8")
    if "Builder.create()" not in raw:
        return 0
    text, n = migrate_content(raw)
    if n:
        text = fix_imports(text)
        path.write_text(text, encoding="utf-8")
    return n


def main() -> int:
    roots = [Path(p) for p in sys.argv[1:]]
    total = 0
    for root in roots:
        for path in sorted(root.rglob("*.java")):
            if "ExpressionBuilder" in path.name:
                continue
            c = process_file(path)
            if c:
                print(f"{c:4d} {path}")
                total += c
    print(f"total chains: {total}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())