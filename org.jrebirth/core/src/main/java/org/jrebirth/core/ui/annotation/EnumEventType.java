package org.jrebirth.core.ui.annotation;

import javafx.event.EventType;

/**
 * The class <strong>EnumEventType</strong>.
 * 
 * @author Sébastien Bordes
 */
public interface EnumEventType {

    /**
     * Return the javaFX event type.
     * 
     * @return the eventType
     */
    EventType<?> eventType();

    /**
     * Return the enum name.
     * 
     * @return the enum name
     */
    String name();

}
