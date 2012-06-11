package org.jrebirth.presentation.ui.base;

import javafx.scene.Parent;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.AbstractView;

/**
 * 
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
    @Override
    public void doHide() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeComponents() {
        // Nothing to do yet

    }

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
