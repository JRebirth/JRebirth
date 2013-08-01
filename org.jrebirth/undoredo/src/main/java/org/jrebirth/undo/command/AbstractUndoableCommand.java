package org.jrebirth.undo.command;

import java.util.List;

import org.jrebirth.core.command.AbstractSingleCommand;
import org.jrebirth.core.exception.CommandException;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveBean;
import org.jrebirth.core.wave.WaveData;

public abstract class AbstractUndoableCommand extends AbstractSingleCommand<WaveBean> implements Undoable {

    @Override
    public void ready() throws CoreException {
        // Nothing to do yet

    }

    @Override
    protected void execute(Wave wave) throws CommandException {

        if (!wave.contains(UndoRedoWaves.undoRedo)) {

            List<WaveData<?>> data = wave.getWaveItems();
            data.add(WaveData.build(UndoRedoWaves.undoableCommand, this));

            init(wave);

            callCommand(StackUpCommand.class, data.toArray(new WaveData[0]));

        } else {
            if (wave.get(UndoRedoWaves.undoRedo)) {
                undo();
            } else {
                redo();
            }
        }

    }

    protected abstract void init(Wave wave);

    protected abstract void undo();

    protected abstract void redo();

}
