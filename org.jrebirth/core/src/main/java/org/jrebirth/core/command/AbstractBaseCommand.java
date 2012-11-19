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

import org.jrebirth.core.concurrent.AbstractJrbRunnable;
import org.jrebirth.core.concurrent.JRebirth;
import org.jrebirth.core.concurrent.RunIntoType;
import org.jrebirth.core.event.EventType;
import org.jrebirth.core.exception.CommandException;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.exception.JRebirthThreadException;
import org.jrebirth.core.link.AbstractWaveReady;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveBean;
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
 */
public abstract class AbstractBaseCommand extends AbstractWaveReady<Command> implements Command {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBaseCommand.class);

    /**
     * The field that indicate how this command must be launched.
     */
    private final RunIntoType runIntoThread;

    /**
     * The parent command, useful when chained or multi commands are used.
     */
    private Command parentCommand;

    /**
     * Default constructor.
     * 
     * @param runIntoThread the way to launch this command
     */
    public AbstractBaseCommand(final RunIntoType runIntoThread) {
        super();
        this.runIntoThread = runIntoThread;
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
    protected abstract void processAction(final Wave wave);

    /**
     * {@inheritDoc}
     */
    @Override
    public final void run() {
        run(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void run(final Wave wave) {

        // Create the runnable that will be run
        // Add the runnable to the runner queue run it as soon as possible
        JRebirth.run(getRunInto(), new AbstractJrbRunnable(this.getClass().getSimpleName()) {

            /**
             * {@inheritDoc}
             */
            @Override
            protected void runInto() throws JRebirthThreadException {

                try {
                    preExecute(wave);

                    execute(wave);

                    postExecute(wave);

                } catch (final CommandException ce) {
                    LOGGER.error("Command has failed :", ce);
                    wave.setStatus(Wave.Status.Failed);
                }
            }
        });
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
    protected final RunIntoType getRunInto() {
        return this.runIntoThread;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void finalize() throws Throwable {
        getLocalFacade().getGlobalFacade().trackEvent(EventType.DESTROY_COMMAND, null, this.getClass());
        super.finalize();
    }

    /**
     * @return Returns the parentCommand.
     */
    public Command getParentCommand() {
        return this.parentCommand;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParentCommand(final Command parentCommand) {
        this.parentCommand = parentCommand;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends WaveBean> getWaveBeanClass() {
        return WaveBean.class;
    }

    /**
     * Fire an achieve event for command listeners.
     * 
     * And consume the wave that trigger this command
     * 
     * @param wave forward the wave that has been performed
     */
    protected void fireAchieve(final Wave wave) {
        LOGGER.trace(this.getClass().getSimpleName() + " consumes  " + wave.toString());
        wave.setStatus(Wave.Status.Consumed);
    }

}
