/**
 * Copyright JRebirth.org © 2011-2012 
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

/**
 * 
 * The class <strong>DefaultView</strong>.
 * 
 * Default implementation of the view.
 * 
 * @author Sébastien Bordes
 * 
 * 
 * @param <M> The class type of the model of the view
 * @param <N> Any object that is a JavaFx2 Node
 * @param <C> The class type of the controller of the view
 */
public class DefaultView<M extends Model, N extends Node, C extends Controller<?, ?>> extends AbstractView<M, N, C> {

    /**
     * Default Constructor.
     * 
     * @param model the model of the view
     * 
     * @throws CoreException if an error occurred while initializing
     */
    public DefaultView(final M model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doStart() {
        // Nothing to do generic
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doReload() {
        // Nothing to do generic
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doHide() {
        // Nothing to do generic
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeComponents() {
        // Nothing to do generic
    }

}
