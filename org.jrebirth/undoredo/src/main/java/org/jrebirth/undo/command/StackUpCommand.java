package org.jrebirth.undo.command;

import org.jrebirth.core.command.DefaultCommand;
import org.jrebirth.core.concurrent.RunInto;
import org.jrebirth.core.concurrent.RunType;
import org.jrebirth.core.exception.CommandException;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.undo.service.UndoRedoService;

@RunInto(RunType.JAT)
public class StackUpCommand extends DefaultCommand {

    private String stackName;

    private Undoable undoableCommand;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void execute(Wave wave) throws CommandException {

        stackName = wave.get(UndoRedoWaves.stackName);
        UndoRedoService service = getService(UndoRedoService.class, stackName);

        undoableCommand = wave.get(UndoRedoWaves.undoableCommand);
        service.stackUp(undoableCommand);

    }

    /**
     * @param stackName The stackName to set.
     */
    public void setStackName(String stackName) {
        this.stackName = stackName;
    }

    /**
     * @param undoableCommand The undoableCommand to set.
     */
    public void setUndoableCommand(Undoable undoableCommand) {
        this.undoableCommand = undoableCommand;
    }

}
