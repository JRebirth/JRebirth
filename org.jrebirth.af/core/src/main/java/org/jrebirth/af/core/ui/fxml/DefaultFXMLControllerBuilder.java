package org.jrebirth.af.core.ui.fxml;

import javafx.util.Callback;

import org.jrebirth.af.api.log.JRLogger;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.ui.View;
import org.jrebirth.af.api.ui.fxml.FXMLController;
import org.jrebirth.af.core.log.JRLoggerFactory;

/**
 * The class <strong>DefaultFXMLControllerBuilder</strong>.
 *
 * @author Sébastien Bordes
 */
public class DefaultFXMLControllerBuilder implements Callback<Class<?>, Object> {

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(DefaultFXMLControllerBuilder.class);

    /** The root model of the FXML file. */
    private final Model relatedModel;

    /**
     * Default Constructor.
     *
     * @param relatedModel the root model for fx/include or hte main model
     */
    public DefaultFXMLControllerBuilder(final Model relatedModel) {
        this.relatedModel = relatedModel;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object call(final Class<?> controllerClass) {
        FXMLController<Model, View<Model, ?, ?>> controller = null;
        try {

            controller = (FXMLController<Model, View<Model, ?, ?>>) controllerClass.newInstance();
            controller.setModel(this.relatedModel);

        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.log(FXMLMessages.DEFAULT_CTRLR_CREATION_ERROR, e, e.getMessage());
        }

        return controller;
    }
}
