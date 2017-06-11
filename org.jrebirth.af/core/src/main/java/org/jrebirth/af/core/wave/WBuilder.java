/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2016
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

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import org.jrebirth.af.api.annotation.PriorityLevel;
import org.jrebirth.af.api.command.Command;
import org.jrebirth.af.api.service.Service;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.WaveBean;
import org.jrebirth.af.api.wave.WaveGroup;
import org.jrebirth.af.api.wave.contract.WaveData;
import org.jrebirth.af.api.wave.contract.WaveItem;
import org.jrebirth.af.api.wave.contract.WaveType;
import org.jrebirth.af.core.command.basic.ChainWaveCommand;

/**
 * The Interface WBuilders used as a convenient wrapper to build wave related JRebirth Internal objects.
 */
public interface WBuilder {

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
     *
     * @return the wave type
     */
    static WaveType waveType(final String action) {
        return waveType().action(action);
    }

    /**
     * Build a fresh Wave type with action name using default hard coded prefix "DO_".
     *
     * @param action the action
     *
     * @return the wave type
     */
    static WaveType waveTypeDo(final String action) {
        return waveType().action(action).prefix("DO_");
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
     * Build a fresh Wave use to attach a model somewhere.
     *
     * Support these kind of objects:<br>
     * <ul>
     * <li>JRebirthWaves.ATTACH_UI_NODE_PLACEHOLDER => ObjectProperty&lt;Node&gt;</li> 
     * <li>JRebirthWaves.ADD_UI_CHILDREN_PLACEHOLDER => ObservableList&lt;Node&gt;</li> 
     * <li>JRebirthWaves.SHOW_MODEL_COMMAND => Class&lt;? extends Command&gt;</li>
     * <li>JRebirthWaves.EXTRA_WAVE_BEANS => WaveBean</li>
     * </ul>
     *
     * @param datas a list of object that can be wrapped into a WaveData with Right waveItem
     *
     * @return a list of WaveData
     */
    @SuppressWarnings("unchecked")
    static List<WaveData<?>> buildUiData(final Object... datas) {

        final List<WaveData<?>> wdList = new ArrayList<>();

        List<WaveBean> wbList = null;

        for (final Object data : datas) {

            if (data instanceof ObjectProperty) {
                wdList.add(waveData(JRebirthWaves.ATTACH_UI_NODE_PLACEHOLDER, (ObjectProperty<Node>) data));
            }

            if (data instanceof ObservableList) {
                wdList.add(waveData(JRebirthWaves.ADD_UI_CHILDREN_PLACEHOLDER, (ObservableList<Node>) data));
            }

            if (data instanceof Class && Command.class.isAssignableFrom((Class<?>) data)) {
                wdList.add(waveData(JRebirthWaves.SHOW_MODEL_COMMAND, (Class<? extends Command>) data));
            }

            if (data instanceof WaveBean) {
                if (wbList == null) {
                    wbList = new ArrayList<>();
                    wdList.add(waveData(JRebirthWaves.EXTRA_WAVE_BEANS, wbList));
                }
                wbList.add((WaveBean) data);
            }

        }
        return wdList;
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
                               WBuilder.waveData(JRebirthWaves.CHAINED_WAVES, waveList));
    }

    /**
     * Create a {@link WaveItem} holding a {@link PriorityLevel}.
     *
     * @param level the level to embed
     *
     * @return the configured wave data
     */
    static WaveData<PriorityLevel> priority(final PriorityLevel level) {
        return waveData(JRebirthItems.priority, level);
    }

    /**
     * Create a {@link WaveItem} holding a timeout value.
     *
     * @param timeout the number of millisecond to embed
     *
     * @return the configured wave data
     */
    static WaveData<Long> timeout(final long timeout) {
        return waveData(JRebirthItems.syncTimeout, timeout);
    }

    /**
     * Create a {@link WaveItem} holding a boolean flag indicating if the task shall be run synchronously.
     *
     * @param sync the flag indicating synchronously execution
     *
     * @return the configured wave data
     */
    static WaveData<Boolean> sync(final boolean sync) {
        return waveData(JRebirthItems.runSynchronously, sync);
    }

}
