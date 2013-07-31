package org.jrebirth.undo.command;

import org.jrebirth.core.command.DefaultCommand;
import org.jrebirth.core.concurrent.RunInto;
import org.jrebirth.core.concurrent.RunType;
import org.jrebirth.core.exception.CommandException;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.undo.service.UndoRedoService;

@RunInto(RunType.JIT)
public class RedoCommand extends DefaultCommand {

    private String stackName = "";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void execute(Wave wave) throws CommandException {
        stackName = wave.get(UndoRedoWaves.stackName);
        getService(UndoRedoService.class, stackName).redo();
    }

    /**
     * @param stackName The stackName to set.
     */
    public void setStackName(String stackName) {
        this.stackName = stackName;
    }

}
