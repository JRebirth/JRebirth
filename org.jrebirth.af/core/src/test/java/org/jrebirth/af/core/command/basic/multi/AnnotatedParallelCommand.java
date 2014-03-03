package org.jrebirth.af.core.command.basic.multi;

import org.jrebirth.af.core.annotation.Sequential;
import org.jrebirth.af.core.command.DefaultMultiCommand;

@Sequential(false)
public class AnnotatedParallelCommand extends DefaultMultiCommand {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void manageSubCommand() {
        addCommandClass(LongCommand.class);
        addCommandClass(UiCommand.class);
    }
}
