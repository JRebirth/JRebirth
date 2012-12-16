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

import org.jrebirth.core.concurrent.RunIntoType;
import org.jrebirth.core.wave.Wave;

/**
 * The class <strong>DefaultMultiCommand</strong>.
 * 
 * @author Sébastien Bordes
 */
public class DefaultMultiCommand extends AbstractBaseMultiCommand {

    /**
     * Default Constructor.
     * 
     * @param runInto The run into thread type
     */
    public DefaultMultiCommand(final RunIntoType runInto) {
        super(runInto, true);
    }

    /**
     * Default Constructor.
     * 
     * @param runInto The run into thread type
     * @param sequential indicate if commands must be run sequentially(true) or in parallel(false)
     */
    public DefaultMultiCommand(final RunIntoType runInto, final boolean sequential) {
        super(runInto, sequential);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waveCancelled(final Wave wave) {
        // Nothing to do yet
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
    protected void addSubCommand() {
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
    protected void processAction(final Wave wave) {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void preExecute(final Wave wave) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void postExecute(final Wave wave) {
        // Nothing to do yet
    }

}
