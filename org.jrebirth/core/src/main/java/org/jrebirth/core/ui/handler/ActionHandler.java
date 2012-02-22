package org.jrebirth.core.ui.handler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import org.jrebirth.core.ui.adapter.ActionAdapter;

/**
 * The class <strong>ActionHandler</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public final class ActionHandler implements EventHandler<ActionEvent> {

    /** The Action adapter. */
    private final ActionAdapter actionAdapter;

    /**
     * Default Constructor.
     * 
     * @param actionAdapter the adapter to use
     */
    public ActionHandler(final ActionAdapter actionAdapter) {
        this.actionAdapter = actionAdapter;
    }

    /**
     * Return the implementation of the action adapter interface.
     * 
     * @return Returns the actionAdapter.
     */
    public ActionAdapter getActionAdapter() {
        return this.actionAdapter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final ActionEvent actionEvent) {

        getActionAdapter().action(actionEvent);

    }
}
