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

import org.jrebirth.af.core.resource.fxml.FXML;
import org.jrebirth.af.core.resource.fxml.FXMLItem;
import org.jrebirth.af.core.ui.Model;
import org.jrebirth.af.core.wave.Wave;

/**
 * The interface <strong>DefaultFXMLModel</strong>.
 *
 * Default implementation of the FXML model.
 *
 * @param <M> the class type of the current model
 * @author Sébastien Bordes
 */
public class DefaultFXMLModel<M extends Model> extends AbstractFXMLModel<M> {

    /** The key part prefix used to attach the fxml path to this class. */
    public static final String KEYPART_FXML_PREFIX = "fxml:";

    /** The key part prefix used to attach the resource bundle path to this class. */
    public static final String KEYPART_RB_PREFIX = "rb:";

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

        // Define fxml path and resource bundle path according to key parts provided

        // If an FXMLItem is provided, simply map it to the internal item handle
        if (!getListKeyPart().isEmpty() && getListKeyPart().get(0) instanceof FXMLItem) {

            this.fxmlItem = (FXMLItem) getListKeyPart().get(0);

            // if the first key part is a string that begins with fxml:
        } else if (!getListKeyPart().isEmpty() && getListKeyPart().get(0).toString().startsWith(KEYPART_FXML_PREFIX)) {

            // Use the string provided and append FXML extension
            final String baseName = getListKeyPart().get(0).toString().substring(KEYPART_FXML_PREFIX.length());
            this.fxmlPath = baseName.replaceAll(FXML.DOT_SEPARATOR, FXML.PATH_SEPARATOR) + FXML.FXML_EXT;

            // if the second key part is a string that begins with rb:
            if (getListKeyPart().size() > 1 && getListKeyPart().get(1).toString().startsWith(KEYPART_RB_PREFIX)) {
                this.resourcePath = getListKeyPart().get(1).toString().substring(KEYPART_RB_PREFIX.length()).replaceAll(FXML.DOT_SEPARATOR, FXML.PATH_SEPARATOR);
            } else {
                // Otherwise use the same base name as fxml file
                this.resourcePath = baseName;
            }

        } else {

            // Otherwise use the current class name to define the fxml and resource bundle file names
            String baseName = this.getClass().getCanonicalName();
            // Remove the Model suffix if any
            baseName = baseName.substring(0, baseName.lastIndexOf(Model.class.getSimpleName()));
            // Replace . by / for the fxml loader
            baseName = baseName.replaceAll(FXML.DOT_SEPARATOR, FXML.PATH_SEPARATOR);

            this.fxmlPath = baseName + FXML.FXML_EXT;
            this.resourcePath = baseName;

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
