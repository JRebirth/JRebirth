/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2014
 * Contact : sebastien.bordes@jrebirth.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.af.core.command.ref;

import java.util.ArrayList;
import java.util.List;

import org.jrebirth.af.api.command.Command;
import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.api.wave.WaveBean;
import org.jrebirth.af.core.command.multi.AbstractMultiCommand;

// TODO: Auto-generated Javadoc
/**
 * The Class GroupRefCommand.
 */
public class GroupRefCommand extends AbstractMultiCommand<WaveBean> {

    /**
     * Instantiates a new group ref command.
     */
    public GroupRefCommand() {
        super(true);
        // Nothing to initialize
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initCommand() {
        // Nothing to do
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initInnerComponents() {
        // Nothing to do yet
    }

}
