package org.jrebirth.af.component.behavior.selectable;

import java.io.Serializable;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

import org.jrebirth.af.api.component.behavior.annotation.BehaviorDataFor;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.core.component.behavior.BehaviorDataBase;

@BehaviorDataFor(SelectableBehavior.class)
public class Selectable extends BehaviorDataBase implements Serializable {

    private static final long serialVersionUID = -2402077469733154818L;

    private Model model;

    private Shape shape;

    public static Selectable of() {
        return new Selectable();
    }

    public Model model() {
        return this.model;
    }

    public Selectable model(final Model model) {
        this.model = model;
        return this;
    }

    public Shape shape() {
        return this.shape;
    }

    public Selectable shape(final Shape shape) {
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
