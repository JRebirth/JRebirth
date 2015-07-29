/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2015
 * Contact : sebastien.bordes@jrebirth.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.af.core.wave;

import java.util.List;

import org.jrebirth.af.api.command.Command;
import org.jrebirth.af.api.service.Service;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.WaveGroup;
import org.jrebirth.af.api.wave.contract.WaveData;
import org.jrebirth.af.api.wave.contract.WaveItem;
import org.jrebirth.af.api.wave.contract.WaveType;
import org.jrebirth.af.core.command.basic.ChainWaveCommand;

/**
 * The Interface Builders used as a convenient wrapper to build a large variety of JRebirth Internal objects.
 */
public interface Builders {

    /**
     * Build a fresh Wave type.
     *
     * @return the wave type
     */
    static WaveType waveType() {
        return new WaveTypeBase();
    }

    /**
     * Build a fresh Wave type with action name.
     *
     * @param action the action
     * @return the wave type
     */
    static WaveType waveType(final String action) {
        return waveType().action(action);
    }

    /**
     * Build a fresh Wave.
     *
     * @return the wave
     */
    static Wave wave() {
        return new WaveBase();
    }

    /**
     * Build a fresh Wave use to call the given command.
     *
     * @param commandClass the command class
     *
     * @return the wave
     */
    static Wave callCommand(final Class<? extends Command> commandClass) {
        return wave().waveGroup(WaveGroup.CALL_COMMAND).componentClass(commandClass);
    }

    /**
     * Build a fresh Wave use to call the given service.
     *
     * @param serviceClass the service class
     *
     * @return the wave
     */
    static Wave returnData(final Class<? extends Service> serviceClass) {
        return wave().waveGroup(WaveGroup.RETURN_DATA).componentClass(serviceClass);
    }

    /**
     * Build a wave data.
     *
     * @param <T> the type of the object wrapped by this WaveData
     * @param waveItem the wave item used as the key into the wave
     * @param value the data hold by he wave data wrapper
     *
     * @return a new fresh wave Data object
     */
    static <T extends Object> WaveData<T> waveData(final WaveItem<T> waveItem, final T value) {
        return new WaveDataBase<>(waveItem, value);
    }

    /**
     * Build a fresh Wave use to call the {@link ChainWaveCommand} command.
     *
     * @param waveList the list of wave to chain
     *
     * @return the wave
     */
    static Wave chainWaveCommand(final List<Wave> waveList) {
        return wave()
                     .waveGroup(WaveGroup.CALL_COMMAND)
                     .componentClass(ChainWaveCommand.class)
                     .addDatas(
                               Builders.waveData(JRebirthWaves.CHAINED_WAVES, waveList)
                     );
    }

}
