package org.jrebirth.af.core.ui.object;

import org.jrebirth.af.api.ui.Model;

public class ModelConfig<M extends Model, MC extends ModelConfig<M, MC>> {

    public static final String UNDETERMINED = "Undetermined";

    private String id = UNDETERMINED;

    private String style = UNDETERMINED;

    private String styleClass = UNDETERMINED;

    protected final Class<M> modelClass;

    public ModelConfig(final Class<M> modelClass) {
        this.modelClass = modelClass;
    }

    public Class<M> modelClass() {
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
