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
package org.jrebirth.af.core.ui;

import static org.jrebirth.af.core.resource.Resources.create;

import org.jrebirth.af.api.resource.i18n.JRLevel;
import org.jrebirth.af.api.resource.i18n.MessageItem;
import org.jrebirth.af.core.log.JRebirthMarkers;
import org.jrebirth.af.core.resource.i18n.LogMessage;
import org.jrebirth.af.core.resource.i18n.Message;
import org.jrebirth.af.core.resource.i18n.MessageContainer;

/**
 * The class <strong>UIMessages</strong>.
 *
 * Messages used by the UI package.
 *
 * @author Sébastien Bordes
 */
public interface UIMessages extends MessageContainer {

    /** AbstractView. */

    /** "{0} creation has failed ". */
    MessageItem CREATION_FAILURE = create(new LogMessage("jrebirth.ui.creationFailure", JRLevel.Error, JRebirthMarkers.UI));

    /** "Impossible to process annotation for root node : {0}" . */
    MessageItem VIEW_ANNO_PROCESSING_FAILURE = create(new LogMessage("jrebirth.ui.viewAnnoProcessingFailure", JRLevel.Warn, JRebirthMarkers.UI));

    /** "Impossible to process annotation for Node property : {0}-{1}" . */
    MessageItem NODE_ANNO_PROCESSING_FAILURE = create(new LogMessage("jrebirth.ui.nodeAnnoProcessingFailure", JRLevel.Warn, JRebirthMarkers.UI));

    /** "Impossible to process annotation for Animation property : {0}-{1}" . */
    MessageItem ANIM_ANNO_PROCESSING_FAILURE = create(new LogMessage("jrebirth.ui.animAnnoProcessingFailure", JRLevel.Warn, JRebirthMarkers.UI));

    /** AnnotationEventHandler. */

    /** "{0} must have a method => void {1} ({2} event)\{\}". */
    MessageItem NO_EVENT_CALLBACK = create(new Message("jrebirth.ui.noEventCallback"));

    /** "Impossible to handle the event" . */
    MessageItem EVENT_HANDLING_IMPOSSIBLE = create(new LogMessage("jrebirth.ui.eventHandlingImpossible", JRLevel.Error, JRebirthMarkers.UI));
}
