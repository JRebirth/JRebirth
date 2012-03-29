package org.jrebirth.presentation.command;

import org.jrebirth.core.command.DefaultUICommand;
import org.jrebirth.core.link.Wave;
import org.jrebirth.presentation.ui.stack.StackModel;

/**
 * The class <strong>ShowNextSlideCommand</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public final class ShowNextSlideCommand extends DefaultUICommand {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void execute(final Wave wave) {
        getModel(StackModel.class).next();
    }

}
