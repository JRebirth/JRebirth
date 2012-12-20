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
     * @param fxmlPath the fxml string path
     * 
     * @return a FXMLComponent object that wrap a fxml node with its controller
     */
    public static <M extends Model> FXMLComponent loadFXML(final M model, final String fxmlPath) {
        return loadFXML(model, fxmlPath, null);
    }

    /**
     * Load a FXML component.
     * 
     * @param fxmlPath the fxml string path
     * @param bundlePath the bundle string path
     * 
     * @return a FXMLComponent object that wrap a fxml node with its controller
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
     * TODO To complete.
     * 
     * @param fxmlPath
     * @return
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
