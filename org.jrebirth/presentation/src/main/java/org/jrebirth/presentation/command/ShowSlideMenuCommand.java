package org.jrebirth.presentation.command;

import org.jrebirth.core.command.DefaultUICommand;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.presentation.ui.stack.StackModel;

/**
 * The class <strong>ShowSlideMenuCommand</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public final class ShowSlideMenuCommand extends DefaultUICommand {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void execute(final Wave wave) {
        getModel(StackModel.class).showSlideMenu();
    }

}
