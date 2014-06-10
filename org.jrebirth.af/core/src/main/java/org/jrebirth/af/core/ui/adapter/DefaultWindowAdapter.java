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
package org.jrebirth.af.core.ui.adapter;

import javafx.stage.WindowEvent;

import org.jrebirth.af.core.ui.AbstractBaseController;

/**
 * The class <strong>DefaultWindowAdapter</strong>.
 *
 * @author Sébastien Bordes
 *
 * @param <C> The controller class which manage this event adapter
 */
public class DefaultWindowAdapter<C extends AbstractBaseController<?, ?>> extends AbstractDefaultAdapter<C> implements WindowAdapter {

    /**
     * {@inheritDoc}
     */
    @Override
    public void window(final WindowEvent windowEvent) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void windowCloseRequest(final WindowEvent windowEvent) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void windowHidden(final WindowEvent windowEvent) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void windowHiding(final WindowEvent windowEvent) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void windowShowing(final WindowEvent windowEvent) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void windowShown(final WindowEvent windowEvent) {
        // Nothing to do yet
    }

}
