package org.jrebirth.af.core.command.ref;

import org.jrebirth.af.core.command.AbstractSingleCommand;
import org.jrebirth.af.core.exception.CommandException;
import org.jrebirth.af.core.wave.Wave;
import org.jrebirth.af.core.wave.WaveBean;

/**
 * 
 */
public class RefCommand extends AbstractSingleCommand<WaveBean> {

    private/* List< */CommandRunner/* > */commandRunner/* List */;// = new ArrayList<Runner>();

    private CommandWaveRunner commandWaveRunner;

    @Override
    protected void initCommand() {

        // Copy all runner stored as key part
//        for (Object keyPart : getListKeyPart()) {
//
//            if (keyPart instanceof SingleRef) {

               // SingleRef ref = (SingleRef) keyPart;

    	 		SingleRef ref = getKeyPart(SingleRef.class);
                // runnerList.add(ref.runner());
                this.commandRunner = ref.runner();
                this.commandWaveRunner = ref.waveRunner();

                this.runIntoThread = ref.runType();
                this.runnablePriority = ref.priority();

               // break;
//            }
//        }
    }

    @Override
    protected void perform(Wave wave) throws CommandException {

        // for(Runner runner : runnerList){
        if (commandRunner != null) {
            commandRunner.perform();
        } else if (commandWaveRunner != null) {
            commandWaveRunner.perform(wave);
        }
        // }

    }

    /*
     * public List<Runner> runnerList() { return runnerList; }
     */
}
