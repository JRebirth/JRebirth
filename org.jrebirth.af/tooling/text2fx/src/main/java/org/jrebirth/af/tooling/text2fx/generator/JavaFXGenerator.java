package org.jrebirth.af.tooling.text2fx.generator;

import org.jrebirth.af.tooling.text2fx.model.CollectionKind;
import org.jrebirth.af.tooling.text2fx.model.EntityDef;
import org.jrebirth.af.tooling.text2fx.model.EnumDef;
import org.jrebirth.af.tooling.text2fx.model.FieldDef;
import org.jrebirth.af.tooling.text2fx.model.Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.TreeSet;

/**
 * JavaFX-style beans (lazy {@code Simple*}Property / {@code ObservableList} / {@code ObservableSet}).
 * Accessors: fluent {@code name()}/{@code name(value)} or JavaBean {@code getName}/{@code setName}/{@code isX}.
 */
public class JavaFXGenerator {

    private final boolean useAccessorPrefixes;

    public JavaFXGenerator() {
        this(false);
    }

    public JavaFXGenerator(boolean useAccessorPrefixes) {
        this.useAccessorPrefixes = useAccessorPrefixes;
    }

    public void generate(Model model, Path outputRoot) throws IOException {
        for (EnumDef enumDef : model.getEnums()) {
            Path packageDir = toPackageDir(outputRoot, enumDef.getPackageName());
            Files.createDirectories(packageDir);
            generateEnum(enumDef, packageDir);
        }

        for (EntityDef entity : model.getEntities()) {
            Path packageDir = toPackageDir(outputRoot, entity.getPackageName());
            Files.createDirectories(packageDir);
            generateEntity(entity, packageDir);
        }
    }

    private void generateEnum(EnumDef enumDef, Path packageDir) throws IOException {
        StringBuilder sb = new StringBuilder();
        appendPackage(enumDef.getPackageName(), sb);
        sb.append("public enum ").append(enumDef.getName()).append(" {\n");
        for (int i = 0; i < enumDef.getLiterals().size(); i++) {
            sb.append("    ").append(enumDef.getLiterals().get(i));
            sb.append(i < enumDef.getLiterals().size() - 1 ? ",\n" : ";\n");
        }
        sb.append("}\n");
        Files.writeString(packageDir.resolve(enumDef.getName() + ".java"), sb.toString());
    }

    private void generateEntity(EntityDef entity, Path packageDir) throws IOException {
        StringBuilder sb = new StringBuilder();
        appendPackage(entity.getPackageName(), sb);

        Set<String> imports = collectImports(entity);
        if (!imports.isEmpty()) {
            for (String imp : imports) {
                sb.append("import ").append(imp).append(";\n");
            }
            sb.append("\n");
        }

        sb.append("public class ").append(entity.getName()).append(" {\n\n");

        for (FieldDef field : entity.getFields()) {
            appendBackingFieldDeclaration(entity, field, sb);
            appendPropertyFieldDeclaration(field, sb);
        }

        sb.append("    public ").append(entity.getName()).append("() {\n");
        sb.append("    }\n\n");

        for (FieldDef field : entity.getFields()) {
            appendAccessors(entity, field, sb);
        }

        sb.append("}\n");
        Files.writeString(packageDir.resolve(entity.getName() + ".java"), sb.toString());
    }

    private void appendBackingFieldDeclaration(EntityDef entity, FieldDef field, StringBuilder sb) {
        sb.append("    private ").append(backingType(field)).append(" ").append(field.getName());
        String init = backingInitializer(field);
        if (init != null) {
            sb.append(" = ").append(init);
        }
        sb.append(";\n");
    }

    private void appendPropertyFieldDeclaration(FieldDef field, StringBuilder sb) {
        sb.append("    private ").append(javafxPropertyType(field)).append(" ").append(propertyFieldName(field.getName()));
        sb.append(";\n");
    }

