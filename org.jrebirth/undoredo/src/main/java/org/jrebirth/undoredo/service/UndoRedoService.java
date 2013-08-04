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
package org.jrebirth.undoredo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jrebirth.core.service.ServiceBase;
import org.jrebirth.core.wave.WaveBuilder;
import org.jrebirth.core.wave.WaveData;
import org.jrebirth.undoredo.command.UndoRedoWaves;
import org.jrebirth.undoredo.command.Undoable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class UndoRedoService is used to manage a stack of {@link Undoable} command.
 * 
 * It must have an unique name if several stack are managed together. (Use a basic JRebirth component key)
 * 
 * @author Sébastien Bordes
 */
public class UndoRedoService extends ServiceBase {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(UndoRedoService.class);

    /** The current index of the task performed. */
    private int currentIndex;

    /** The command stack. */
    private final List<Undoable> commandStack = Collections.synchronizedList(new ArrayList<Undoable>());

    /**
     * Stack up a command.
     * 
     * Move internal index and execute the command by triggering a Redo Wave Command
     * 
     * @param command the command
     */
    public void stackUp(final Undoable command) {

        // Stack up the command
        this.commandStack.add(command);

        // Move forward the command cursor
        this.currentIndex++;

        // Call the redo method of the Undoable command
        this.commandStack.get(this.currentIndex - 1).run(
                WaveBuilder.create()
                        .data(WaveData.build(UndoRedoWaves.UNDO_REDO, false))
                        .build()
                );
    }

    /**
     * Undo the last command.
     */
    public void undo() {

        // If there is at least one command left to undo
        if (this.currentIndex > 0) {

            // Call Undo method
            this.commandStack.get(this.currentIndex - 1).run(
                    WaveBuilder.create()
                            .data(WaveData.build(UndoRedoWaves.UNDO_REDO, true))
                            .build()
                    );

            // Move backward the command cursor
            this.currentIndex--;

        } else {
            // begin of stack, do nothing
            LOGGER.info("No more command to undo, begin of stack");
        }
    }

    /**
     * Redo the last command that was undo-ed.
     */
    public void redo() {

        // If there is at least one command to redo
        if (this.currentIndex < this.commandStack.size()) {

            // Call Redo method
            this.commandStack.get(this.currentIndex).run(
                    WaveBuilder.create()
                            .data(WaveData.build(UndoRedoWaves.UNDO_REDO, false))
                            .build()
                    );

            // Move forward the command cursor
            this.currentIndex++;

        } else {
            // End of stack, do nothing
            LOGGER.info("No more command to redo, end of stack");
        }
    }

}
