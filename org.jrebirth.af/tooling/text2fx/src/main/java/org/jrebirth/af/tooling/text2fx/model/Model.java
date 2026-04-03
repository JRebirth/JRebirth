package org.jrebirth.af.tooling.text2fx.model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private final List<EntityDef> entities = new ArrayList<>();
    private final List<EnumDef> enums = new ArrayList<>();

    public List<EntityDef> getEntities() {
        return entities;
    }

    public List<EnumDef> getEnums() {
        return enums;
    }
}
