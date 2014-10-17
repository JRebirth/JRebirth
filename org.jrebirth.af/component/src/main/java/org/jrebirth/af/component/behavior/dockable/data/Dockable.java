package org.jrebirth.af.component.behavior.dockable.data;

import java.io.Serializable;

import org.jrebirth.af.component.behavior.dockable.DockableBehavior;
import org.jrebirth.af.core.behavior.annotation.BehaviorDataFor;
import org.jrebirth.af.core.behavior.data.base.BehaviorDataBase;
import org.jrebirth.af.core.key.UniqueKey;
import org.jrebirth.af.core.ui.Model;

@BehaviorDataFor(DockableBehavior.class)
public class Dockable extends BehaviorDataBase implements Serializable {

    /** The generated serial version uid. */
    private static final long serialVersionUID = 8137109665415403740L;

    private String name;

    private UniqueKey<? extends Model> modelKey;

    public static Dockable create() {
        return new Dockable();
    }

    public String name() {
        return this.name;
    }

    public Dockable name(final String name) {
        this.name = name;
        return this;
    }

    public UniqueKey<? extends Model> modelKey() {
        return this.modelKey;
    }

    public Dockable modelKey(final UniqueKey<? extends Model> modelKey) {
        this.modelKey = modelKey;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append(" [");
        sb.append(this.name).append(" ").append(this.modelKey.getKey()).append("]");
        return sb.toString();
    }

}
