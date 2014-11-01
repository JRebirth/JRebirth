package org.jrebirth.af.api.ui.annotation.type;

import javafx.event.EventType;
import javafx.scene.input.KeyEvent;

import org.jrebirth.af.api.ui.annotation.EnumEventType;

/**
 * The Key event type.<br />
 * The Key type will be appended to method name to use.
 */
public enum Key implements EnumEventType {

    /** Any Key Event. */
    Any(KeyEvent.ANY),

    /** Key pressed event. */
    Pressed(KeyEvent.KEY_PRESSED),

    /** Key released event. */
    Released(KeyEvent.KEY_RELEASED),

    /** Key typed event. */
    Typed(KeyEvent.KEY_TYPED);

    /** The JavaFX internal api name. */
    private EventType<?> eventType;

    /**
     * Default constructor used to link the apiName.
     *
     * @param eventType the javafx event type
     */
    private Key(final EventType<?> eventType) {
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
