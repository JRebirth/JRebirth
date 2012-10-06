/**
 * Copyright JRebirth.org © 2011-2012 
 * Contact sebastien.bordes@jrebirth.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.core.command;

import java.util.ArrayList;
import java.util.List;

import org.jrebirth.core.concurrent.RunIntoType;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.wave.Wave;

/**
 * The class <strong>AbstractBaseMultiCommand</strong>.
 * 
 * The base multi command class for Internal commands.
 * 
 * @author Sébastien Bordes
 */
public abstract class AbstractBaseMultiCommand extends AbstractBaseCommand implements MultiCommand, CommandListener {

    /** The list of command that will be chained. */
    private final List<Class<? extends Command>> commandList = new ArrayList<>();

    private final boolean sequential;

    private int commandRunIndex = 0;

    /**
     * Default Constructor.
     * 
     * @param runInto The run into thread type
     */
    public AbstractBaseMultiCommand(final RunIntoType runInto) {
        this(runInto, true);
    }

    /**
     * Default Constructor.
     * 
     * @param runInto The run into thread type
     * @param sequential
     */
    public AbstractBaseMultiCommand(final RunIntoType runInto, final boolean sequential) {
        super(runInto);
        this.sequential = sequential;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void ready() throws CoreException {

        addSubCommand();

        for (final Class<? extends Command> commandClass : this.commandList) {
            getLocalFacade().retrieve(commandClass);
        }
    }

    /**
     * This method must be used to add sub command.
     */
    protected abstract void addSubCommand();

    /**
     * {@inheritDoc}
     */
    @Override
    protected void execute(final Wave wave) {
        if (this.sequential) {

            final Command command = getLocalFacade().retrieve(this.commandList.get(this.commandRunIndex));
            command.addCommandListener(this);
            command.run(wave);

        } else {
            // Launch all sub command in parallel
            for (final Class<? extends Command> commandClass : this.commandList) {
                getLocalFacade().retrieve(commandClass).run(wave);
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void commandAchieved(final Wave wave) {
        this.commandRunIndex++;
        // Run next command if any
        if (this.commandList.size() > this.commandRunIndex) {
            execute(wave);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCommandClass(final Class<? extends Command> commandClass) {
        this.commandList.add(commandClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processAction(final Wave wave) {
        // Nothing to do yet
    }

}
