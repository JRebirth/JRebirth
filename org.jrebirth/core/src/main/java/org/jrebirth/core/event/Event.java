package org.jrebirth.core.event;

import org.jrebirth.core.facade.UniqueKey;

/**
 * The interface <strong>Event</strong>.
 * 
 * An Event is stored for each important action into the application. Events are useful to understand and debug complex interactions.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public interface Event extends UniqueKey {

    /**
     * @return Returns the sequence number.
     */
    int getSequence();

    /**
     * @param sequence The sequence to set.
     */
    void setSequence(int sequence);

    /**
     * @return Returns the eventType.
     */
    EventType getEventType();

    /**
     * @param eventType The eventType to set.
     */
    void setEventType(EventType eventType);

    /**
     * @return Returns the source class.
     */
    Class<?> getSource();

    /**
     * @param source The source class to set.
     */
    void setSource(Class<?> source);

    /**
     * @return Returns the target class.
     */
    Class<?> getTarget();

    /**
     * @param target The data to set.
     */
    void setTarget(Class<?> target);

    /**
     * @return Returns the data.
     */
    String getEventData();

    /**
     * @param data The data to set.
     */
    void setEventData(String data);

}
