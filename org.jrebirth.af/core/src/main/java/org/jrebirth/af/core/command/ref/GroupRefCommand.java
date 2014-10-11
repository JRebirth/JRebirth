package org.jrebirth.af.core.command.ref;

import org.jrebirth.af.core.command.AbstractMultiCommand;
import org.jrebirth.af.core.wave.WaveBean;

public class GroupRefCommand extends AbstractMultiCommand<WaveBean> {

    public GroupRefCommand() {
        super(true);
        // Nothing to initialize
    }

    @Override
    protected void manageSubCommand() {
        // for (Object keyPart : getListKeyPart()) {

        // if (keyPart instanceof GroupRef) {

        // GroupRef groupRef = (GroupRef) keyPart;
        final GroupRef groupRef = getKeyPart(GroupRef.class);

        setSequential(groupRef.sequential());

        this.runIntoThread = groupRef.runInto();
        this.runnablePriority = groupRef.priority();

        for (final Ref ref : groupRef.children()) {
            if (ref instanceof GroupRef) {
                addCommandKey(GroupRefCommand.class, ref);
            } else if (ref instanceof SingleRef) {
                addCommandKey(RefCommand.class, ref);
            } else if (ref instanceof RealRef) {
                addCommandKey(((RealRef) ref).key());
            }
        }

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
