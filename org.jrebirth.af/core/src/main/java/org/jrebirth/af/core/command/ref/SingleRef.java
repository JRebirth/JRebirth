package org.jrebirth.af.core.command.ref;

public final class SingleRef extends AbstractRef<SingleRef> {

    private CommandRunner commandRunner;

    private CommandWaveRunner commandWaveRunner;

    public static SingleRef create() {
        return new SingleRef();
    }

    public CommandRunner runner() {
        return commandRunner;
    }

    public SingleRef runner(CommandRunner commandRunner) {
        this.commandRunner = commandRunner;
        return this;
    }

    public CommandWaveRunner waveRunner() {
        return commandWaveRunner;
    }

    public SingleRef waveRunner(CommandWaveRunner commandWaveRunner) {
        this.commandWaveRunner = commandWaveRunner;
        return this;
    }
}
