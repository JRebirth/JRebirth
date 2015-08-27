package org.jrebirth.af.core.resource.fxml;

import org.jrebirth.af.api.ui.fxml.FXMLComponent;

/**
 * The interface <strong>SingletonFXMLEnum</strong> should be inherited by any Enumeration that want to manage multiton {@link FXMLComponent}.
 *
 * Each call to FXMLItem.get() will return anew fresh instance.
 *
 * @author Sébastien Bordes
 */
public interface MultitonFXMLEnum extends SingletonFXMLEnum {

    /**
     * {@inheritDoc}
     */
    @Override
    default boolean isSingleton() {
        return false;
    }

}
