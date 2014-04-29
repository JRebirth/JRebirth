package org.jrebirth.af.core.ui.annotation.type;

import javafx.event.EventType;
import javafx.scene.input.SwipeEvent;

import org.jrebirth.af.core.ui.annotation.EnumEventType;

/**
 * The Swipe event type.<br />
 * The swipe type will be appended to method name to use.
 */
public enum Swipe implements EnumEventType {

    /** Any Swipe Event. */
    Any(SwipeEvent.ANY),

    /** Swipe Up event. */
    Up(SwipeEvent.SWIPE_UP),

    /** Swipe Down event. */
    Down(SwipeEvent.SWIPE_DOWN),

    /** Swipe Left event. */
    Left(SwipeEvent.SWIPE_LEFT),

    /** Swipe Right event. */
    Right(SwipeEvent.SWIPE_RIGHT);

    /** The JavaFX internal api name. */
    private EventType<?> eventType;

    /**
     * Default constructor used to link the apiName.
     * 
     * @param eventType the javafx event type
     */
    private Swipe(final EventType<?> eventType) {
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