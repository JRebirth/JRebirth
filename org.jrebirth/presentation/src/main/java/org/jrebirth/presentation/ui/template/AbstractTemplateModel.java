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
package org.jrebirth.presentation.ui.template;

import org.jrebirth.presentation.ui.base.AbstractSlideModel;
import org.jrebirth.presentation.ui.base.SlideStep;

/**
 * The class <strong>AbstractTemplateModel</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @param <M> the TemplateModel class
 * @param <V> the TemplateView class
 */
public abstract class AbstractTemplateModel<M extends AbstractTemplateModel<M, V, S>, V extends AbstractTemplateView<?, ?, ?>, S extends SlideStep> extends AbstractSlideModel<M, V, S> {

    /**
     * Return the title string from the slide object.
     * 
     * @return the slide title
     */
    protected String getTitle() {
        return getSlide().getTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected S[] initializeSlideStep() {
        // Nothing to do yet
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showSlideStep(final S slideStep) {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getSlideNumber() + "/" + getStepPosition() + " " + getTitle() + this.getClass().getSimpleName();
    }

}
