package org.jrebirth.core.command;

import org.jrebirth.core.concurrent.RunIntoType;
import org.jrebirth.core.wave.Wave;

/**
 * The class <strong>DefaultMultiCommand</strong>. TODO To complete
 * 
 * @author Sébastien Bordes
 */
public class DefaultMultiCommand extends AbstractBaseMultiCommand {

    /**
     * Default Constructor.
     * 
     * @param runInto The run into thread type
     */
    public DefaultMultiCommand(final RunIntoType runInto) {
        super(runInto, true);
    }

    /**
     * Default Constructor.
     * 
     * @param runInto The run into thread type
     * @param sequential indicate if commands must be run sequentially(true) or in parallel(false)
     */
    public DefaultMultiCommand(final RunIntoType runInto, final boolean sequential) {
        super(runInto, sequential);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waveCancelled(final Wave wave) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waveDestroyed(final Wave wave) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addSubCommand() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waveCreated(final Wave wave) {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waveSent(final Wave wave) {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waveProcessed(final Wave wave) {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processAction(final Wave wave) {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void preExecute(final Wave wave) {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void postExecute(final Wave wave) {
        // Nothing to do yet

    }

}
