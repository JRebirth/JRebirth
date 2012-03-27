package org.jrebirth.core.facade;

import org.jrebirth.core.command.Command;
import org.jrebirth.core.exception.WaveException;
import org.jrebirth.core.link.Wave;
import org.jrebirth.core.link.WaveData;
import org.jrebirth.core.link.WaveType;
import org.jrebirth.core.ui.Model;

/**
 * The interface <strong>WaveReady</strong>.
 * 
 * Define the contract used to manage waves.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public interface WaveReady {

    /**
     * Begin to listen the type of wave for the current component.
     * 
     * @param waveType the type to listen
     */
    void listen(WaveType waveType);

    /**
     * Stop to listen the type of wave for the current component.
     * 
     * @param waveType the type to stop to listen
     */
    void unlisten(WaveType waveType);

    /**
     * Send a wave to the notifier.
     * 
     * @param waveType the type of wave to send
     * @param waveData the data (key-value
     */
    void send(WaveType waveType, WaveData<?>... waveData);

    /**
     * Send a wave used to call a command.
     * 
     * @param commandClass the command class to call
     * @param data the data to transport
     */
    void callCommand(final Class<? extends Command> commandClass, final WaveData<?>... data);

    /**
     * Send a wave used to return data from a service.
     * 
     * @param serviceClass the service called
     * @param data the data to transport
     */
    void returnData(final Class<? extends Command> serviceClass, final WaveData<?>... data);

    /**
     * Send a wave used to display an UI model.
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
