/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
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
package org.jrebirth.af.undoredo.command;

import java.util.List;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.WaveBean;
import org.jrebirth.af.api.wave.contract.WaveData;
import org.jrebirth.af.core.command.single.AbstractSingleCommand;
import org.jrebirth.af.core.exception.CommandException;
import org.jrebirth.af.core.wave.Builders;
import org.jrebirth.af.core.wave.WaveDataBase;

/**
 * The Class AbstractUndoableCommand is the base class for all other undoable command.
 *
 * @param <WB> The WaveBean type used for this command (by default you can use the WaveBean interface)
 *
 * @author Sébastien Bordes
 */
public abstract class AbstractUndoableCommand<WB extends WaveBean> extends AbstractSingleCommand<WB> implements Undoable<WB> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void perform(final Wave wave) throws CommandException {

        // Get the undo/redo flag to know which action to perform
        if (wave.contains(UndoRedoWaves.UNDO_REDO)) {

            // The flag is true perform undo action
            if (wave.get(UndoRedoWaves.UNDO_REDO)) {
                undo();
            } else {
                // The flag is false perform redo action
                redo();
            }

        } else {

            // If the flag is not set we must initialize the command
            init(wave);
            // Get existing wave data
            final List<WaveData<?>> data = wave.waveDatas();
            // and add the undoable command
            data.add(Builders.waveData(UndoRedoWaves.UNDOABLE_COMMAND, this));
            // in order to register it into the right command stack
            callCommand(StackUpCommand.class, data.toArray(new WaveDataBase[data.size()]));
        }

    }

    /**
     * Initialize the command internal properties to be able to undo or redo the command.
     *
     * @param wave the wave
     */
    protected abstract void init(final Wave wave);

    /**
     * Undo the command.
     */
    protected abstract void undo();

    /**
     * Do or Redo the command.
     */
    protected abstract void redo();

}
