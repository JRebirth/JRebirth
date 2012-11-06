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

import org.jrebirth.core.concurrent.AbstractJrbRunnable;
import org.jrebirth.core.concurrent.JRebirth;
import org.jrebirth.core.concurrent.RunIntoType;
import org.jrebirth.core.event.EventType;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.exception.JRebirthThreadException;
import org.jrebirth.core.link.AbstractWaveReady;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveBean;

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

    /**
     * The field that indicate how this command must be launched.
     */
    private final RunIntoType runIntoThread;

    /**
     * The parent command, useful when chained or multi commands are used.
     */
    private Command parentCommand;

    /**
     * The list of command Listener to warn taht an event occurred.
     */
    private final List<CommandListener> commandListeners = new ArrayList<>();

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
     */
    protected abstract void execute(final Wave wave);

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
        final AbstractJrbRunnable runnable = new AbstractJrbRunnable(this.getClass().getSimpleName()) {

            @Override
            protected void runInto() throws JRebirthThreadException {
                execute(wave);
                fireAchieve(wave);
            }
        };

        // Add the runnable to the runner queue run it as soon as possible
        JRebirth.run(getRunInto(), runnable);
    }

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
     * Fire an achieve event.
     * 
     * @param wave forward the wave that has been performed
     */
    protected void fireAchieve(final Wave wave) {
        for (final CommandListener commandListener : this.commandListeners) {
            commandListener.commandAchieved(wave);
        }
        wave.setStatus(Wave.Status.consumed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCommandListener(final CommandListener commandListener) {
        this.commandListeners.add(commandListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeCommandListener(final CommandListener commandListener) {
        this.commandListeners.remove(commandListener);
    }

}
