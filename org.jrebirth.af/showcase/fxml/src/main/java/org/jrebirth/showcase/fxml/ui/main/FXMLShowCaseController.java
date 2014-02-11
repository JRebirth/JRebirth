package org.jrebirth.showcase.fxml.ui.main;

import javafx.event.ActionEvent;

import org.jrebirth.component.ui.stack.StackWaves;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.DefaultController;
import org.jrebirth.core.wave.WaveData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>SampleController</strong>.
 * 
 * @author
 */
public final class FXMLShowCaseController extends DefaultController<FXMLShowCaseModel, FXMLShowCaseView> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(FXMLShowCaseController.class);

    /**
     * Default Constructor.
     * 
     * @param view the view to control
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public FXMLShowCaseController(final FXMLShowCaseView view) throws CoreException {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initEventAdapters() throws CoreException {

        // WaveData<Class<? extends PageEnum>> stackName = WaveData.build(StackWaves.STACK_PAGES, FXMLShowCaseModel.STACK_PAGES);

        // Manage Ui Command Button
        linkWave(getView().getShowIncluded(), ActionEvent.ACTION, StackWaves.SHOW_PAGE_ENUM,
                WaveData.build(StackWaves.PAGE_ENUM, FXMLPage.IncludedFxml)/* , stackName */);

        linkWave(getView().getShowEmbedded(), ActionEvent.ACTION, StackWaves.SHOW_PAGE_ENUM,
                WaveData.build(StackWaves.PAGE_ENUM, FXMLPage.ViewEmbeddedFxml)/* , stackName */);

        linkWave(getView().getShowStandalone(), ActionEvent.ACTION, StackWaves.SHOW_PAGE_ENUM,
                WaveData.build(StackWaves.PAGE_ENUM, FXMLPage.StandaloneFxml)/* , stackName */);

        linkWave(getView().getShowHybrid(), ActionEvent.ACTION, StackWaves.SHOW_PAGE_ENUM,
                WaveData.build(StackWaves.PAGE_ENUM, FXMLPage.HybridFxml)/* , stackName */);

    }
}