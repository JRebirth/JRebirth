package org.jrebirth.af.core.ui.annotation.type;

import javafx.event.EventType;
import javafx.scene.input.RotateEvent;

import org.jrebirth.af.core.ui.annotation.EnumEventType;

/**
 * The Rotate event type.<br />
 * The rotate type will be appended to method name to use.
 */
public enum Rotate implements EnumEventType {

    /** Any Rotate Event. */
    Any(RotateEvent.ANY),

    /** Rotation started event. */
    Started(RotateEvent.ROTATION_STARTED),

    /** Rotate event. */
    Rotate(RotateEvent.ROTATE),

    /** Rotation finished event. */
    Finished(RotateEvent.ROTATION_FINISHED);

    /** The JavaFX internal api name. */
    private EventType<?> eventType;

    /**
     * Default constructor used to link the apiName.
     *
     * @param eventType the javafx event type
     */
    private Rotate(final EventType<?> eventType) {
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