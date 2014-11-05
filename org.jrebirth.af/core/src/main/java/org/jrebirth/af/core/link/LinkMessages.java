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
package org.jrebirth.af.core.link;

import static org.jrebirth.af.core.resource.Resources.create;

import org.jrebirth.af.api.resource.i18n.JRLevel;
import org.jrebirth.af.api.resource.i18n.MessageItem;
import org.jrebirth.af.core.log.JRebirthMarkers;
import org.jrebirth.af.core.resource.i18n.LogMessage;
import org.jrebirth.af.core.resource.i18n.Message;
import org.jrebirth.af.core.resource.i18n.MessageContainer;

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
    MessageItem WAVE_LOST = create(new Message("jrebirth.link.waveLost"));

    /** "Wave Lost [{1}]:  {0}". */
    MessageItem WAVE_LOST_CONTEXT = create(new Message("jrebirth.link.waveLostContext"));

    /** AbstractComponent. */

    /** "Listen {0} by {1}". */
    MessageItem LISTEN_WAVE_TYPE = create(new Message("jrebirth.link.listenWaveType"));

    /** "UnListen {0} by {1}". */
    MessageItem UNLISTEN_WAVE_TYPE = create(new Message("jrebirth.link.unlistenWaveType"));

    /** "Send Wave {0}". */
    MessageItem SEND_WAVE = create(new Message("jrebirth.link.sendWave"));

    /** "Custom method not found {0}". */
    MessageItem CUSTOM_METHOD_NOT_FOUND = create(new LogMessage("jrebirth.link.customMethodNotFound", JRLevel.Info, JRebirthMarkers.LINK));

    /** "Error while dispatching a wave". */
    MessageItem WAVE_DISPATCH_ERROR = create(new LogMessage("jrebirth.link.waveDispatchError", JRLevel.Error, JRebirthMarkers.WAVE));

    /** "Error while trying to call annoated method {0} for component {1}. " */
    MessageItem CALL_ANNOTATED_METHOD_ERROR = create(new LogMessage("jrebirth.link.callAnnotatedMethodError", JRLevel.Error, JRebirthMarkers.LINK));

    /** "Error while trying to release the component {0}. " */
    MessageItem COMPONENT_RELEASE_ERROR = create(new LogMessage("jrebirth.link.componentReleaseError", JRLevel.Error, JRebirthMarkers.LINK));

    /** NotifierBase. */

    /** "Impossible to load the UnprocessedWaveHandler , will use the default one". */
    MessageItem USE_DEFAULT_WAVE_HANDLER = create(new LogMessage("jrebirth.link.useDefaultWaveHandler", JRLevel.Error, JRebirthMarkers.LINK));

    /** "Failed to send Wave". */
    MessageItem WAVE_SENDING_ERROR = create(new LogMessage("jrebirth.link.waveSendingError", JRLevel.Error, JRebirthMarkers.WAVE));

    /** "Cannot get the right Command to process the wave : {0}". */
    MessageItem COMMAND_NOT_FOUND_ERROR = create(new LogMessage("jrebirth.link.commandNotFoundError", JRLevel.Error, JRebirthMarkers.LINK));

    /** "Cannot get the right Command to process the wave". */
    MessageItem COMMAND_NOT_FOUND_MESSAGE = create(new Message("jrebirth.link.commandNotFoundMessage"));

    /** "Cannot get the right Service to process the wave : {0}". */
    MessageItem SERVICE_NOT_FOUND_ERROR = create(new LogMessage("jrebirth.link.serviceNotFoundError", JRLevel.Error, JRebirthMarkers.LINK));

    /** "Cannot get the right Service to process the wave". */
    MessageItem SERVICE_NOT_FOUND_MESSAGE = create(new Message("jrebirth.link.serviceNotFoundMessage"));

    /** "Cannot get the right Model to process the wave : {0}". */
    MessageItem MODEL_NOT_FOUND_ERROR = create(new LogMessage("jrebirth.link.modelNotFoundError", JRLevel.Error, JRebirthMarkers.LINK));

    /** "Cannot get the right Model to process the wave". */
    MessageItem MODEL_NOT_FOUND_MESSAGE = create(new Message("jrebirth.link.modelNotFoundMessage"));

    /** "No Listener attached for wave type : {0}". */
    MessageItem NO_WAVE_LISTENER = create(new LogMessage("jrebirth.link.noWaveListener", JRLevel.Warn, JRebirthMarkers.LINK));

    /** "NB consumes : {0}". */
    MessageItem NOTIFIER_CONSUMES = create(new LogMessage("jrebirth.link.notifierConsumes", JRLevel.Info, JRebirthMarkers.WAVE));

    /** "Error while handling a wave". */
    MessageItem WAVE_HANDLING_ERROR = create(new LogMessage("jrebirth.link.waveHandlingError", JRLevel.Error, JRebirthMarkers.WAVE));

    /** "Impossible to build WaveBean instance : {0}". */
    MessageItem WAVE_BEAN_CREATION_ERROR = create(new LogMessage("jrebirth.link.waveBeanCreationError", JRLevel.Error, JRebirthMarkers.WAVE));

    /** ComponentEnhancer. */

    /** "EnhancedComponent {0} injection has failed". */
    MessageItem COMPONENT_INJECTION_FAILURE = create(new LogMessage("jrebirth.link.componentInjectionFailure", JRLevel.Error, JRebirthMarkers.LINK));

    /** BehavioredComponent. */

    /** Add behavior {0} to EnhancedComponent {1}. */
    MessageItem ADD_BEHAVIOR = create(new LogMessage("jrebirth.link.addBehavior", JRLevel.Info, JRebirthMarkers.LINK));
}
