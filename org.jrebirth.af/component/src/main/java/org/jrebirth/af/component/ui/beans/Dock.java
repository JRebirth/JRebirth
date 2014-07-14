package org.jrebirth.af.component.ui.beans;

import java.io.Serializable;

import org.jrebirth.af.core.key.UniqueKey;
import org.jrebirth.af.core.ui.Model;

public class Dock implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8111826206258276488L;

    private String name;

    private UniqueKey<? extends Model> modelKey;

    public static Dock create() {
        return new Dock();
    }

    public String name() {
        return this.name;
    }

    public Dock name(final String name) {
        this.name = name;
        return this;
    }

    public UniqueKey<? extends Model> modelKey() {
        return this.modelKey;
    }

    public Dock modelKey(final UniqueKey<? extends Model> modelKey) {
        this.modelKey = modelKey;
        return this;
    }

}
