package org.jrebirth.presentation.ui.image;

import org.jrebirth.presentation.ui.base.AbstractSlideModel;
import org.jrebirth.presentation.ui.base.SlideStep;

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
        return getSlide().getTitle() == null ? "" : getSlide().getTitle().replaceAll("\\\\n", "\n");
    }

    /**
     * Return the style class.
     * 
     * @return the style class
     */
    public String getStyleClass() {
        return getSlide().getStyle();
    }

    public String getImage() {
        return getSlide().getContent().get(0).getItem().get(0).getImage();
    }

}
