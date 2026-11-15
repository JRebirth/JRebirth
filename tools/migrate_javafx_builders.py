#!/usr/bin/env python3
"""Migrate JavaFX 2.x Builder API to constructors + setters in Java sources."""
from __future__ import annotations

import re
import sys
from pathlib import Path

# Builder simple name -> Java type (strip "Builder" suffix)
JAVA_FX_BUILDER_SUFFIX = "Builder"

# Methods that map to collection helpers instead of setXxx
COLLECTION_METHODS = {
    "children": "getChildren().addAll",
    "items": "getItems().addAll",
    "keyFrames": "getKeyFrames().addAll",
    "points": "getPoints().addAll",
    "stops": "getStops().addAll",
    "transforms": "getTransforms().addAll",
}

SINGLE_ADD_METHODS = {
    "styleClass": "getStyleClass().add",
}

# Types that accept common constructor args (builder method -> constructor param order)
CONSTRUCTOR_HINTS: dict[str, list[str]] = {
    "Label": ["text"],
    "Button": ["text"],
    "Hyperlink": ["text"],
    "CheckBox": ["text"],
    "Text": [],  # content handled separately
    "Circle": ["radius"],
    "Line": [],
    "Slider": [],
    "TextArea": [],
    "HBox": [],
    "VBox": [],
    "Pane": [],
    "StackPane": [],
    "AnchorPane": [],
    "BorderPane": [],
    "FlowPane": [],
    "ImageView": [],
    "Rectangle": [],
    "Polyline": [],
    "SVGPath": [],
    "WebView": [],
    "Group": [],
    "Scene": [],
    "Stage": [],
    "Stop": ["offset", "color"],
}

SKIP_IMPORT_BUILDERS = {"ExpressionBuilder"}  # not JavaFX

_counter = 0


def next_var(prefix: str = "_fx") -> str:
    global _counter
    _counter += 1
    return f"{prefix}{_counter}"


def is_javafx_builder(name: str) -> bool:
    return name.endswith(JAVA_FX_BUILDER_SUFFIX) and name not in SKIP_IMPORT_BUILDERS


def builder_type(builder_name: str) -> str:
    return builder_name[: -len(JAVA_FX_BUILDER_SUFFIX)]


def capitalize(s: str) -> str:
    return s[0].upper() + s[1:] if s else s


def find_matching_paren(s: str, open_idx: int) -> int:
    """open_idx points at '('"""
    depth = 0
    i = open_idx
    while i < len(s):
        c = s[i]
        if c == "(":
            depth += 1
        elif c == ")":
            depth -= 1
            if depth == 0:
                return i
        elif c == '"':
            i += 1
            while i < len(s) and s[i] != '"':
                if s[i] == "\\":
                    i += 1
                i += 1
        elif c == "'":
            i += 1
            while i < len(s) and s[i] != "'":
                if s[i] == "\\":
                    i += 1
                i += 1
        i += 1
    return -1


def parse_builder_at(s: str, start: int) -> tuple[int, str, list[tuple[str, str]]] | None:
    """Parse BuilderName.create()....build() starting at start index."""
    m = re.match(r"(\w+Builder)\.create\(\)", s[start:])
    if not m:
        return None
    builder_name = m.group(1)
    if not is_javafx_builder(builder_name):
        return None
    pos = start + m.end()
    methods: list[tuple[str, str]] = []
    while True:
        while pos < len(s) and s[pos] in " \t\n\r":
            pos += 1
        if pos >= len(s):
            break
        if s.startswith(".build()", pos):
            end = pos + len(".build()")
            # optional suffix like .play()
            return end, builder_name, methods
        if s[pos] != ".":
            break
        pos += 1
        mm = re.match(r"(\w+)(\()", s[pos:])
        if not mm:
            break
        method = mm.group(1)
        arg_start = pos + mm.end() - 1
        arg_end = find_matching_paren(s, arg_start)
        if arg_end < 0:
            break
        args = s[arg_start + 1 : arg_end].strip()
        methods.append((method, args))
        pos = arg_end + 1
    return None


def contains_nested_builder(segment: str) -> bool:
    return bool(re.search(r"\w+Builder\.create\(\)", segment))


def find_innermost_builder(s: str) -> tuple[int, int, str, list[tuple[str, str]]] | None:
    all_pos = [m.start() for m in re.finditer(r"\w+Builder\.create\(\)", s)]
    candidates = []
    for st in all_pos:
        parsed = parse_builder_at(s, st)
        if not parsed:
            continue
        end, bname, methods = parsed
        segment = s[st:end]
        inner_start = re.match(r"\w+Builder\.create\(\)", segment)
        if inner_start and contains_nested_builder(segment[inner_start.end() :]):
            continue
        candidates.append((st, end, bname, methods))
    if not candidates:
        return None
    candidates.sort(key=lambda x: (x[1] - x[0], -x[0]))
    return candidates[0]


