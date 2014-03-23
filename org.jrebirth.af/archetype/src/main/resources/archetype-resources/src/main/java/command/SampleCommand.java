#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.command;

import org.jrebirth.af.core.command.DefaultCommand;
import org.jrebirth.af.core.wave.Wave;
import org.jrebirth.af.sample.command.SampleCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>SampleCommand</strong> used to process short action into the JRebirth Internal Thread.
 * 
 * @author
 */
public final class SampleCommand extends DefaultCommand {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SampleCommand.class);

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

        LOGGER.info("Perform a short action JIT");
        LOGGER.info("Be careful it locks wave processing");

    }

}
