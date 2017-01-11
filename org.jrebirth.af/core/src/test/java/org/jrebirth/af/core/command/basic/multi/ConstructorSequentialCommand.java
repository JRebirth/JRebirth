package org.jrebirth.af.core.command.basic.multi;

import java.util.Arrays;
import java.util.List;

import org.jrebirth.af.api.command.Command;
import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.core.command.multi.DefaultMultiCommand;

public class ConstructorSequentialCommand extends DefaultMultiCommand {

    public ConstructorSequentialCommand() {
        super(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<UniqueKey<? extends Command>> defineSubCommand() {
        return Arrays.asList(
                             getCommandKey(LongCommand.class),
                             getCommandKey(UiCommand.class));
    }

    @Override
    protected void initCommand() {
        // Nothing to do yet

    }

    @Override
    protected void initInnerComponents() {
    }
}
