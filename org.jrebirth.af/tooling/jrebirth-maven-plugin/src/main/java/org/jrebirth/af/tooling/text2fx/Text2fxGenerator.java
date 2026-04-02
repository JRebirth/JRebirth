package org.jrebirth.af.tooling.text2fx;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses the JRebirth {@code .model} text DSL and emits JavaFX property-based beans and enums.
 */
public final class Text2fxGenerator {

    private static final Pattern PACKAGE = Pattern.compile("^package\\s+([\\w.]+)\\s*$");
    private static final Pattern ENTITY = Pattern.compile("^entity\\s+(\\w+)\\s*$");
    private static final Pattern ENUM = Pattern.compile("^enum\\s+(\\w+)\\s*$");
    private static final Pattern FIELD = Pattern.compile("^\\s+(\\w+)\\s*:\\s*(.+)$");
    private static final Pattern ENUM_CONST = Pattern.compile("^\\s+(\\w+)\\s*$");
    private static final Pattern LIST_TYPE = Pattern.compile("^List<(\\w+)>\\s*$");

    public void generate(final Path modelFile, final Path outputRoot, final boolean javaFxAccessorPrefixes) throws IOException {
        final List<String> rawLines = Files.readAllLines(modelFile, StandardCharsets.UTF_8);
        final List<String> lines = new ArrayList<>();
        for (String line : rawLines) {
            final int hash = line.indexOf('#');
            if (hash >= 0) {
                line = line.substring(0, hash);
            }
            line = line.stripTrailing();
            if (!line.isBlank()) {
                lines.add(line);
            }
        }

        String pkg = null;
        final List<ParsedEnum> enums = new ArrayList<>();
        final List<ParsedEntity> entities = new ArrayList<>();

        ParsedEnum curEnum = null;
        ParsedEntity curEntity = null;

        for (final String line : lines) {
            final Matcher pm = PACKAGE.matcher(line);
            if (pm.matches()) {
                pkg = pm.group(1);
                continue;
            }
            final Matcher em = ENTITY.matcher(line);
            if (em.matches()) {
                curEnum = null;
                curEntity = new ParsedEntity(em.group(1));
                entities.add(curEntity);
                continue;
            }
            final Matcher nm = ENUM.matcher(line);
            if (nm.matches()) {
                curEntity = null;
                curEnum = new ParsedEnum(nm.group(1));
                enums.add(curEnum);
                continue;
            }
            if (curEnum != null) {
                final Matcher cm = ENUM_CONST.matcher(line);
                if (cm.matches()) {
                    curEnum.constants.add(cm.group(1));
                }
                continue;
            }
            if (curEntity != null) {
                final Matcher fm = FIELD.matcher(line);
                if (fm.matches()) {
                    curEntity.fields.add(parseField(fm.group(1), fm.group(2).trim()));
                }
            }
        }

        if (pkg == null || pkg.isBlank()) {
            throw new IOException("Model must declare a package line: package your.pkg.name");
        }

        final Path base = outputRoot.resolve(pkg.replace('.', '/'));
        Files.createDirectories(base);

        for (final ParsedEnum e : enums) {
            writeEnum(base.resolve(e.name + ".java"), pkg, e);
        }
        for (final ParsedEntity e : entities) {
            writeEntity(base.resolve(e.name + ".java"), pkg, e, javaFxAccessorPrefixes);
        }
    }

    private static ParsedField parseField(final String name, final String rhs) {
        String typePart = rhs;
        String defaultVal = null;
        final int eq = rhs.indexOf('=');
        if (eq >= 0) {
            typePart = rhs.substring(0, eq).trim();
            defaultVal = rhs.substring(eq + 1).trim();
        }
        return new ParsedField(name, typePart, defaultVal);
    }

    private static void writeEnum(final Path file, final String pkg, final ParsedEnum e) throws IOException {
        final StringBuilder sb = new StringBuilder();
        sb.append("package ").append(pkg).append(";\n\n");
        sb.append("public enum ").append(e.name).append(" {\n\n");
        for (int i = 0; i < e.constants.size(); i++) {
            sb.append("    ").append(e.constants.get(i));
            sb.append(i < e.constants.size() - 1 ? ",\n" : "\n");
        }
        sb.append("}\n");
        Files.writeString(file, sb.toString(), StandardCharsets.UTF_8);
    }

