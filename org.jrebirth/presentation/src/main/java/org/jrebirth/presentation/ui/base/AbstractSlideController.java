package org.jrebirth.presentation.ui.base;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.AbstractController;
import org.jrebirth.core.ui.adapter.DefaultKeyAdapter;
import org.jrebirth.core.ui.adapter.DefaultMouseAdapter;
import org.jrebirth.presentation.command.ShowNextSlideCommand;
import org.jrebirth.presentation.command.ShowPreviousSlideCommand;
import org.jrebirth.presentation.command.ShowSlideMenuCommand;

/**
 * The class <strong>AbstractSlideController</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @param <M> The SlideModel class
 * @param <V> The SlideView class
 */
public abstract class AbstractSlideController<M extends AbstractSlideModel<M, V, ?>, V extends AbstractSlideView<M, ?, ? extends AbstractSlideController<M, V>>>
        extends AbstractController<M, V> {

    /**
     * Default Constructor.
     * 
     * @param view the view to control
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public AbstractSlideController(final V view) throws CoreException {
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
        getRootNode().addEventFilter(KeyEvent.KEY_RELEASED, getKeyHandler());

        // Listen mouse event on the root node
        getRootNode().setOnMouseClicked(getMouseHandler());

        getView().getRootNode().requestFocus();
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
    private class SlideKeyAdapter extends DefaultKeyAdapter<AbstractSlideController<M, V>> {

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
    private class SlideMouseAdapter extends DefaultMouseAdapter<AbstractSlideController<M, V>> {

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

}
