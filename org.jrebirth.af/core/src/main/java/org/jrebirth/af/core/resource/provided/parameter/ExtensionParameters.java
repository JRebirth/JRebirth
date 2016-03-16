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
package org.jrebirth.af.core.resource.provided.parameter;

import static org.jrebirth.af.core.resource.Resources.create;

//import org.jrebirth.af.api.resource.image.ImageParams;
import org.jrebirth.af.api.resource.parameter.ParameterItem;
import org.jrebirth.af.core.component.factory.DefaultComponentFactory;
import org.jrebirth.af.core.link.DefaultUnprocessedWaveHandler;
import org.jrebirth.af.core.ui.fxml.DefaultFXMLControllerFactory;

/**
 * The class <strong>JRebirthParameters</strong>.
 *
 * Parameters used by JRebirth Application Framework itself
 *
 * @author Sébastien Bordes
 */
public interface ExtensionParameters {

    /**************************************************************************************/
    /** _______________________________Customizable Classes._____________________________ */
    /**************************************************************************************/

    /** The handler used while running in developer mode to manage unprocessed wave. */
    ParameterItem<Class<?>> UNPROCESSED_WAVE_HANDLER = create("unprocessedWaveHandler", (Class<?>) DefaultUnprocessedWaveHandler.class);

    /** The factory used to create components. */
    ParameterItem<Class<?>> COMPONENT_FACTORY = create("componentFactory", (Class<?>) DefaultComponentFactory.class);

    /** The factory used to create FXMLController. */
    ParameterItem<Class<?>> FXML_CONTROLLER_FACTORY = create("fxmlControllerFactory", (Class<?>) DefaultFXMLControllerFactory.class);

}
