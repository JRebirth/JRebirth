package org.jrebirth.af.core.command.basic.multi;

import org.jrebirth.af.core.command.DefaultMultiCommand;

public class DefaultSequentialCommand extends DefaultMultiCommand {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void manageSubCommand() {
        addCommandClass(LongCommand.class);
        addCommandClass(UiCommand.class);
    }
}
