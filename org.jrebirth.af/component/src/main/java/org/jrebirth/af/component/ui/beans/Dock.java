package org.jrebirth.af.component.ui.beans;

import java.io.Serializable;

import org.jrebirth.af.component.ui.Dockable;
import org.jrebirth.af.core.key.UniqueKey;

public class Dock implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8111826206258276488L;

    private String name;

    private UniqueKey<? extends Dockable> modelKey;

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

    public UniqueKey<? extends Dockable> modelKey() {
        return this.modelKey;
    }

    public Dock modelKey(final UniqueKey<? extends Dockable> modelKey) {
        this.modelKey = modelKey;
        return this;
    }

}
