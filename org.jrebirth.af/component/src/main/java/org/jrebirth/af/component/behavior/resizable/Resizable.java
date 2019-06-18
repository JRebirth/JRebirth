package org.jrebirth.af.component.behavior.resizable;

import java.io.Serializable;

import javafx.scene.shape.Shape;

import org.jrebirth.af.api.component.behavior.annotation.BehaviorDataFor;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.core.component.behavior.BehaviorDataBase;

@BehaviorDataFor(ResizableBehavior.class)
public class Resizable extends BehaviorDataBase implements Serializable {

    private static final long serialVersionUID = 3837892379740938350L;

    private Model model;

    private Shape shape;

    public static Resizable of() {
        return new Resizable();
    }

    public Model model() {
        return this.model;
    }

    public Resizable model(final Model model) {
        this.model = model;
        return this;
    }

    public Shape shape() {
        return this.shape;
    }

    public Resizable shape(final Shape shape) {
        this.shape = shape;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append(" [");
        sb.append(this.model).append(", ");
        sb.append(this.shape).append(", ");
        sb.append("]");
        return sb.toString();
    }

}
