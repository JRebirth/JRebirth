/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2016
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
package org.jrebirth.af.dialog;

import org.jrebirth.af.api.concurrent.RunInto;
import org.jrebirth.af.api.concurrent.RunType;
import org.jrebirth.af.api.module.BootComponent;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.annotation.OnWave;
import org.jrebirth.af.api.wave.contract.WaveType;
import org.jrebirth.af.core.command.single.AbstractSingleCommand;
import org.jrebirth.af.core.exception.CommandException;
import org.jrebirth.af.core.wave.WBuilder;

/**
 * The Class DialogDaemonCommand.
 */
@BootComponent
@RunInto(RunType.JIT)
public class DialogDaemonCommand extends AbstractSingleCommand<DialogWB> {

    /** The Constant OPEN_DIALOG_WT. */
    public static final String OPEN_DIALOG_WT = "OPEN_DIALOG";

    /** The open dialog. */
    private static final WaveType OPEN_DIALOG = WBuilder.waveType(OPEN_DIALOG_WT);

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initCommand() {
        listen(OPEN_DIALOG);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void perform(Wave wave) throws CommandException {
        callCommand(DialogCommand.class, waveBean(wave));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initInnerComponents() {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @OnWave(OPEN_DIALOG_WT)
    protected void processWave(final Wave wave) {
        if (OPEN_DIALOG.equals(wave.waveType())) {

            callCommand(DialogCommand.class, waveBean(wave));

        }
    }

}
