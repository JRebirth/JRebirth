package org.jrebirth.af.core.ui.annotation.type;

import javafx.event.EventType;
import javafx.scene.input.ZoomEvent;

import org.jrebirth.af.core.ui.annotation.EnumEventType;

/**
 * The Zoom event type.<br />
 * The zoom type will be appended to method name to use.
 */
public enum Zoom implements EnumEventType {

    /** Any Zoom Event. */
    Any(ZoomEvent.ANY),

    /** Zoom started event. */
    Started(ZoomEvent.ZOOM_STARTED),

    /** Zoom event. */
    Zoom(ZoomEvent.ZOOM),

    /** Zoom finished event. */
    Finished(ZoomEvent.ZOOM_FINISHED);

    /** The JavaFX internal api name. */
    private EventType<?> eventType;

    /**
     * Default constructor used to link the apiName.
     *
     * @param eventType the javafx event type
     */
    private Zoom(final EventType<?> eventType) {
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