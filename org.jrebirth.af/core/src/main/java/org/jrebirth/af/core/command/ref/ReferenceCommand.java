package org.jrebirth.af.core.command.ref;

import org.jrebirth.af.core.command.AbstractSingleCommand;
import org.jrebirth.af.core.exception.CommandException;
import org.jrebirth.af.core.wave.Wave;

/**
 * Created by seb on 23/05/2014.
 */
public class ReferenceCommand extends AbstractSingleCommand {

    private CommandRunner commandRunner;
    private CommandWaveRunner commandWaveRunner;

    @Override
    protected void initCommand() {
        for(Object keyPart :  getListKeyPart()){

            if(keyPart instanceof CommandRunner){
                commandRunner = (CommandRunner)keyPart;
            }
            if(keyPart instanceof CommandWaveRunner){
                commandWaveRunner = (CommandWaveRunner)keyPart;
            }
        }
    }

    @Override
    protected void perform(Wave wave) throws CommandException {

        if(commandRunner != null){
            commandRunner.perform();
        }

        if(commandWaveRunner != null){
            commandWaveRunner.perform(wave);
        }
    }

}
