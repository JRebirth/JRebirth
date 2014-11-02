/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2014
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
