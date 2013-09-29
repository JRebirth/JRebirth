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
package org.jrebirth.core.resource.i18n;

import org.jrebirth.core.log.JRLevel;
import org.jrebirth.core.resource.ResourceItem;

import org.slf4j.Marker;

/**
 * The class <strong>MessageItem</strong>.
 * 
 * The key of the i18n message.
 * 
 * The name will be transformed : camelCase => CAMEL_CASE
 * 
 * @author Sébastien Bordes
 */
public interface MessageItem extends ResourceItem<String, MessageBuilder> {

    /**
     * Get the message formatted with parameterized objects.
     * 
     * @param stringParameters the list of object used as string parameter
     * 
     * @return Returns right message foramtted with given parameters.
     */
    String get(final Object... stringParameters);

    /**
     * Return the log marker if any.
     * 
     * @return the log marker or the Empty one
     */
    Marker getMarker();

    /**
     * Return the log level if any.
     * 
     * @return the log level or the info one
     */
    JRLevel getLevel();

    /**
     * Define a new forced string.
     * 
     * This forced value will be used instead of default programmatic one and all other retrieved from properties files.
     * 
     * @param forcedValue the new string for this message
     */
    void define(final String forcedValue);

    /**
     * Persist a message value.
     */
    void persist();

}
