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

import org.jrebirth.af.api.annotation.PriorityLevel;
import org.jrebirth.af.api.command.Command;
import org.jrebirth.af.api.command.CommandBean;
import org.jrebirth.af.api.concurrent.RunInto;
import org.jrebirth.af.api.concurrent.RunType;
import org.jrebirth.af.api.facade.JRebirthEventType;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.WaveBean;
import org.jrebirth.af.core.component.behavior.AbstractBehavioredComponent;
import org.jrebirth.af.core.concurrent.JRebirth;
import org.jrebirth.af.core.exception.CommandException;
import org.jrebirth.af.core.util.ClassUtility;
import org.jrebirth.af.core.wave.DefaultWaveBean;
import org.jrebirth.af.core.wave.WBuilder;

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
public abstract class AbstractBaseCommand<WB extends WaveBean> extends AbstractBehavioredComponent<Command> implements CommandBean<WB> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBaseCommand.class);

    /**
     * The field that indicates how this command must be launched.
     */
    protected RunType runIntoThread;

    /**
     * The field that indicates the threading priority.
     */
    protected PriorityLevel runnablePriority;

    private Class<WB> waveBeanClass;

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
        this(null, null);
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
     * @param priority the runnable priority
     */
    @SuppressWarnings("unchecked")
    public AbstractBaseCommand(final RunType runType, final PriorityLevel priority) {
        super();
        // Try to retrieve the RunInto annotation at class level within class hierarchy
        final RunInto ria = ClassUtility.getLastClassAnnotation(this.getClass(), RunInto.class);

        // First try to get the annotation value
        // Secondly by provided runtType argument
        // Thirdly (default case) use JIT
        this.runIntoThread = ria == null ? runType == null ? RunType.JIT : runType : ria.value();

        // Do same job for the priority
        this.runnablePriority = ria == null ? priority == null ? PriorityLevel.Normal : priority : ria.priority();

        this.waveBeanClass = (Class<WB>) ClassUtility.getGenericClassAssigned(this.getClass(), WaveBean.class);
        if (this.waveBeanClass == null) {
            this.waveBeanClass = (Class<WB>) DefaultWaveBean.class;
        }

        // if (getClass().getGenericInterfaces().length > 0) {
        // try {
        // this.waveBeanClass = (Class<WB>) ClassUtility.getClassFromType(((ParameterizedType) getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0]);
        // } catch (final Exception e) {
        //
        // }
        // }
        // if (getClass().getGenericSuperclass() != null) {
        // try {
        // this.waveBeanClass = (Class<WB>) ClassUtility.getClassFromType(((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        // } catch (final Exception e) {
        //
        // }
        // }
    }

    /**
     * Perform the command code.
     *
     * @param wave the wave that contain data to be processed
     *
     * @throws CommandException if an error occurred while processing the command
     */
    protected abstract void perform(final Wave wave) throws CommandException;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processWave(final Wave wave) {
        // Command that listen WaveType are retained in memory by the notifier
        LOGGER.warn(this.getClass() + " should manage custom code to handle the wave " + wave.toString() + " or unlisten this type");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Wave run() {
        // No wave was created to trigger this command
        return run(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Wave run(final Wave wave) {

        // If given wave is null
        // Build a default Wave to avoid NullPointerException when
        // command was directly called by its run() method
        final Wave commandWave = wave == null ? WBuilder.callCommand(this.getClass()) : wave;

        // Create the runnable that will be run
        // Add the runnable to the runner queue run it as soon as possible
        JRebirth.run(getRunInto(), new CommandRunnable(this.getClass().getSimpleName(), this, commandWave));

        return commandWave;
    }

    /**
     * Run the inner task.
     *
     * @param wave the wave that have triggered this command
     *
     * @throws CommandException if an error occurred
     */
    final void innerRun(final Wave wave) throws CommandException {
        beforePerform(wave);
        perform(wave);
        afterPerform(wave);
    }

    /**
     * Actions to perform before the command into the executor thread.
     *
     * @param wave the wave that triggered this command
     */
    protected abstract void beforePerform(final Wave wave);

    /**
     * Actions to perform after the command into the executor thread.
     *
     * @param wave the wave that triggered this command
     */
    protected abstract void afterPerform(final Wave wave);

    /**
     * @return Returns the runInto.
     */
    protected final RunType getRunInto() {
        return this.runIntoThread;
    }

    /**
     * @return Returns the runInto.
     */
    protected final PriorityLevel getPriorityLevel() {
        return this.runnablePriority;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void finalize() throws Throwable {
        localFacade().getGlobalFacade().trackEvent(JRebirthEventType.DESTROY_COMMAND, null, this.getClass());
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
     * {@inheritDoc}
     */
    @Override
    public WB waveBean(final Wave wave) {
        return wave.waveBean(this.waveBeanClass);
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
        wave.status(Wave.Status.Consumed);
    }

    /**
     * Fire an handled event for command listeners.
     *
     * And handle the wave that trigger this command
     *
     * @param wave forward the wave that has been performed
     */
    protected void fireHandled(final Wave wave) {
        LOGGER.trace(this.getClass().getSimpleName() + " handles  " + wave.toString());
        wave.status(Wave.Status.Handled);
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
        wave.status(Wave.Status.Failed);
    }

}
