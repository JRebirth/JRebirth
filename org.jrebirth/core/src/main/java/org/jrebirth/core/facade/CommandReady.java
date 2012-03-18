package org.jrebirth.core.facade;

import org.jrebirth.core.command.Command;

/**
 * 
 * The class <strong>CommandReady</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 173 $ $Author: sbordes $
 * @since $Date: 2011-11-14 22:28:08 +0100 (lun., 14 nov. 2011) $
 */
public interface CommandReady {

    /**
     * Return the command singleton or part of multiton.
     * 
     * @param clazz the service class to find
     * @param keyPart the unique key (in option)
     * 
     * @param <C> a sub class of command
     * 
     * @return a command instance
     */
    <C extends Command> C getCommand(final Class<C> clazz, final Object... keyPart);

}
