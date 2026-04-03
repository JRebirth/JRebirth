package org.jrebirth.af.tooling.text2fx.parser;

import org.jrebirth.af.tooling.text2fx.model.CollectionKind;
import org.jrebirth.af.tooling.text2fx.model.EntityDef;
import org.jrebirth.af.tooling.text2fx.model.EnumDef;
import org.jrebirth.af.tooling.text2fx.model.FieldDef;
import org.jrebirth.af.tooling.text2fx.model.Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ModelParser {

    public Model parse(Path path) throws IOException {
        return parse(Files.readAllLines(path));
    }

    public Model parse(List<String> lines) {
        Model model = new Model();

        EntityDef currentEntity = null;
        EnumDef currentEnum = null;
        String currentPackage = null;

        int lineNumber = 0;
        for (String rawLine : lines) {
            lineNumber++;
            String line = rawLine == null ? "" : rawLine;

            if (line.trim().isEmpty()) {
                continue;
            }

            String trimmed = line.trim();
            if (trimmed.startsWith("#")) {
                continue;
            }

            boolean indented = Character.isWhitespace(line.charAt(0));

            if (!indented) {
                currentEntity = null;
                currentEnum = null;

                if (trimmed.startsWith("package ")) {
                    currentPackage = trimmed.substring("package ".length()).trim();
                    if (currentPackage.isEmpty()) {
                        throw new IllegalArgumentException("Invalid package declaration at line " + lineNumber);
                    }
                    continue;
                }

                if (trimmed.startsWith("entity ")) {
                    String entityName = trimmed.substring("entity ".length()).trim();
                    if (entityName.isEmpty()) {
                        throw new IllegalArgumentException("Invalid entity declaration at line " + lineNumber);
                    }
                    currentEntity = new EntityDef(currentPackage, entityName);
                    model.getEntities().add(currentEntity);
                    continue;
                }

                if (trimmed.startsWith("enum ")) {
                    String enumName = trimmed.substring("enum ".length()).trim();
                    if (enumName.isEmpty()) {
                        throw new IllegalArgumentException("Invalid enum declaration at line " + lineNumber);
                    }
                    currentEnum = new EnumDef(currentPackage, enumName);
                    model.getEnums().add(currentEnum);
                    continue;
                }

                throw new IllegalArgumentException("Unknown top-level declaration at line " + lineNumber + ": " + trimmed);
            }

            if (currentEntity != null) {
                currentEntity.getFields().add(parseField(trimmed, lineNumber));
                continue;
            }

            if (currentEnum != null) {
                currentEnum.getLiterals().add(trimmed);
                continue;
            }

            throw new IllegalArgumentException("Indented line outside entity/enum at line " + lineNumber + ": " + trimmed);
        }

        return model;
    }

    private FieldDef parseField(String line, int lineNumber) {
        int colonIndex = line.indexOf(':');
        if (colonIndex <= 0) {
            throw new IllegalArgumentException("Invalid field syntax at line " + lineNumber + ": " + line);
        }

        String fieldName = line.substring(0, colonIndex).trim();
        String remainder = line.substring(colonIndex + 1).trim();

        String defaultValue = null;
        int eqIndex = remainder.indexOf('=');
        if (eqIndex >= 0) {
            defaultValue = remainder.substring(eqIndex + 1).trim();
            remainder = remainder.substring(0, eqIndex).trim();
        }

        String[] parts = remainder.split("\\s+");
        if (parts.length == 0 || parts[0].isBlank()) {
            throw new IllegalArgumentException("Missing field type at line " + lineNumber + ": " + line);
        }

        FieldDef field = new FieldDef();
        field.setName(fieldName);
        field.setDefaultValue(defaultValue);

        String rawType = parts[0].trim();
        if (rawType.startsWith("List<") && rawType.endsWith(">")) {
            field.setCollectionKind(CollectionKind.LIST);
            field.setType(rawType.substring(5, rawType.length() - 1));
        } else if (rawType.startsWith("Set<") && rawType.endsWith(">")) {
            field.setCollectionKind(CollectionKind.SET);
            field.setType(rawType.substring(4, rawType.length() - 1));
        } else {
            field.setCollectionKind(CollectionKind.NONE);
            field.setType(rawType);
        }

        for (int i = 1; i < parts.length; i++) {
            String modifier = parts[i];
            switch (modifier) {
                case "required" -> field.setRequired(true);
                case "id" -> field.setId(true);
                case "unique" -> field.setUnique(true);
                default -> throw new IllegalArgumentException("Unknown modifier '" + modifier + "' at line " + lineNumber + ": " + line);
            }
        }

        return field;
    }
}
