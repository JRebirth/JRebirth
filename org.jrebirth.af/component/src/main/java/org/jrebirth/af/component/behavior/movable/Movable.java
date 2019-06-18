package org.jrebirth.af.component.behavior.movable;

import java.io.Serializable;

import org.jrebirth.af.api.component.behavior.annotation.BehaviorDataFor;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.core.component.behavior.BehaviorDataBase;

@BehaviorDataFor(MovableBehavior.class)
public class Movable extends BehaviorDataBase implements Serializable {

    private static final long serialVersionUID = 894007787531052620L;
    
    private Model model;

    public static Movable of() {
        return new Movable();
    }

    public Model model() {
        return this.model;
    }

    public Movable model(final Model model) {
        this.model = model;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append(" [");
        sb.append(this.model).append(", ");
        sb.append("]");
        return sb.toString();
    }

}
