package org.jrebirth.core.command;

import org.jrebirth.core.concurrent.RunIntoType;
import org.jrebirth.core.wave.Wave;

/**
 * The class <strong>AbstractSingleCommand</strong>.
 * 
 * @author Sébastien Bordes
 */
public abstract class AbstractSingleCommand extends AbstractBaseCommand {

    /**
     * Default constructor.
     * 
     * @param runIntoThread the way to launch this command
     */
    public AbstractSingleCommand(final RunIntoType runIntoThread) {
        super(runIntoThread);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void preExecute(final Wave wave) {
        // Nothing to do
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void postExecute(final Wave wave) {
        fireAchieve(wave);
    }
}
