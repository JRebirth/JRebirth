package org.jrebirth.af.api.ui.annotation.type;

import javafx.event.EventType;
import javafx.scene.input.DragEvent;

import org.jrebirth.af.api.ui.annotation.EnumEventType;

/**
 * The Drag event type.<br />
 * The Drag type will be appended to method name to use.
 */
public enum Drag implements EnumEventType {

    /** Any Drag Event. */
    Any(DragEvent.ANY),

    /** Drag done event. */
    Done(DragEvent.DRAG_DONE),

    /** Drag dropped event. */
    Dropped(DragEvent.DRAG_DROPPED),

    /** Drag entered event. */
    Entered(DragEvent.DRAG_ENTERED),

    /** Drag entered target event. */
    EnteredTarget(DragEvent.DRAG_ENTERED_TARGET),

    /** Drag exited event. */
    Exited(DragEvent.DRAG_EXITED),

    /** Drag exited target event. */
    ExitedTarget(DragEvent.DRAG_EXITED_TARGET),

    /** Drag over event. */
    Over(DragEvent.DRAG_OVER);

    /** The JavaFX internal api name. */
    private EventType<?> eventType;

    /**
     * Default constructor used to link the apiName.
     *
     * @param eventType the javafx event type
     */
    private Drag(final EventType<?> eventType) {
        this.eventType = eventType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EventType<?> eventType() {
        return this.eventType;
    }

}