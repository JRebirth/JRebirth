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

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.util.Callback;
import org.jrebirth.af.api.exception.CoreRuntimeException;
import org.jrebirth.af.api.log.JRLogger;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.ui.fxml.FXMLController;
import org.jrebirth.af.api.ui.fxml.FXMLControllerFactory;
import org.jrebirth.af.core.log.JRLoggerFactory;
import org.jrebirth.af.core.resource.provided.parameter.ExtensionParameters;
import org.jrebirth.af.core.util.ParameterUtility;

import java.io.IOException;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * The class <strong>FXMLUtils</strong>.
 *
 * @author Sébastien Bordes
 */
public final class FXMLUtils implements FXMLMessages {

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(FXMLUtils.class);

    /**
     * Private constructor.
     */
    private FXMLUtils() {
        // Nothing to do
    }

    /**
     * Load a FXML component without resource bundle.
     *
     * The fxml path could be :
     * <ul>
     * <li>Relative : fxml file will be loaded with the classloader of the given model class</li>
     * <li>Absolute : fxml file will be loaded with default thread class loader, packages must be separated by / character</li>
     * </ul>
     *
     * @param model the model that will manage the fxml node
     * @param fxmlPath the fxml string path
     *
     * @return a FXMLComponent object that wrap a fxml node with its controller
     *
     * @param <M> the model type that will manage this fxml node
     */
    public static <M extends Model> FXMLComponentBase loadFXML(final M model, final String fxmlPath) {
        return loadFXML(model, fxmlPath, null);
    }

    /**
     * Load a FXML component.
     *
     * The fxml path could be :
     * <ul>
     * <li>Relative : fxml file will be loaded with the classloader of the given model class</li>
     * <li>Absolute : fxml file will be loaded with default thread class loader, packages must be separated by / character</li>
     * </ul>
     *
     * @param model the model that will manage the fxml node
     * @param fxmlPath the fxml string path
     * @param bundlePath the bundle string path
     *
     * @return a FXMLComponent object that wrap a fxml node with its controller
     *
     * @param <M> the model type that will manage this fxml node
     */
    @SuppressWarnings("unchecked")
    public static <M extends Model> FXMLComponentBase loadFXML(final M model, final String fxmlPath, final String bundlePath) {

        final FXMLLoader fxmlLoader = new FXMLLoader();

        final Callback<Class<?>, Object> fxmlControllerFactory = (Callback<Class<?>, Object>) ParameterUtility.buildCustomizableClass(ExtensionParameters.FXML_CONTROLLER_FACTORY,
                                                                                                                                      DefaultFXMLControllerFactory.class, FXMLControllerFactory.class);
        if (fxmlControllerFactory instanceof FXMLControllerFactory) {
            ((FXMLControllerFactory) fxmlControllerFactory).relatedModel(model);
        }

        // Use Custom controller factory to attach the root model to the controller
        fxmlLoader.setControllerFactory(fxmlControllerFactory);

        fxmlLoader.setLocation(convertFxmlUrl(model, fxmlPath));

        try {
            if (bundlePath != null) {
                fxmlLoader.setResources(ResourceBundle.getBundle(bundlePath));
            }
        } catch (final MissingResourceException e) {
            LOGGER.log(MISSING_RESOURCE_BUNDLE, e, bundlePath);
        }

        Node node = null;
        boolean error = false;
        try {
            error = fxmlLoader.getLocation() == null;
            if (error) {
                node = new Text();
                ((Text) node).setText(FXML_ERROR_NODE_LABEL.getText(fxmlPath));
            } else {
                node = (Node) fxmlLoader.load(fxmlLoader.getLocation().openStream());
            }

        } catch (final IOException e) {
            throw new CoreRuntimeException(FXML_NODE_DOESNT_EXIST.getText(fxmlPath), e);
        }

        final FXMLController<M, ?> fxmlController = (FXMLController<M, ?>) fxmlLoader.getController();

        // It's tolerated to have a null controller for an fxml node
        if (fxmlController != null) {
            // The fxml controller must extends AbstractFXMLController
            if (!error && !(fxmlLoader.getController() instanceof AbstractFXMLController)) {
                throw new CoreRuntimeException(BAD_FXML_CONTROLLER_ANCESTOR.getText(fxmlLoader.getController().getClass().getCanonicalName()));
            }
            // Link the View component with the fxml controller
            fxmlController.model(model);
        }

        return new FXMLComponentBase(node, fxmlController);
    }

    /**
     * Convert The url of fxml files to allow local and path loading.
     *
     * @param model the model class that will be used for relative loading
     * @param fxmlPath the path of the fxml file (relative or absolute)
     *
     * @return the FXML file URL
     *
     * @param <M> the model type that will manage this fxml node
     */
    private static <M extends Model> URL convertFxmlUrl(final M model, final String fxmlPath) {
        URL fxmlUrl = null;
        // Replace all '.' separator by path separator '/'
        if (model != null) {
            // Try to load the resource from the same path as the model class
            fxmlUrl = model.getClass().getResource(fxmlPath);
        }
        if (fxmlUrl == null) {
            // Try to load the resource from the full path org/jrebirth/core/ui/Test.fxml
            fxmlUrl = Thread.currentThread().getContextClassLoader().getResource(fxmlPath);
        }

        return fxmlUrl;
    }
}
