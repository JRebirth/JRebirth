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

import org.jrebirth.af.core.command.impl.single.syncro.DefaultCommand;
import org.jrebirth.af.core.concurrent.RunInto;
import org.jrebirth.af.core.concurrent.RunType;
import org.jrebirth.af.core.exception.CommandException;
import org.jrebirth.af.core.wave.Wave;
import org.jrebirth.af.undoredo.service.UndoRedoService;

/**
 * The Class StackUpCommand.
 */
@RunInto(RunType.JAT)
public class StackUpCommand extends DefaultCommand {

    /** The stack name. */
    private String stackName;

    /** The undoable command. */
    private Undoable undoableCommand;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void perform(final Wave wave) throws CommandException {

        this.stackName = wave.get(UndoRedoWaves.STACK_NAME);
        final UndoRedoService service = getService(UndoRedoService.class, this.stackName);

        this.undoableCommand = wave.get(UndoRedoWaves.UNDOABLE_COMMAND);
        service.stackUp(this.undoableCommand);

    }

    /**
     * Sets the stack name.
     * 
     * @param stackName The stackName to set.
     */
    public void setStackName(final String stackName) {
        this.stackName = stackName;
    }

    /**
     * Sets the undoable command.
     * 
     * @param undoableCommand The undoableCommand to set.
     */
    public void setUndoableCommand(final Undoable undoableCommand) {
        this.undoableCommand = undoableCommand;
    }

}
