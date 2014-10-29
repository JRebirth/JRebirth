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
package org.jrebirth.af.core.application;

import static org.jrebirth.af.core.resource.Resources.create;

import org.jrebirth.af.api.log.JRLevel;
import org.jrebirth.af.api.resource.i18n.MessageItem;
import org.jrebirth.af.core.log.JRebirthMarkers;
import org.jrebirth.af.core.resource.i18n.LogMessage;
import org.jrebirth.af.core.resource.i18n.Message;
import org.jrebirth.af.core.resource.i18n.MessageContainer;

/**
 * The class <strong>ApplicationMessages</strong>.
 *
 * Messages used by the Application package.
 *
 * @author Sébastien Bordes
 */
public interface ApplicationMessages extends MessageContainer {

    /** AbstractApplication. */

    /** "Starting {0}" . */
    MessageItem START_APPLICATION = create(new LogMessage("jrebirth.application.startApplication", JRLevel.Trace, JRebirthMarkers.APPLICATION));

    /** "{} has started successfully". */
    MessageItem STARTED_SUCCESSFULLY = create(new LogMessage("jrebirth.application.startedSuccessfully", JRLevel.Trace, JRebirthMarkers.APPLICATION));

    /** "Error while initializing the application {0} : ". */
    MessageItem INIT_ERROR = create(new LogMessage("jrebirth.application.initError", JRLevel.Error, JRebirthMarkers.APPLICATION));

    /** "Error while starting the application {0} : ". */
    MessageItem START_ERROR = create(new LogMessage("jrebirth.application.startError", JRLevel.Error, JRebirthMarkers.APPLICATION));

    /** "Stopping {0}" . */
    MessageItem STOP_APPLICATION = create(new LogMessage("jrebirth.application.stopApplication", JRLevel.Trace, JRebirthMarkers.APPLICATION));

    /** "{0} has stopped successfully". */
    MessageItem STOPPED_SUCCESSFULLY = create(new LogMessage("jrebirth.application.stoppedSuccessfully", JRLevel.Trace, JRebirthMarkers.APPLICATION));

    /** "Error while stopping the application {0} : ". */
    MessageItem STOP_ERROR = create(new LogMessage("jrebirth.application.stopError", JRLevel.Error, JRebirthMarkers.APPLICATION));

    /** ""Impossible to load CSS: {0} using folder: {1}/". */
    MessageItem CSS_LOADING_ERROR = create(new LogMessage("jrebirth.application.cssLoadingError", JRLevel.Error, JRebirthMarkers.APPLICATION));

    /** "No style sheet has been added to the scene, will link the default.css". */
    MessageItem NO_CSS_DEFINED = create(new LogMessage("jrebirth.application.noCssDefined", JRLevel.Warn, JRebirthMarkers.APPLICATION));

    /** "Attach JAT Uncaught Exception Handler." . */
    MessageItem ATTACH_JAT_UEH = create(new Message("jrebirth.application.attachJatUeh"));

    /** "Attach JIT Uncaught Exception Handler." . */
    MessageItem ATTACH_JIT_UEH = create(new Message("jrebirth.application.attachJitUeh"));

    /** DefaultApplication. */

    /** "You must override getFirstModelClass without calling super.". */
    MessageItem OVERRIDE_FIRST_MODEL_CLASS = create(new Message("jrebirth.application.overrideFirstModelClass"));

}
