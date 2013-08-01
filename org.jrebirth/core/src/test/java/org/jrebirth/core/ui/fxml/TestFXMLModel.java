package org.jrebirth.core.ui.fxml;

import org.jrebirth.core.wave.Wave;

/**
 * The class <strong>TestFXMLModel</strong>. TODO To complete
 * 
 * @author Sébastien Bordes
 */
public class TestFXMLModel extends DefaultFXMLModel<TestFXMLModel> {

    private String fxmlPath;

    private String resourcePath;

    /**
     * @param fxmlPath
     * @param resourcePath
     */
    public TestFXMLModel(/* final String fxmlPath, final String resourcePath */) {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getFXMLPath() {
        // Nothing to do yet
        return this.fxmlPath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getFXMLBundlePath() {
        // Nothing to do yet
        return this.resourcePath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fxmlPreInitialize() {
        if (getModelObject() != null) {
            this.fxmlPath = getModelObject().toString();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processWave(final Wave wave) {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initInnerModels() {
        // Nothing to do yet

    }

}
