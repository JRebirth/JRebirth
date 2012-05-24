package ui;

import javafx.scene.layout.StackPane;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.AbstractView;

/**
 * 
 * The class <strong>TransitionView</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class TransitionView extends AbstractView<TransitionModel, StackPane, TransitionController> {

    /**
     * Default Constructor.
     * 
     * @param model the view model
     * 
     * @throws CoreException if build fails
     */
    public TransitionView(final TransitionModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeComponents() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {

        getRootNode().setPrefWidth(getModel().getService().getImage().getWidth());
        getRootNode().setPrefHeight(getModel().getService().getImage().getHeight());

        getRootNode().getChildren().addAll(getModel().getService().getSlices());

        // getModel().getService2().getTimeline().play();

        getModel().getService2().getFullTransition().play();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {
        // Nothing to do yet

    }
}
