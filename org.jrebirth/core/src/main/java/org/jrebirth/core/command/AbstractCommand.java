package org.jrebirth.core.command;

import org.jrebirth.core.concurent.RunIntoType;

/**
 * The class <strong>AbstractCommand</strong>.
 * 
 * Base implementation for Internal Commands.
 * 
 * @author Sébastien Bordes
 */
public abstract class AbstractCommand extends AbstractBaseCommand implements Command {

    /**
     * Default Constructor used to run this action into the JET.
     */
    public AbstractCommand() {
        super(RunIntoType.JET);
    }

}
