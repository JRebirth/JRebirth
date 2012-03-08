package org.jrebirth.core.command;

import org.jrebirth.core.concurent.RunIntoType;

/**
 * The class <strong>AbstractUICommand</strong>.
 * 
 * Base implementation for UI Commands.
 * 
 * @author Sébastien Bordes
 */
public abstract class AbstractUICommand extends AbstractBaseCommand implements Command {

    /**
     * Default Constructor used to run this action into the JAT.
     */
    public AbstractUICommand() {
        super(RunIntoType.JAT);
    }

}
