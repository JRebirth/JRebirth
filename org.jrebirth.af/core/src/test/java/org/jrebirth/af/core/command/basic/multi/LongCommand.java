package org.jrebirth.af.core.command.basic.multi;

import org.jrebirth.af.core.command.DefaultCommand;
import org.jrebirth.af.core.exception.CommandException;
import org.jrebirth.af.core.wave.Wave;

public class LongCommand extends DefaultCommand {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initCommand() {
        // Nothing to do yet
        super.initCommand();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void perform(final Wave wave) throws CommandException {
        try {
            Thread.sleep(2000);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("long command done");
    }

}
