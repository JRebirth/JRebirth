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
package org.jrebirth.core.link;

import static org.jrebirth.core.resource.Resources.create;

import org.jrebirth.core.log.JRebirthMarkers;
import org.jrebirth.core.resource.i18n.LogMessage;
import org.jrebirth.core.resource.i18n.MessageContainer;
import org.jrebirth.core.resource.i18n.MessageItem;

/**
 * The class <strong>LinkMessages</strong>.
 * 
 * Messages used by the Link package.
 * 
 * @author Sébastien Bordes
 */
public interface LinkMessages extends MessageContainer {

    /** DefaultUnprocessedWaveHandler. */

    /** "Wave Lost :  {0}". */
    MessageItem WAVE_LOST = create(new LogMessage("jrebirth.link.waveLost", JRebirthMarkers.LINK));

    /** "Wave Lost [{1}]:  {0}". */
    MessageItem WAVE_LOST_CONTEXT = create(new LogMessage("jrebirth.link.waveLostContext", JRebirthMarkers.LINK));

    /** AbstractWaveReady. */

    /** "Custom method not found {0}". */
    MessageItem CUSTOM_METHOD_NOT_FOUND = create(new LogMessage("jrebirth.link.customMethodNotFound", JRebirthMarkers.LINK));

    /** "Error while dispatching a wave". */
    MessageItem WAVE_DISPATCH_ERROR = create(new LogMessage("jrebirth.link.waveDispatchError", JRebirthMarkers.WAVE));

    /** NotifierBase. */

    /** "Impossible to load the UnprocessedWaveHandler , will use the default one". */
    MessageItem USE_DEFAULT_WAVE_HANDLER = create(new LogMessage("jrebirth.link.useDefaultWaveHandler", JRebirthMarkers.LINK));

    /** "Failed to send Wave". */
    MessageItem WAVE_SENDING_ERROR = create(new LogMessage("jrebirth.link.waveSendingError", JRebirthMarkers.WAVE));

    /** "Cannot get the right Command to process the wave : {0}". */
    MessageItem COMMAND_NOT_FOUND_ERROR = create(new LogMessage("jrebirth.link.commandNotFoundError", JRebirthMarkers.LINK));

    /** "Cannot get the right Command to process the wave". */
    MessageItem COMMAND_NOT_FOUND_MESSAGE = create(new LogMessage("jrebirth.link.commandNotFoundMessage", JRebirthMarkers.LINK));

    /** "Cannot get the right Service to process the wave : {0}". */
    MessageItem SERVICE_NOT_FOUND_ERROR = create(new LogMessage("jrebirth.link.serviceNotFoundError", JRebirthMarkers.LINK));

    /** "Cannot get the right Service to process the wave". */
    MessageItem SERVICE_NOT_FOUND_MESSAGE = create(new LogMessage("jrebirth.link.serviceNotFoundMessage", JRebirthMarkers.LINK));

    /** "Cannot get the right Model to process the wave : {0}". */
    MessageItem MODEL_NOT_FOUND_ERROR = create(new LogMessage("jrebirth.link.modelNotFoundError", JRebirthMarkers.LINK));

    /** "Cannot get the right Model to process the wave". */
    MessageItem MODEL_NOT_FOUND_MESSAGE = create(new LogMessage("jrebirth.link.modelNotFoundMessage", JRebirthMarkers.LINK));

    /** "No Listener attached for wave type : {0}". */
    MessageItem NO_WAVE_LISTENER = create(new LogMessage("jrebirth.link.noWaveListener", JRebirthMarkers.LINK));

    /** "NB consumes : {0}". */
    MessageItem NOTIFIER_CONSUMES = create(new LogMessage("jrebirth.link.notifierConsumes", JRebirthMarkers.WAVE));

    /** "Error while handling a wave". */
    MessageItem WAVE_HANDLING_ERROR = create(new LogMessage("jrebirth.link.waveHandlingError", JRebirthMarkers.WAVE));

}
