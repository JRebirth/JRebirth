package org.jrebirth.af.api.resource.i18n;

import org.jrebirth.af.api.log.JRLevel;
import org.slf4j.Marker;

/**
 * The class <strong>MessageResource</strong>.
 *
 * @author Sébastien Bordes
 */
public interface MessageResource {

    /**
     * Gets the message.
     *
     * @return Returns the message.
     */
    String getMessage();

    /**
     * Sets the message.
     *
     * @param message The message to set.
     */
    void setMessage(final String message);

    /**
     * Gets the marker.
     *
     * @return Returns the marker.
     */
    Marker getMarker();

    /**
     * Gets the level.
     *
     * @return Returns the level.
     */
    JRLevel getLevel();

}
