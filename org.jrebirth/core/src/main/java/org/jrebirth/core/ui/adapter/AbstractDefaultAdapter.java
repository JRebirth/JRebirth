/**
 * Copyright JRebirth.org © 2011-2012 
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
package org.jrebirth.core.ui.adapter;

import org.jrebirth.core.ui.AbstractController;

/**
 * The class <strong>AbstractDefaultAdapter</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @param <C> The controller class which manage this event adapter
 */
public abstract class AbstractDefaultAdapter<C extends AbstractController<?, ?>> implements EventAdapter<C> {

    /** The controller that manage these events. */
    private C controller;

    /**
     * @param controller The controller to set.
     */
    @Override
    public void setController(final C controller) {
        this.controller = controller;
    }

    /**
     * @return Returns the controller.
     */
    public C getController() {
        return this.controller;
    }

}
