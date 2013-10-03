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

import org.jrebirth.core.resource.fxml.FXMLItem;
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

    /** The fxml resource. */
    private FXMLItem fxmlItem;

    /**
     * @return Returns the fxmlItem.
     */
    @Override
    protected FXMLItem getFXMLItem() {
        return this.fxmlItem;
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
        if (!getListModelObject().isEmpty() && getListModelObject().get(0) instanceof FXMLItem) {

            this.fxmlItem = (FXMLItem) getListModelObject().get(0);

        } else {
            if (!getListModelObject().isEmpty()) {
                this.fxmlPath = getListModelObject().get(0).toString();
            }
            if (getListModelObject().size() > 1) {
                this.resourcePath = getListModelObject().get(1).toString();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void bind() {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initInnerModels() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void showView() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void hideView() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processWave(final Wave wave) {
        // Nothing to do yet
    }

}
