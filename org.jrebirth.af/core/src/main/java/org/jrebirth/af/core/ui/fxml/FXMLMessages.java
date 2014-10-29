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
package org.jrebirth.af.core.ui.fxml;

import static org.jrebirth.af.core.resource.Resources.create;

import org.jrebirth.af.api.log.JRLevel;
import org.jrebirth.af.api.resource.i18n.MessageItem;
import org.jrebirth.af.core.log.JRebirthMarkers;
import org.jrebirth.af.core.resource.i18n.LogMessage;
import org.jrebirth.af.core.resource.i18n.Message;
import org.jrebirth.af.core.resource.i18n.MessageContainer;

/**
 * The class <strong>FXMLMessages</strong>.
 *
 * Messages used by the ui.fxml package.
 *
 * @author Sébastien Bordes
 */
public interface FXMLMessages extends MessageContainer {

    /** FXMLUtils. */

    /** "Resource Bundle is missing: {0}". */
    MessageItem MISSING_RESOURCE_BUNDLE = create(new LogMessage("jrebirth.ui.fxml.missingResourceBundle", JRLevel.Error, JRebirthMarkers.FXML));

    /** "FXML Error : {0}" . */
    MessageItem FXML_ERROR_NODE_LABEL = create(new Message("jrebirth.ui.fxml.fxmlErrorNodeLabel"));

    /** "The FXML node doesn't exist : {0}" . */
    MessageItem FXML_NODE_DOESNT_EXIST = create(new Message("jrebirth.ui.fxml.fxmlNodeDoesntExist"));

    /** "The FXML controller must extends the FXMLController class : {0}" . */
    MessageItem BAD_FXML_CONTROLLER_ANCESTOR = create(new Message("jrebirth.ui.fxml.badFxmlControllerAncestor"));

    /** AbstractFXMLController. */

    /** "Initialize fxml node : {0}". */
    MessageItem INIT_FXML_NODE = create(new LogMessage("jrebirth.ui.fxml.initFxmlNode", JRLevel.Trace, JRebirthMarkers.FXML));

    /** DefaultFXMLControllerBuilder. */

    /** "Initialize fxml node : {0}". */
    MessageItem DEFAULT_CTRLR_CREATION_ERROR = create(new LogMessage("jrebirth.ui.fxml.defaultCtrlrCreationError", JRLevel.Trace, JRebirthMarkers.FXML));

}
