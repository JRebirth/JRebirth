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
package org.jrebirth.af.core.ui.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import org.jrebirth.af.core.log.JRLogger;
import org.jrebirth.af.core.log.JRLoggerFactory;
import org.jrebirth.af.core.ui.Model;
import org.jrebirth.af.core.ui.View;

/**
 * The class <strong>AbstractFXMLController</strong>.
 *
 * @author Sébastien Bordes
 *
 * @param <M> The model responsible of the view
 * @param <V> The view hosting the FXML component
 */
public abstract class AbstractFXMLController<M extends Model, V extends View<M, ?, ?>> implements FXMLController<M, V> {

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(AbstractFXMLController.class);

    /** The linked model that manage the view that load this FXML component or the root model for included fxml. */
    private M model;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setModel(final M model) {
        this.model = model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public M getModel() {
        return this.model;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public V getView() {
        return (V) this.model.getView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL url, final ResourceBundle resource) {
        LOGGER.log(FXMLMessages.INIT_FXML_NODE, url.toString());
    }

}
