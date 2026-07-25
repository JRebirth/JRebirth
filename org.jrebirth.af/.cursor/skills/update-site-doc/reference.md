<!-- gen from .mdh -->

# Site documentation reference

Detailed reference update-site-doc skill

## Page template

```velocity
<head>
<![CDATA[
    <title>PAGE TITLE</title>
    <link rel="stylesheet" type="text/css" href="../css/shCoreEclipse.css" media="all"/>
]]>
</head>

<div id="catcherTitle">SHORT TITLE</div>
<div id="catcherContent">One-line description</div>

#if(!$all)
<!-- MACRO{toc|section=0|fromDepth=1|toDepth=4|class=toc} -->
#end

#[[

# Page Title

## Section

Text and code snippets here.

]]#

#if( !$all )
#parse("TocList.vm")
#displayFooterToc("FILENAME")
#end
```

`FILENAME` file name without `.md.vm` extension (for example `Commands`).

## Including live code snippets

Use doxia-include-macro inside `#[[ ]]#` block:

```html
<!-- MACRO{include|source=PATH|snippet=SELECTOR|highlight-theme=eclipse} -->
```

Source path relative project root example `core/src/main/java/org/jrebirth/af/core/...`.

### Snippet selectors

| Syntax | Meaning | Example |
|--------|---------|---------|
| `aj:..ClassName.method(..)` | AspectJ pointcut | `aj:..SampleApplication.main(..)` |
| `aj:ClassName` | Whole class body | `aj:SampleModel` |
| `re:(regex)` | First line matching regex | `re:(@RunInto)` |
| `xp:xpath` | XPath in XML | `xp:dependencies/dependency[1]` |

### Extra parameters

- `snippet-start-offset=N` skip N lines after match
- `snippet-end-offset=N` include N extra lines after match
- `highlight-theme=eclipse` syntax highlighting
- `highlight-lines=grep:pattern` highlight matching lines
- `first-line=1` include first line no snippet filter

## Adding new page

1. Create `src/site/markdown/doc/NewPage.md.vm` using template above
2. Add entry `TocList.vm`:
   ```velocity
   {"link":"NewPage", "name":"Display Name"},
   ```
3. Add `site.xml` Documentation menu:
   ```xml
   <item name="Display Name" href="doc/NewPage.html" />
   ```
4. Add `jrebirth-book.xml` appropriate chapter:
   ```xml
   <section><id>NewPage</id></section>
   ```

## Module-to-page mapping

| Module | Doc page |
|--------|----------|
| `api` + `core` | Overview, Component, Facades, Thread, Wave, Notifier |
| `core/application` | Application |
| `core/command` | Commands |
| `core/service` | Services |
| `core/ui` | Ui |
| `core/resource` | Resources |
| `core/concurrent` | Thread |
| `core/log` | Logging |
| `component` | ComponentLibrary |
| `undoredo` | UndoRedo |
| `transition` | Transitions |
| `rest` | REST |
| `security` | Security |
| `dialog` | Dialog |
| `form` | Form |
| `iconfont-bridge` | IconFont |
| `processor` + `tooling` | Tooling |
| `core/ui/fxml` | FXML |
| `core/module` | Modularization |
| `core/component/behavior` | Behavior |
