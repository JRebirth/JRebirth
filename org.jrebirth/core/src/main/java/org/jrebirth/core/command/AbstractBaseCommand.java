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

import org.jrebirth.core.concurrent.JRebirth;
import org.jrebirth.core.concurrent.RunInto;
import org.jrebirth.core.concurrent.RunType;
import org.jrebirth.core.exception.CommandException;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.facade.JRebirthEventType;
import org.jrebirth.core.link.AbstractWaveReady;
import org.jrebirth.core.util.ClassUtility;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveBean;
import org.jrebirth.core.wave.WaveBuilder;
import org.jrebirth.core.wave.WaveGroup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>AbstractBaseCommand</strong>.
 * 
 * Base implementation of the command.
 * 
 * Allow to run the command in different thread according to the runInto field value.
 * 
 * @author Sébastien Bordes
 * 
 * @param <WB> The WaveBean type used for this command (by default you can use the WaveBean interface)
 */
public abstract class AbstractBaseCommand<WB extends WaveBean> extends AbstractWaveReady<Command> implements Command {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBaseCommand.class);

    /**
     * The field that indicate how this command must be launched.
     */
    private final RunType runIntoThread;

    // /**
    // * The parent command, useful when chained or multi commands are used.
    // */
    // private Command parentCommand;

    /**
     * Default constructor.
     * 
     * The RunIntoThread property is defined according to (ordered by priority):
     * <ol>
     * <li>RunInto annotation</li>
     * <li>JRebirth Internal Thread</li>
     * </ol>
     */
    public AbstractBaseCommand() {
        this(null);
    }

    /**
     * Default constructor.
     * 
     * The RunIntoThread property is defined according to (ordered by priority):
     * <ol>
     * <li>RunInto annotation</li>
     * <li>Provided RunType argument</li>
     * <li>JRebirth Internal Thread</li>
     * </ol>
     * 
     * @param runType the way to launch this command
     */
    public AbstractBaseCommand(final RunType runType) {
        super();
        // Try to retrieve the RunInto annotation at class level within class hierarchy
        final RunInto ria = ClassUtility.extractAnnotation(this.getClass(), RunInto.class);

        // First try to get the annotation value
        // Secondly by provided runtType argument
        // Thirdly (default case) use JIT
        this.runIntoThread = ria == null ? runType == null ? RunType.JIT : runType : ria.value();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void ready() throws CoreException;

    /**
     * Execute the command code.
     * 
     * @param wave the wave that contain data to be processed
     * 
     * @throws CommandException if an error occurred while processing the command
     */
    protected abstract void execute(final Wave wave) throws CommandException;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processAction(final Wave wave) {
        // Command must not be run a long time, so they can't handle wave types
        LOGGER.warn("Command don't manage wave type handling");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void run() {
        // No wave was created
        run(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void run(final Wave wave) {

        // If given wave is null
        // Build a default Wave to avoid NullPointerException when
        // command was directly called by its run() method
        final Wave commandWave = wave == null ?
                WaveBuilder.create()
                        .waveGroup(WaveGroup.CALL_COMMAND)
                        .relatedClass(this.getClass())
                        .build() : wave;

        // Create the runnable that will be run
        // Add the runnable to the runner queue run it as soon as possible
        JRebirth.run(getRunInto(), new CommandRunnable(this.getClass().getSimpleName(), this, commandWave));
    }

    /**
     * Run the inner task.
     * 
     * @param wave the wave that have triggered this command
     * 
     * @throws CommandException if an error occurred
     */
    final void innerRun(final Wave wave) throws CommandException {
        preExecute(wave);
        execute(wave);
        postExecute(wave);
    }

    /**
     * Actions to perform before the command into the executor thread.
     * 
     * @param wave the wave that triggered this command
     */
    protected abstract void preExecute(final Wave wave);

    /**
     * Actions to perform after the command into the executor thread.
     * 
     * @param wave the wave that triggered this command
     */
    protected abstract void postExecute(final Wave wave);

    /**
     * @return Returns the runInto.
     */
    protected final RunType getRunInto() {
        return this.runIntoThread;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void finalize() throws Throwable {
        getLocalFacade().getGlobalFacade().trackEvent(JRebirthEventType.DESTROY_COMMAND, null, this.getClass());
        super.finalize();
    }

    // /**
    // * @return Returns the parentCommand.
    // */
    // public Command getParentCommand() {
    // return this.parentCommand;
    // }
    //
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public void setParentCommand(final Command parentCommand) {
    // this.parentCommand = parentCommand;
    // }

    /**
     * Get the wave bean and cast it.
     * 
     * @param wave the wave that hold the bean
     * 
     * @return the casted wave bean
     */
    @SuppressWarnings("unchecked")
    public WB getWaveBean(final Wave wave) {
        return (WB) wave.getWaveBean();
    }

    /**
     * Fire a consumed event for command listeners.
     * 
     * And consume the wave that trigger this command
     * 
     * @param wave forward the wave that has been performed
     */
    protected void fireConsumed(final Wave wave) {
        LOGGER.trace(this.getClass().getSimpleName() + " consumes  " + wave.toString());
        wave.setStatus(Wave.Status.Consumed);
    }

    /**
     * Fire a failed event for command listeners.
     * 
     * And mark as failed the wave that trigger this command
     * 
     * @param wave forward the wave that has been performed
     */
    protected void fireFailed(final Wave wave) {
        LOGGER.trace(this.getClass().getSimpleName() + " has failed  " + wave.toString());
        wave.setStatus(Wave.Status.Failed);
    }

}
