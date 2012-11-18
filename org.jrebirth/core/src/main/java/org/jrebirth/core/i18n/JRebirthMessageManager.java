/**
 * Copyright JRebirth.org © 2011-2012 
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
package org.jrebirth.core.i18n;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * The class <strong>JRebirthMessageManager</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class JRebirthMessageManager {

    /** The singleton instance of the manager. */
    private static JRebirthMessageManager instance = new JRebirthMessageManager();

    /** The jrebirth bundle name. */
    private static final String BUNDLE_NAME = "org.jrebirth.core.messages";

    /** The Resources bundle built. */
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    /**
     * Default Constructor.
     */
    private JRebirthMessageManager() {
        // Nothing to do
    }

    /**
     * Get singleton instance.
     * 
     * @return the message manager instance
     */
    public static JRebirthMessageManager getInstance() {
        return instance;
    }

    /**
     * Return the internationalized message.
     * 
     * @param message the key of the message
     * @param parameter optional parameters used for format
     * 
     * @return the formatted internationalized message
     */
    public String get(final MessageReady message, final Object... parameter) {
        String res = null;
        try {
            res = RESOURCE_BUNDLE.getString(message.getSymbolicName());
        } catch (final MissingResourceException e) {
            res = '<' + message.getSymbolicName() + '>';
        }
        // TODO use string formatting
        return res;
    }
}
