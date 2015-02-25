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
package org.jrebirth.af.core.service;

import static org.jrebirth.af.core.resource.Resources.create;

import org.jrebirth.af.api.resource.i18n.JRLevel;
import org.jrebirth.af.api.resource.i18n.MessageItem;
import org.jrebirth.af.core.log.JRebirthMarkers;
import org.jrebirth.af.core.resource.i18n.LogMessage;
import org.jrebirth.af.core.resource.i18n.MessageContainer;

/**
 * The class <strong>ServiceMessages</strong>.
 *
 * Messages used by the Service package.
 *
 * @author Sébastien Bordes
 */
public interface ServiceMessages extends MessageContainer {

    /** AbstractService. */

    /** "Wave processed without any wave type for service {0}". */
    MessageItem NO_WAVE_TYPE_DEFINED = create(new LogMessage("jrebirth.service.noWaveTypeDefined", JRLevel.Error, JRebirthMarkers.SERVICE));

    /** ServiceTaskReturnWaveListener. */

    /** "{0} Consumes wave {1}" . */
    MessageItem SERVICE_TASK_RETURN_CONSUMES = create(new LogMessage("jrebirth.service.serviceTaskReturnConsumes", JRLevel.Trace, JRebirthMarkers.SERVICE));

    /** "{0} experience a service task failure for wave {1}" . */
    MessageItem SERVICE_TASK_HAS_FAILED = create(new LogMessage("jrebirth.service.serviceTaskHasFailed", JRLevel.Error, JRebirthMarkers.SERVICE));

    /** ServiceTask. */

    /** "{0} Consumes wave (noReturn) {1}". */
    MessageItem NO_RETURN_WAVE_CONSUMED = create(new LogMessage("jrebirth.service.noReturnedWaveConsumed", JRLevel.Trace, JRebirthMarkers.SERVICE));

    /** "No Return WaveType defined for this WaveType {0}". */
    MessageItem NO_RETURNED_WAVE_TYPE_DEFINED = create(new LogMessage("jrebirth.service.noReturnedWaveTypeDefined", JRLevel.Error, JRebirthMarkers.SERVICE));

    /** "The Return WaveType must contain at least one WaveItem to wrap the service return". */
    MessageItem NO_RETURNED_WAVE_ITEM = create(new LogMessage("jrebirth.service.noReturnedWaveItem", JRLevel.Error, JRebirthMarkers.SERVICE));

    /** "Unable to perform the Service Task {0}" . */
    MessageItem SERVICE_TASK_ERROR = create(new LogMessage("jrebirth.service.serviceTaskError", JRLevel.Error, JRebirthMarkers.SERVICE));

    /** "{0} - thrown by the Service Task {1}" . */
    MessageItem SERVICE_TASK_EXCEPTION = create(new LogMessage("jrebirth.service.serviceTaskException", JRLevel.Warn, JRebirthMarkers.SERVICE));

    /** "{0} - thrown by the Service Task {1} and handled with a Wave" . */
    MessageItem SERVICE_TASK_HANDLE_EXCEPTION = create(new LogMessage("jrebirth.service.serviceTaskHandleException", JRLevel.Warn, JRebirthMarkers.SERVICE));

    /** "{0} - thrown by the Service Task {1} and not managed" . */
    MessageItem SERVICE_TASK_NOT_MANAGED_EXCEPTION = create(new LogMessage("jrebirth.service.serviceTaskNotManagedException", JRLevel.Warn, JRebirthMarkers.SERVICE));

    /** ServiceUtility. */

    /** "Impossible to count lines for file: {0}" . */
    MessageItem COUNT_LINES_ERROR = create(new LogMessage("jrebirth.service.countLinesError", JRLevel.Error, JRebirthMarkers.SERVICE));

}
