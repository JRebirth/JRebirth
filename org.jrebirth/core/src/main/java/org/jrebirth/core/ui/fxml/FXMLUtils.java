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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.text.TextBuilder;

import org.jrebirth.core.exception.CoreRuntimeException;
import org.jrebirth.core.ui.Model;

/**
 * The class <strong>FXMLUtils</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class FXMLUtils {

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
    public static <M extends Model> FXMLComponent loadFXML(final M model, final String fxmlPath) {
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
    public static <M extends Model> FXMLComponent loadFXML(final M model, final String fxmlPath, final String bundlePath) {

        final FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(convertFxmlUrl(model, fxmlPath));

        if (bundlePath != null) {
            fxmlLoader.setResources(ResourceBundle.getBundle(bundlePath));
        }

        Node node = null;
        boolean error = false;
        try {
            error = fxmlLoader.getLocation() == null;
            if (error) {
                node = TextBuilder.create().text("FXML Error : " + fxmlPath).build();
            } else {
                node = (Node) fxmlLoader.load(fxmlLoader.getLocation().openStream());
            }

        } catch (final IOException e) {
            throw new CoreRuntimeException("The FXML node doesn't exist : " + fxmlPath, e);
        }

        final FXMLController<M, ?> fxmlController = (FXMLController<M, ?>) fxmlLoader.getController();

        // It's tolerated to have a null controller for an fxml node
        if (fxmlController != null) {
            // The fxml controller must extends AbstractFXMLController
            if (!error && !(fxmlLoader.getController() instanceof AbstractFXMLController)) {
                throw new CoreRuntimeException("The FXML controller must extends the FXMLController class : "
                        + fxmlLoader.getController().getClass().getCanonicalName());
            }
            // Link the View component with the fxml controller
            fxmlController.setModel(model);
        }

        return new FXMLComponent(node, fxmlController);
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
