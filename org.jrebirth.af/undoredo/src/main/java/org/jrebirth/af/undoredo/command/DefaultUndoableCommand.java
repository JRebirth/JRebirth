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
package org.jrebirth.af.undoredo.command;

import org.jrebirth.af.core.wave.Wave;
import org.jrebirth.af.core.wave.WaveBean;

/**
 * The Class DefaultUndoableCommand is the default class to build an undoable command.
 * 
 * @param <WB> The WaveBean type used for this command (by default you can use the WaveBean interface)
 * 
 * @author Sébastien Bordes
 */
public class DefaultUndoableCommand<WB extends WaveBean> extends AbstractUndoableCommand<WB> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void init(final Wave wave) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void undo() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void redo() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initCommand() {
        // Nothing to do yet
    }

    protected void initInnerComponents() {
    }

}
