/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
 * Contact : sebastien.bordes@jrebirth.org
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
import org.jrebirth.core.wave.WaveBuilder;
import org.jrebirth.core.wave.WaveGroup;
import org.jrebirth.core.wave.WaveListener;

/**
 * The class <strong>AbstractBaseMultiCommand</strong>.
 * 
 * The base multi command class for Internal commands.
 * 
 * @author Sébastien Bordes
 */
public abstract class AbstractBaseMultiCommand extends AbstractBaseCommand implements MultiCommand, WaveListener {

    /** The list of command that will be chained. */
    private final List<Class<? extends Command>> commandList = new ArrayList<>();

    /**
     * Flag that indicate if commands must be run sequentially(true) or in parallel(false).
     */
    private final boolean sequential;

    /** The index of the last command performed. */
    private int commandRunIndex;

    /** The source wave that trigger this command. */
    private Wave waveSource;

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
     * @param sequential indicate if commands must be run sequentially(true) or in parallel(false)
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

            // Store the wave when we are running the first command
            synchronized (this) {
                if (this.commandRunIndex == 0) {
                    this.waveSource = wave;
                }

                if (this.commandList.size() > this.commandRunIndex) {
                    final Wave subCommandWave = WaveBuilder.create()
                            .waveGroup(WaveGroup.CALL_COMMAND)
                            .relatedClass(this.commandList.get(this.commandRunIndex))
                            .build();

                    subCommandWave.linkWaveBean(wave.getWaveBean());
                    subCommandWave.addWaveListener(this);
                    sendWave(subCommandWave);
                }
            }

        } else {
            // Launch all sub command in parallel
            for (final Class<? extends Command> commandClass : this.commandList) {
                getLocalFacade().retrieve(commandClass).run();
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waveConsumed(final Wave wave) {
        if (this.sequential) {

            synchronized (this) {

                // Move the index to retrieve the next command to run
                this.commandRunIndex++;

                // Run next command if any
                if (this.commandList.size() > this.commandRunIndex) {
                    execute(wave);
                } else {
                    // No more command to run the MultiCommand is achieved
                    fireConsumed(this.waveSource);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waveFailed(final Wave wave) {
        if (this.sequential) {
            synchronized (this) {
                // A sub command has failed
                fireFailed(this.waveSource);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCommandClass(final Class<? extends Command> commandClass) {
        this.commandList.add(commandClass);
    }

}