    private void appendAccessors(EntityDef entity, FieldDef field, StringBuilder sb) {
        String name = field.getName();
        String backing = backingType(field);
        String propField = propertyFieldName(name);
        String propType = javafxPropertyType(field);
        String propertyMethod = name + "Property";
        String getter = getterMethodName(field, backing);
        String setter = setterMethodName(field);

        if (field.getCollectionKind() == CollectionKind.LIST) {
            sb.append("    public ").append(backing).append(" ").append(getter).append("() {\n");
            sb.append("        if (this.").append(propField).append(" != null) {\n");
            sb.append("            return this.").append(propField).append(".stream().collect(java.util.stream.Collectors.toList());\n");
            sb.append("        }\n");
            sb.append("        if (this.").append(name).append(" == null) {\n");
            sb.append("            this.").append(name).append(" = new java.util.ArrayList<>();\n");
            sb.append("        }\n");
            sb.append("        return this.").append(name).append(";\n");
            sb.append("    }\n\n");

            sb.append("    public ").append(entity.getName()).append(" ").append(setter).append("(").append(backing).append(" ").append(name).append(") {\n");
            sb.append("        if (this.").append(propField).append(" != null) {\n");
            sb.append("            this.").append(propField).append(".setAll(").append(name).append(");\n");
            sb.append("        }\n");
            sb.append("        this.").append(name).append(" = ").append(name).append(";\n");
            sb.append("        return this;\n");
            sb.append("    }\n\n");

            sb.append("    public ").append(propType).append(" ").append(propertyMethod).append("() {\n");
            sb.append("        if (this.").append(propField).append(" == null) {\n");
            sb.append("            this.").append(propField).append(" = javafx.collections.FXCollections.observableList(this.").append(getter).append("());\n");
            sb.append("        }\n");
            sb.append("        return this.").append(propField).append(";\n");
            sb.append("    }\n\n");
            return;
        }

        if (field.getCollectionKind() == CollectionKind.SET) {
            sb.append("    public ").append(backing).append(" ").append(getter).append("() {\n");
            sb.append("        if (this.").append(propField).append(" != null) {\n");
            sb.append("            return new java.util.LinkedHashSet<>(this.").append(propField).append(");\n");
            sb.append("        }\n");
            sb.append("        if (this.").append(name).append(" == null) {\n");
            sb.append("            this.").append(name).append(" = new java.util.LinkedHashSet<>();\n");
            sb.append("        }\n");
            sb.append("        return this.").append(name).append(";\n");
            sb.append("    }\n\n");

            sb.append("    public ").append(entity.getName()).append(" ").append(setter).append("(").append(backing).append(" ").append(name).append(") {\n");
            sb.append("        if (this.").append(propField).append(" != null) {\n");
            sb.append("            this.").append(propField).append(".clear();\n");
            sb.append("            this.").append(propField).append(".addAll(").append(name).append(");\n");
            sb.append("        }\n");
            sb.append("        this.").append(name).append(" = ").append(name).append(" != null ? new java.util.LinkedHashSet<>(").append(name).append(") : new java.util.LinkedHashSet<>();\n");
            sb.append("        return this;\n");
            sb.append("    }\n\n");

            sb.append("    public ").append(propType).append(" ").append(propertyMethod).append("() {\n");
            sb.append("        if (this.").append(propField).append(" == null) {\n");
            sb.append("            this.").append(propField).append(" = javafx.collections.FXCollections.observableSet(new java.util.LinkedHashSet<>(this.").append(getter).append("()));\n");
            sb.append("        }\n");
            sb.append("        return this.").append(propField).append(";\n");
            sb.append("    }\n\n");
            return;
        }

        sb.append("    public ").append(backing).append(" ").append(getter).append("() {\n");
        sb.append("        if (this.").append(propField).append(" != null) {\n");
        sb.append("            return this.").append(propField).append(".get();\n");
        sb.append("        }\n");
        sb.append("        return this.").append(name).append(";\n");
        sb.append("    }\n\n");

        sb.append("    public ").append(entity.getName()).append(" ").append(setter).append("(").append(backing).append(" ").append(name).append(") {\n");
        sb.append("        if (this.").append(propField).append(" != null) {\n");
        sb.append("            this.").append(propField).append(".set(").append(name).append(");\n");
        sb.append("        }\n");
        sb.append("        this.").append(name).append(" = ").append(name).append(";\n");
        sb.append("        return this;\n");
        sb.append("    }\n\n");

        sb.append("    public ").append(propType).append(" ").append(propertyMethod).append("() {\n");
        sb.append("        if (this.").append(propField).append(" == null) {\n");
        sb.append("            this.").append(propField).append(" = new ").append(javafxConcretePropertyType(field)).append("();\n");
        String def = propertyInitialSetValue(field);
        if (def != null) {
            sb.append("            this.").append(propField).append(".set(").append(def).append(");\n");
        }
        sb.append("        }\n");
        sb.append("        return this.").append(propField).append(";\n");
        sb.append("    }\n\n");
    }

