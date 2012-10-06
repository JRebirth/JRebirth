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

import javafx.scene.input.MouseEvent;

import org.jrebirth.core.ui.AbstractController;

/**
 * The class <strong>DefaultMouseAdapter</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @param <C> The controller class which manage this event adapter
 */
public class DefaultMouseAdapter<C extends AbstractController<?, ?>> extends AbstractDefaultAdapter<C> implements MouseAdapter {

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouse(final MouseEvent mouseEvent) {
        // Nothing to do yet must be overridden
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseDragDetected(final MouseEvent mouseEvent) {
        // Nothing to do yet must be overridden
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
        // Nothing to do yet must be overridden
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseDragged(final MouseEvent mouseEvent) {
        // Nothing to do yet must be overridden
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
        // Nothing to do yet must be overridden
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseEnteredTarget(final MouseEvent mouseEvent) {
        // Nothing to do yet must be overridden
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
        // Nothing to do yet must be overridden
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseExitedTarget(final MouseEvent mouseEvent) {
        // Nothing to do yet must be overridden
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseMoved(final MouseEvent mouseEvent) {
        // Nothing to do yet must be overridden
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
        // Nothing to do yet must be overridden
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
        // Nothing to do yet must be overridden
    }

}
