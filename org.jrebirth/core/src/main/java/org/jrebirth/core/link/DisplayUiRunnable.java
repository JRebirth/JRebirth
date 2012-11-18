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
package org.jrebirth.core.link;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import org.jrebirth.core.concurrent.AbstractJrbRunnable;
import org.jrebirth.core.exception.JRebirthThreadException;
import org.jrebirth.core.ui.Model;
import org.jrebirth.core.wave.JRebirthWaves;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.Wave.Status;

/**
 * The class <strong>DisplayUiRunnable</strong>.
 * 
 * @author Sébastien Bordes
 */
final class DisplayUiRunnable extends AbstractJrbRunnable {
    /**
     * The <code>model</code>.
     */
    private final Model model;
    /**
     * The <code>wave</code>.
     */
    private final Wave wave;

    /**
     * Default constructor only visible by link package.
     * 
     * @param runnableName The name of the runnable
     * @param model the model to display
     * @param wave the wave that contain all relavant information
     */
    DisplayUiRunnable(final String runnableName, final Model model, final Wave wave) {
        super(runnableName);
        this.model = model;
        this.wave = wave;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void runInto() throws JRebirthThreadException {
        if (this.wave.contains(JRebirthWaves.ATTACH_UI_NODE_PLACEHOLDER)) {
            // Add an Ui view into a the place holder provided
            final ObjectProperty<Node> nodePlaceHolder = this.wave.get(JRebirthWaves.ATTACH_UI_NODE_PLACEHOLDER);
            nodePlaceHolder.setValue(this.model.getView().getRootNode());

        } else if (this.wave.contains(JRebirthWaves.ADD_UI_CHILDREN_PLACEHOLDER)) {
            // Add an Ui view into a children list of its parent container
            final ObservableList<Node> childrenPlaceHolder = this.wave.get(JRebirthWaves.ADD_UI_CHILDREN_PLACEHOLDER);
            childrenPlaceHolder.add(this.model.getView().getRootNode());
        }
        // We can consume the wave because the link is done synchronously into the JAT
        this.wave.setStatus(Status.Consumed);
    }
}
