package org.jrebirth.af.showcase.fxml.ui.main;

import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.core.ui.AbstractView;

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

        final ToggleGroup group = new PersistentButtonToggleGroup();
        group.getToggles().addAll(this.showEmbedded, this.showStandalone, this.showHybrid, this.showIncluded);

        final FlowPane fp = new FlowPane();
        fp.setStyle("-fx-background-color: #2f4f4f;");
        fp.setAlignment(Pos.CENTER);
        fp.setHgap(4);
        fp.getChildren().addAll(this.showEmbedded, this.showStandalone, this.showIncluded, this.showHybrid);

        node().setTop(fp);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        LOGGER.debug("Start the View and fire the first tab");
        this.showEmbedded.fire();
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

    private class PersistentButtonToggleGroup extends ToggleGroup {

        public PersistentButtonToggleGroup() {
            super();
            getToggles().addListener(new ListChangeListener<Toggle>() {
                @Override
                public void onChanged(final Change<? extends Toggle> c) {
                    while (c.next()) {
                        for (final Toggle addedToggle : c.getAddedSubList()) {
                            ((ToggleButton) addedToggle).addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(final MouseEvent mouseEvent) {
                                    if (addedToggle.equals(getSelectedToggle())) {
                                        mouseEvent.consume();
                                    }
                                }
                            });
                        }
                    }
                }
            });
        }
    }

}
