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
package org.jrebirth.core.util;

import static org.jrebirth.core.resource.Resources.create;

import org.jrebirth.core.log.JRebirthMarkers;
import org.jrebirth.core.resource.i18n.LogMessage;
import org.jrebirth.core.resource.i18n.MessageContainer;
import org.jrebirth.core.resource.i18n.MessageItem;

/**
 * The class <strong>UtilMessages</strong>.
 * 
 * Messages used by the Util package.
 * 
 * @author Sébastien Bordes
 */
public interface UtilMessages extends MessageContainer {

    /** CheckerUtility. */

    /** "{0} API is broken, no method {1} is available". */
    MessageItem BROKEN_API_NO_METHOD = create(new LogMessage("jrebirth.util.brokenApiNoMethod", JRebirthMarkers.UTIL));

    /** "{0} API is broken, the method {1} has wrong parameters, expected:{2}  provided:{3}" . */
    MessageItem BROKEN_API_WRONG_PARAMETERS = create(new LogMessage("jrebirth.util.brokenApiWrongParameters", JRebirthMarkers.UTIL));

    /** ClasspathUtility. */

    /** "The resource {0} is ignored from classpath search engine (not a zip|jar|directory)". */
    MessageItem RESOURCE_IGNORED = create(new LogMessage("jrebirth.util.resourceIgnored", JRebirthMarkers.UTIL));

    /** "Impossible to get the resource canonical path" . */
    MessageItem BAD_CANONICAL_PATH = create(new LogMessage("jrebirth.util.badCanonicalPath", JRebirthMarkers.UTIL));

    /** "Impossible to read the file" . */
    MessageItem FILE_UNREADABLE = create(new LogMessage("jrebirth.util.fileUnreadable", JRebirthMarkers.UTIL));

    /** ClassUtility. */

    /** "Impossible to build the object assignable to {0} for the class {1}" . */
    MessageItem GENERIC_TYPE_ERROR_1 = create(new LogMessage("jrebirth.util.genericTypeError1", JRebirthMarkers.UTIL));

    /** "Impossible to build the {0} object for the class {1}" . */
    MessageItem GENERIC_TYPE_ERROR_2 = create(new LogMessage("jrebirth.util.genericTypeError2", JRebirthMarkers.UTIL));

    /** "Arguments are : " . */
    MessageItem ARGUMENT_LIST = create(new LogMessage("jrebirth.util.argumentList", JRebirthMarkers.UTIL));

    /** "{0} = {1}" . */
    MessageItem ARGUMENT_DETAIL = create(new LogMessage("jrebirth.util.argumentDetail", JRebirthMarkers.UTIL));

    /** "Impossible to retrieve the constructor due to security" . */
    MessageItem NO_CONSTRUCTOR = create(new LogMessage("jrebirth.util.noConstructor", JRebirthMarkers.UTIL));

    /** "Impossible to find the annotation property : {0}" . */
    MessageItem NO_ANNOTATION_PROPERTY = create(new LogMessage("jrebirth.util.noAnnotationProperty", JRebirthMarkers.UTIL));

    /** "Impossible to retrieve value for the annotation property : {0}". */
    MessageItem NO_ANNOTATION_PROPERTY_VALUE = create(new LogMessage("jrebirth.util.noAnnotationPropertyValue", JRebirthMarkers.UTIL));

}