    private static void writeEntity(final Path file, final String pkg, final ParsedEntity e, final boolean javaFxAccessorPrefixes) throws IOException {
        final StringBuilder body = new StringBuilder();
        for (final ParsedField f : e.fields) {
            body.append(fieldMembersAndAccessors(f, e.name, javaFxAccessorPrefixes));
        }

        final StringBuilder sb = new StringBuilder();
        sb.append("package ").append(pkg).append(";\n\n");
        sb.append("import javafx.beans.property.*;\n");
        sb.append("import javafx.collections.FXCollections;\n");
        sb.append("import javafx.collections.ObservableList;\n\n");
        sb.append("/** Generated from text2fx (.model DSL). */\n");
        sb.append("public class ").append(e.name).append(" {\n\n");
        sb.append(body);
        sb.append("}\n");
        Files.writeString(file, sb.toString(), StandardCharsets.UTF_8);
    }

    /**
     * @param selfType simple name of the entity (for fluent setters returning {@code this})
     * @param javaFxAccessorPrefixes {@code true} → JavaBean {@code getFoo}/{@code setFoo}; {@code false} → {@code foo()}/{@code foo(v)} returning {@code this}
     */
    private static String fieldMembersAndAccessors(final ParsedField f, final String selfType, final boolean javaFxAccessorPrefixes) {
        if (!javaFxAccessorPrefixes) {
            return fluentAccessors(f, selfType);
        }
        return beanAccessors(f);
    }

    /** Record-style names + chained mutators (ezDojo / {@code javaFxAccessorPrefixes=false}). */
    private static String fluentAccessors(final ParsedField f, final String selfType) {
        final String cap = capitalize(f.name);
        final StringBuilder sb = new StringBuilder();

        if ("String".equals(f.typeName)) {
            final String init = stringInit(f.defaultValue);
            sb.append("    private final StringProperty ").append(f.name).append(" = new SimpleStringProperty(").append(init).append(");\n\n");
            sb.append("    public StringProperty ").append(f.name).append("Property() {\n        return ").append(f.name).append(";\n    }\n\n");
            sb.append("    public String ").append(f.name).append("() {\n        return ").append(f.name).append(".get();\n    }\n\n");
            sb.append("    public ").append(selfType).append(" ").append(f.name).append("(final String v) {\n        ").append(f.name).append(".set(v);\n        return this;\n    }\n\n");
            return sb.toString();
        }
        if ("boolean".equals(f.typeName)) {
            final String init = boolInit(f.defaultValue);
            sb.append("    private final BooleanProperty ").append(f.name).append(" = new SimpleBooleanProperty(").append(init).append(");\n\n");
            sb.append("    public BooleanProperty ").append(f.name).append("Property() {\n        return ").append(f.name).append(";\n    }\n\n");
            sb.append("    public boolean ").append(f.name).append("() {\n        return ").append(f.name).append(".get();\n    }\n\n");
            sb.append("    public ").append(selfType).append(" ").append(f.name).append("(final boolean v) {\n        ").append(f.name).append(".set(v);\n        return this;\n    }\n\n");
            return sb.toString();
        }
        if ("int".equals(f.typeName)) {
            final String init = intInit(f.defaultValue);
            sb.append("    private final IntegerProperty ").append(f.name).append(" = new SimpleIntegerProperty(").append(init).append(");\n\n");
            sb.append("    public IntegerProperty ").append(f.name).append("Property() {\n        return ").append(f.name).append(";\n    }\n\n");
            sb.append("    public int ").append(f.name).append("() {\n        return ").append(f.name).append(".get();\n    }\n\n");
            sb.append("    public ").append(selfType).append(" ").append(f.name).append("(final int v) {\n        ").append(f.name).append(".set(v);\n        return this;\n    }\n\n");
            return sb.toString();
        }
        if ("double".equals(f.typeName)) {
            final String init = doubleInit(f.defaultValue);
            sb.append("    private final DoubleProperty ").append(f.name).append(" = new SimpleDoubleProperty(").append(init).append(");\n\n");
            sb.append("    public DoubleProperty ").append(f.name).append("Property() {\n        return ").append(f.name).append(";\n    }\n\n");
            sb.append("    public double ").append(f.name).append("() {\n        return ").append(f.name).append(".get();\n    }\n\n");
            sb.append("    public ").append(selfType).append(" ").append(f.name).append("(final double v) {\n        ").append(f.name).append(".set(v);\n        return this;\n    }\n\n");
            return sb.toString();
        }

        final Matcher lm = LIST_TYPE.matcher(f.typeName);
        if (lm.matches()) {
            final String elem = lm.group(1);
            sb.append("    private final ListProperty<").append(elem).append("> ").append(f.name)
              .append(" = new SimpleListProperty<>(FXCollections.observableArrayList());\n\n");
            sb.append("    public ListProperty<").append(elem).append("> ").append(f.name).append("Property() {\n        return ").append(f.name).append(";\n    }\n\n");
            return sb.toString();
        }

        final String ref = f.typeName;
        final String initExpr = objectInitExpr(f.defaultValue);
        sb.append("    private final ObjectProperty<").append(ref).append("> ").append(f.name).append(" = ");
        if (initExpr.isEmpty()) {
            sb.append("new SimpleObjectProperty<>();\n\n");
        } else {
            sb.append("new SimpleObjectProperty<>(").append(initExpr).append(");\n\n");
        }
        sb.append("    public ObjectProperty<").append(ref).append("> ").append(f.name).append("Property() {\n        return ").append(f.name).append(";\n    }\n\n");
        sb.append("    public ").append(ref).append(" ").append(f.name).append("() {\n        return ").append(f.name).append(".get();\n    }\n\n");
        sb.append("    public ").append(selfType).append(" ").append(f.name).append("(final ").append(ref).append(" v) {\n        ").append(f.name).append(".set(v);\n        return this;\n    }\n\n");
        return sb.toString();
    }

