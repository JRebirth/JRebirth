---
name: javafx-css-ref
description: >-
  Verify JavaFX CSS rules, selectors, pseudo-classes, and property names against
  the official reference. Use when the user asks to validate or fix JavaFX CSS
  or inline JavaFX styles.
---
<!-- gen .mdh -->

# JavaFX CSS Reference Checker

Use skill when user asks verify fix or validate JavaFX CSS rules against official JavaFX CSS documentation JavaFX CSS not standard web CSS — properties selectors value syntax differ significantly

## Source truth

The official JavaFX CSS reference available at:

- https://openjfx.io/javadoc/26/javafx.graphics/javafx/scene/doc-files/cssref.html

Always fetch live reference page rather than relying training data since reference changes between JavaFX versions

## Workflow

1. Read target CSS file or inline JavaFX style user's files
2. Fetch JavaFX CSS reference page URL above
3. Check each rule against reference: selector validity property name spelling value syntax whether property applies control type styled
4. Report findings three blocks: valid rules invalid or doubtful rules reasons concrete corrections corrected CSS
5. If JavaFX version ambiguous ask version target Default version reference URL

## Output style

- Keep answers short actionable
- Quote exact CSS fragment when reporting issue
- Prefer minimal corrections preserve user's intent
