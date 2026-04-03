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

public class JavaGenerator {

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
            sb.append("    private ").append(javaType(field)).append(" ").append(field.getName());
            String javaDefaultValue = javaDefaultValue(field);
            if (javaDefaultValue != null) {
                sb.append(" = ").append(javaDefaultValue);
            }
            sb.append(";\n");
        }

        sb.append("\n");
        sb.append("    public ").append(entity.getName()).append("() {\n");
        sb.append("    }\n\n");

        for (FieldDef field : entity.getFields()) {
            String javaType = javaType(field);
            String capitalized = capitalize(field.getName());
            String getterPrefix = "boolean".equals(javaType) ? "is" : "get";

            sb.append("    public ").append(javaType).append(" ").append(getterPrefix).append(capitalized).append("() {\n");
            sb.append("        return ").append(field.getName()).append(";\n");
            sb.append("    }\n\n");

            sb.append("    public void set").append(capitalized).append("(").append(javaType).append(" ").append(field.getName()).append(") {\n");
            sb.append("        this.").append(field.getName()).append(" = ").append(field.getName()).append(";\n");
            sb.append("    }\n\n");
        }

        sb.append("}\n");
        Files.writeString(packageDir.resolve(entity.getName() + ".java"), sb.toString());
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
            } else if (field.getCollectionKind() == CollectionKind.SET) {
                imports.add("java.util.LinkedHashSet");
                imports.add("java.util.Set");
            }

            String type = field.getType();
            switch (simpleName(type)) {
                case "BigDecimal" -> imports.add("java.math.BigDecimal");
                case "LocalDate" -> imports.add("java.time.LocalDate");
                case "LocalDateTime" -> imports.add("java.time.LocalDateTime");
                case "Instant" -> imports.add("java.time.Instant");
                default -> {
                    if (type.contains(".")) {
                        String normalized = type;
                        if (!normalized.startsWith("java.lang.") && !samePackage(currentPackage, packagePart(normalized))) {
                            imports.add(normalized);
                        }
                    }
                }
            }
        }

        return imports;
    }

    private String javaType(FieldDef field) {
        String simpleType = simpleName(field.getType());
        return switch (field.getCollectionKind()) {
            case LIST -> "List<" + simpleType + ">";
            case SET -> "Set<" + simpleType + ">";
            case NONE -> simpleType;
        };
    }

    private String javaDefaultValue(FieldDef field) {
        if (field.getCollectionKind() == CollectionKind.LIST) {
            return "new ArrayList<>()";
        }
        if (field.getCollectionKind() == CollectionKind.SET) {
            return "new LinkedHashSet<>()";
        }
        if (field.getDefaultValue() == null || field.getDefaultValue().isBlank()) {
            return null;
        }

        String type = simpleName(field.getType());
        String value = field.getDefaultValue();

        return switch (type) {
            case "String", "boolean", "Boolean", "int", "Integer", "long", "Long", "double", "Double" -> value;
            case "BigDecimal", "LocalDate", "LocalDateTime", "Instant" -> value;
            default -> type + "." + value;
        };
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
