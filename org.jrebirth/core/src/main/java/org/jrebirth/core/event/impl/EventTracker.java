package org.jrebirth.core.event.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.jrebirth.core.event.AbstractRecord;
import org.jrebirth.core.event.Event;

/**
 * The class <strong>EventTracker</strong>.
 * 
 * Used to track all events of JRebirth application to replay them into the analyzer tool.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public class EventTracker extends AbstractRecord {

    /** The list of events tracked. */
    private final List<Event> eventList;

    /**
     * {@inheritDoc}
     */
    @Override
    protected OutputStream buildOutputStream() {
        // Nothing to do yet
        return null;
    }

    /**
     * Default Constructor.
     */
    public EventTracker() {
        super();

        this.eventList = new ArrayList<>();
    }

    /**
     * Track an event.
     * 
     * @param event the event to record
     */
    public void track(final Event event) {
        event.setSequence(getEventList().size());
        getEventList().add(event);
        record(event.toString());
    }

    /**
     * @return Returns the eventList.
     */
    List<Event> getEventList() {
        return this.eventList;
    }

}
