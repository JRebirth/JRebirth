package org.jrebirth.af.tooling.text2fx.model;

import java.util.ArrayList;
import java.util.List;

public class EnumDef extends ModelElement {
    private final List<String> literals = new ArrayList<>();

    public EnumDef() {
    }

    public EnumDef(String packageName, String name) {
        setPackageName(packageName);
        setName(name);
    }

    public List<String> getLiterals() {
        return literals;
    }
}
