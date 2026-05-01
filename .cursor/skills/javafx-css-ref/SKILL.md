# JavaFX CSS reference checker

Use this skill when the user asks to verify JavaFX CSS rules, selectors, pseudo-classes, or property names against official documentation.

Source of truth:
- https://openjfx.io/javadoc/26/javafx.graphics/javafx/scene/doc-files/cssref.html

Workflow:
1. Read the target CSS (or CSS-like JavaFX inline style) from user files.
2. Fetch the JavaFX CSS reference page.
3. Check each requested rule against the reference:
   - selector validity
   - property name validity
   - value syntax or supported value types
   - control-specific scope (for example if a property only applies to a specific node/control family)
4. Return findings in three blocks:
   - valid rules
   - invalid or doubtful rules
   - concrete corrections with JavaFX-compatible alternatives
5. If the request is ambiguous, ask which JavaFX version should be targeted. Default to the provided URL version.

Output style:
- Keep answers short and actionable.
- Quote exact CSS rule fragments from user code when reporting issues.
- Prefer minimal corrections that preserve the user intent.
