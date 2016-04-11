package org.jrebirth.af.showcase.fxml.ui.main;

import org.jrebirth.af.api.module.Register;
import org.jrebirth.af.api.ui.ModuleModel;
import org.jrebirth.af.component.ui.stack.StackModel;
import org.jrebirth.af.core.ui.DefaultModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>SampleModel</strong>.
 *
 * @author
 */
@Register(value = ModuleModel.class)
public final class FXMLShowCaseModel extends DefaultModel<FXMLShowCaseModel, FXMLShowCaseView> implements ModuleModel {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(FXMLShowCaseModel.class);

    private StackModel stackModel;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {
        super.initModel();

        stackModel = getModel(StackModel.class, FXMLPage.class);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void showView() {
        super.showView();
        view().node().setCenter(stackModel.node());

        // getModel(StackModel.class, FXMLPage.class).doShowView(null);
    }

    @Override
    public String moduleName() {
        return "FXML";
    }

}
