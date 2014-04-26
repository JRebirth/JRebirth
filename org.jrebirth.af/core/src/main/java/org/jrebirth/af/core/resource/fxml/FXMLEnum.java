package org.jrebirth.af.core.resource.fxml;

public interface FXMLEnum extends FXMLItem {

    default void fxml(final String fxmlPath, final String fxmlName, final String bundlePath, final String bundleName) {
        set(new FXML(fxmlPath, fxmlName, bundlePath, bundleName));
    }

    default void fxml(final String globalPath, final String globalName) {
        set(new FXML(globalPath, globalName));
    }

    /**
     * 
     * TODO To complete.
     * 
     * @param name the fxml and resource bundle file name
     */
    default void fxml(final String name) {
        set(new FXML(name));
    }

}