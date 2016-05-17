package org.jrebirth.af.component.ui.beans;

import org.jrebirth.af.api.ui.Model;

public class PartConfig<PC extends PartConfig<PC>> {

    private final Class<? extends Model> modelClass;

    private String key;

    public PartConfig(final Class<? extends Model> modelClass) {
        this.modelClass = modelClass;
    }

    public Class<? extends Model> modelClass() {
        return this.modelClass;
    }

    public String key() {
        return this.key;
    }

    @SuppressWarnings("unchecked")
    public PC key(final String key) {
        this.key = key;
        return (PC) this;
    }

}
