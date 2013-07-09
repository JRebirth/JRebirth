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
package org.jrebirth.core.ui;

import javafx.scene.Node;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.facade.JRebirthEventType;
import org.jrebirth.core.link.AbstractWaveReady;
import org.jrebirth.core.wave.Wave;

/**
 * 
 * The interface <strong>AbstractSimpleModel</strong>.
 * 
 * Base implementation for simple model without View-Controller part.
 * 
 * @author Sébastien Bordes
 * 
 * @param <N> the root node type
 */
public abstract class AbstractSimpleModel<N extends Node> extends AbstractWaveReady<Model> implements Model {

    /** The root model not null for inner model. */
    private Model rootModel;

    /** The root node. */
    private N rootNode;

    /**
     * {@inheritDoc}
     */
    @Override
    public final void ready() throws CoreException {

        // Initialize the current model
        initialize();

        // Prepare the root node
        this.rootNode = prepareNode();
    }

    /**
     * Prepare the root node.
     * 
     * With simple model no view neither controller are created.<br />
     * You must manage them yourself.
     */
    protected abstract N prepareNode();

    /**
     * Initialize the model.
     * 
     * @throws CoreException if the creation of the view fails
     */
    protected void initialize() throws CoreException {
        customInitialize();
    }

    /**
     * Initialize method to implement for adding custom processes.
     */
    protected abstract void customInitialize();

    /**
     * {@inheritDoc}
     */
    @Override
    public N getRootNode() {
        return this.rootNode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Model getRootModel() {
        return this.rootModel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRootModel(final Model rootModel) {
        this.rootModel = rootModel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected abstract void processAction(final Wave wave);

    /**
     * {@inheritDoc}
     */
    @Override
    protected void finalize() throws Throwable {
        getLocalFacade().getGlobalFacade().trackEvent(JRebirthEventType.DESTROY_MODEL, null, this.getClass());
        super.finalize();
    }

}
