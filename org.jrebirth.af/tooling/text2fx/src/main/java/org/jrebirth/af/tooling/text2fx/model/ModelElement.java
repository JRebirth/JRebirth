package org.jrebirth.af.tooling.text2fx.model;

public abstract class ModelElement {
    private String packageName;
    private String name;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualifiedName() {
        if (packageName == null || packageName.isBlank()) {
            return name;
        }
        return packageName + "." + name;
    }
}
