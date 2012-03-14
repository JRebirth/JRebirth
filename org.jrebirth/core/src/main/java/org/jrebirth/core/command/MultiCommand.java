package org.jrebirth.core.command;

/**
 * The class <strong>MultiCommand</strong>.
 * 
 * The contract to chain multiple commands.
 * 
 * @author Sébastien Bordes
 */
public interface MultiCommand extends Command {

    /**
     * Add command to the queue.
     * 
     * @param command the command to add
     */
    void addCommand(Command command);
}
