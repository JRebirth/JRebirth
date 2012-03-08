package org.jrebirth.core.command;

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
public abstract class AbstractBaseMultiCommand extends AbstractBaseCommand implements MultiCommand{

	
	private List<Command> commandList;
	
	/**
	 * Default Constructor.
	 */
	public AbstractBaseMultiCommand(RunIntoType runInto) {
		super(runInto);
	}

	/**
	 * 
	 */
	@Override
	public void ready() throws CoreException {
	}

	/**
	 * 
	 */
	@Override
	public void addCommand(Command command) {
	}

}
