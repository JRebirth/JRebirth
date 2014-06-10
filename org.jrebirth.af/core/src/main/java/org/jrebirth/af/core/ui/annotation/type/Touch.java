package org.jrebirth.af.core.ui.annotation.type;

import javafx.event.EventType;
import javafx.scene.input.TouchEvent;

import org.jrebirth.af.core.ui.annotation.EnumEventType;

/**
 * The Touch event type.<br />
 * The Touch type will be appended to method name to use.
 */
public enum Touch implements EnumEventType {

    /** Any Swipe Event. */
    Any(TouchEvent.ANY),

    /** Touch pressed event. */
    Pressed(TouchEvent.TOUCH_PRESSED),

    /** Touch released event. */
    Released(TouchEvent.TOUCH_RELEASED),

    /** Touch moved event. */
    Moved(TouchEvent.TOUCH_MOVED),

    /** Touch stationary event. */
    Stationary(TouchEvent.TOUCH_STATIONARY);

    /** The JavaFX internal api name. */
    private EventType<?> eventType;

    /**
     * Default constructor used to link the apiName.
     *
     * @param eventType the javafx event type
     */
    private Touch(final EventType<?> eventType) {
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