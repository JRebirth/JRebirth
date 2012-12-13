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
package org.jrebirth.core.facade;

import org.jrebirth.core.command.Command;
import org.jrebirth.core.exception.WaveException;
import org.jrebirth.core.service.Service;
import org.jrebirth.core.ui.Model;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveData;
import org.jrebirth.core.wave.WaveType;

/**
 * The interface <strong>WaveReady</strong>.
 * 
 * Define the contract used to manage waves.
 * 
 * @author Sébastien Bordes
 */
public interface WaveReady {

    /**
     * Begin to listen the type of wave for the current component.
     * 
     * By default the Wave Contract will be checked use {@link #listen(WaveType, boolean)} otherwise
     * 
     * @param waveType the type to listen
     */
    void listen(WaveType waveType);

    /**
     * Begin to listen the type of wave for the current component.
     * 
     * @param waveType the type to listen
     * @param checkWaveContract true to check the wave contract, false otherwise
     */
    void listen(WaveType waveType, boolean checkWaveContract);

    /**
     * Stop to listen the type of wave for the current component.
     * 
     * @param waveType the type to stop to listen
     */
    void unlisten(WaveType waveType);

    /**
     * Send a wave to the notifier.
     * 
     * The wave will automatically be sent from JRebirthThread.
     * 
     * @param wave the wave to send
     */
    void sendWave(Wave wave);

    /**
     * Send a wave to the notifier.
     * 
     * The wave will automatically be sent from JRebirthThread.
     * 
     * @param waveType the type of wave to send
     * @param waveData the data (key-value
     */
    void sendWave(WaveType waveType, WaveData<?>... waveData);

    /**
     * Send a wave used to call a command.
     * 
     * The command will be called from JRebirthThread and could execute itself from another thread.
     * 
     * @param commandClass the command class to call
     * @param data the data to transport
     */
    void callCommand(final Class<? extends Command> commandClass, final WaveData<?>... data);

    /**
     * Send a wave used to return data from a service.
     * 
     * The service will be called from JRebirthThread and could execute itself from another thread.
     * 
     * @param serviceClass the service called
     * @param waveType the type of the wave
     * @param data the data to transport
     */
    void returnData(final Class<? extends Service> serviceClass, final WaveType waveType, final WaveData<?>... data);

    /**
     * Send a wave used to display an UI model.
     * 
     * The command will be called from JRebirthThread but will execute itself from the JavaFX Application thread.
     * 
     * @param modelClass the model class to display
     * @param data the data to transport
     */
    void attachUi(final Class<? extends Model> modelClass, final WaveData<?>... data);

    /**
     * Process a wave.
     * 
     * @param wave the wave to process.
     * 
     * @throws WaveException if an error occurred while dispatching the wave
     */
    void handle(Wave wave) throws WaveException;

}
