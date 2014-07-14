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
        // for (Object keyPart : getListKeyPart()) {
        //
        // if (keyPart instanceof SingleRef) {

        // SingleRef ref = (SingleRef) keyPart;

        final SingleRef ref = getKeyPart(SingleRef.class);
        // runnerList.add(ref.runner());
        this.commandRunner = ref.run();
        this.commandWaveRunner = ref.runWave();

        this.runIntoThread = ref.runInto();
        this.runnablePriority = ref.priority();

        // break;
        // }
        // }
    }

    @Override
    protected void perform(final Wave wave) throws CommandException {

        // for(Runner runner : runnerList){
        if (this.commandRunner != null) {
            this.commandRunner.perform();
        } else if (this.commandWaveRunner != null) {
            this.commandWaveRunner.perform(wave);
        }
        // }

    }

    /*
     * public List<Runner> runnerList() { return runnerList; }
     */
}
