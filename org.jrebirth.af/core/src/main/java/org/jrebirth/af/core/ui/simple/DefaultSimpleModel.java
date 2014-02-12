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
package org.jrebirth.af.core.ui.simple;

import javafx.scene.Node;

import org.jrebirth.af.core.exception.CoreRuntimeException;
import org.jrebirth.af.core.wave.Wave;

/**
 * 
 * The interface <strong>DefaultSimpleModel</strong>.
 * 
 * Default implementation for Simple model (models that don't have neither View nor Controller).
 * 
 * @author Sébastien Bordes
 * 
 * @param <N> the root node type
 */
public class DefaultSimpleModel<N extends Node> extends AbstractSimpleModel<N> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected N prepareNode() {
        throw new CoreRuntimeException("You must return a JavaFX node for Simple Model");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initInnerModels() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processWave(final Wave wave) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void showView() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void hideView() {
        // Nothing to do yet
    }

    @Override
    protected void bindInternal() {
        // Nothing to do yet

    }

    @Override
    protected void bind() {
        // Nothing to do yet

    }

}
