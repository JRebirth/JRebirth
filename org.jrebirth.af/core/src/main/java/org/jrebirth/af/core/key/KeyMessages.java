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
package org.jrebirth.af.core.key;

import static org.jrebirth.af.core.resource.Resources.create;

import org.jrebirth.af.api.resource.i18n.JRLevel;
import org.jrebirth.af.api.resource.i18n.MessageItem;
import org.jrebirth.af.core.log.JRebirthMarkers;
import org.jrebirth.af.core.resource.i18n.LogMessage;
import org.jrebirth.af.core.resource.i18n.MessageContainer;

/**
 * The class <strong>KeyMessages</strong>.
 *
 * Messages used by the Key package.
 *
 * @author Sébastien Bordes
 */
public interface KeyMessages extends MessageContainer {

    /** MultitonKey. */

    /** "No KeyGenerator method found ({0}) for type {1}" . */
    MessageItem NO_KEY_GENERATOR_METHOD = create(new LogMessage("jrebirth.key.noKeyGeneratorMethod", JRLevel.Error, JRebirthMarkers.KEY));

    /** "Fail to generate the key for method: {0} for of {1}" . */
    MessageItem KEY_GENERATOR_FAILURE = create(new LogMessage("jrebirth.key.keyGeneratorFailure", JRLevel.Error, JRebirthMarkers.KEY));

    /** "Fail to generate the method key for method: {0} of type {1}" . */
    MessageItem METHOD_KEY_GENERATOR_FAILURE = create(new LogMessage("jrebirth.key.methodKeyGeneratorFailure", JRLevel.Error, JRebirthMarkers.KEY));

    /** "Method not found: {0} for type {1}" . */
    MessageItem METHOD_NOT_FOUND = create(new LogMessage("methodNotFound", JRLevel.Error, JRebirthMarkers.KEY));

    /** "The method key returned is null for method: {0} of type {1}" . */
    MessageItem NULL_METHOD_KEY = create(new LogMessage("jrebirth.key.nullMethodKey", JRLevel.Warn, JRebirthMarkers.KEY));

    /** "No toString method found for the returned object of ({0}) of type {1}" . */
    MessageItem NO_TOSTRING_KEY_METHOD = create(new LogMessage("jrebirth.key.noToStringKeyMethod", JRLevel.Error, JRebirthMarkers.KEY));

    /** "Fail to stringify the returned key of the method {0} of type {1}" . */
    MessageItem METHOD_KEY_TOSTRING_FAILURE = create(new LogMessage("jrebirth.key.methodKeyToStringFailure", JRLevel.Error, JRebirthMarkers.KEY));

    /** "The string of the method key returned is null for method: {0} of type {1}" . */
    MessageItem NULL_METHOD_KEY_STRING = create(new LogMessage("jrebirth.key.nullMethodKeyString", JRLevel.Warn, JRebirthMarkers.KEY));

}
