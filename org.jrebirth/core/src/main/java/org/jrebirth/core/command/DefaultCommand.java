package org.jrebirth.core.command;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.link.Wave;

/**
 * The class <strong>DefaultCommand</strong>.
 * 
 * The default empty class for Internal commands.
 * 
 * @author Sébastien Bordes
 */
public class DefaultCommand extends AbstractCommand {

    /**
     * {@inheritDoc}
     */
    @Override
    public void ready() throws CoreException {
        // Nothing to do yet by the default command, must be overridden
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void execute(final Wave wave) {
        // Nothing to do yet by the default command, must be overridden
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processAction(final Wave wave) {
        // Nothing to do yet by the default command, must be overridden
    }

}
