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
package org.jrebirth.core.ui.fxml;

import javafx.scene.Node;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.AbstractBaseModel;
import org.jrebirth.core.ui.Model;
import org.jrebirth.core.ui.NullView;
import org.jrebirth.core.wave.JRebirthWaves;
import org.jrebirth.core.wave.Wave;

/**
 * 
 * The interface <strong>DefaultModel</strong>.
 * 
 * Default implementation of the model.
 * 
 * @author Sébastien Bordes
 * 
 * @param <M> the class type of the current model
 * @param <V> the class type of the view managed by this model
 */
public abstract class FXMLModel<M extends Model> extends AbstractBaseModel<M, NullView> {

    /** The fxmlComponent that wrap the node and its controller. */
    private FXMLComponent fxmlComponent;

    /**
     * 
     * TODO To complete.
     * 
     * @return
     */
    protected abstract String getFXMLPath();

    /**
     * 
     * TODO To complete.
     * 
     * @return
     */
    protected abstract String getFXMLBundlePath();

    /**
     * {@inheritDoc}
     */
    @Override
    protected final void initialize() throws CoreException {
        // Do generic stuff
        listen(JRebirthWaves.SHOW_VIEW);
        listen(JRebirthWaves.HIDE_VIEW);

        // Do custom stuff
        customInitialize();
    }

    /**
     * Perform show view.
     * 
     * @param wave the wave that trigger the action
     */
    public abstract void performShowView(final Wave wave);

    /**
     * Perform hide view.
     * 
     * @param wave the wave that trigger the action
     */
    public abstract void performHideView(final Wave wave);

    /**
     * {@inheritDoc}
     */
    @Override
    public Node getRootNode() {
        return this.fxmlComponent.getNode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeInnerModels() {
        // Nothing to do generic
    }

}
