package org.jrebirth.af.api.ui.annotation.type;

import javafx.event.EventType;
import javafx.stage.WindowEvent;

import org.jrebirth.af.api.ui.annotation.EnumEventType;

/**
 * The Window event type.<br />
 * The window type will be appended to method name to use.
 */
public enum Window implements EnumEventType {

    /** Any Swipe Event. */
    Any(WindowEvent.ANY),

    /** Window close requested event. */
    CloseRequest(WindowEvent.WINDOW_CLOSE_REQUEST),

    /** Window hidden event. */
    Hidden(WindowEvent.WINDOW_HIDDEN),

    /** Window hiding event. */
    Hiding(WindowEvent.WINDOW_HIDING),

    /** Window showing event. */
    Showing(WindowEvent.WINDOW_SHOWING),

    /** Window shown event. */
    Shown(WindowEvent.WINDOW_SHOWN);

    /** The JavaFX internal api name. */
    private EventType<?> eventType;

    /**
     * Default constructor used to link the apiName.
     *
     * @param eventType the javafx event type
     */
    private Window(final EventType<?> eventType) {
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
