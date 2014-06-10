package org.jrebirth.af.component.ui.beans;

import java.io.Serializable;

import org.jrebirth.af.component.ui.Dockable;
import org.jrebirth.af.core.behavior.BehaviorBean;
import org.jrebirth.af.core.key.UniqueKey;
import org.jrebirth.af.core.ui.Model;

public class TabBB<M extends Model & Dockable> implements BehaviorBean, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8137109665415403740L;

    private String name;

    private UniqueKey<M> modelKey;

    @SuppressWarnings("rawtypes")
    public static TabBB create() {
        return new TabBB();
    }

    public String name() {
        return this.name;
    }

    public TabBB<M> name(final String name) {
        this.name = name;
        return this;
    }

    public UniqueKey<M> modelKey() {
        return this.modelKey;
    }

    public TabBB<M> modelKey(final UniqueKey<M> modelKey) {
        this.modelKey = modelKey;
        return this;
    }

}
