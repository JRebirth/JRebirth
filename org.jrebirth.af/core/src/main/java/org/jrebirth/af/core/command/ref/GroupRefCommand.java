package org.jrebirth.af.core.command.ref;

import java.util.ArrayList;
import java.util.List;

import org.jrebirth.af.core.command.Command;
import org.jrebirth.af.core.command.impl.multi.AbstractMultiCommand;
import org.jrebirth.af.core.key.UniqueKey;
import org.jrebirth.af.core.wave.WaveBean;

public class GroupRefCommand extends AbstractMultiCommand<WaveBean> {

    public GroupRefCommand() {
        super(true);
        // Nothing to initialize
    }

    @Override
    protected List<UniqueKey<? extends Command>> defineSubCommand() {
        // for (Object keyPart : getListKeyPart()) {

        // if (keyPart instanceof GroupRef) {

        final List<UniqueKey<? extends Command>> commandList = new ArrayList<>();

        // GroupRef groupRef = (GroupRef) keyPart;
        final GroupRef groupRef = getKeyPart(GroupRef.class);

        setSequential(groupRef.sequential());

        this.runIntoThread = groupRef.runInto();
        this.runnablePriority = groupRef.priority();

        for (final Ref ref : groupRef.children()) {
            if (ref instanceof GroupRef) {
                commandList.add(getCommandKey(GroupRefCommand.class, ref));
            } else if (ref instanceof SingleRef) {
                commandList.add(getCommandKey(RefCommand.class, ref));
            } else if (ref instanceof RealRef) {
                commandList.add(((RealRef) ref).key());
            }
        }

        return commandList;
        // break;
        // }
        // }
    }

    @Override
    protected void initCommand() {
        // Nothing to do
    }

    @Override
    protected void initInnerComponents() {
    }

}
