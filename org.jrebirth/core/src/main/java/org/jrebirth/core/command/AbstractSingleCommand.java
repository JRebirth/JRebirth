package org.jrebirth.core.command;

import org.jrebirth.core.concurrent.RunIntoType;
import org.jrebirth.core.wave.Wave;

/**
 * The class <strong>AbstractSingleCommand</strong>.
 * 
 * @author Sébastien Bordes
 */
public abstract class AbstractSingleCommand extends AbstractBaseCommand {

    /** The command is running. */
    private boolean running;

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
        synchronized (this) {
            this.running = true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void postExecute(final Wave wave) {
        synchronized (this) {
            this.running = false;
        }
        fireAchieve(wave);
    }

    /**
     * @return Returns the running.
     */
    public boolean isRunning() {
        return this.running;
    }
}
