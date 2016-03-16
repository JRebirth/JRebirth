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

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import org.jrebirth.af.api.resource.builder.ResourceBuilder;
import org.jrebirth.af.api.resource.i18n.MessageItem;
import org.jrebirth.af.api.resource.i18n.MessageParams;
import org.jrebirth.af.api.resource.i18n.MessageResource;
import org.jrebirth.af.core.log.JRebirthMarkers;
import org.jrebirth.af.core.resource.builder.AbstractResourceBuilder;
import org.jrebirth.af.core.resource.provided.parameter.CoreParameters;
import org.jrebirth.af.core.util.ClasspathUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>MessageBuilder</strong>.
 *
 * Class used to manage message with weak reference.
 *
 * @author Sébastien Bordes
 */
public final class MessageBuilder extends AbstractResourceBuilder<MessageItem, MessageParams, MessageResource> implements ResourceBuilder<MessageItem, MessageParams, MessageResource> {

    /**
     * The class logger.
     *
     * All Logs of this class are hard coded because l10n engine is not started.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageBuilder.class);

    /** The list of all resource bundle loaded according to annotation. */
    private final List<ResourceBundle> resourceBundles = new ArrayList<>();

    /** Store all overridden values defined by the call of define method. */
    private final Map<MessageItem, MessageResource> overriddenMessageMap = new ConcurrentHashMap<>();

    /** The Wildcard used to load Messages files. */
    private String messageFileWildcard;

    /** Flag mapped to the parameter to know if we must resolve log code. */
    // private boolean logResolutionActivated = true;

    // /**
    // * @param logResolutionActivated The logResolutionActivated to set.
    // */
    // public void setLogResolutionActivated(final boolean logResolutionActivated) {
    // this.logResolutionActivated = logResolutionActivated;
    // }

    /**
     * Search configuration files according to the parameters provided.
     *
     * @param wildcard the regex wildcard (must not be null)
     */
    public void searchMessagesFiles(final String wildcard) {

        // Store parameters
        this.messageFileWildcard = wildcard;

        // Search and analyze all properties files available
        readPropertiesFiles();
    }

    /**
     * Read all configuration files available into the application classpath.
     */
    private void readPropertiesFiles() {

        if (this.messageFileWildcard.isEmpty() || !CoreParameters.LOG_RESOLUTION.get()) {
            // Skip configuration loading
            LOGGER.info(JRebirthMarkers.MESSAGE, "Messages Loading is skipped");

        } else {
            // Assemble the regex pattern
            final Pattern filePattern = Pattern.compile(this.messageFileWildcard + "\\.properties");

            // Retrieve all resources from default classpath
            final Collection<String> list = ClasspathUtility.getClasspathResources(filePattern);

            LOGGER.info(JRebirthMarkers.MESSAGE, "{} Messages file{} found.", list.size(), list.size() > 1 ? "s" : "");

            for (final String rbFilename : list) {
                readPropertiesFile(rbFilename);
            }
        }
    }

    /**
     * Read a customized Message file to load all translated messages.
     *
     * @param rbFilename the resource bundle file to load
     */
    private void readPropertiesFile(final String rbFilename) {

        final File rbFile = new File(rbFilename);

        final String rbName = rbFile.getName().substring(0, rbFile.getName().lastIndexOf(".properties"));

        if (rbName == null || rbName.isEmpty()) {
            LOGGER.error(JRebirthMarkers.MESSAGE, "Resource Bundle must be not null and not empty");
        } else {

            LOGGER.info(JRebirthMarkers.MESSAGE, "Store ResourceBundle : {} ", rbName);
            try {
                this.resourceBundles.add(ResourceBundle.getBundle(rbName));
            } catch (final MissingResourceException e) {
                LOGGER.error(JRebirthMarkers.MESSAGE, "{} Resource Bundle not found", rbName);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected MessageResource buildResource(final MessageItem messageItem, final MessageParams messageParams) {

        MessageResource messageResource = null;

        // Load overridden values first
        if (messageParams.name() != null && this.overriddenMessageMap.containsKey(messageItem)) {

            // Retrieve the customized parameter
            messageResource = this.overriddenMessageMap.get(messageItem);
        }

        // No overridden value is defined
        // Check if the message has a message key and
        // check if the message has been loaded from any customized Messages file
        if (messageResource == null && messageParams.name() != null /* && this.propertiesParametersMap.containsKey(op.name()) */) {

            // Translation is always allowed for all Message
            // It depends on LOG_RESOLUTION parameter for all LogMessage
            final boolean translationAllowed = !(messageParams instanceof LogMessageParams) || CoreParameters.LOG_RESOLUTION.get();

            // Translate the message code as required
            String rawMessage = translationAllowed ? findMessage(messageParams.name()) : null;

            // No translation found, the message will be the key with wrapped by <>

            if (rawMessage == null) {
                rawMessage = '<' + messageParams.name() + '>';
            }

            if (messageParams instanceof LogMessageParams) {
                // Use default log marker and log level
                messageResource = new MessageResourceBase(rawMessage, ((LogMessageParams) messageParams).marker(), ((LogMessageParams) messageParams).level());
            } else {
                // Just convert the code into message
                messageResource = new MessageResourceBase(rawMessage);
            }
        }

        return messageResource;
    }

    /**
     * Retrieved the message mapped with the given key.
     *
     * Perform the search by iterating over all resource bundles available in reverse order.
     *
     * @param messageKey the key of the message to translate
     *
     * @return the translated message or null
     */
    private String findMessage(final String messageKey) {
        String message = null;

        try {
            if (!this.resourceBundles.isEmpty()) {
                for (int i = this.resourceBundles.size() - 1; i >= 0 && message == null; i--) {

                    if (this.resourceBundles.get(i).containsKey(messageKey)) {
                        message = this.resourceBundles.get(i).getString(messageKey);
                    }
                }
            }
        } catch (final MissingResourceException e) {
            LOGGER.error("Message key not found into resource bundle", e);
        }

        return message;
    }

    /**
     * Override a parameter value.
     *
     * @param key the parameter item key
     * @param forcedValue the overridden value
     */
    public void define(final MessageItem key, final MessageResource forcedValue) {
        this.overriddenMessageMap.put(key, forcedValue);
        set(getParamKey(key), forcedValue);
    }
}
