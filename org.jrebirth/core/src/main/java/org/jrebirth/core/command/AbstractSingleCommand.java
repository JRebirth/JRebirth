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

import org.jrebirth.core.wave.Wave;

/**
 * The class <strong>AbstractSingleCommand</strong>.
 * 
 * @author Sébastien Bordes
 */
public abstract class AbstractSingleCommand extends AbstractBaseCommand {

    /** The command is running. */
    private boolean running;

    // /**
    // * Default constructor.
    // *
    // * @param runIntoThread the way to launch this command
    // */
    // public AbstractSingleCommand(final RunIntoType runIntoThread) {
    // super(runIntoThread);
    // }

    /**
     * {@inheritDoc}
     */
    @Override
    public void preExecute(final Wave wave) {
        synchronized (this) {
            this.running = true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void postExecute(final Wave wave) {
        synchronized (this) {
            this.running = false;
        }
        fireConsumed(wave);
    }

    /**
     * @return Returns the running.
     */
    public boolean isRunning() {
        synchronized (this) {
            return this.running;
        }
    }
}