def emit_builder_expr(
    builder_name: str,
    methods: list[tuple[str, str]],
    as_statement: bool,
    indent: str,
) -> str:
    jtype = builder_type(builder_name)
    var = next_var()
    lines: list[str] = []

    ctor_args: list[str] = []
    ctor_methods = list(methods)
    hints = CONSTRUCTOR_HINTS.get(jtype, [])
    for hint in hints:
        for i, (meth, args) in enumerate(ctor_methods):
            if meth == hint:
                ctor_args.append(args)
                ctor_methods.pop(i)
                break

    if ctor_args:
        lines.append(f"{indent}final {jtype} {var} = new {jtype}({', '.join(ctor_args)});")
    else:
        lines.append(f"{indent}final {jtype} {var} = new {jtype}();")

    for meth, args in ctor_methods:
        if meth in COLLECTION_METHODS:
            if args:
                lines.append(f"{indent}{var}.{COLLECTION_METHODS[meth]}({args});")
            else:
                lines.append(f"{indent}{var}.{COLLECTION_METHODS[meth]}();")
        elif meth in SINGLE_ADD_METHODS:
            lines.append(f"{indent}{var}.{SINGLE_ADD_METHODS[meth]}({args});")
        elif args == "":
            lines.append(f"{indent}{var}.{meth}();")
        else:
            setter = "set" + capitalize(meth)
            lines.append(f"{indent}{var}.{setter}({args});")

    if as_statement:
        return "\n".join(lines)
    return "\n".join(lines) + f"\n{indent}{var}"


def migrate_text(content: str) -> tuple[str, int]:
    replacements = 0
    max_iter = 10000
    while max_iter > 0:
        max_iter -= 1
        found = find_innermost_builder(content)
        if not found:
            break
        st, end, bname, methods = found
        # Check if used as full statement (starts line after ws, ends with ; or .play())
        before = content[:st].rstrip()
        after = content[end:].lstrip()

        # suffix after .build() e.g. .play()
        suffix = ""
        if after.startswith("."):
            sm = re.match(r"\.(\w+)\(\)", after)
            if sm:
                suffix = after[: sm.end()]
                after_rest = after[sm.end() :]
            else:
                after_rest = after
        else:
            after_rest = after

        inline = bool(before and not before.endswith((",", "(", "=", "return ", "return")))
        as_stmt = (
            not inline
            and (after_rest.startswith(";") or after_rest.startswith("\n") or after_rest == "")
        )

        indent_m = re.search(r"(?m)^(\s*)", content[:st][::-1])
        # rough indent from previous newline
        line_start = content.rfind("\n", 0, st) + 1
        indent = re.match(r"\s*", content[line_start:st]).group(0)

        migrated = emit_builder_expr(bname, methods, as_stmt, indent)

        if inline:
            # expression position: use only var name on last line
            var_name = migrated.strip().split("\n")[-1].strip()
            if var_name.startswith("final "):
                var_name = var_name.split()[-1]
            else:
                var_name = migrated.split("\n")[-1].strip()
            # last line is var reference
            parts = migrated.split("\n")
            var_ref = parts[-1].strip()
            pre_lines = "\n".join(parts[:-1])
            # insert pre_lines before current line, replace builder with var
            line_start = content.rfind("\n", 0, st) + 1
            content = content[:line_start] + pre_lines + "\n" + content[line_start:st] + var_ref + suffix + content[end + len(suffix) :]
        elif as_stmt:
            line_start = content.rfind("\n", 0, st) + 1
            # may have been: Foo x = Builder...;
            line_prefix = content[line_start:st]
            assign_m = re.match(r"(\s*)(?:final\s+)?(\w+(?:<[^>]+>)?)\s+(\w+)\s*=\s*$", line_prefix)
            if assign_m:
                ind, typ, vname = assign_m.groups()
                parts = migrated.split("\n")
                body = "\n".join(parts)
                body = body.replace(f"final {builder_type(bname)} {parts[-1].strip().split()[-1]}", f"final {typ} {vname}", 1)
                # fix: migrated uses wrong var - rebuild
                jtype = builder_type(bname)
                lines = [f"{ind}final {typ} {vname} = new {jtype}();"]
                ctor_done = False
                hints = CONSTRUCTOR_HINTS.get(jtype, [])
                rem = list(methods)
                for hint in hints:
                    for i, (meth, args) in enumerate(rem):
                        if meth == hint:
                            lines[0] = f"{ind}final {typ} {vname} = new {jtype}({args});"
                            rem.pop(i)
                            ctor_done = True
                            break
                if not ctor_done and hints and rem and rem[0][0] in hints:
                    pass
                for meth, args in rem:
                    if meth in COLLECTION_METHODS:
                        lines.append(f"{ind}{vname}.{COLLECTION_METHODS[meth]}({args});")
                    elif meth in SINGLE_ADD_METHODS:
                        lines.append(f"{ind}{vname}.{SINGLE_ADD_METHODS[meth]}({args});")
                    elif args == "":
                        lines.append(f"{ind}{vname}.{meth}();")
                    else:
                        lines.append(f"{ind}{vname}.set{capitalize(meth)}({args});")
                stmt = "\n".join(lines) + suffix
                if after_rest.startswith(";"):
                    stmt += ";"
                content = content[:line_start] + stmt + after_rest.lstrip(";") 
            else:
                stmt = migrated + suffix
                if after_rest.startswith(";"):
                    stmt += ";"
                content = content[:line_start] + stmt + after_rest.lstrip(";")
        else:
            var_ref = migrated.split("\n")[-1].strip()
            pre = "\n".join(migrated.split("\n")[:-1])
            line_start = content.rfind("\n", 0, st) + 1
            content = content[:line_start] + pre + "\n" + content[line_start:st] + var_ref + suffix + content[end + len(suffix) :]

        replacements += 1

    return content, replacements


