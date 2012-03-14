package org.jrebirth.core.ui.adapter;

import javafx.scene.input.KeyEvent;

import org.jrebirth.core.ui.AbstractController;

/**
 * The class <strong>KeyAdapter</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 * 
 * @param <C> the controller class
 */
public interface KeyAdapter<C extends AbstractController<?, ?>> extends EventAdapter<C> {

    /**
     * Manage key ANY events.
     * 
     * Common supertype for all key event types.
     * 
     * @param keyEvent the event to manage
     */
    void key(KeyEvent keyEvent);

    /**
     * Manage key pressed events.
     * 
     * This event occurs when a key has been pressed.
     * 
     * @param keyEvent the event to manage
     */
    void keyPressed(KeyEvent keyEvent);

    /**
     * Manage key released events.
     * 
     * This event occurs when a key has been released.
     * 
     * @param keyEvent the event to manage
     */
    void keyReleased(KeyEvent keyEvent);

    /**
     * Manage key typed events.
     * 
     * This event occurs when a key has been typed (pressed and released).
     * 
     * @param keyEvent the event to manage
     */
    void keyTyped(KeyEvent keyEvent);

}
