package org.jrebirth.presentation.ui.stack;

import javafx.event.ActionEvent;
import javafx.scene.control.Control;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;

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
        getRootNode().setOnKeyPressed(getKeyHandler());

        // Listen mouse event on the root node
        getRootNode().setOnMousePressed(getMouseHandler());
    }

    /**
     * 
     * The class <strong>SlideKeyAdapter</strong>.
     * 
     * @author Sébastien Bordes
     */
    private class SlideKeyAdapter extends DefaultKeyAdapter<StackController> {

        /**
         * {@inheritDoc}
         */
        @Override
        public void keyPressed(final KeyEvent keyEvent) {

            if (keyEvent.getCode() == KeyCode.PAGE_DOWN) {
                getModel().callCommand(ShowNextSlideCommand.class);
                keyEvent.consume();
            } else if (keyEvent.getCode() == KeyCode.PAGE_UP) {
                getModel().callCommand(ShowPreviousSlideCommand.class);
                keyEvent.consume();
            }
        }

    }

    /**
     * 
     * The class <strong>SlideMouseAdapter</strong>.
     * 
     * @author Sébastien Bordes
     */
    private class SlideMouseAdapter extends DefaultMouseAdapter<StackController> {

        /**
         * {@inheritDoc}
         */
        @Override
        public void mousePressed(final MouseEvent mouseEvent) {
            if (!(mouseEvent.getTarget() instanceof Control) && !(mouseEvent.getTarget() instanceof WebView)) {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    getModel().callCommand(ShowNextSlideCommand.class);
                    mouseEvent.consume();
                } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                    getModel().callCommand(ShowPreviousSlideCommand.class);
                    mouseEvent.consume();
                } else if (mouseEvent.getButton() == MouseButton.MIDDLE) {
                    getModel().callCommand(ShowSlideMenuCommand.class);
                    mouseEvent.consume();
                }
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void mouseClicked(final MouseEvent mouseEvent) {

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
