package org.jrebirth.showcase.fxml.ui.included;

import org.jrebirth.core.ui.fxml.DefaultFXMLModel;

/**
 * The class <strong>StandaloneModel</strong>.
 * 
 * @author Sébastien Bordes
 */
public class IncludedModel extends DefaultFXMLModel<IncludedModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getFXMLPath() {
        return "org/jrebirth/showcase/fxml/ui/included/Included.fxml";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getFXMLBundlePath() {
        return "org/jrebirth/showcase/fxml/ui/included/Included";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fxmlPreInitialize() {
        // if (getModelObject() != null) {
        // this.fxmlPath = getModelObject().toString();
        // }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {
        // Nothing to do yet

    }

}
