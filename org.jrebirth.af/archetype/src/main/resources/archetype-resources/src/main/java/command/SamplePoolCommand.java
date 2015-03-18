#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.command;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.command.single.pool.DefaultPoolCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>SamplePoolCommand</strong> used to process long action (without monitoring)into the JRebirth Thread Pool.
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
    protected void perform(final Wave wave) {

        LOGGER.info("Perform a short or long action into a decicated thread from JTP");
        LOGGER.info("Be careful the JTP size depend on configuration");

    }

}
