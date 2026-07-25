---
name: resources
description: >-
  Centralize shared JRebirth UI resources (images, fonts, styles, labels, sizes,
  colors) through resource enums and items. Use when adding assets or replacing
  magic constants and raw getResource strings.
---
<!-- gen from .mdh -->

# Create Resources

Centralize shared UI resources avoid magic constants

## Typical content

- label text keys
- image icon references
- CSS references
- spacing sizes colors

## Key API

- images: enum implementing `ImageEnum`, each entry calling `rel("basename", ImageExtension.PNG)`; read `.get()`.
- styles: `StyleSheetItem` `Resources.create(new StyleSheet("Main"))`.
- prefer typed resource enums over raw `getResource("/path")` strings so JPMS module access stays correct

Template: `templates/ResourcesTemplate.java.txt`.
