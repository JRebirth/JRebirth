package org.jrebirth.presentation.ui.image;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.presentation.ui.base.AbstractSlideController;

/**
 * The class <strong>ImageSlide</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public final class ImageSlideController extends AbstractSlideController<ImageSlideModel, ImageSlideView> {

    /**
     * Default Constructor.
     * 
     * @param view the view to control
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public ImageSlideController(final ImageSlideView view) throws CoreException {
        super(view);
    }

}
