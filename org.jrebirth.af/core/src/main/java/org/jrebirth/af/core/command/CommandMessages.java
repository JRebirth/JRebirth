/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2018
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
package org.jrebirth.af.core.command;

import static org.jrebirth.af.core.resource.Resources.create;

import org.jrebirth.af.api.resource.i18n.JRLevel;
import org.jrebirth.af.api.resource.i18n.MessageItem;
import org.jrebirth.af.core.log.JRebirthMarkers;
import org.jrebirth.af.core.resource.i18n.LogMessage;
import org.jrebirth.af.core.resource.i18n.MessageContainer;

/**
 * The class <strong>CommandMessages</strong>.
 *
 * Messages used by the Command package.
 *
 * @author Sébastien Bordes
 */
public interface CommandMessages extends MessageContainer {

    /** CommandRunnable. */

    /** "CommandException:" . */
    MessageItem CMD_EXCEPTION = create(new LogMessage("jrebirth.command.cmdException", JRLevel.Error, JRebirthMarkers.COMMAND));

    /** "Unplanned failure while performing command :" . */
    MessageItem UNPLANNED_FAILURE = create(new LogMessage("jrebirth.command.unplannedFailure", JRLevel.Error, JRebirthMarkers.COMMAND));

}