    private static String beanAccessors(final ParsedField f) {
        final String cap = capitalize(f.name);
        final StringBuilder sb = new StringBuilder();

        if ("String".equals(f.typeName)) {
            final String init = stringInit(f.defaultValue);
            sb.append("    private final StringProperty ").append(f.name).append(" = new SimpleStringProperty(").append(init).append(");\n\n");
            sb.append("    public StringProperty ").append(f.name).append("Property() {\n        return ").append(f.name).append(";\n    }\n\n");
            sb.append("    public String get").append(cap).append("() {\n        return ").append(f.name).append(".get();\n    }\n\n");
            sb.append("    public void set").append(cap).append("(final String v) {\n        ").append(f.name).append(".set(v);\n    }\n\n");
            return sb.toString();
        }
        if ("boolean".equals(f.typeName)) {
            final String init = boolInit(f.defaultValue);
            sb.append("    private final BooleanProperty ").append(f.name).append(" = new SimpleBooleanProperty(").append(init).append(");\n\n");
            sb.append("    public BooleanProperty ").append(f.name).append("Property() {\n        return ").append(f.name).append(";\n    }\n\n");
            sb.append("    public boolean get").append(cap).append("() {\n        return ").append(f.name).append(".get();\n    }\n\n");
            sb.append("    public void set").append(cap).append("(final boolean v) {\n        ").append(f.name).append(".set(v);\n    }\n\n");
            return sb.toString();
        }
        if ("int".equals(f.typeName)) {
            final String init = intInit(f.defaultValue);
            sb.append("    private final IntegerProperty ").append(f.name).append(" = new SimpleIntegerProperty(").append(init).append(");\n\n");
            sb.append("    public IntegerProperty ").append(f.name).append("Property() {\n        return ").append(f.name).append(";\n    }\n\n");
            sb.append("    public int get").append(cap).append("() {\n        return ").append(f.name).append(".get();\n    }\n\n");
            sb.append("    public void set").append(cap).append("(final int v) {\n        ").append(f.name).append(".set(v);\n    }\n\n");
            return sb.toString();
        }
        if ("double".equals(f.typeName)) {
            final String init = doubleInit(f.defaultValue);
            sb.append("    private final DoubleProperty ").append(f.name).append(" = new SimpleDoubleProperty(").append(init).append(");\n\n");
            sb.append("    public DoubleProperty ").append(f.name).append("Property() {\n        return ").append(f.name).append(";\n    }\n\n");
            sb.append("    public double get").append(cap).append("() {\n        return ").append(f.name).append(".get();\n    }\n\n");
            sb.append("    public void set").append(cap).append("(final double v) {\n        ").append(f.name).append(".set(v);\n    }\n\n");
            return sb.toString();
        }

        final Matcher lm = LIST_TYPE.matcher(f.typeName);
        if (lm.matches()) {
            final String elem = lm.group(1);
            sb.append("    private final ListProperty<").append(elem).append("> ").append(f.name)
              .append(" = new SimpleListProperty<>(FXCollections.observableArrayList());\n\n");
            sb.append("    public ListProperty<").append(elem).append("> ").append(f.name).append("Property() {\n        return ").append(f.name).append(";\n    }\n\n");
            sb.append("    public ObservableList<").append(elem).append("> get").append(cap).append("() {\n        return ").append(f.name).append(".get();\n    }\n\n");
            sb.append("    public void set").append(cap).append("(final ObservableList<").append(elem).append("> v) {\n        ").append(f.name).append(".set(v);\n    }\n\n");
            return sb.toString();
        }

        final String ref = f.typeName;
        final String initExpr = objectInitExpr(f.defaultValue);
        sb.append("    private final ObjectProperty<").append(ref).append("> ").append(f.name).append(" = ");
        if (initExpr.isEmpty()) {
            sb.append("new SimpleObjectProperty<>();\n\n");
        } else {
            sb.append("new SimpleObjectProperty<>(").append(initExpr).append(");\n\n");
        }
        sb.append("    public ObjectProperty<").append(ref).append("> ").append(f.name).append("Property() {\n        return ").append(f.name).append(";\n    }\n\n");
        sb.append("    public ").append(ref).append(" get").append(cap).append("() {\n        return ").append(f.name).append(".get();\n    }\n\n");
        sb.append("    public void set").append(cap).append("(final ").append(ref).append(" v) {\n        ").append(f.name).append(".set(v);\n    }\n\n");
        return sb.toString();
    }

