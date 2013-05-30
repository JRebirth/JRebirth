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
import org.jrebirth.core.ui.View;
import org.jrebirth.core.wave.JRebirthWaves;
import org.jrebirth.core.wave.Wave;

/**
 * The interface <strong>FXMLModel</strong>.
 * 
 * Default implementation used to manage FXML file.
 * 
 * @author Sébastien Bordes
 * 
 * @param <M> the class type of the current model
 */
public abstract class AbstractFXMLModel<M extends Model> extends AbstractBaseModel<M, NullView> {

    /** The fxmlComponent that wrap the node and its controller. */
    private FXMLComponent fxmlComponent;

    /**
     * Return the fxml path of the the file to load.
     * 
     * @see FXMLUtils
     * 
     * @return the fxml path
     */
    protected abstract String getFXMLPath();

    /**
     * Return the bunlde path of the the properties file to load.
     * 
     * @see FXMLUtils
     * 
     * @return the bundle path
     */
    protected abstract String getFXMLBundlePath();

    /**
     * {@inheritDoc}
     */
    @Override
    protected final void initInternalModel() throws CoreException {

        fxmlPreInitialize();

        // Do generic stuff
        listen(JRebirthWaves.SHOW_VIEW);
        listen(JRebirthWaves.HIDE_VIEW);

        if (getFXMLPath() != null) {
            this.fxmlComponent = FXMLUtils.loadFXML(this, getFXMLPath(), getFXMLBundlePath());
        }

        // Do custom stuff
        initModel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final void bindInternal() {
        // Auto bound !

        // Do custom binding stuff
        bind();
    }

    /**
     * Pre init.
     */
    protected abstract void fxmlPreInitialize();

    /**
     * {@inheritDoc}
     */
    @Override
    public final View<?, ?, ?> getView() {
        return NullView.NULL_VIEW;
    }

    /**
     * Perform show view.
     * 
     * Method handler for Wave JRebirthWaves.SHOW_VIEW
     * 
     * @param wave the wave that trigger the action
     */
    @Override
    public final void doShowView(final Wave wave) {
        showInternalView();
    }

    /**
     * Perform hide view.
     * 
     * Method handler for Wave JRebirthWaves.HIDE_VIEW
     * 
     * @param wave the wave that trigger the action
     */
    @Override
    public final void doHideView(final Wave wave) {
        hideInternalView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Node getRootNode() {
        return this.fxmlComponent.getNode();
    }

}
