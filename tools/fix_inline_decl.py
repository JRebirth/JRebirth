"""Fix broken 'final T x = ...;' inserted inside argument lists by javafx_builder_migrate."""
import re
import sys
from pathlib import Path

pattern = re.compile(
    r"final\s+([\w.<>,\[\]]+)\s+(_\w+)\s*=\s*([^;]+);\s*\2\s*,",
    re.MULTILINE,
)


def fix(text: str) -> str:
    prev = None
    while prev != text:
        prev = text
        text = pattern.sub(r"\3,", text)
    # remove orphan lines like ".path(_b1)" after broken migration - skip
    text = re.sub(
        r"PathTransitionBuilder\.create\(\)\s*\n\s*\.node\([^\n]+\)\s*\n\s*final SVGPath _b\d+ = new SVGPath\(\);\s*\n\s*_b\d+\.setContent\(path\);\s*\n\s*\.path\(_b\d+\)",
        lambda m: "PathTransitionBuilder.create().node(this.demoRectangle).path(new SVGPath())",
        text,
    )
    return text


for path in map(Path, sys.argv[1:]):
    t = path.read_text(encoding="utf-8")
    path.write_text(fix(t), encoding="utf-8")
    print(path)