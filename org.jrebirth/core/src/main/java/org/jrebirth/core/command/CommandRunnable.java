package org.jrebirth.core.command;

import org.jrebirth.core.concurrent.AbstractJrbRunnable;
import org.jrebirth.core.exception.CommandException;
import org.jrebirth.core.exception.JRebirthThreadException;
import org.jrebirth.core.wave.Wave;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>CommandRunnable</strong>.
 * 
 * @author Sébastien Bordes
 */
final class CommandRunnable extends AbstractJrbRunnable {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandRunnable.class);

    /**
     * The <code>wave</code>.
     */
    private final Wave wave;

    /** The command link. */
    private final AbstractBaseCommand command;

    /**
     * Default Constructor.
     * 
     * @param runnableName the name of the action to perform
     * @param command the command to run with the right thread
     * @param wave the wave that generates this command call
     */
    CommandRunnable(final String runnableName, final AbstractBaseCommand command, final Wave wave) {
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
            // Call the innerRun available with package visibility
            this.command.innerRun(this.wave);
        } catch (final CommandException ce) {
            // Log any error occurred during the execution of this command
            LOGGER.error("Command has failed :", ce);
            // Then update the wave status in order to perform right task after this failure
            this.wave.setStatus(Wave.Status.Failed);
        }
    }

}