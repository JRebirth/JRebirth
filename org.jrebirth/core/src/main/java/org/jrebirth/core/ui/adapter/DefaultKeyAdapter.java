package org.jrebirth.core.ui.adapter;

import javafx.scene.input.KeyEvent;

import org.jrebirth.core.ui.AbstractController;

/**
 * The class <strong>DefaultKeyAdapter</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 * 
 * @param <C> The controller class which manage this event adapter
 */
public class DefaultKeyAdapter<C extends AbstractController<?, ?>> extends AbstractDefaultAdapter<C> implements KeyAdapter<C> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void key(final KeyEvent keyEvent) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyPressed(final KeyEvent keyEvent) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyReleased(final KeyEvent keyEvent) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyTyped(final KeyEvent keyEvent) {
        // Nothing to do yet
    }

}
