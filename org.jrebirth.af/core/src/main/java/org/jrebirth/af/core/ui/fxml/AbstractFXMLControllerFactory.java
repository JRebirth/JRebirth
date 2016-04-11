package org.jrebirth.af.core.ui.fxml;

import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.ui.fxml.FXMLControllerFactory;

public abstract class AbstractFXMLControllerFactory implements FXMLControllerFactory {

    /**
     * The root model of the FXML file. The root model for fx/include or the main model.
     */
    private Model relatedModel;

    /**
     * {@inheritDoc}
     */
    @Override
    public Model relatedModel() {
        return this.relatedModel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void relatedModel(final Model relatedModel) {
        this.relatedModel = relatedModel;
    }
}
