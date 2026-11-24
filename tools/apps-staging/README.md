# Local staging for `apps.jrebirth.org`

This folder is **upload output only**. Do **not** commit ZIPs, jlink trees, or copied site HTML here.

Build portable apps from their project `target/` trees (or showcase `-Ppackage-portable`), then run:

```powershell
powershell -File tools/stage-jrebirth-apps.ps1
```

Publish with Hosting `publish-jrebirth-to-dmz.ps1` (`-AppsStagingDir` pointing here).