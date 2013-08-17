package org.jrebirth.showcase.fxml.ui.main;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPaneBuilder;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.AbstractView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>SampleView</strong>.
 * 
 * @author
 */
public final class FXMLShowCaseView extends AbstractView<FXMLShowCaseModel, BorderPane, FXMLShowCaseController> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(FXMLShowCaseView.class);

    private Button showIncluded;

    private Button showEmbedded;

    private Button showHybrid;

    private Button showStandalone;

    /**
     * Default Constructor.
     * 
     * @param model the controls view model
     * 
     * @throws CoreException if build fails
     */
    public FXMLShowCaseView(final FXMLShowCaseModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {

        this.showIncluded = new Button("Included");
        this.showEmbedded = new Button("Embedded");
        this.showHybrid = new Button("Hybrid");
        this.showStandalone = new Button("Standalone");

        getRootNode().setTop(FlowPaneBuilder.create()
                .children(showIncluded, showEmbedded, showHybrid, showStandalone)
                .build());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        LOGGER.debug("Start the Sample View");
        // Custom code to process when the view is displayed the first time
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reload() {
        LOGGER.debug("Reload the Sample View");
        // Custom code to process when the view is displayed the 1+n time
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {
        LOGGER.debug("Hide the Sample View");
        // Custom code to process when the view is hidden
    }

    Button getShowIncluded() {
        return showIncluded;
    }

    Button getShowEmbedded() {
        return showEmbedded;
    }

    Button getShowHybrid() {
        return showHybrid;
    }

    Button getShowStandalone() {
        return showStandalone;
    }

}