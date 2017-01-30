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
package org.jrebirth.af.core.command.multi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.jrebirth.af.api.annotation.PriorityLevel;
import org.jrebirth.af.api.annotation.Sequential;
import org.jrebirth.af.api.command.Command;
import org.jrebirth.af.api.command.MultiCommand;
import org.jrebirth.af.api.concurrent.RunType;
import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.exception.CoreRuntimeException;
import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.WaveBean;
import org.jrebirth.af.api.wave.WaveListener;
import org.jrebirth.af.core.command.AbstractBaseCommand;
import org.jrebirth.af.core.key.Key;
import org.jrebirth.af.core.util.ClassUtility;
import org.jrebirth.af.core.wave.WBuilder;
import org.jrebirth.af.core.wave.WaveDataBase;

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

    /** The list of sub command that will be chained. */
    private List<UniqueKey<? extends Command>> subCommandList;

    /** The list of pending waves triggered by each command when launched in parallel. */
    private final List<Wave> pendingWaves = Collections.synchronizedList(new ArrayList<Wave>());

    /** Flag that indicate if commands must be run sequentially(true) or in parallel(false). */
    private boolean sequential;

    /** The index of the last command performed. */
    private int commandRunIndex;

    /** The source wave that trigger this command. */
    private Wave waveSource;

    /** Cancellation has been requested. */
    private final AtomicBoolean cancelRequested = new AtomicBoolean(false);

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
        this(null, null, sequential);
    }

    /**
     * Default Constructor.
     *
     * @param runInto The run into thread type
     * @param sequential indicate if commands must be run sequentially(true) or in parallel(false)
     */
    public AbstractMultiCommand(final RunType runInto, final boolean sequential) {
        this(runInto, null, sequential);
    }

    /**
     * Default Constructor.
     *
     * @param runInto The run into thread type
     * @param priority the runnable priority
     * @param sequential indicate if commands must be run sequentially(true) or in parallel(false)
     */
    public AbstractMultiCommand(final RunType runInto, final PriorityLevel priority, final boolean sequential) {
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

        synchronized (this) {

            this.subCommandList = defineSubCommand();

            if (this.subCommandList == null || this.subCommandList.isEmpty()) {
                throw new CoreRuntimeException("Warning, 'defineSubCommand' should return at least one Command Key.");
            }

            for (final UniqueKey<? extends Command> commandKey : this.subCommandList) {
                localFacade().retrieve(commandKey);
            }

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
     * This method must be used to return the list of sub command keys.
     *
     * Keys can be build using {@link #getCommandKey(Class, Object...)}.
     *
     * @return the list of Command Key that will be performed
     */
    protected abstract List<UniqueKey<? extends Command>> defineSubCommand();

    /**
     * {@inheritDoc}
     */
    @Override
    public final void beforePerform(final Wave wave) {
        this.running.set(true);
        before(wave);
    }

    /**
     * Hook called before execution of all sub commands.
     * 
     * @param wave the wave that triggered the multi command
     */
    protected abstract void before(Wave wave);

    /**
     * Hook called after execution of all sub commands.
     * 
     * @param wave the wave that triggered the multi command
     */
    protected abstract void after(Wave wave);

    /**
     * Hook called before each call of sub commands.
     * 
     * @param wave the wave that triggered the multi command
     */
    protected abstract void beforeEach(Wave wave, Wave childWave);

    /**
     * Hook called after each call of sub commands. Command is probably not completed.
     * 
     * @param wave the wave that triggered the multi command
     */
    protected abstract void afterEach(Wave wave, Wave childWave);

    /**
     * {@inheritDoc}
     */
    @Override
    public final void afterPerform(final Wave wave) {
        after(wave);
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
    protected void perform(final Wave wave) {

        // Avoid to continue launching next command if cancellation has been requested
        if (!this.cancelRequested.get()) {
            if (isSequential()) {

                // Store the wave when we are running the first command
                synchronized (this) {
                    if (this.commandRunIndex == 0) {
                        this.waveSource = wave;
                        fireConsumed(this.waveSource);
                    }

                    if (this.subCommandList.size() > this.commandRunIndex) {

                        final Wave subCommandWave = WBuilder.callCommand(this.subCommandList.get(this.commandRunIndex).classField())
                                                            // FIXME UNIQUE KEY of sub command
                                                            // Recopy all WaveBeans
                                                            .waveBeanList(wave.waveBeanList())
                                                            // Recopy the WaveData from the previous wave
                                                            .addDatas(wave.waveDatas().toArray(new WaveDataBase[0]))
                                                            .addWaveListener(this);
                        beforeEach(wave, subCommandWave);
                        sendWave(subCommandWave);
                        afterEach(wave, subCommandWave);
                    }
                }

            } else {

                // Store the original wave to be able to mark it as consumed when all of these sub comamnds are achieved
                this.waveSource = wave;

                synchronized (this) {

                    // Launch all sub command in parallel
                    for (final UniqueKey<? extends Command> commandKey : this.subCommandList) {
                        beforeEach(wave, null);
                        final Wave commandWave = localFacade().retrieve(commandKey).run();
                        afterEach(wave, commandWave);
                        // register to Wave status of all command triggered
                        commandWave.addWaveListener(this);

                        // Store the pending command to know when all command are achieved
                        this.pendingWaves.add(commandWave);
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waveConsumed(final Wave wave) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waveHandled(final Wave wave) {
        synchronized (this) {
            if (isSequential()) {

                // Move the index to retrieve the next command to run
                this.commandRunIndex++;

                // Run next command if any
                if (this.subCommandList.size() > this.commandRunIndex) {
                    // Recurse to handle next command
                    perform(wave);
                } else {
                    this.running.set(false);
                    // No more command to run the MultiCommand is achieved
                    fireHandled(this.waveSource);
                }
            } else {

                // Remove each command that has been performed
                this.pendingWaves.remove(wave);

                // If there is no pending waves left, send the waveConsumed event on the MultiCommand wave
                if (this.pendingWaves.isEmpty()) {
                    fireHandled(this.waveSource);
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
        // Nothing to do yet
        // cancelRequested.set(true);
        // if (isSequential()) {
        //
        // } else {
        //
        // }
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

    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public void addCommandClass(final Class<? extends Command> commandClass) {
    // this.commandList.add(UniqueKey.key(commandClass));
    // }

    /**
     * Return the command key.
     *
     * @param commandClass the class of the command to call
     * @param keyPart the object used as key parts
     */
    protected UniqueKey<? extends Command> getCommandKey(final Class<? extends Command> commandClass, final Object... keyPart) {
        return Key.create(commandClass, keyPart);
    }

    // /**
    // * {@inheritDoc}
    // */
    // protected void addSubCommand(final UniqueKey<? extends Command> commandKey) {
    // this.commandList.add(commandKey);
    // }

}
