package org.jrebirth.core.ui.adapter;

import javafx.event.ActionEvent;

import org.jrebirth.core.ui.AbstractController;

/**
 * The class <strong>DefaultActionAdapter</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 * 
 * @param <C> The controller class which manage this event adapter
 */
public class DefaultActionAdapter<C extends AbstractController<?, ?>> extends AbstractDefaultAdapter<C> implements ActionAdapter {

    /**
     * {@inheritDoc}
     */
    @Override
    public void action(final ActionEvent actionEvent) {
        // Nothing to do yet
    }

}