def fix_imports(content: str) -> str:
    lines = content.split("\n")
    new_lines: list[str] = []
    builders_removed: set[str] = set()
    types_to_add: set[str] = set()

    for line in lines:
        imp = re.match(r"import (javafx\.[\w.]+\.)(\w+Builder);", line)
        if imp:
            pkg, bname = imp.groups()
            if is_javafx_builder(bname):
                builders_removed.add(bname)
                jtype = builder_type(bname)
                types_to_add.add(f"import {pkg}{jtype};")
                continue
        # javafx.animation.FooBuilder -> Foo
        imp2 = re.match(r"import (javafx\.animation\.)(\w+Builder);", line)
        if imp2 and is_javafx_builder(imp2.group(2)):
            builders_removed.add(imp2.group(2))
            types_to_add.add(f"import {imp2.group(1)}{builder_type(imp2.group(2))};")
            continue
        new_lines.append(line)

    # animation transitions
    for b in list(builders_removed):
        jtype = builder_type(b)
        if jtype in (
            "ParallelTransition", "SequentialTransition", "TranslateTransition",
            "RotateTransition", "ScaleTransition", "FadeTransition", "Timeline",
            "FillTransition", "PathTransition", "PauseTransition", "StrokeTransition",
        ):
            types_to_add.add(f"import javafx.animation.{jtype};")
        elif jtype in ("RadialGradient", "LinearGradient", "Stop"):
            types_to_add.add(f"import javafx.scene.paint.{jtype};")
        elif jtype in ("DropShadow", "Glow", "InnerShadow", "MotionBlur", "BoxBlur",
                       "Reflection", "SepiaTone", "Bloom", "GaussianBlur", "Shadow",
                       "Lighting", "PerspectiveTransform", "Blend", "ColorAdjust"):
            types_to_add.add(f"import javafx.scene.effect.{jtype};")
        elif jtype == "ImageInput":
            types_to_add.add("import javafx.scene.effect.ImageInput;")
        elif jtype in ("Rotate", "Translate"):
            types_to_add.add(f"import javafx.scene.transform.{jtype};")
        elif jtype in ("ColumnConstraints", "RowConstraints"):
            types_to_add.add(f"import javafx.scene.layout.{jtype};")
        elif jtype == "Scene":
            types_to_add.add("import javafx.scene.Scene;")
        elif jtype == "Stage":
            types_to_add.add("import javafx.stage.Stage;")

    # Insert new imports after last javafx import block
    insert_at = 0
    for i, line in enumerate(new_lines):
        if line.startswith("import javafx."):
            insert_at = i + 1
    existing = set(new_lines)
    to_insert = sorted(types_to_add - existing)
    for imp in reversed(to_insert):
        new_lines.insert(insert_at, imp)

    return "\n".join(new_lines)


def migrate_file(path: Path) -> int:
    global _counter
    _counter = 0
    text = path.read_text(encoding="utf-8")
    if "Builder" not in text or "Builder.create()" not in text:
        return 0
    text, n = migrate_text(text)
    if n > 0 or re.search(r"import javafx\..*Builder;", text):
        text = fix_imports(text)
        path.write_text(text, encoding="utf-8")
    return n


def main(argv: list[str]) -> int:
    roots = [Path(p) for p in argv[1:]] if len(argv) > 1 else []
    if not roots:
        print("Usage: migrate_javafx_builders.py <src-root>...", file=sys.stderr)
        return 1
    total = 0
    files = 0
    for root in roots:
        for path in sorted(root.rglob("*.java")):
            if "ExpressionBuilder" in path.name:
                continue
            n = migrate_file(path)
            if n:
                print(f"  {n:3d}  {path}")
                total += n
                files += 1
    print(f"Migrated {total} builder chains in {files} files")
    return 0


if __name__ == "__main__":
    raise SystemExit(main(sys.argv))