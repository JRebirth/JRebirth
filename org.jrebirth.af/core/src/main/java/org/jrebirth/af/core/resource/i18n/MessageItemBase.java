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

import java.text.MessageFormat;

import org.jrebirth.af.core.exception.CoreRuntimeException;
import org.jrebirth.af.core.log.JRLevel;
import org.jrebirth.af.core.resource.AbstractResourceItem;
import org.jrebirth.af.core.resource.provided.JRebirthParameters;

import org.slf4j.Marker;

/**
 * The class <strong>MessageItemBase</strong> is used to build i18n Message.
 * 
 * @author Sébastien Bordes
 */
public final class MessageItemBase extends AbstractResourceItem<MessageResource, MessageItem, MessageParams, MessageBuilder> implements MessageItem {

    public static MessageItemBase create() {
        return new MessageItemBase();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText(final Object... stringParameters) {
        String res = get().getMessage();
        if (stringParameters.length > 0) {
            try {
                // Use the message formatter
                res = MessageFormat.format(res, stringParameters);

            } catch (final IllegalArgumentException e) {

                // Display special markups
                res = "<!!" + builder().getParam(this).toString() + "!!>";

                // In developer mode throw a runtime exception to stop the current task
                if (JRebirthParameters.DEVELOPER_MODE.get()) {
                    throw new CoreRuntimeException("Bad formatted Message key : " + res, e);
                }
            }
        }
        return res;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void define(final MessageResource forcedValue) {
        // The default programmatic value (stored into ObjectParameter) is not updated but overridden into the local map
        builder().define(this, forcedValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void persist() {
        throw new CoreRuntimeException("Not Implemented yet");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Marker getMarker() {
        return get().getMarker();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JRLevel getLevel() {
        return get().getLevel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MessageItem set(final MessageParams messageParams) {
        builder().storeParams(this, messageParams);
        return this;
    }
}
