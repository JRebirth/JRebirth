package org.jrebirth.presentation.ui.splash;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.presentation.ui.base.AbstractSlideController;

/**
 * The class <strong>SplashController</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public final class SplashController extends AbstractSlideController<SplashModel, SplashView> {

    /**
     * Default Constructor.
     * 
     * @param view the view to control
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public SplashController(final SplashView view) throws CoreException {
        super(view);
    }

}
