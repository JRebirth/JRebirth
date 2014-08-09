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
import javafx.scene.layout.Pane;

import org.jrebirth.af.core.exception.CoreException;

/**
 * The interface <strong>View</strong>.
 *
 * The contract for the view layer.
 *
 * @author Sébastien Bordes
 *
 * @param <M> A class that implement the Model interface, this is the model of the view
 * @param <N> A class that extend the JavaFX node, this is the base component of the view
 * @param <C> The class type which will control this view, it must implements the controller interface
 */
public interface View<M extends Model, N extends Node, C extends Controller<?, ?>> {

    /**
     * Return the view root node.
     *
     * @return the view base javafx node
     */
    N getRootNode();

    /**
     * Return the error node.
     *
     * @return the error node
     */
    Pane getErrorNode();

    /**
     * Return the view model.
     *
     * @return the view model
     */
    M getModel();

    /**
     * Return the view controller.
     *
     * @return the view controller
     */
    C getController();

    /**
     * Prepare the view by creating all visual nodes.
     *
     * This method is called after Model initialization
     *
     * @throws CoreException if the preparation fails
     */
    void prepare() throws CoreException;

    /**
     * Handle custom tasks to do the first time after creation of the view.
     *
     * For example : you could start the show animation of the view.
     */
    void start();

    /**
     * Handle custom tasks to do at each rendering of the view (re-display).
     *
     * For example : play from start the start animation.
     */
    void reload();

    /**
     * Handle custom tasks to do when the view is closed or hidden.
     *
     * For example : you could start the hide animation of the view.
     */
    void hide();

}
