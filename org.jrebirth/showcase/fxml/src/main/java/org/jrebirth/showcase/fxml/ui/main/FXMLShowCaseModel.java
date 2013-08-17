package org.jrebirth.showcase.fxml.ui.main;

import org.jrebirth.component.ui.stack.StackModel;
import org.jrebirth.core.ui.DefaultModel;

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

    // static final Class<? extends PageEnum> STACK_PAGES = FXMLPage.class;// "Main";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {
        super.initModel();

        getView().getRootNode().setCenter(getModel(StackModel.class, FXMLPage.class).getRootNode());
    }

}
