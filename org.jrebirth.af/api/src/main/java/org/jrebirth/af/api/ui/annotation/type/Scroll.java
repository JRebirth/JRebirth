package org.jrebirth.af.api.ui.annotation.type;

import javafx.event.EventType;
import javafx.scene.input.ScrollEvent;

import org.jrebirth.af.api.ui.annotation.EnumEventType;

/**
 * The Scroll event type.<br />
 * The scroll type will be appended to method name to use.
 */
public enum Scroll implements EnumEventType {

    /** Any Scroll Event. */
    Any(ScrollEvent.ANY),

    /** Scroll started event. */
    Started(ScrollEvent.SCROLL_STARTED),

    /** Scroll event. */
    Rotate(ScrollEvent.SCROLL),

    /** Scroll finished event. */
    Finished(ScrollEvent.SCROLL_FINISHED);

    /** The JavaFX internal api name. */
    private EventType<?> eventType;

    /**
     * Default constructor used to link the apiName.
     *
     * @param eventType the javafx event type
     */
    private Scroll(final EventType<?> eventType) {
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