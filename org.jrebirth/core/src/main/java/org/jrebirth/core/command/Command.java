package org.jrebirth.core.command;

import org.jrebirth.core.facade.FacadeReady;
import org.jrebirth.core.link.Wave;

/**
 * The class <strong>Command</strong>.
 * 
 * The contract for the command layer.
 * 
 * @author Sébastien Bordes
 */
public interface Command extends FacadeReady<Command> {

    /**
     * Run the command.
     * 
     * @param wave the wave that have triggered this command
     */
    void run(Wave wave);
    
}
