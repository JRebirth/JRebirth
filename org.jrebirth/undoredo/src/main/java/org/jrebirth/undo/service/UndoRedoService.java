package org.jrebirth.undo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.service.ServiceBase;
import org.jrebirth.core.wave.WaveBuilder;
import org.jrebirth.core.wave.WaveData;
import org.jrebirth.undo.command.UndoRedoWaves;
import org.jrebirth.undo.command.Undoable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UndoRedoService extends ServiceBase {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(UndoRedoService.class);

    private int currentIndex = 0;

    private final List<Undoable> commandStack = Collections.synchronizedList(new ArrayList<Undoable>());

    /**
     * {@inheritDoc}
     */
    @Override
    public void ready() throws CoreException {
        super.ready();
    }

    public void stackUp(Undoable command) {
        commandStack.add(command);

        // Move forward the command cursor
        currentIndex++;

        commandStack.get(currentIndex - 1).run(
                WaveBuilder.create()
                        .data(WaveData.build(UndoRedoWaves.undoRedo, false))
                        .build()
                );
    }

    public void undo() {
        if (currentIndex > 0) {

            // Call Undo

            commandStack.get(currentIndex - 1).run(
                    WaveBuilder.create()
                            .data(WaveData.build(UndoRedoWaves.undoRedo, true))
                            .build()
                    );

            // callCommand(, WaveData.build(UndoRedoWaves.undoRedo, true), JRebirthWaves.REUSE);

            currentIndex--;

        } else {
            LOGGER.info("No more command to undo, begin of stack");
        }
    }

    public void redo() {
        if (currentIndex < commandStack.size()) {

            // Call Redo
            commandStack.get(currentIndex).run(
                    WaveBuilder.create()
                            .data(WaveData.build(UndoRedoWaves.undoRedo, false))
                            .build()
                    );

            // callCommand(commandStack.get(currentIndex + 1).getClass(), WaveData.build(UndoRedoWaves.undoRedo, false), JRebirthWaves.REUSE);

            currentIndex++;

        } else {
            // End of stack do nothing
            LOGGER.info("No more command to redo, end of stack");
        }
    }

}
