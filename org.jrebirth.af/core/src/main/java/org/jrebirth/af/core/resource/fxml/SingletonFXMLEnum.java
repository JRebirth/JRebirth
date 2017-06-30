package org.jrebirth.af.core.resource.fxml;

import org.jrebirth.af.api.resource.fxml.FXMLParams;
import org.jrebirth.af.api.ui.fxml.FXMLComponent;

/**
 * The interface <strong>SingletonFXMLEnum</strong> should be inherited by any Enumeration that want to manage Singleton {@link FXMLComponent}.
 *
 * Each call to FXMLItem will return the same instance of FXMLComponent, anyway please note that a call to FXMLItem.getNew() will return a new instance.
 *
 * @author Sébastien Bordes
 */
public interface SingletonFXMLEnum extends FXMLItemBase {

    /**
     * Build and register a {@link FXMLParams}.
     *
     * @param fxmlPath the path of the fxml file
     * @param fxmlName the name of the fxml file
     * @param bundlePath the path of the resource bundle
     * @param bundleName the name of the resource bundle
     */
    default void fxml(final String fxmlPath, final String fxmlName, final String bundlePath, final String bundleName) {
        set(new FXML(fxmlPath, fxmlName, bundlePath, bundleName));
    }

    /**
     * Build and register a {@link FXMLParams}.
     *
     * @param globalPath the path of the fxml file and the resource bundle
     * @param globalName the name of the fxml file and the resource bundle
     */
    default void fxml(final String globalPath, final String globalName) {
        set(new FXML(globalPath, globalName));
    }

    /**
     * Build and register a {@link FXMLParams}.
     *
     * @param name the fxml and resource bundle file name (including path)
     */
    default void fxml(final String name) {
        set(new FXML(name));
    }

}
