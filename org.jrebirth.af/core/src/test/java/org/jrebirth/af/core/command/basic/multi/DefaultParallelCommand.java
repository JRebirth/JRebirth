package org.jrebirth.af.core.command.basic.multi;

import org.jrebirth.af.core.command.DefaultMultiCommand;

public class DefaultParallelCommand extends DefaultMultiCommand {

    /**
     *
     */
    public DefaultParallelCommand() {
        super();
        setSequential(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void manageSubCommand() {
        addCommandClass(LongCommand.class);
        addCommandClass(UiCommand.class);
    }
}
