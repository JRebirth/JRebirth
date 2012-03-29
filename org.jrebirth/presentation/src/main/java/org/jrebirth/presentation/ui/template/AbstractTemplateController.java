package org.jrebirth.presentation.ui.template;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.presentation.ui.base.AbstractSlideController;

/**
 * The class <strong>AbstractTemplateController</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @param <M> The TemplateModel class
 * @param <V> The TemplateView class
 */
public abstract class AbstractTemplateController<M extends AbstractTemplateModel<M, V, ?>, V extends AbstractTemplateView<M, ?, ? extends AbstractTemplateController<M, V>>>
        extends AbstractSlideController<M, V> {

    /**
     * Default Constructor.
     * 
     * @param view the view to control
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public AbstractTemplateController(final V view) throws CoreException {
        super(view);
    }

}
