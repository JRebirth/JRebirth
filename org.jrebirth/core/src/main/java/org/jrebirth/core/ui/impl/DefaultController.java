package org.jrebirth.core.ui.impl;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.Model;
import org.jrebirth.core.ui.View;

/**
 * The class <strong>DefaultController</strong>.
 * 
 * Default implementation with empty methods (to override)
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 * 
 * @param <M> the class type of the model of the view controlled
 * @param <V> the class type of the view controlled
 */
public class DefaultController<M extends Model, V extends View<M, ?, ?>> extends AbstractController<M, V> {

    /**
     * Default Constructor.
     * 
     * @param view the view controlled
     * @throws CoreException if an error occurred while initialisation
     */
    public DefaultController(final V view) throws CoreException {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeEventAdapters() throws CoreException {
        // Nothing to do generic
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeEventHandlers() throws CoreException {
        // Nothing to do generic
    }

}
