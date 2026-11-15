#!/usr/bin/env python3
"""Copy already-migrated JAF tour view sources into another presentation module."""
from __future__ import annotations

import sys
from pathlib import Path

MAPPINGS = [
    ("org.jrebirth.af.tour.ui.slides.base", "org.jrebirth.af.presentation.lightningtalk.ui.slides.base"),
    ("org.jrebirth.af.tour.ui.slides.basic", "org.jrebirth.af.presentation.lightningtalk.ui.slides.basic"),
    ("org.jrebirth.af.tour.resources.JpFonts", "org.jrebirth.af.presentation.lightningtalk.resources.LtFonts"),
    ("org.jrebirth.af.tour.resources.JpImages", "org.jrebirth.af.presentation.lightningtalk.resources.LtImages"),
    ("JpFonts", "LtFonts"),
    ("JpImages.JREBIRTH_LOGO", "LtImages.PLACE_LOGO"),
    ("private ImageView logo;", "private ImageView placeLogo;"),
    ("this.logo", "this.placeLogo"),
    (
        "this.secondaryTitle.setTextFill(PrezColors.SUB_TITLE.get());",
        "this.secondaryTitle.setTextFill(PrezColors.SLIDE_TITLE.get());",
    ),
    ("this.secondaryTitle.setLayoutX(450);", "this.secondaryTitle.setLayoutX(110);"),
    ("this.secondaryTitle.setLayoutY(78);", "this.secondaryTitle.setLayoutY(80);"),
    ("this.placeLogo.setLayoutY(675);", "this.placeLogo.setLayoutY(700);"),
    ("tt.setToX(-470);", "tt.setToX(-400);"),
    (
        "new KeyValue(this.topRectangle.widthProperty(), 50 + this.primaryTitle.getLayoutBounds()\n"
        "                                                                                                                                          .getWidth())));",
        "new KeyValue(this.topRectangle.widthProperty(), 300))),",
    ),
]

HEADER_PREZ = """
        this.prezTitle = new Label();
        this.prezTitle.setFont(LtFonts.PREZ_TITLE.get());
        this.prezTitle.setTextFill(Color.LIGHTGRAY);
        this.prezTitle.setLayoutX(480);
        this.prezTitle.setLayoutY(14.0);
        this.prezTitle.setMinWidth(450);
        this.prezTitle.setAlignment(Pos.CENTER_RIGHT);
        this.prezTitle.setTextAlignment(TextAlignment.RIGHT);
"""


def port(jaf: Path, dest: Path) -> None:
    text = jaf.read_text(encoding="utf-8")
    for old, new in MAPPINGS:
        text = text.replace(old, new)
    # Uncomment prezTitle in header children list
    text = text.replace(
        "pl, this.pageLabel/*\n                                                           * , this.prezTitle\n                                                           */);",
        "pl, this.pageLabel, this.prezTitle);",
    )
    # Insert prezTitle init before placeLogo in getHeaderPanel
    marker = "        this.placeLogo = new ImageView();"
    if marker in text and HEADER_PREZ.strip() not in text:
        text = text.replace(marker, HEADER_PREZ + "\n" + marker)
    dest.write_text(text, encoding="utf-8")
    print(f"Wrote {dest}")


def main() -> int:
    jaf_root = Path(r"D:\DEV\GIT\JRebirth-Tour\org.jrebirth.af.presentation.jaf\src\main\java\org\jrebirth\af\tour\ui\slides")
    lt_root = Path(r"D:\DEV\GIT\LightningTalk\org.jrebirth.af.presentation.lightningtalk\src\main\java\org\jrebirth\af\presentation\lightningtalk\ui\slides")
    port(jaf_root / "base" / "AbstractBaseView.java", lt_root / "base" / "AbstractBaseView.java")
    port(jaf_root / "basic" / "BasicView.java", lt_root / "basic" / "BasicView.java")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())