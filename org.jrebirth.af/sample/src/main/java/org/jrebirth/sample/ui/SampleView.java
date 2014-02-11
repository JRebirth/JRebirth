package org.jrebirth.sample.ui;

import javafx.scene.control.Button;
import javafx.scene.control.LabelBuilder;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPaneBuilder;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.AbstractView;
import org.jrebirth.core.ui.annotation.OnMouse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>SampleView</strong>.
 * 
 * @author
 */
public final class SampleView extends AbstractView<SampleModel, BorderPane, SampleController> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SampleView.class);

    /** Button used to trigger the SampleCommand. */
    @OnMouse(OnMouse.MouseType.Clicked)
    private Button defaultCommand;

    /** Button used to trigger the SampleUICommand. */
    private Button uiCommand;

    /** Button used to trigger the SamplePoolCommand. */
    private Button pooledCommand;

    /**
     * Default Constructor.
     * 
     * @param model the controls view model
     * 
     * @throws CoreException if build fails
     */
    public SampleView(final SampleModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {

        this.defaultCommand = new Button("Trigger a default Command into JIT");
        this.uiCommand = new Button("Trigger an UI Command into JAT");
        this.pooledCommand = new Button("Trigger a pooled Command into JTP");

        getRootNode().setCenter(
                LabelBuilder.create()
                        .text("JRebirth Sample")
                        .build()
                );

        getRootNode().setBottom(FlowPaneBuilder.create().children(
                this.defaultCommand,
                this.uiCommand,
                this.pooledCommand
                ).build());
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

    /**
     * Return the button that trigger the default command.
     * 
     * @return the button that trigger the default command
     */
    Button getDefaultCommand() {
        return this.defaultCommand;
    }

    /**
     * Return the button that trigger the UI command.
     * 
     * @return the button that trigger the UI command
     */
    Button getUiCommand() {
        return this.uiCommand;
    }

    /**
     * Return the button that trigger the pooled command.
     * 
     * @return the button that trigger the pooled command
     */
    Button getPooledCommand() {
        return this.pooledCommand;
    }

}