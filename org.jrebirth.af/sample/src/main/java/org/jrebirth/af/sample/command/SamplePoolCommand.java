package org.jrebirth.af.sample.command;

import org.jrebirth.af.core.command.DefaultPoolCommand;
import org.jrebirth.af.core.wave.Wave;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>SampleUICommand</strong> used to process long action (without monitoring)into the JRebirth Thread Pool.
 * 
 * @author
 */
public final class SamplePoolCommand extends DefaultPoolCommand {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SamplePoolCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void initCommand() {
        // You must put your initialization code here (if any)
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void execute(final Wave wave) {

        LOGGER.info("Perform a short or long action into a decicated thread from JTP");
        LOGGER.info("Be careful the JTP size depend on configuration");

    }

}
