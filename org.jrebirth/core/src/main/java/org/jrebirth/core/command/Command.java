package org.jrebirth.core.command;

import org.jrebirth.core.facade.FacadeReady;
import org.jrebirth.core.link.Wave;

/**
 * 
 * The class <strong>Command</strong>.
 * 
 * The contract for the command layer.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 172 $ $Author: sbordes $
 * @since $Date: 2011-11-11 01:06:08 +0100 (ven., 11 nov. 2011) $
 */
public interface Command extends FacadeReady<Command> {

    /**
     * Run the command.
     * 
     * @param wave the wave that have triggered this command
     */
    void run(Wave wave);
}
