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
package org.jrebirth.af.presentation.ui.image;

import org.jrebirth.af.presentation.ui.base.AbstractSlideModel;
import org.jrebirth.af.presentation.ui.base.SlideStep;

/**
 * The class <strong>ImageSlideModel</strong>.
 *
 * @author Sébastien Bordes
 *
 */
public final class ImageSlideModel extends AbstractSlideModel<ImageSlideModel, ImageSlideView, SlideStep> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected SlideStep[] initializeSlideStep() {
        return new SlideStep[0];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showSlideStep(final SlideStep slideStep) {
        // Nothing to do yet
    }

    /**
     * Return the splash title.
     *
     * @return the title
     */
    public String getTitle() {
        return getSlide().getContent() == null || getSlide().getContent().isEmpty() || getSlide().getContent().get(0).getTitle() == null ? null
                : getSlide().getContent().get(0).getTitle().replaceAll("\\\\n", "\n");
    }

    /**
     * Return the style class.
     *
     * @return the style class
     */
    public String getStyleClass() {
        return getSlide().getStyle();
    }

    /**
     * Return the image.
     *
     * @return the image path name
     */
    public String getImage() {
        return getSlide().getContent().get(0).getItem().get(0).getImage();
    }

}
