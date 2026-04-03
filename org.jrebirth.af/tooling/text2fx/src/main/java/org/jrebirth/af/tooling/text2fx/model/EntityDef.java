package org.jrebirth.af.tooling.text2fx.model;

import java.util.ArrayList;
import java.util.List;

public class EntityDef extends ModelElement {
    private final List<FieldDef> fields = new ArrayList<>();

    public EntityDef() {
    }

    public EntityDef(String packageName, String name) {
        setPackageName(packageName);
        setName(name);
    }

    public List<FieldDef> getFields() {
        return fields;
    }
}
