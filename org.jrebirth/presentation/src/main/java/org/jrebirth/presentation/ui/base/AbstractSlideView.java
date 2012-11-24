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

import javafx.scene.Parent;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.AbstractView;

/**
 * The class <strong>AbstractSlideView</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @param <M> the slide model class
 * @param <N> the layout node
 * @param <C> the slide controller class
 */
public abstract class AbstractSlideView<M extends AbstractSlideModel<?, ?, ?>, N extends Parent, C extends AbstractSlideController<?, ?>> extends
        AbstractView<M, N, C> {

    /** Sub slide animation flag. */
    private boolean slideLocked;

    /**
     * Default Constructor.
     * 
     * @param model the controls view model
     * 
     * @throws CoreException if build fails
     */
    public AbstractSlideView(final M model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    // @Override
    // public void doHide() {
    // // Nothing to do yet
    // }
    /**
     * {@inheritDoc}
     */
    // @Override
    // protected void customInitializeComponents() {
    // // Nothing to do yet
    //
    // }

    /**
     * @return Returns the slideLocked.
     */
    protected boolean isSlideLocked() {
        return this.slideLocked;
    }

    /**
     * @param slideLocked The slideLocked to set.
     */
    protected void setSlideLocked(final boolean slideLocked) {
        this.slideLocked = slideLocked;
    }

}
