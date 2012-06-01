package org.jrebirth.presentation.ui.stack;

import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.AbstractController;
import org.jrebirth.core.ui.adapter.ActionAdapter;
import org.jrebirth.core.ui.adapter.DefaultKeyAdapter;
import org.jrebirth.core.ui.adapter.DefaultMouseAdapter;
import org.jrebirth.presentation.command.ShowNextSlideCommand;
import org.jrebirth.presentation.command.ShowPreviousSlideCommand;
import org.jrebirth.presentation.command.ShowSlideMenuCommand;

/**
 * The class <strong>StackController</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public final class StackController extends AbstractController<StackModel, StackView> implements ActionAdapter {

    /**
     * Default Constructor.
     * 
     * @param view the view to control
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public StackController(final StackView view) throws CoreException {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeEventAdapters() throws CoreException {

        // Use the inner class
        buildKeyHandler(new SlideKeyAdapter());
        // Use the inner class
        buildMouseHandler(new SlideMouseAdapter());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeEventHandlers() throws CoreException {

        // Listen keys event on the root node
        // getRootNode().addEventFilter(KeyEvent.KEY_PRESSED, getKeyHandler());
        getRootNode().setOnKeyPressed(getKeyHandler());

        // Listen mouse event on the root node
        getRootNode().setOnMouseClicked(getMouseHandler());

    }

    /**
     * 
     * The class <strong>SlideKeyAdapter</strong>.
     * 
     * @author Sébastien Bordes
     * 
     * @version $Revision$ $Author$
     * @since $Date$
     */
    private class SlideKeyAdapter extends DefaultKeyAdapter<StackController> {

        /**
         * {@inheritDoc}
         */
        @Override
        public void keyPressed(final KeyEvent keyEvent) {

            if (keyEvent.getCode() == KeyCode.PAGE_DOWN) {
                /* getController(). */getModel().callCommand(ShowNextSlideCommand.class);
            } else if (keyEvent.getCode() == KeyCode.PAGE_UP) {
                /* getController(). */getModel().callCommand(ShowPreviousSlideCommand.class);
            }
        }

    }

    /**
     * 
     * The class <strong>SlideMouseAdapter</strong>.
     * 
     * @author Sébastien Bordes
     * 
     * @version $Revision$ $Author$
     * @since $Date$
     */
    private class SlideMouseAdapter extends DefaultMouseAdapter<StackController> {

        /**
         * {@inheritDoc}
         */
        @Override
        public void mouseClicked(final MouseEvent mouseEvent) {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                /* getController(). */getModel().callCommand(ShowNextSlideCommand.class);
            } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                /* getController(). */getModel().callCommand(ShowPreviousSlideCommand.class);
            } else if (mouseEvent.getButton() == MouseButton.MIDDLE) {
                /* getController(). */getModel().callCommand(ShowSlideMenuCommand.class);
            }

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void action(final ActionEvent actionEvent) {
        // Nothing to do yet

    }

}
