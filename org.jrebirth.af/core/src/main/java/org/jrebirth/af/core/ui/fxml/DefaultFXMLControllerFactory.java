package org.jrebirth.af.core.ui.fxml;

import org.jrebirth.af.api.log.JRLogger;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.ui.View;
import org.jrebirth.af.api.ui.fxml.FXMLController;
import org.jrebirth.af.core.log.JRLoggerFactory;

/**
 * The class <strong>DefaultFXMLControllerFactory</strong>.
 *
 * @author Sébastien Bordes
 */
public class DefaultFXMLControllerFactory extends AbstractFXMLControllerFactory {

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(DefaultFXMLControllerFactory.class);

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object call(final Class<?> controllerClass) {
        FXMLController<Model, View<Model, ?, ?>> controller = null;
        try {

            controller = (FXMLController<Model, View<Model, ?, ?>>) controllerClass.newInstance();
            controller.model(relatedModel());

        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.log(FXMLMessages.DEFAULT_CTRLR_CREATION_ERROR, e, e.getMessage());
        }

        return controller;
    }
}
