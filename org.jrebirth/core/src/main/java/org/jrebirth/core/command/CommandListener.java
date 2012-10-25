package org.jrebirth.core.command;

import org.jrebirth.core.wave.Wave;

/**
 * The class <strong>CommandListener</strong>.
 * 
 * @author Sébastien Bordes
 */
public interface CommandListener {

    /**
     * This event is fired when a command has been performed. .
     * 
     * @param wave the wave processed
     */
    void commandAchieved(Wave wave);

}
