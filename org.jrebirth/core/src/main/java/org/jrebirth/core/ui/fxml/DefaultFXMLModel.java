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

import org.jrebirth.core.ui.Model;
import org.jrebirth.core.wave.Wave;

/**
 * The interface <strong>DefaultFXMLModel</strong>.
 * 
 * Default implementation of the FXML model.
 * 
 * @param <M> the class type of the current model
 * @author Sébastien Bordes
 */
public class DefaultFXMLModel<M extends Model> extends AbstractFXMLModel<M> {

    /** The fxml path. */
    private String fxmlPath;

    /** The resource path. */
    private String resourcePath;

    /**
     * {@inheritDoc}
     */
    @Override
    public void showView() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hideView() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getFXMLPath() {
        return this.fxmlPath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getFXMLBundlePath() {
        return this.resourcePath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fxmlPreInitialize() {
        if (!getListModelObject().isEmpty()) {
            this.fxmlPath = getListModelObject().get(0).toString();
        }
        if (getListModelObject().size() > 1) {
            this.resourcePath = getListModelObject().get(1).toString();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performShowView(final Wave wave) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performHideView(final Wave wave) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitialize() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeInnerModels() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processAction(final Wave wave) {
        // Nothing to do yet
    }
}
