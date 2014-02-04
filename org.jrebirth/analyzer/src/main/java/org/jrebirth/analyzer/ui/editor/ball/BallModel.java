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
package org.jrebirth.analyzer.ui.editor.ball;

import org.jrebirth.analyzer.ui.editor.EditorWaves;
import org.jrebirth.core.facade.JRebirthEvent;
import org.jrebirth.core.ui.DefaultModel;
import org.jrebirth.core.wave.Wave;

/**
 * The class <strong>BallModel</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class BallModel extends DefaultModel<BallModel, BallView> {

    /** The ballModel reference. */
    private BallModel referenceBallModel;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {
        listen(EditorWaves.DO_SELECT_EVENT);
    }

    /**
     * Call when event previous button is pressed.
     * 
     * @param eventSelected the selected event
     * @param wave the wave received
     */
    public void doEventSelected(final JRebirthEvent eventSelected, final Wave wave) {
        // Same object (reference)
        if (getEventModel() == eventSelected) {
            getView().getScaleTransition().play();
        } else {
            getView().getScaleTransition().stop();
            getView().resetScale();
        }
    }

    /**
     * @return Returns the referenceBallModel.
     */
    BallModel getReferenceBallModel() {
        return this.referenceBallModel;
    }

    /**
     * @return Returns the eventModel.
     */
    public JRebirthEvent getEventModel() {
        return (JRebirthEvent) getFirstKeyPart();
    }

    /**
     * Show the ball node.
     */
    public void show() {
        getView().setStyle(getEventModel().getEventType()); // TODO remove
        getView().start();
    }

    /**
     * TODO To complete.
     */
    public void hide() {
        getView().hide();
    }

    /**
     * TODO To complete.
     */
    public void destroy() {
        getView().hide();
    }

    /**
     * TODO To complete.
     */
    public void access() {
        // Nothing to do yet

    }
}
