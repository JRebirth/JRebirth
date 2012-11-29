package org.jrebirth.core.ui.handler;

import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * The class <strong>AbstractNamedEventHandler</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @param <T> the event to handle
 */
public abstract class AbstractNamedEventHandler<E extends Event> implements EventHandler<E> {

    /**
     * The name of the event handler. It should give some information about the call context
     */
    private final String name;

    /**
     * Default Constructor.
     * 
     * @param name the name of this event handler
     */
    public AbstractNamedEventHandler(final String name) {
        super();
        this.name = name;
    }

}
