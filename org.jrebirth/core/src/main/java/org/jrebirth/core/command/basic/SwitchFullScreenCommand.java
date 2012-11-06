package org.jrebirth.core.command.basic;

import javafx.stage.Stage;

import org.jrebirth.core.command.DefaultUICommand;
import org.jrebirth.core.wave.Wave;

/**
 * The class <strong>SwitchFullScreenCommand</strong>.
 * 
 * @author Sébastien Bordes
 */
public class SwitchFullScreenCommand extends DefaultUICommand {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Wave wave) {

        final Stage stage = getLocalFacade().getGlobalFacade().getApplication().getStage();

        // Switch the full screen state
        stage.setFullScreen(!stage.isFullScreen());

    }
}
