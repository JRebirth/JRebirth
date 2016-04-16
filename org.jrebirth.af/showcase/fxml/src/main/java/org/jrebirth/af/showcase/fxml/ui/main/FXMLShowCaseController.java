package org.jrebirth.af.showcase.fxml.ui.main;

import javafx.event.ActionEvent;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.component.ui.stack.StackWaves;
import org.jrebirth.af.core.ui.DefaultController;
import org.jrebirth.af.core.wave.WBuilder;
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

        // WaveData<Class<? extends PageEnum>> stackName = Builders.waveData(StackWaves.STACK_PAGES, FXMLShowCaseModel.STACK_PAGES);

        // Manage Ui Command Button
        linkWave(view().getShowIncluded(), ActionEvent.ACTION, StackWaves.SHOW_PAGE_ENUM,
                 WBuilder.waveData(StackWaves.PAGE_ENUM, FXMLPage.IncludedFxml));

        linkWave(view().getShowEmbedded(), ActionEvent.ACTION, StackWaves.SHOW_PAGE_ENUM,
                 WBuilder.waveData(StackWaves.PAGE_ENUM, FXMLPage.ViewEmbeddedFxml));

        linkWave(view().getShowStandalone(), ActionEvent.ACTION, StackWaves.SHOW_PAGE_ENUM,
                 WBuilder.waveData(StackWaves.PAGE_ENUM, FXMLPage.StandaloneFxml));

        linkWave(view().getShowHybrid(), ActionEvent.ACTION, StackWaves.SHOW_PAGE_ENUM,
                 WBuilder.waveData(StackWaves.PAGE_ENUM, FXMLPage.HybridFxml));

    }
}
