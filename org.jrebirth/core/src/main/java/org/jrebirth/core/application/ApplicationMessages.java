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
package org.jrebirth.core.application;

import org.jrebirth.core.log.JRebirthMarkers;
import org.jrebirth.core.resource.i18n.LogMessage;
import org.jrebirth.core.resource.i18n.MessageItem;

import static org.jrebirth.core.resource.Resources.create;

/**
 * The class <strong>ApplicationMessages</strong>.
 * 
 * Messages used by the Application package.
 * 
 * @author Sébastien Bordes
 */
public interface ApplicationMessages {

    /** "Starting {0}" . */
    MessageItem START_APPLICATION = create(new LogMessage("application.startApplication", JRebirthMarkers.APPLICATION));

    /** "{} has started successfully". */
    MessageItem STARTED_SUCCESSFULLY = create(new LogMessage("application.startedSuccessfully", JRebirthMarkers.APPLICATION));

    /** "You must override getFirstModelClass without calling super.". */
    MessageItem OVERRIDE_FIRST_MODEL_CLASS = create(new LogMessage("application.overrideFirstModelClass", JRebirthMarkers.APPLICATION));

    /** "Error while initializing the application {0} : ". */
    MessageItem INIT_ERROR = create(new LogMessage("application.initError", JRebirthMarkers.APPLICATION));

    /** "Error while starting the application {0} : ". */
    MessageItem START_ERROR = create(new LogMessage("application.startError", JRebirthMarkers.APPLICATION));

    /** "Stopping {0}" . */
    MessageItem STOP_APPLICATION = create(new LogMessage("application.stopApplication", JRebirthMarkers.APPLICATION));

    /** "{0} has stopped successfully". */
    MessageItem STOPPED_SUCCESSFULLY = create(new LogMessage("application.stoppedSuccessfully", JRebirthMarkers.APPLICATION));

    /** "Error while stopping the application {0} : ". */
    MessageItem STOP_ERROR = create(new LogMessage("application.stopError", JRebirthMarkers.APPLICATION));

    /** ""Impossible to load CSS: {0} using folder: {1}/". */
    MessageItem CSS_LOADING_ERROR = create(new LogMessage("application.cssLoadingError", JRebirthMarkers.APPLICATION));

    /** "No style sheet has been added to the scene, will link the default.css". */
    MessageItem NO_CSS_DEFINED = create(new LogMessage("application.noCssDefined", JRebirthMarkers.APPLICATION));
}
