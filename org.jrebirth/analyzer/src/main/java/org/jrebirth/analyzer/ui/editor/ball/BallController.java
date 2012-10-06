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
package org.jrebirth.analyzer.ui.editor.ball;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import org.jrebirth.analyzer.ui.editor.EditorWave;
import org.jrebirth.analyzer.ui.properties.PropertiesWaveItem;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.DefaultController;
import org.jrebirth.core.ui.adapter.MouseAdapter;
import org.jrebirth.core.wave.WaveData;

/**
 * The class <strong>BallController</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class BallController extends DefaultController<BallModel, BallView> implements MouseAdapter {

    /**
     * Default Constructor.
     * 
     * @param view the view to control
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public BallController(final BallView view) throws CoreException {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeEventHandlers() throws CoreException {

        // Register mouse clicked handler
        getView().getRootNode().setOnMouseClicked(getMouseHandler());

        // getView().getShowTransition().setOnFinished(getActionHandler());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouse(final MouseEvent mouseEvent) {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseDragDetected(final MouseEvent mouseEvent) {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {

        if (mouseEvent.getSource() instanceof Node) {
            // Send Event Selected Wave
            getModel().send(EditorWave.EVENT_SELECTED, new WaveData(PropertiesWaveItem.EVENT_OBJECT, getModel().getEventModel()));
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseDragged(final MouseEvent mouseEvent) {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseEnteredTarget(final MouseEvent mouseEvent) {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseExitedTarget(final MouseEvent mouseEvent) {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseMoved(final MouseEvent mouseEvent) {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
        // Nothing to do yet

    }

}
