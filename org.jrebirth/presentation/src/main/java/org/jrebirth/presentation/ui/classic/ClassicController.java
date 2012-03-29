package org.jrebirth.presentation.ui.classic;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.presentation.ui.template.AbstractTemplateController;

/**
 * The class <strong>ClassicController</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public final class ClassicController extends AbstractTemplateController<ClassicModel, ClassicView> {

    /**
     * Default Constructor.
     * 
     * @param view the view to control
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public ClassicController(final ClassicView view) throws CoreException {
        super(view);
    }

}
