/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
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

import org.jrebirth.af.core.resource.provided.JRebirthParameters;

/**
 *
 * The interface <strong>WaveType</strong>.
 *
 * //A WaveType could be an enumeration that return an action name.
 *
 * @author Sébastien Bordes
 */
public interface WaveType {

    int uid();

    WaveType uid(int uid);

    String action();

    WaveType action(String action);

    List<WaveItem<?>> items();

    WaveType items(WaveItem<?>... items);

    String returnAction();

    WaveType returnAction(String action);

    WaveItem<?> returnItem();

    WaveType returnItem(WaveItem<?> returnItem);

    WaveType returnWaveType();

    /**
     * Return the name of the wave type, commonly bound on enum.name() method.
     *
     * @return the name of the wave type
     */
    // String getName();

    /**
     * Return the method to process in the processor class.
     *
     * @return a method name or null
     */
    // String getAction();

    /**
     * Build a wave type.
     *
     * @param action The action to perform, "DO_" keyword (by default see {@link JRebirthParameters.WAVE_HANDLER_PREFIX}) will be prepended to the action name to generate the handler method
     *
     * @param waveItems the list of {@link WaveItem} required by this wave
     *
     * @return a new fresh wave type object
     */
    static WaveType create(final String action/* , final WaveItem<?>... waveItems */) {

        return WaveTypeBase.create().action(action/* , waveItems */);
    }

    // /**
    // * Build a wave type.
    // *
    // * @param action The action to perform "DO_" (by default see {@link JRebirthParameters.WAVE_HANDLER_PREFIX}) keyword will be prepended to the action name to generate the handler method
    // * @param returnWaveType the return wave Type to call after having processing the current
    // * @param waveItems the list of {@link WaveItem} required by this wave
    // *
    // * @return a new fresh wave type object
    // */
    // static WaveTypeBase create(final String action, final WaveType returnWaveType, final WaveItem<?>... waveItems) {
    // final WaveTypeBase waveType = create(action, waveItems);
    // waveType.setReturnWaveType(waveType);
    // return waveType;
    // }

}
