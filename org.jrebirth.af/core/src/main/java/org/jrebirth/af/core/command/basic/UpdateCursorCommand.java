package org.jrebirth.af.core.command.basic;

import javafx.scene.Cursor;
import javafx.scene.Node;

import org.jrebirth.af.api.exception.CoreRuntimeException;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.command.single.ui.DefaultUICommand;

public class UpdateCursorCommand extends DefaultUICommand {

    /**
     * {@inheritDoc}
     */
    @Override
    public void perform(final Wave wave) {

        final Cursor cursor = getKeyPart(Cursor.class);

        if (cursor == null) {
            throw new CoreRuntimeException("You must use a Cursor instance as a key part.");
        }

        final Node node = getKeyPart(Node.class);

        //
        if (node == null) {
            getLocalFacade().getGlobalFacade().getApplication().getRootNode().setCursor(cursor);
        } else {
            node.setCursor(cursor);
        }

    }
}
