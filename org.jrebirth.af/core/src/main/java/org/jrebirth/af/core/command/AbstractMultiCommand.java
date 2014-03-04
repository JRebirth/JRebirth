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
package org.jrebirth.af.core.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.jrebirth.af.core.annotation.Sequential;
import org.jrebirth.af.core.concurrent.RunType;
import org.jrebirth.af.core.concurrent.RunnablePriority;
import org.jrebirth.af.core.exception.CoreException;
import org.jrebirth.af.core.util.ClassUtility;
import org.jrebirth.af.core.wave.Wave;
import org.jrebirth.af.core.wave.WaveBean;
import org.jrebirth.af.core.wave.WaveBuilder;
import org.jrebirth.af.core.wave.WaveGroup;
import org.jrebirth.af.core.wave.WaveListener;

/**
 * The class <strong>AbstractMultiCommand</strong>.
 * 
 * The base multi command class for Internal commands.
 * 
 * @author Sébastien Bordes
 * 
 * @param <WB> The WaveBean type used for this command (by default you can use the WaveBean interface)
 */
public abstract class AbstractMultiCommand<WB extends WaveBean> extends AbstractBaseCommand<WB> implements MultiCommand<WB>, WaveListener {

    /** The default sequential value, could be overridden by annotation or constructor argument. */
    private static final Boolean DEFAULT_SEQUENTIAL_VALUE = Boolean.TRUE;

    /** The command is running. */
    private final AtomicBoolean running = new AtomicBoolean(false);

    /** The list of command that will be chained. */
    private final List<Class<? extends Command>> commandList = new ArrayList<>();

    /** The list of pending waves triggered by each command when launched in parallel. */
    private final List<Wave> pendingWaves = Collections.synchronizedList(new ArrayList<Wave>());

    /** Flag that indicate if commands must be run sequentially(true) or in parallel(false). */
    private boolean sequential;

    /** The index of the last command performed. */
    private int commandRunIndex;

    /** The source wave that trigger this command. */
    private Wave waveSource;

    /**
     * Default Constructor.
     * 
     * @param runInto The run into thread type
     */
    public AbstractMultiCommand(final RunType runInto) {
        this(runInto, true);
        initSequential(null);
    }

    /**
     * Default Constructor.
     * 
     * @param sequential indicate if commands must be run sequentially(true) or in parallel(false)
     */
    public AbstractMultiCommand(final boolean sequential) {
        super();
        initSequential(sequential);
    }

    /**
     * Default Constructor.
     * 
     * @param runInto The run into thread type
     * @param sequential indicate if commands must be run sequentially(true) or in parallel(false)
     */
    public AbstractMultiCommand(final RunType runInto, final boolean sequential) {
        super(runInto, null);
        initSequential(sequential);
    }

    /**
     * Default Constructor.
     * 
     * @param runInto The run into thread type
     * @param priority the runnable priority
     * @param sequential indicate if commands must be run sequentially(true) or in parallel(false)
     */
    public AbstractMultiCommand(final RunType runInto, final RunnablePriority priority, final boolean sequential) {
        super(runInto, priority);
        initSequential(sequential);
    }

    /**
     * Define the sequential value.
     * 
     * It will try to load the annotation value, then the parameter given to constructor. If none of them have been used the default false value will be used.
     * 
     * @param sequential the constructor parameter
     */
    private void initSequential(final Boolean sequential) {

        // Try to retrieve the Sequential annotation at class level within class hierarchy
        final Sequential seq = ClassUtility.getLastClassAnnotation(this.getClass(), Sequential.class);

        // First try to get the annotation value (if present
        // Secondly by provided runtType argument
        // Thirdly (default case) use FALSE value
        setSequential(seq == null ? sequential == null ? DEFAULT_SEQUENTIAL_VALUE : sequential : seq.value());

    }

    /**
     * @return Returns the sequential.
     */
    protected boolean isSequential() {
        return this.sequential;
    }

    /**
     * @param sequential The sequential to set.
     */
    protected void setSequential(final boolean sequential) {
        this.sequential = sequential;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void ready() throws CoreException {

        manageSubCommand();

        for (final Class<? extends Command> commandClass : this.commandList) {
            getLocalFacade().retrieve(commandClass);
        }

        initCommand();
    }

    /**
     * Custom method used to initialize the command.
     * 
     * Called into JIT by ready method.
     */
    protected abstract void initCommand();

    /**
     * This method must be used to add sub command by calling {@link #addCommandClass(Class)}.
     */
    protected abstract void manageSubCommand();

    /**
     * {@inheritDoc}
     */
    @Override
    public void preExecute(final Wave wave) {
        this.running.set(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void postExecute(final Wave wave) {
        // Nothing to do
    }

    /**
     * @return Returns the running.
     */
    public boolean isRunning() {
        return this.running.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void execute(final Wave wave) {

        if (isSequential()) {

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
            // Store the orignal wave to be able to mark it as consumed when all of these sub comamnds are achieved
            this.waveSource = wave;

            // Launch all sub command in parallel
            for (final Class<? extends Command> commandClass : this.commandList) {
                final Wave commandWave = getLocalFacade().retrieve(commandClass).run();
                // register to Wave status of all command triggered
                commandWave.addWaveListener(this);
                // Store the pending command to know when all command are achieved
                this.pendingWaves.add(commandWave);
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waveConsumed(final Wave wave) {
        if (isSequential()) {

            synchronized (this) {

                // Move the index to retrieve the next command to run
                this.commandRunIndex++;

                // Run next command if any
                if (this.commandList.size() > this.commandRunIndex) {
                    // Recurse to handle next command
                    execute(wave);
                } else {
                    this.running.set(false);
                    // No more command to run the MultiCommand is achieved
                    fireConsumed(this.waveSource);
                }
            }
        } else {

            // Remove each command that has been performed
            this.pendingWaves.remove(wave);

            // If there is no pending waves left, send the waveConsumed event on the MultiCommand wave
            if (this.pendingWaves.size() == 0) {
                fireConsumed(this.waveSource);
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
        } else {
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
    public void waveCancelled(final Wave wave) {
        // TODO stop pending waves
        if (isSequential()) {

        } else {

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waveDestroyed(final Wave wave) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waveCreated(final Wave wave) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waveSent(final Wave wave) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waveProcessed(final Wave wave) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCommandClass(final Class<? extends Command> commandClass) {
        this.commandList.add(commandClass);
    }

}
