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
package org.jrebirth.core.ui.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import org.jrebirth.core.ui.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>AbstractFXMLController</strong>.
 * 
 * @author Sébastien Bordes
 */
public abstract class AbstractFXMLController implements FXMLController {

    /** The class logger. */
    private final static Logger LOGGER = LoggerFactory.getLogger(AbstractFXMLController.class);

    /** The linked view that load this FXML component. */
    private View<?, ?, ?> view;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setView(final View<?, ?, ?> view) {
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View<?, ?, ?> getView() {
        return this.view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL url, final ResourceBundle resource) {
        LOGGER.trace("Initialize fxml node : " + url.toString());
    }

}
