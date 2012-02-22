package org.jrebirth.core.ui.adapter;

import javafx.stage.WindowEvent;

import org.jrebirth.core.ui.impl.AbstractController;

/**
 * The class <strong>DefaultWindowAdapter</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 * 
 * @param <C> The controller class which manage this event adapter
 */
public class DefaultWindowAdapter<C extends AbstractController<?, ?>> extends AbstractDefaultAdapter<C> implements WindowAdapter {

    /**
     * {@inheritDoc}
     */
    @Override
    public void window(final WindowEvent windowEvent) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void windowCloseRequest(final WindowEvent windowEvent) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void windowHidden(final WindowEvent windowEvent) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void windowHiding(final WindowEvent windowEvent) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void windowShowing(final WindowEvent windowEvent) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void windowShown(final WindowEvent windowEvent) {
        // Nothing to do yet
    }

}
