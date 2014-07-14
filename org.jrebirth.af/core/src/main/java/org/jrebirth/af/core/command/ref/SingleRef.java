package org.jrebirth.af.core.command.ref;

public final class SingleRef extends AbstractRef<SingleRef> {

    private CommandRunner commandRunner;

    private CommandWaveRunner commandWaveRunner;

    public static SingleRef create() {
        return new SingleRef();
    }

    public CommandRunner run() {
        return this.commandRunner;
    }

    public SingleRef run(final CommandRunner commandRunner) {
        this.commandRunner = commandRunner;
        return this;
    }

    public CommandWaveRunner runWave() {
        return this.commandWaveRunner;
    }

    public SingleRef runWave(final CommandWaveRunner commandWaveRunner) {
        this.commandWaveRunner = commandWaveRunner;
        return this;
    }
}
