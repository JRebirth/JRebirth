package org.jrebirth.af.showcase.fxml.ui.included;

import org.jrebirth.af.core.ui.fxml.DefaultFXMLModel;

/**
 * The class <strong>IncludedModel</strong>.
 * 
 * @author Sébastien Bordes
 */
public class IncludedModel extends DefaultFXMLModel<IncludedModel> {

    // private final FXMLItem subIncluded1Item = Resources.create(new FXML("org.jrebirth.showcase.fxml.ui.included.SubIncluded1"));
    // private final FXMLItem subIncluded2Item = Resources.create(new FXML("org.jrebirth.showcase.fxml.ui.included.SubIncluded2"));
    //
    // private final InnerModels subIncluded1 = InnerModelBase.build(DefaultFXMLModel.class, subIncluded1Item);
    // private final InnerModels subIncluded2 = InnerModelBase.build(DefaultFXMLModel.class, subIncluded2Item);

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
