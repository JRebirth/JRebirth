package org.jrebirth.core.command;

import org.jrebirth.core.concurrent.AbstractJrbRunnable;
import org.jrebirth.core.exception.CommandException;
import org.jrebirth.core.exception.JRebirthThreadException;
import org.jrebirth.core.wave.Wave;

/**
 * The class <strong>CommandRunnable</strong>.
 * 
 * @author Sébastien Bordes
 */
final class CommandRunnable extends AbstractJrbRunnable {

    /**
     * The <code>wave</code>.
     */
    private final Wave wave;

    /** The command link. */
    private final Command command;

    /**
     * Default Constructor.
     * 
     * @param runnableName the name of the action to perform
     * @param command the command to run
     * @param wave the wave that generates this command call
     */
    CommandRunnable(final String runnableName, final Command command, final Wave wave) {
        super(runnableName);
        this.command = command;
        this.wave = wave;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void runInto() throws JRebirthThreadException {

        try {
            this.command.innerRun(this.wave);
        } catch (final CommandException ce) {
            AbstractBaseCommand.LOGGER.error("Command has failed :", ce);
            this.wave.setStatus(Wave.Status.Failed);
        }
    }
}