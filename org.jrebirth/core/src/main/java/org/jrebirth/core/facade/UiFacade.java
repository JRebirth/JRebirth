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
package org.jrebirth.core.facade;

import org.jrebirth.core.event.EventType;
import org.jrebirth.core.ui.Model;

/**
 * 
 * The class <strong>UiFacade</strong>.
 * 
 * @author Sébastien Bordes
 */
public class UiFacade extends AbstractFacade<Model> {

    /**
     * Default Constructor.
     * 
     * @param globalFacade the global facade
     */
    public UiFacade(final GlobalFacade globalFacade) {
        super(globalFacade);
        getGlobalFacade().trackEvent(EventType.CREATE_UI_FACADE, globalFacade.getClass(), this.getClass());
    }

}
