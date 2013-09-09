package org.jrebirth.showcase.fxml.ui.main;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToggleGroupBuilder;
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

    private ToggleButton showEmbedded;

    private ToggleButton showStandalone;

    private ToggleButton showHybrid;

    private ToggleButton showIncluded;

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
        this.showEmbedded = new ToggleButton("Embedded");
        this.showStandalone = new ToggleButton("Standalone");
        this.showHybrid = new ToggleButton("Hybrid");
        this.showIncluded = new ToggleButton("Included");

        ToggleGroup group = ToggleGroupBuilder.create()
                .toggles(this.showEmbedded, this.showStandalone, this.showHybrid, this.showIncluded)
                .build();

        getRootNode().setTop(FlowPaneBuilder.create()
                .children(this.showEmbedded, this.showStandalone, this.showIncluded, this.showHybrid)
                .build());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        LOGGER.debug("Start the View");
        //
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

    ToggleButton getShowIncluded() {
        return this.showIncluded;
    }

    ToggleButton getShowEmbedded() {
        return this.showEmbedded;
    }

    ToggleButton getShowHybrid() {
        return this.showHybrid;
    }

    ToggleButton getShowStandalone() {
        return this.showStandalone;
    }

}