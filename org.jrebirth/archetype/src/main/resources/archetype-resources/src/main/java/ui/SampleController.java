#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ui;

import javafx.scene.input.MouseEvent;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.AbstractController;
import org.jrebirth.core.ui.adapter.DefaultMouseAdapter;
import org.jrebirth.core.wave.WaveBuilder;
import org.jrebirth.core.wave.WaveGroup;
import org.jrebirth.sample.command.SampleCommand;
import org.jrebirth.sample.command.SamplePoolCommand;
import org.jrebirth.sample.command.SampleUICommand;
import org.jrebirth.sample.ui.SampleController;
import org.jrebirth.sample.ui.SampleView;
import org.jrebirth.sample.ui.SampleController.SampleMouseAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>SampleController</strong>.
 * 
 * @author
 */
public class SampleController extends AbstractController<SampleModel, SampleView> {

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

            getModel().sendWave(
                    WaveBuilder.create()
                            .waveGroup(WaveGroup.CALL_COMMAND)
                            .relatedClass(SamplePoolCommand.class)
                            .build()
                    );
        }

    }

}