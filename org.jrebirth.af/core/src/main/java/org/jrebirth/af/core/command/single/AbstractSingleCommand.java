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
package org.jrebirth.af.core.command.single;

import java.util.concurrent.atomic.AtomicBoolean;

import org.jrebirth.af.api.concurrent.RunType;
import org.jrebirth.af.api.concurrent.RunnablePriority;
import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.WaveBean;
import org.jrebirth.af.core.command.AbstractBaseCommand;

/**
 * The class <strong>AbstractSingleCommand</strong>.
 *
 * @author Sébastien Bordes
 *
 * @param <WB> The WaveBean type used for this command (by default you can use the WaveBean interface)
 */
public abstract class AbstractSingleCommand<WB extends WaveBean> extends AbstractBaseCommand<WB> {

    /** The command is running. */
    private final AtomicBoolean running = new AtomicBoolean(false);

    /**
     * Default constructor.
     */
    public AbstractSingleCommand() {
        super();
    }

    /**
     * Default constructor.
     *
     * @param runInto the way to launch this command
     */
    public AbstractSingleCommand(final RunType runInto) {
        super(runInto, null);
    }

    /**
     * Default constructor.
     *
     * @param runInto the way to launch this command
     * @param priority the runnable priority
     */
    public AbstractSingleCommand(final RunType runInto, final RunnablePriority priority) {
        super(runInto, priority);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void ready() throws CoreException {

        // Call the custom method
        initCommand();
    }

    /**
     * Custom method used to initialize the command.
     *
     * Called into JIT by ready method.
     */
    protected abstract void initCommand();

    /**
     * {@inheritDoc}
     */
    @Override
    public void beforePerform(final Wave wave) {
        fireConsumed(wave);
        this.running.set(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void afterPerform(final Wave wave) {
        this.running.set(false);
        fireHandled(wave);
    }

    /**
     * @return Returns the running.
     */
    public boolean isRunning() {
        return this.running.get();
    }

}
