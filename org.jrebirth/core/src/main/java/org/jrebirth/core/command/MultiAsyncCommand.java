package org.jrebirth.core.command;

/**
 * 
 * The class <strong>MultiAsyncCommand</strong>. TODO To complete
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 65 $ $Author: sebboon $
 * @since $Date: 2011-10-14 23:44:07 +0200 (ven., 14 oct. 2011) $
 */
public interface MultiAsyncCommand extends Command {

    /**
     * Add command to the queue.
     * 
     * @param command the command to add
     */
    void addCommand(Command command);
}
