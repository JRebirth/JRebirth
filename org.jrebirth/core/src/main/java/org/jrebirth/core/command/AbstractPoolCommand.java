package org.jrebirth.core.command;

import org.jrebirth.core.concurent.RunIntoType;

/**
 * The class <strong>AbstractPoolCommand</strong>.
 * 
 * Base implementation for Commands that will be run into a thread pool.
 * 
 * @author Sébastien Bordes
 */
public abstract class AbstractPoolCommand extends AbstractBaseCommand {

    /**
     * Default Constructor used to run this action within the thread pool.
     */
    public AbstractPoolCommand() {
        super(RunIntoType.POOL);
    }

}
