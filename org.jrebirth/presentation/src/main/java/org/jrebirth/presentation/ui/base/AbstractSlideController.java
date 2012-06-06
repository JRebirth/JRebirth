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