    private String getterMethodName(FieldDef field, String backing) {
        String fname = field.getName();
        if (!useAccessorPrefixes) {
            return fname;
        }
        if (field.getCollectionKind() == CollectionKind.NONE && "boolean".equals(backing)) {
            return "is" + capitalize(fname);
        }
        return "get" + capitalize(fname);
    }

    private String setterMethodName(FieldDef field) {
        String fname = field.getName();
        if (!useAccessorPrefixes) {
            return fname;
        }
        return "set" + capitalize(fname);
    }

    private String propertyInitialSetValue(FieldDef field) {
        if (field.getDefaultValue() == null || field.getDefaultValue().isBlank()) {
            return "this." + field.getName();
        }
        String type = simpleName(field.getType());
        String value = field.getDefaultValue();
        return switch (type) {
            case "String", "boolean", "Boolean", "int", "Integer", "long", "Long", "double", "Double", "float", "Float" -> value;
            case "BigDecimal", "LocalDate", "LocalDateTime", "Instant" -> value;
            default -> type + "." + value;
        };
    }

    private void appendPackage(String packageName, StringBuilder sb) {
        if (packageName != null && !packageName.isBlank()) {
            sb.append("package ").append(packageName).append(";\n\n");
        }
    }

    private Set<String> collectImports(EntityDef entity) {
        Set<String> imports = new TreeSet<>();
        String currentPackage = entity.getPackageName();

        for (FieldDef field : entity.getFields()) {
            if (field.getCollectionKind() == CollectionKind.LIST) {
                imports.add("java.util.ArrayList");
                imports.add("java.util.List");
                imports.add("javafx.collections.FXCollections");
            } else if (field.getCollectionKind() == CollectionKind.SET) {
                imports.add("java.util.LinkedHashSet");
                imports.add("java.util.Set");
                imports.add("javafx.collections.FXCollections");
            }

            addJavafxImports(field, imports);

            String type = field.getType();
            switch (simpleName(type)) {
                case "BigDecimal" -> imports.add("java.math.BigDecimal");
                case "LocalDate" -> imports.add("java.time.LocalDate");
                case "LocalDateTime" -> imports.add("java.time.LocalDateTime");
                case "Instant" -> imports.add("java.time.Instant");
                default -> {
                    if (type.contains(".")) {
                        if (!type.startsWith("java.lang.") && !samePackage(currentPackage, packagePart(type))) {
                            imports.add(type);
                        }
                    }
                }
            }
        }
        return imports;
    }

    private void addJavafxImports(FieldDef field, Set<String> imports) {
        switch (field.getCollectionKind()) {
            case LIST -> imports.add("javafx.collections.ObservableList");
            case SET -> imports.add("javafx.collections.ObservableSet");
            case NONE -> {
                String simple = simpleName(field.getType());
                switch (simple) {
                    case "boolean", "Boolean" -> {
                        imports.add("javafx.beans.property.BooleanProperty");
                        imports.add("javafx.beans.property.SimpleBooleanProperty");
                    }
                    case "int", "Integer" -> {
                        imports.add("javafx.beans.property.IntegerProperty");
                        imports.add("javafx.beans.property.SimpleIntegerProperty");
                    }
                    case "long", "Long" -> {
                        imports.add("javafx.beans.property.LongProperty");
                        imports.add("javafx.beans.property.SimpleLongProperty");
                    }
                    case "float", "Float" -> {
                        imports.add("javafx.beans.property.FloatProperty");
                        imports.add("javafx.beans.property.SimpleFloatProperty");
                    }
                    case "double", "Double" -> {
                        imports.add("javafx.beans.property.DoubleProperty");
                        imports.add("javafx.beans.property.SimpleDoubleProperty");
                    }
                    case "String" -> {
                        imports.add("javafx.beans.property.StringProperty");
                        imports.add("javafx.beans.property.SimpleStringProperty");
                    }
                    default -> {
                        imports.add("javafx.beans.property.ObjectProperty");
                        imports.add("javafx.beans.property.SimpleObjectProperty");
                    }
                }
            }
        }
    }

