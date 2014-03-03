package org.jrebirth.af.core.command.basic.multi;

import org.jrebirth.af.core.annotation.Sequential;
import org.jrebirth.af.core.command.DefaultMultiCommand;

@Sequential
public class AnnotatedSequentialCommand extends DefaultMultiCommand {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void manageSubCommand() {
        addCommandClass(LongCommand.class);
        addCommandClass(UiCommand.class);
    }
}
