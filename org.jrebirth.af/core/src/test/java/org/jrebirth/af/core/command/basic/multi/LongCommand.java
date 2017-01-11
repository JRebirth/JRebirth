package org.jrebirth.af.core.command.basic.multi;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.command.single.internal.DefaultCommand;
import org.jrebirth.af.core.exception.CommandException;

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
            Thread.sleep(4000);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("long command done");
    }

}
