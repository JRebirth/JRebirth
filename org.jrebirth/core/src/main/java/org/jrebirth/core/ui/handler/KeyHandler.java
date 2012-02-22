package org.jrebirth.core.ui.handler;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.KeyEvent;

import org.jrebirth.core.ui.adapter.KeyAdapter;

/**
 * The interface <strong>KeyHandler</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public final class KeyHandler implements EventHandler<KeyEvent> {

    /** The Key adapter. */
    private final KeyAdapter keyAdapter;

    /**
     * Default Constructor.
     * 
     * @param keyAdapter the adapter to use
     */
    public KeyHandler(final KeyAdapter keyAdapter) {
        this.keyAdapter = keyAdapter;
    }

    /**
     * Return the implementation of the key adapter interface.
     * 
     * @return Returns the keyAdapter.
     */
    public KeyAdapter getKeyAdapter() {
        return this.keyAdapter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final KeyEvent keyEvent) {

        final EventType<?> type = keyEvent.getEventType();

        if (KeyEvent.KEY_PRESSED == type) {
            getKeyAdapter().keyPressed(keyEvent);
        } else if (KeyEvent.KEY_RELEASED == type) {
            getKeyAdapter().keyReleased(keyEvent);
        } else if (KeyEvent.KEY_TYPED == type) {
            getKeyAdapter().keyTyped(keyEvent);
        } else {
            getKeyAdapter().key(keyEvent);
        }

    }
}
