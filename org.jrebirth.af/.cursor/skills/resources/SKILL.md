---
name: resources
description: >-
  Centralize shared JRebirth UI resources (images, fonts, styles, labels, sizes,
  colors) through resource enums and items. Use when adding assets or replacing
  magic constants and raw getResource strings.
---
<!-- gen .mdh -->

# Create Resources

Centralize shared UI resources using typed JRebirth resource enums items Avoid magic constant strings raw `getResource("/path")` calls break under JPMS invisible compiler

## Typical content

- Label text keys internationalized or shared strings
- Image icon references
- CSS stylesheet references
- Spacing, size color constants

## Key API

- Images: create enum implementing `ImageEnum`. Each entry calls `rel("basename", ImageExtension.PNG)`. Load image runtime `.get()`.
- Stylesheets: create `StyleSheetItem` `Resources.create(new StyleSheet("Main"))`. Attach View `addCSS(StyleSheetItem)` or Application scene `addCSS(scene, StyleSheetItem)`.
- Prefer typed resource enums over raw `getResource("/path")` strings everywhere so JPMS module access remains correct paths validated compile time

Template: `templates/ResourcesTemplate.java.txt`.
