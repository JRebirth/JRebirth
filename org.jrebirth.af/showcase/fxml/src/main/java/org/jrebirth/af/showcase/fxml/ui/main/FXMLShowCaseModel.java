package org.jrebirth.af.showcase.fxml.ui.main;

import org.jrebirth.af.component.ui.stack.StackModel;
import org.jrebirth.af.core.ui.DefaultModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>SampleModel</strong>.
 * 
 * @author
 */
public final class FXMLShowCaseModel extends DefaultModel<FXMLShowCaseModel, FXMLShowCaseView> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(FXMLShowCaseModel.class);

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {
        super.initModel();

        getView().getRootNode().setCenter(getModel(StackModel.class, FXMLPage.class).getRootNode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void showView() {
        super.showView();

        // getModel(StackModel.class, FXMLPage.class).doShowView(null);
    }

}
