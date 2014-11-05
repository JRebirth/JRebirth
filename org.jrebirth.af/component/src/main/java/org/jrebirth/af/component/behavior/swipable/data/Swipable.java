package org.jrebirth.af.component.behavior.swipable.data;

import java.io.Serializable;

import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.input.SwipeEvent;

import org.jrebirth.af.api.component.behavior.annotation.BehaviorDataFor;
import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.component.behavior.swipable.SwipableBehavior;
import org.jrebirth.af.core.behavior.data.BehaviorDataBase;

@BehaviorDataFor(SwipableBehavior.class)
public class Swipable extends BehaviorDataBase implements Serializable {

    /** The generated serial version uid. */
    private static final long serialVersionUID = 8137109665415403740L;

    public enum SwipableKind {
        Custom, Left, Top, Right, Bottom
    }

    private UniqueKey<Model> modelKey;

    private Node node;

    private int touchCount = 1;

    private SwipableKind swipableKind = SwipableKind.Right;

    private EventType<SwipeEvent> redo = SwipeEvent.SWIPE_LEFT;

    private EventType<SwipeEvent> undo = SwipeEvent.SWIPE_RIGHT;

    public static Swipable create() {
        return new Swipable();
    }

    public UniqueKey<Model> modelKey() {
        return this.modelKey;
    }

    public Swipable modelKey(final UniqueKey<Model> modelKey) {
        this.modelKey = modelKey;
        return this;
    }

    public Node node() {
        return this.node;
    }

    public Swipable node(final Node node) {
        this.node = node;
        return this;
    }

    public int touchCount() {
        return this.touchCount;
    }

    public Swipable touchCount(final int touchCount) {
        this.touchCount = touchCount;
        return this;
    }

    public SwipableKind swipableKind() {
        return this.swipableKind;
    }

    public Swipable swipableKind(final SwipableKind swipableKind) {
        this.swipableKind = swipableKind;
        initEventTypes();
        return this;
    }

    private void initEventTypes() {
        switch (swipableKind()) {
            case Left:
                redo(SwipeEvent.SWIPE_RIGHT);
                redo(SwipeEvent.SWIPE_LEFT);
                break;
            case Top:
                redo(SwipeEvent.SWIPE_DOWN);
                redo(SwipeEvent.SWIPE_UP);
                break;
            case Right:
                redo(SwipeEvent.SWIPE_LEFT);
                redo(SwipeEvent.SWIPE_RIGHT);
                break;
            case Bottom:
                redo(SwipeEvent.SWIPE_UP);
                redo(SwipeEvent.SWIPE_DOWN);
                break;
            case Custom:
            default:
                break;
        }

    }

    public EventType<SwipeEvent> redo() {
        return this.redo;
    }

    public Swipable redo(final EventType<SwipeEvent> redo) {
        this.redo = redo;
        return this;
    }

    public EventType<SwipeEvent> undo() {
        return this.undo;
    }

    public Swipable undo(final EventType<SwipeEvent> undo) {
        this.undo = undo;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append(" [");
        sb.append(this.node).append(", ");
        sb.append(this.touchCount);
        sb.append("]");
        return sb.toString();
    }

}
