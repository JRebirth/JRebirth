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

import org.jrebirth.core.exception.CoreException;

/**
 * The class <strong>DefaultController</strong>.
 * 
 * Default implementation with empty methods (to override)
 * 
 * @author Sébastien Bordes
 * 
 * @param <M> the class type of the model of the view controlled
 * @param <V> the class type of the view controlled
 */
public class DefaultController<M extends Model, V extends View<M, ?, ?>> extends AbstractController<M, V> {

    /**
     * Default Constructor.
     * 
     * @param view the view controlled
     * @throws CoreException if an error occurred while initialisation
     */
    public DefaultController(final V view) throws CoreException {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeEventAdapters() throws CoreException {
        // Nothing to do generic
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeEventHandlers() throws CoreException {
        // Nothing to do generic
    }

}
