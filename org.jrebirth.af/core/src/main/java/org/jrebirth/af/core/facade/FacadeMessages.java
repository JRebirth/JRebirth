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
package org.jrebirth.af.core.facade;

import org.jrebirth.af.core.log.JRLevel;
import org.jrebirth.af.core.log.JRebirthMarkers;
import org.jrebirth.af.core.resource.i18n.LogMessage;
import org.jrebirth.af.core.resource.i18n.Message;
import org.jrebirth.af.core.resource.i18n.MessageContainer;
import org.jrebirth.af.core.resource.i18n.MessageItem;

import static org.jrebirth.af.core.resource.Resources.create;

/**
 * The class <strong>FacadeMessages</strong>.
 *
 * Messages used by the Facade package.
 *
 * @author Sébastien Bordes
 */
public interface FacadeMessages extends MessageContainer {

    /** DefaultComponentFactory. */

    /** "Impossible to build the the component {0}". */
    MessageItem COMPONENT_BUILD_ERROR = create(new Message("jrebirth.facade.componentBuildError"));

    /** GlobalFacadeBase. */

    /** "Impossible to load the custom ComponentFactory, will use the default one". */
    MessageItem COMPONENT_CUSTOM_FACTORY_ERROR = create(new LogMessage("jrebirth.facade.componentFactoryError", JRLevel.Error, JRebirthMarkers.FACADE));

    /** "Create the JRebirth Thread Pool". */
    MessageItem JTP_CREATION = create(new LogMessage("jrebirth.facade.jtpCreation", JRLevel.Trace, JRebirthMarkers.FACADE));

    /** "JREvent>>". */
    MessageItem JREBIRTH_EVENT = create(new LogMessage("jrebirth.facade.jrebirthEvent", JRLevel.Info, JRebirthMarkers.JREVENT));

    /** AbstractFacade. */

    /** "Component Retrieval Error". */
    MessageItem COMPONENT_RETRIEVAL_ERROR = create(new LogMessage("jrebirth.facade.componentRetrievalError", JRLevel.Error, JRebirthMarkers.FACADE));

}
