package org.jrebirth.af.core.wave;

import org.jrebirth.af.api.command.Command;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.WaveData;
import org.jrebirth.af.api.wave.WaveGroup;
import org.jrebirth.af.api.wave.WaveItem;
import org.jrebirth.af.api.wave.WaveType;

public interface Builders {

    static WaveType waveType() {
        return new WaveTypeBase();
    }

    static WaveType waveType(final String action/* , final WaveItem<?>... waveItems */) {
        return waveType().action(action/* , waveItems */);
    }

    static Wave wave() {
        return new WaveBase();
    }

    static Wave callCommand(final Class<? extends Command> commandClass) {
        return wave().waveGroup(WaveGroup.CALL_COMMAND).componentClass(commandClass);
    }

    /**
     * Build a wave data.
     *
     * @param waveItem the wave item used as the key into the wave
     * @param value the data hold by he wave data wrapper
     *
     * @return a new fresh wave Data object
     *
     * @param <T> the type of the object wrapped by this WaveData
     */
    static <T extends Object> WaveData<T> waveData(final WaveItem<T> waveItem, final T value) {
        return new WaveDataBase<>(waveItem, value);
    }

}
