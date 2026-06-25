---
name: javafx-css-ref
description: >-
  Verify JavaFX CSS rules, selectors, pseudo-classes, and property names against
  the official reference. Use when the user asks to validate or fix JavaFX CSS
  or inline JavaFX styles.
---
<!-- gen .mdh -->

# JavaFX CSS reference checker

Use when user asks verify JavaFX CSS rules against official documentation

## Source truth

- https://openjfx.io/javadoc/26/javafx.graphics/javafx/scene/doc-files/cssref.html

## Workflow

1. Read target CSS or inline JavaFX style user files
2. Fetch JavaFX CSS reference page
3. Check each requested rule against reference: selector validity property name value syntax control-specific scope
4. Return findings three blocks: valid invalid or doubtful concrete corrections
5. If version ambiguous ask JavaFX version target default reference URL version

## Output style

- keep answers short actionable
- quote exact CSS fragment when reporting issue
- prefer minimal corrections preserve user intent
