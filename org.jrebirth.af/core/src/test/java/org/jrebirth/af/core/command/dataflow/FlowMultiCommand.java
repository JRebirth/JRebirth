package org.jrebirth.af.core.command.dataflow;

import java.util.Arrays;
import java.util.List;

import org.jrebirth.af.core.command.Command;
import org.jrebirth.af.core.command.impl.multi.DefaultMultiCommand;
import org.jrebirth.af.core.key.UniqueKey;

public class FlowMultiCommand extends DefaultMultiCommand {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<UniqueKey<? extends Command>> defineSubCommand() {
        return Arrays.asList(
                    getCommandKey(CreateDataCommand.class),
                    getCommandKey(UpdateDataCommand.class),
                    getCommandKey(DisplayDataCommand.class)
                );
    }
    
}
