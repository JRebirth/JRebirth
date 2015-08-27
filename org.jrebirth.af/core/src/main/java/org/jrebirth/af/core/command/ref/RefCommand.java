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

import org.jrebirth.af.api.command.ref.CommandRunner;
import org.jrebirth.af.api.command.ref.CommandWaveRunner;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.WaveBean;
import org.jrebirth.af.core.command.single.AbstractSingleCommand;
import org.jrebirth.af.core.exception.CommandException;

/**
 * The Class RefCommand.
 */
public class RefCommand extends AbstractSingleCommand<WaveBean> {

    /** The command runner. */
    private CommandRunner commandRunner;
    // private List<Runner> commandRunnerList new ArrayList<Runner>();

    /** The command wave runner. */
    private CommandWaveRunner commandWaveRunner;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initCommand() {

        // Copy all runner stored as key part
        // for (Object keyPart : getListKeyPart()) {
        //
        // if (keyPart instanceof SingleRef) {

        // SingleRef ref = (SingleRef) keyPart;

        final SingleRef ref = getKeyPart(SingleRef.class);
        // runnerList.add(ref.runner());
        this.commandRunner = ref.run();
        this.commandWaveRunner = ref.runWave();

        this.runIntoThread = ref.runInto();
        this.runnablePriority = ref.priority();

        // break;
        // }
        // }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void perform(final Wave wave) throws CommandException {

        // for(Runner runner : runnerList){
        if (this.commandRunner != null) {
            this.commandRunner.perform();
        } else if (this.commandWaveRunner != null) {
            this.commandWaveRunner.perform(wave);
        }
        // }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initInnerComponents() {
        // Nothing to do yet
    }

    /*
     * public List<Runner> runnerList() { return runnerList; }
     */
}
