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

import org.jrebirth.af.api.resource.builder.ResourceBuilder;
import org.jrebirth.af.api.resource.i18n.JRLevel;
import org.jrebirth.af.api.resource.i18n.MessageItem;
import org.jrebirth.af.api.resource.i18n.MessageParams;
import org.jrebirth.af.api.resource.i18n.MessageResource;
import org.jrebirth.af.core.resource.ResourceBuilders;
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
public interface MessageItemReal extends MessageItem {

    /**
     * Get the message formatted text with or without parameterized objects.
     *
     * @param stringParameters the list of object used as string parameter
     *
     * @return Returns right message formatted with given parameters.
     */
    @Override
    String getText(final Object... stringParameters);

    /**
     * Return the optional log marker if any.
     *
     * @return the optional log marker or the Empty one
     */
    @Override
    Marker getMarker();

    /**
     * Return the optional log level if any.
     *
     * @return the optional log level or the info one
     */
    @Override
    JRLevel getLevel();

    /**
     * Define a new forced string.
     *
     * This forced value will be used instead of default programmatic one and all other retrieved from properties files.
     *
     * @param forcedValue the new string for this message
     */
    @Override
    void define(final MessageResource forcedValue);

    /**
     * Persist a message value.
     */
    @Override
    void persist();

    /**
     * {@inheritDoc}
     */
    @Override
    default MessageItem set(final MessageParams messageParams) {
        builder().storeParams(this, messageParams);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default MessageResource get() {
        return builder().get(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default ResourceBuilder<MessageItem, MessageParams, MessageResource> builder() {
        return ResourceBuilders.MESSAGE_BUILDER;
    }

    interface Log extends MessageItem {

        default void log(final String parameterName, final JRLevel level, final Marker marker) {
            set(new LogMessage(parameterName, level, marker));
        }

    }

    interface Msg extends MessageItem {

        /**
         * Default Constructor.
         *
         * @param parameterName the name of the parameter
         */
        default void msg(final String parameterName) {
            set(new Message(parameterName));
        }
    }
}
