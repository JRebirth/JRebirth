package org.jrebirth.core.command;

/**
 * 
 * The class <strong>MultiSyncCommand</strong>. TODO To complete
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 63 $ $Author: sbordes $
 * @since $Date: 2011-10-14 23:21:04 +0200 (ven., 14 oct. 2011) $
 */
public interface MultiSyncCommand extends Command {

    /**
     * Add command to the queue.
     * 
     * @param command the command to add
     */
    void addCommand(Command command);
}
