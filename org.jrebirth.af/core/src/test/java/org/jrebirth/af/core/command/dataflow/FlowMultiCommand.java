package org.jrebirth.af.core.command.dataflow;

import java.util.Arrays;
import java.util.List;

import org.jrebirth.af.api.command.Command;
import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.core.command.multi.DefaultMultiCommand;

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
