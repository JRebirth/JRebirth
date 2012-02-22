package org.jrebirth.core.ui.adapter;

import javafx.scene.input.MouseEvent;

/**
 * The class <strong>MouseAdapter</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public interface MouseAdapter {

    /**
     * Manage mouse ANY events.
     * 
     * Common supertype for all mouse event types.
     * 
     * @param mouseEvent the event to manage
     */
    void mouse(MouseEvent mouseEvent);

    /**
     * Manage mouse drag detected events.
     * 
     * This event is delivered to a node that is identified as a potential source of drag and drop gesture.
     * 
     * @param mouseEvent the event to manage
     */
    void mouseDragDetected(MouseEvent mouseEvent);

    /**
     * Manage mouse clicked events.
     * 
     * This event occurs when mouse button has been clicked (pressed and released on the same node).
     * 
     * @param mouseEvent the event to manage
     */
    void mouseClicked(MouseEvent mouseEvent);

    /**
     * Manage mouse dragged events.
     * 
     * This event occurs when mouse moves with a pressed button.
     * 
     * @param mouseEvent the event to manage
     */
    void mouseDragged(MouseEvent mouseEvent);

    /**
     * Manage mouse entered events.
     * 
     * This event occurs when mouse enters a node.
     * 
     * @param mouseEvent the event to manage
     */
    void mouseEntered(MouseEvent mouseEvent);

    /**
     * Manage mouse entered target events.
     * 
     * This event occurs when mouse enters a node.
     * 
     * @param mouseEvent the event to manage
     */
    void mouseEnteredTarget(MouseEvent mouseEvent);

    /**
     * Manage mouse exited events.
     * 
     * This event occurs when mouse exits a node.
     * 
     * @param mouseEvent the event to manage
     */
    void mouseExited(MouseEvent mouseEvent);

    /**
     * Manage mouse exited target events.
     * 
     * This event occurs when mouse exits a node.
     * 
     * @param mouseEvent the event to manage
     */
    void mouseExitedTarget(MouseEvent mouseEvent);

    /**
     * Manage mouse moved events.
     * 
     * This event occurs when mouse moves within a node and no buttons are pressed.
     * 
     * @param mouseEvent the event to manage
     */
    void mouseMoved(MouseEvent mouseEvent);

    /**
     * Manage mouse pressed events.
     * 
     * This event occurs when mouse button is pressed.
     * 
     * @param mouseEvent the event to manage
     */
    void mousePressed(MouseEvent mouseEvent);

    /**
     * Manage mouse released events.
     * 
     * This event occurs when mouse button is released.
     * 
     * @param mouseEvent the event to manage
     */
    void mouseReleased(MouseEvent mouseEvent);

}
