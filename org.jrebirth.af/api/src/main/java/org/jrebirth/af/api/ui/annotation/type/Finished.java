package org.jrebirth.af.api.ui.annotation.type;

import javafx.event.ActionEvent;
import javafx.event.EventType;

import org.jrebirth.af.api.ui.annotation.EnumEventType;

/**
 * The Finished event type.<br />
 * The Finished type will be appended to method name to use.
 */
public enum Finished implements EnumEventType {

    /** Unique Action event type. */
    Action(ActionEvent.ACTION);

    /** The JavaFX internal api name. */
    private EventType<?> eventType;

    /**
     * Default constructor used to link the apiName.
     *
     * @param eventType the javafx event type
     */
    private Finished(final EventType<?> eventType) {
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