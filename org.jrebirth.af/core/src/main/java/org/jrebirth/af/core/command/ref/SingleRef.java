package org.jrebirth.af.core.command.ref;

public final class SingleRef extends AbstractRef<SingleRef> {

    private CommandRunner commandRunner;

    private CommandWaveRunner commandWaveRunner;

    public static SingleRef create() {
        return new SingleRef();
    }

    public CommandRunner run() {
        return commandRunner;
    }

    public SingleRef run(CommandRunner commandRunner) {
        this.commandRunner = commandRunner;
        return this;
    }

    public CommandWaveRunner runWave() {
        return commandWaveRunner;
    }

    public SingleRef runWave(CommandWaveRunner commandWaveRunner) {
        this.commandWaveRunner = commandWaveRunner;
        return this;
    }
}
