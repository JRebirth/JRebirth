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
package org.jrebirth.presentation.ui.base;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.DefaultController;

/**
 * The class <strong>AbstractSlideController</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @param <M> The SlideModel class
 * @param <V> The SlideView class
 */
public abstract class AbstractSlideController<M extends AbstractSlideModel<M, V, ?>, V extends AbstractSlideView<M, ?, ? extends AbstractSlideController<M, V>>>
        extends DefaultController<M, V> {

    /**
     * Default Constructor.
     * 
     * @param view the view to control
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public AbstractSlideController(final V view) throws CoreException {
        super(view);
    }

}
