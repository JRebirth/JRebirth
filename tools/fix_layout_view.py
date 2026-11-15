import re
from pathlib import Path

p = Path(r"D:\DEV\GIT\JFX-Presentation\org.jrebirth.af.presentation.javafx2\src\main\java\org\jrebirth\af\presentation\javafx\ui\slides\layout\LayoutView.java")
text = p.read_text(encoding="utf-8")
text = re.sub(r'label\("([^"]+)"\)\.style\(([^)]+)\)\.build\(\)', r'label("\1", \2)', text)
text = re.sub(r'label\("([^"]+)"\)\.build\(\)', r'label("\1")', text)
text = text.replace(
    'stackPane(700, 300, Pos.TOP_LEFT, label("1"), BG_1).padding(new Insets(20.0)).layoutX(100).layoutY(100).build()',
    'stackPane(700, 300, Pos.TOP_LEFT, label("1"), BG_1, new Insets(20.0), 100, 100)',
)
text = text.replace(
    'stackPane(200, 500, Pos.TOP_RIGHT, label("2"), BG_2).padding(new Insets(20.0)).layoutX(500).layoutY(50).build()',
    'stackPane(200, 500, Pos.TOP_RIGHT, label("2"), BG_2, new Insets(20.0), 500, 50)',
)
text = text.replace(
    'stackPane(300, 100, Pos.CENTER, label("3"), "-fx-background-color:#65AF23").layoutX(300).layoutY(250).build()',
    'stackPane(300, 100, Pos.CENTER, label("3"), "-fx-background-color:#65AF23", new Insets(0), 300, 250)',
)
text = text.replace(
    'stackPane(600, 50, Pos.CENTER, label("4"), BG_3).layoutX(150).layoutY(450).build()',
    'stackPane(600, 50, Pos.CENTER, label("4"), BG_3, new Insets(0), 150, 450)',
)
# generic stackPane(...).build()
while ").build()" in text and "stackPane(" in text:
    new = re.sub(r"stackPane\(([^()]*(?:\([^()]*\)[^()]*)*)\)\.build\(\)", r"stackPane(\1)", text, count=1)
    if new == text:
        break
    text = new
p.write_text(text, encoding="utf-8")
print("fixed")