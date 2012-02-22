package org.jrebirth.core.command.impl;

import javafx.stage.Stage;

import org.jrebirth.core.link.Wave;

/**
 * The class <strong>SwitchFullScreen</strong>. TODO To complete
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public class SwitchFullScreen extends CommandImpl {

    /**
     * {@inheritDoc}
     */
    @Override
    public void run(final Wave wave) {

        final Stage stage = getLocalFacade().getGlobalFacade().getApplication().getStage();

        stage.setFullScreen(!stage.isFullScreen());

    }
}
