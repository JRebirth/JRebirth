package org.jrebirth.core.command;

import java.util.ArrayList;
import java.util.List;

import org.jrebirth.core.concurent.RunIntoType;
import org.jrebirth.core.exception.CoreException;

/**
 * The class <strong>AbstractBaseMultiCommand</strong>.
 * 
 * The base multi command class for Internal commands.
 * 
 * @author Sébastien Bordes
 */
public abstract class AbstractBaseMultiCommand extends AbstractBaseCommand implements MultiCommand {

    /** The list of command that will be chained. */
    private final List<Command> commandList = new ArrayList<>();;

    /**
     * Default Constructor.
     * 
     * @param runInto The run into thread type
     */
    public AbstractBaseMultiCommand(final RunIntoType runInto) {
        super(runInto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void ready() throws CoreException {
        this.commandList.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCommand(final Command command) {
        this.commandList.add(command);
    }

}
