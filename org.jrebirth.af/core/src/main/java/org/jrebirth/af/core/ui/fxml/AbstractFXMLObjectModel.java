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

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.resource.fxml.FXMLItem;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.ui.NullView;
import org.jrebirth.af.api.ui.fxml.FXMLComponent;
import org.jrebirth.af.api.ui.fxml.FXMLController;
import org.jrebirth.af.core.resource.fxml.FXML;
import org.jrebirth.af.core.ui.object.AbstractObjectModel;
import org.jrebirth.af.core.wave.JRebirthWaves;

/**
 * The interface <strong>FXMLModel</strong>.
 *
 * Default implementation used to manage FXML file.
 *
 * @author Sébastien Bordes
 *
 * @param <M> the class type of the current model
 * @param <O> the class type of the bindable object
 */
public abstract class AbstractFXMLObjectModel<M extends Model, O extends Object> extends AbstractObjectModel<M, NullView, O> {

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

    /** The fxmlComponent that wrap the node and its controller. */
    private FXMLComponent fxmlComponent;

    /**
     * Return the fxml item used used to build the fxml component.
     *
     * @see FXMLItem
     *
     * @return the fxml item used used to build the fxml component
     */
    protected FXMLItem getFXMLItem() {
        return this.fxmlItem;
    }

    /**
     * Return the fxml path of the the file to load.
     *
     * @see FXMLUtils
     *
     * @return the fxml path
     */
    protected String getFXMLPath() {
        return this.fxmlPath;
    }

    /**
     * Return the bundle path of the the properties file to load.
     *
     * @see FXMLUtils
     *
     * @return the bundle path
     */
    protected String getFXMLBundlePath() {
        return this.resourcePath;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    protected final void initInternalModel() throws CoreException {

        fxmlPreInitialize();

        // Do generic stuff
        listen(JRebirthWaves.SHOW_VIEW);
        listen(JRebirthWaves.HIDE_VIEW);

        if (getFXMLItem() != null) {
            this.fxmlComponent = getFXMLItem().get();
            if (this.fxmlComponent.getController() != null) {
                // Attach manually the model to the FXMLController, because the FXMLBuilder#buildComponent hadn't done it
                this.fxmlComponent.getController().setModel(this);
            }
        } else if (getFXMLPath() != null) {
            this.fxmlComponent = FXMLUtils.loadFXML(this, getFXMLPath(), getFXMLBundlePath());
        }

        // Allow to release the model if the root business object doesn't exist anymore
        getRootNode().parentProperty().addListener(new ChangeListener<Node>() {

            @Override
            public void changed(final ObservableValue<? extends Node> observable, final Node oldValue, final Node newValue) {
                if (newValue == null) {
                    release();
                    getRootNode().parentProperty().removeListener(this);
                }
            }

        });

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
    public Node getRootNode() {
        return this.fxmlComponent.getNode();
    }

    /**
     * Return the Controller associated to the FXML file.
     *
     * @return the FXML controller
     */
    @SuppressWarnings("unchecked")
    public FXMLController<M, ?> getFXMLController() {
        return this.fxmlComponent.getController();
    }

}
