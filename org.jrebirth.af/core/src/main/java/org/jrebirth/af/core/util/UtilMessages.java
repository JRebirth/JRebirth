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
package org.jrebirth.af.core.util;

import static org.jrebirth.af.core.resource.Resources.create;

import org.jrebirth.af.api.resource.i18n.JRLevel;
import org.jrebirth.af.api.resource.i18n.MessageItem;
import org.jrebirth.af.core.log.JRebirthMarkers;
import org.jrebirth.af.core.resource.i18n.LogMessage;
import org.jrebirth.af.core.resource.i18n.MessageContainer;

/**
 * The class <strong>UtilMessages</strong>.
 *
 * Messages used by the Util package.
 *
 * @author Sébastien Bordes
 */
public interface UtilMessages extends MessageContainer {

    /** CheckerUtility. */

    /** "{0} must have a method => void {1} ({2}, Wave wave)\{\}". */
    MessageItem WAVE_HANDLER_METHOD_REQUIRED = create(new LogMessage("jrebirth.util.waveHandlerMethodRequired", JRLevel.Exception, JRebirthMarkers.UTIL));

    /** "{0} API is broken, no method {1} is available". */
    MessageItem BROKEN_API_NO_METHOD = create(new LogMessage("jrebirth.util.brokenApiNoMethod", JRLevel.Warn, JRebirthMarkers.UTIL));

    /** "{0} API is broken, the method {1} has wrong parameters, expected:{2} provided:{3}" . */
    MessageItem BROKEN_API_WRONG_PARAMETERS = create(new LogMessage("jrebirth.util.brokenApiWrongParameters", JRLevel.Warn, JRebirthMarkers.UTIL));

    /** "Broken Wave sent : {0}" . */
    MessageItem BROKEN_WAVE_SENT = create(new LogMessage("jrebirth.util.brokenWaveSent", JRLevel.Warn, JRebirthMarkers.UTIL));

    /** "This wave must provide these items: {0}" . */
    MessageItem BROKEN_WAVE_BAD_ITEM_LIST = create(new LogMessage("jrebirth.util.brokenWaveBadItemList", JRLevel.Warn, JRebirthMarkers.UTIL));

    /** ClasspathUtility. */

    /** "The resource {0} is ignored from classpath search engine (not a zip|jar|directory)". */
    MessageItem RESOURCE_IGNORED = create(new LogMessage("jrebirth.util.resourceIgnored", JRLevel.Info, JRebirthMarkers.UTIL));

    /** "Impossible to get the resource canonical path" . */
    MessageItem BAD_CANONICAL_PATH = create(new LogMessage("jrebirth.util.badCanonicalPath", JRLevel.Error, JRebirthMarkers.UTIL));

    /** "Impossible to read the file" . */
    MessageItem FILE_UNREADABLE = create(new LogMessage("jrebirth.util.fileUnreadable", JRLevel.Error, JRebirthMarkers.UTIL));

    /** "Use JNLPClassLoader to search properties files" . */
    MessageItem USE_JNLP_CLASSLOADER = create(new LogMessage("jrebirth.util.useJNLPClassLoader", JRLevel.Debug, JRebirthMarkers.UTIL));

    /** "Parse Cached Jar File [{0}] for resource [{1}]" . */
    MessageItem PARSE_CACHED_JAR_FILE = create(new LogMessage("jrebirth.util.parseCachedJarFile", JRLevel.Debug, JRebirthMarkers.UTIL));

    /** "Can not read Cached Jar File : {0}" . */
    MessageItem CANT_READ_CACHED_JAR_FILE = create(new LogMessage("jrebirth.util.cantReadCachedJarFile", JRLevel.Error, JRebirthMarkers.UTIL));

    /** "Use Default ClassLoader to search properties files" . */
    MessageItem USE_DEFAULT_CLASSLOADER = create(new LogMessage("jrebirth.util.useDefaultClassLoader", JRLevel.Debug, JRebirthMarkers.UTIL));

    /** ClassUtility. */

    /** "Impossible to build the object assignable to {0} for the class {1}" . */
    MessageItem GENERIC_TYPE_ERROR_1 = create(new LogMessage("jrebirth.util.genericTypeError1", JRLevel.Error, JRebirthMarkers.UTIL));

    /** "Impossible to build the {0} object for the class {1}" . */
    MessageItem GENERIC_TYPE_ERROR_2 = create(new LogMessage("jrebirth.util.genericTypeError2", JRLevel.Error, JRebirthMarkers.UTIL));

    /** "Arguments are : " . */
    MessageItem ARGUMENT_LIST = create(new LogMessage("jrebirth.util.argumentList", JRLevel.Error, JRebirthMarkers.UTIL));

    /** "{0} = {1}" . */
    MessageItem ARGUMENT_DETAIL = create(new LogMessage("jrebirth.util.argumentDetail", JRLevel.Error, JRebirthMarkers.UTIL));

    /** "Impossible to retrieve the constructor due to security" . */
    MessageItem NO_CONSTRUCTOR = create(new LogMessage("jrebirth.util.noConstructor", JRLevel.Error, JRebirthMarkers.UTIL));

    /** "Impossible to find the annotation property : {0}" . */
    MessageItem NO_ANNOTATION_PROPERTY = create(new LogMessage("jrebirth.util.noAnnotationProperty", JRLevel.Error, JRebirthMarkers.UTIL));

    /** "Impossible to retrieve value for the annotation property : {0}". */
    MessageItem NO_ANNOTATION_PROPERTY_VALUE = create(new LogMessage("jrebirth.util.noAnnotationPropertyValue", JRLevel.Error, JRebirthMarkers.UTIL));

    /** ParameterUtility. */

    /** "Impossible to load the custom {0}, will use the default one". */
    MessageItem CUSTOM_CLASS_LOADING_ERROR = create(new LogMessage("jrebirth.util.customClassLoadingError", JRLevel.Error, JRebirthMarkers.UTIL));

}
