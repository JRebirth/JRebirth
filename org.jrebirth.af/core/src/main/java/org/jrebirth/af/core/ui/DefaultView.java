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
package org.jrebirth.af.core.ui;

import javafx.scene.Node;

import org.jrebirth.af.api.ui.Controller;
import org.jrebirth.af.api.ui.Model;

/**
 * The class <strong>DefaultView</strong>.
 *
 * Default implementation of the view, use to avoid implementation of all abstract method (just override those you need).
 *
 * @author Sébastien Bordes
 *
 * @param <M> The class type of the model of the view
 * @param <N> Any object that is a JavaFX Node
 * @param <C> The class type of the controller of the view
 */
public class DefaultView<M extends Model, N extends Node, C extends Controller<?, ?>> extends AbstractView<M, N, C> {

    /**
     * Default Constructor.
     *
     * @param model the model of the view
     */
    public DefaultView(final M model) {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {
        // Custom code used to update the root node and add some children
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void bootView() {
        // Custom code used to update the root node and add some children
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        // Custom code to process when the view is displayed the first time
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reload() {
        // Custom code to process when the view is displayed the 1+n time
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {
        // Custom code to process when the view is hidden
    }

}
