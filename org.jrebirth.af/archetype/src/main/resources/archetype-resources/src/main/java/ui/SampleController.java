#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ui;

import javafx.scene.input.MouseEvent;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.core.ui.AbstractController;
import org.jrebirth.af.core.ui.adapter.DefaultMouseAdapter;
import org.jrebirth.af.core.wave.Builders;

import ${package}.command.SampleCommand;
import ${package}.command.SamplePoolCommand;
import ${package}.command.SampleUICommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>SampleController</strong>.
 * 
 * @author
 */
public final class SampleController extends AbstractController<SampleModel, SampleView> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SampleController.class);

    /**
     * Default Constructor.
     * 
     * @param view the view to control
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public SampleController(final SampleView view) throws CoreException {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initEventAdapters() throws CoreException {

        // Manage Ui Command Button
        linkCommand(getView().getUiCommand(), MouseEvent.MOUSE_CLICKED, SampleUICommand.class);

        // Use the inner class
        addAdapter(new SampleMouseAdapter());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initEventHandlers() throws CoreException {
        // Listen events

        // Manage Pooled Command Button
        getView().getPooledCommand().setOnMouseClicked(getHandler(MouseEvent.MOUSE_CLICKED));
    }

    /**
     * Manage Mouse click of widget that have annotation.
     * 
     * @param event the mouse event
     */
    void onMouseClicked(final MouseEvent event) {

        LOGGER.debug("MouseClicked => Call Sample Command");

        // Manage Default Command Button
        getModel().getCommand(SampleCommand.class).run();

    }

    /**
     * The class <strong>SampleMouseAdapter</strong>.
     */
    private class SampleMouseAdapter extends DefaultMouseAdapter<SampleController> {

        @Override
        public void mouseClicked(final MouseEvent mouseEvent) {
            super.mouseClicked(mouseEvent);

            LOGGER.debug("MouseClicked => Call Sample Pool Command");

            getModel().sendWave(Builders.callCommand(SamplePoolCommand.class));
            
        }

    }

}