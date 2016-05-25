package org.jrebirth.af.core.ui.object;

import org.jrebirth.af.api.ui.Model;

public class ModelConfig<MC extends ModelConfig<MC>> {

    public static final String UNDETERMINED = "Undetermined";

    private String id = UNDETERMINED;

    private String style = UNDETERMINED;

    private String styleClass = UNDETERMINED;

    protected final Class<? extends Model> modelClass;

    public ModelConfig(final Class<? extends Model> modelClass) {
        this.modelClass = modelClass;
    }

    public Class<? extends Model> modelClass() {
        return this.modelClass;
    }

    public String id() {
        return this.id;
    }

    @SuppressWarnings("unchecked")
    public MC id(final String id) {
        this.id = id;
        return (MC) this;
    }

    public String style() {
        return this.style;
    }

    @SuppressWarnings("unchecked")
    public MC style(final String style) {
        this.style = style;
        return (MC) this;
    }

    public String styleClass() {
        return this.styleClass;
    }

    @SuppressWarnings("unchecked")
    public MC styleClass(final String styleClass) {
        this.styleClass = styleClass;
        return (MC) this;
    }

}
