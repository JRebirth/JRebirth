package org.jrebirth.core.ui.adapter;

import javafx.event.ActionEvent;

/**
 * The class <strong>ActionAdapter</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public interface ActionAdapter {

    /**
     * Manage action events.
     * 
     * @param actionEvent the event to manage
     */
    void action(ActionEvent actionEvent);

}
