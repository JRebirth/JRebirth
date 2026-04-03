package org.jrebirth.af.tooling.text2fx.model;

public class FieldDef {
    private String name;
    private String type;
    private CollectionKind collectionKind = CollectionKind.NONE;
    private boolean required;
    private boolean id;
    private boolean unique;
    private String defaultValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CollectionKind getCollectionKind() {
        return collectionKind;
    }

    public void setCollectionKind(CollectionKind collectionKind) {
        this.collectionKind = collectionKind;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isId() {
        return id;
    }

    public void setId(boolean id) {
        this.id = id;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
