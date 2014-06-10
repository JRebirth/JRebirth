package org.jrebirth.af.core.command.basic;

import javafx.scene.Cursor;
import javafx.scene.Node;

import org.jrebirth.af.core.command.DefaultUICommand;
import org.jrebirth.af.core.exception.CoreRuntimeException;
import org.jrebirth.af.core.wave.Wave;

public class UpdateCursorCommand extends DefaultUICommand {

    /**
     * {@inheritDoc}
     */
    @Override
    public void perform(final Wave wave) {

        Cursor cursor = getKeyPart(Cursor.class);

        if (cursor == null) {
            throw new CoreRuntimeException("You must use a Cursor instance as a key part.");
        }

        Node node = getKeyPart(Node.class);

        //
        if (node == null) {
            getLocalFacade().getGlobalFacade().getApplication().getRootNode().setCursor(cursor);
        } else {
            node.setCursor(cursor);
        }

    }
}
