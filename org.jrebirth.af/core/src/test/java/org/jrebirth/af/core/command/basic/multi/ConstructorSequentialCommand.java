package org.jrebirth.af.core.command.basic.multi;

import org.jrebirth.af.core.command.AbstractMultiCommand;
import org.jrebirth.af.core.wave.WaveBean;

public class ConstructorSequentialCommand extends AbstractMultiCommand<WaveBean> {

    public ConstructorSequentialCommand() {
        super(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void manageSubCommand() {
        addCommandClass(LongCommand.class);
        addCommandClass(UiCommand.class);
    }

    @Override
    protected void initCommand() {
        // Nothing to do yet

    }
}