    private static String capitalize(final String s) {
        if (s.isEmpty()) {
            return s;
        }
        return s.substring(0, 1).toUpperCase(Locale.ROOT) + s.substring(1);
    }

    /** Java source expression (quoted) for {@code new SimpleStringProperty(...)}. */
    private static String stringInit(final String defaultValue) {
        if (defaultValue == null || defaultValue.isBlank()) {
            return "\"\"";
        }
        return escapeStringLiteral(defaultValue);
    }

    private static String escapeStringLiteral(final String s) {
        return "\"" + s.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
    }

    private static String boolInit(final String defaultValue) {
        if (defaultValue == null) {
            return "false";
        }
        return Boolean.parseBoolean(defaultValue) ? "true" : "false";
    }

    private static String intInit(final String defaultValue) {
        if (defaultValue == null) {
            return "0";
        }
        return String.valueOf(Integer.parseInt(defaultValue.trim()));
    }

    private static String doubleInit(final String defaultValue) {
        if (defaultValue == null) {
            return "0.0d";
        }
        return Double.parseDouble(defaultValue.trim()) + "d";
    }

    /** Java expression inside {@code new SimpleObjectProperty<>(...)}; empty for no-arg constructor. */
    private static String objectInitExpr(final String defaultValue) {
        if (defaultValue == null || defaultValue.isBlank()) {
            return "";
        }
        return defaultValue.trim();
    }

    private static final class ParsedEntity {
        final String name;
        final List<ParsedField> fields = new ArrayList<>();

        ParsedEntity(final String name) {
            this.name = name;
        }
    }

    private static final class ParsedEnum {
        final String name;
        final List<String> constants = new ArrayList<>();

        ParsedEnum(final String name) {
            this.name = name;
        }
    }

    private static final class ParsedField {
        final String name;
        final String typeName;
        final String defaultValue;

        ParsedField(final String name, final String typeName, final String defaultValue) {
            this.name = name;
            this.typeName = typeName;
            this.defaultValue = defaultValue;
        }
    }
}
