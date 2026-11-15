from pathlib import Path

target = Path(
    r"D:\DEV\GIT\EclipseCON-France\org.jrebirth.af.presentation.eclipsecon"
    r"\src\main\java\org\jrebirth\af\presentation\eclipsecon\ui\slides\base\AbstractBaseView.java"
)
mid = Path(r"D:\DEV\GIT\JRebirth\tools\ec_abstract_base_mid.java.frag").read_text(encoding="utf-8")
lines = target.read_text(encoding="utf-8").splitlines(keepends=True)
target.write_text("".join(lines[:352]) + mid + "".join(lines[891:]), encoding="utf-8")
print("patched", target)