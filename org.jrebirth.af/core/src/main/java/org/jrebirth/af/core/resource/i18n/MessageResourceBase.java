/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
 * Contact : sebastien.bordes@jrebirth.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.af.core.resource.i18n;

import org.jrebirth.af.api.resource.ResourceParams;
import org.jrebirth.af.api.resource.i18n.JRLevel;
import org.jrebirth.af.api.resource.i18n.MessageResource;
import org.jrebirth.af.core.log.JRebirthMarkers;

import org.slf4j.Marker;

/**
 * The class <strong>MessageResource</strong>.
 *
 * @author Sébastien Bordes
 */
public class MessageResourceBase implements MessageResource {

    /** The message. */
    private String message;

    /** The log marker, could be null for Message. */
    private Marker marker;

    /** The log level, could be null for Message. */
    private JRLevel level;

    /**
     * Instantiates a new message resource.
     *
     * @param message the message
     */
    public MessageResourceBase(final String message) {
        super();
        this.message = message;
    }

    /**
     * Instantiates a new message resource.
     *
     * @param rawMessage the raw message
     * @param defaultMarker the default marker
     * @param defaultLevel the default level
     */
    public MessageResourceBase(final String rawMessage, final Marker defaultMarker, final JRLevel defaultLevel) {
        super();

        final String[] messageContent = rawMessage.split(ResourceParams.PARAMETER_SEPARATOR_REGEX);

        this.message = messageContent[0];

        if (messageContent.length >= 2) {
            this.level = JRLevel.valueOf(messageContent[1]);
        } else {
            this.level = defaultLevel;
        }

        if (messageContent.length >= 3) {
            try {
                this.marker = (Marker) JRebirthMarkers.class.getField(messageContent[2]).get(null);
            } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
                // Bad marker
                this.marker = defaultMarker;
            }
        } else {
            this.marker = defaultMarker;
        }

    }

    /**
     * Gets the message.
     *
     * @return Returns the message.
     */
    @Override
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets the message.
     *
     * @param message The message to set.
     */
    @Override
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * Gets the marker.
     *
     * @return Returns the marker.
     */
    @Override
    public Marker getMarker() {
        return this.marker;
    }

    /**
     * Gets the level.
     *
     * @return Returns the level.
     */
    @Override
    public JRLevel getLevel() {
        return this.level;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getMessage();
    }

}