    private String backingType(FieldDef field) {
        String simpleType = simpleName(field.getType());
        return switch (field.getCollectionKind()) {
            case LIST -> "List<" + simpleType + ">";
            case SET -> "Set<" + simpleType + ">";
            case NONE -> normalizePrimitiveWrapper(simpleType);
        };
    }

    private String normalizePrimitiveWrapper(String simpleType) {
        return switch (simpleType) {
            case "Integer" -> "int";
            case "Long" -> "long";
            case "Double" -> "double";
            case "Float" -> "float";
            case "Boolean" -> "boolean";
            default -> simpleType;
        };
    }

    private String backingInitializer(FieldDef field) {
        if (field.getCollectionKind() == CollectionKind.LIST) {
            return "new java.util.ArrayList<>()";
        }
        if (field.getCollectionKind() == CollectionKind.SET) {
            return "new java.util.LinkedHashSet<>()";
        }
        if (field.getDefaultValue() == null || field.getDefaultValue().isBlank()) {
            return null;
        }
        String type = simpleName(field.getType());
        String value = field.getDefaultValue();
        return switch (type) {
            case "String", "boolean", "Boolean", "int", "Integer", "long", "Long", "double", "Double", "float", "Float" -> value;
            case "BigDecimal", "LocalDate", "LocalDateTime", "Instant" -> value;
            default -> type + "." + value;
        };
    }

    private String javafxPropertyType(FieldDef field) {
        String simpleType = simpleName(field.getType());
        return switch (field.getCollectionKind()) {
            case LIST -> "ObservableList<" + simpleType + ">";
            case SET -> "ObservableSet<" + simpleType + ">";
            case NONE -> switch (normalizePrimitiveWrapper(simpleType)) {
                case "boolean" -> "BooleanProperty";
                case "int" -> "IntegerProperty";
                case "long" -> "LongProperty";
                case "float" -> "FloatProperty";
                case "double" -> "DoubleProperty";
                case "String" -> "StringProperty";
                default -> "ObjectProperty<" + simpleType + ">";
            };
        };
    }

    private String javafxConcretePropertyType(FieldDef field) {
        String simpleType = simpleName(field.getType());
        return switch (field.getCollectionKind()) {
            case LIST, SET -> throw new IllegalStateException();
            case NONE -> switch (normalizePrimitiveWrapper(simpleType)) {
                case "boolean" -> "SimpleBooleanProperty";
                case "int" -> "SimpleIntegerProperty";
                case "long" -> "SimpleLongProperty";
                case "float" -> "SimpleFloatProperty";
                case "double" -> "SimpleDoubleProperty";
                case "String" -> "SimpleStringProperty";
                default -> "SimpleObjectProperty<" + simpleType + ">";
            };
        };
    }

    private String propertyFieldName(String fieldName) {
        return "p" + capitalize(fieldName);
    }

    private Path toPackageDir(Path root, String packageName) {
        if (packageName == null || packageName.isBlank()) {
            return root;
        }
        return root.resolve(packageName.replace('.', '/'));
    }

    private String capitalize(String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        return Character.toUpperCase(value.charAt(0)) + value.substring(1);
    }

    private String simpleName(String qualifiedName) {
        int index = qualifiedName.lastIndexOf('.');
        return index >= 0 ? qualifiedName.substring(index + 1) : qualifiedName;
    }

    private String packagePart(String qualifiedName) {
        int index = qualifiedName.lastIndexOf('.');
        return index >= 0 ? qualifiedName.substring(0, index) : null;
    }

    private boolean samePackage(String left, String right) {
        if (left == null || left.isBlank()) {
            return right == null || right.isBlank();
        }
        return left.equals(right);
    }
}
