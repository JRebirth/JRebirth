package org.jrebirth.core.command;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.link.Wave;

/**
 * The class <strong>DefaultPoolCommand</strong>.
 * 
 * The default empty class for commands that must be run into a new thread using a pool.
 * 
 * @author Sébastien Bordes
 */
public class DefaultPoolCommand extends AbstractPoolCommand {

    /**
     * {@inheritDoc}
     */
    @Override
    public void ready() throws CoreException {
        // Nothing to do yet by the default Pool command, must be overridden
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void execute(final Wave wave) {
        // Nothing to do yet by the default Pool command, must be overridden
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processAction(final Wave wave) {
        // Nothing to do yet by the default Pool command, must be overridden
    }

}
