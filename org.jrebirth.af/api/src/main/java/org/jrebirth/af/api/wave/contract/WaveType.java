/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2014
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
package org.jrebirth.af.api.wave.contract;

import java.util.List;

import org.jrebirth.af.api.command.Command;

/**
 *
 * The interface <strong>WaveType</strong> could be used to create a type that return an action name and hold {@link WaveItem} parameters.
 *
 * @author Sébastien Bordes
 */
public interface WaveType {

    /**
     * Return the unique identifier of the WaveType.
     *
     * @return the unique identifier
     */
    int uid();

    /**
     * Sets the unique identifier of the WaveType.
     *
     * @param uid the uid to set
     *
     * @return the wave type to chain method call
     */
    WaveType uid(int uid);

    /**
     * Return the current action name.
     *
     * @return the action name
     */
    String action();

    /**
     * Set the action name.
     *
     * @param action the action name to set
     *
     * @return the wave type to chain method call
     */
    WaveType action(String action);

    /**
     * Return all WaveItems used by this WaveType.
     *
     * @return the wave items list
     */
    List<WaveItem<?>> items();

    /**
     * Set all WaveItems used by the current WaveType used as parameter during wave handling.
     *
     * @param items the wave items to set
     *
     * @return the wave type to chain method call
     */
    WaveType items(WaveItem<?>... items);

    /**
     * Return the return Wave Type action name.
     *
     * @return the return action name
     */
    String returnAction();

    /**
     * Set the return Wave Type action name.
     *
     * @param action the return action name to set
     *
     * @return the wave type to chain method call
     */
    WaveType returnAction(String action);

    /**
     * Set the WaveItem returned.
     *
     * @return the wave item returned
     */
    WaveItem<?> returnItem();

    /**
     * Return the WaveItem that hold the type sent to hold the current WaveType result.
     *
     * @param returnItem the return WaveItem to set
     *
     * @return the wave type to chain method call
     */
    WaveType returnItem(WaveItem<?> returnItem);

    /**
     * Return the WaveType emitted as the result of the current WaveType.
     *
     * @return the wave type to call after the processing of the current WaveType
     */
    WaveType returnWaveType();

    /**
     * Set the Command to call in order to process the returned value.
     *
     * @param returnCommandClass the command class to set
     *
     * @return the wave type to chain method call
     */
    WaveType returnCommandClass(Class<? extends Command> returnCommandClass);

    /**
     * Return the Command to call in order to process the returned value.
     *
     * @return the command class to call
     */
    Class<? extends Command> returnCommandClass();

}
