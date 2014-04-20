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

import org.jrebirth.af.core.command.DefaultCommand;
import org.jrebirth.af.core.concurrent.RunInto;
import org.jrebirth.af.core.concurrent.RunType;
import org.jrebirth.af.core.exception.CommandException;
import org.jrebirth.af.core.wave.Wave;
import org.jrebirth.af.undoredo.service.UndoRedoService;

/**
 * The Class UndoCommand is used to undo latest action done for the given stack.
 * 
 * It retrieves the stack name from the WaveItem {@link UndoRedoWaves}.stackName or use the default one.
 */
@RunInto(RunType.JIT)
public class UndoCommand extends DefaultCommand {

    /** The command stack name. */
    private String stackName = "";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void perform(final Wave wave) throws CommandException {
        this.stackName = wave.get(UndoRedoWaves.STACK_NAME);
        getService(UndoRedoService.class, this.stackName).undo();
    }

    /**
     * Sets the stack name.
     * 
     * @param stackName The stackName to set.
     */
    public void setStackName(final String stackName) {
        this.stackName = stackName;
    }

}
