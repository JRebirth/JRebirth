package org.jrebirth.analyzer.ui.editor.ball;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import org.jrebirth.analyzer.ui.editor.EditorWave;
import org.jrebirth.analyzer.ui.properties.PropertiesWaveItem;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.link.impl.WaveData;
import org.jrebirth.core.ui.adapter.MouseAdapter;
import org.jrebirth.core.ui.impl.DefaultController;

/**
 * The class <strong>BallController</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 72 $ $Author: sbordes $
 * @since $Date: 2011-10-17 22:26:35 +0200 (Mon, 17 Oct 2011) $
 */
public final class BallController extends DefaultController<BallModel, BallView> implements MouseAdapter {

    /**
     * Default Constructor.
     * 
     * @param view the view to control
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public BallController(final BallView view) throws CoreException {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeEventHandlers() throws CoreException {

        // Register mouse clicked handler
        getView().getRootNode().setOnMouseClicked(getMouseHandler());

        // getView().getShowTransition().setOnFinished(getActionHandler());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouse(final MouseEvent mouseEvent) {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseDragDetected(final MouseEvent mouseEvent) {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {

        if (mouseEvent.getSource() instanceof Node) {
            // Send Event Selected Wave
            getModel().send(EditorWave.EVENT_SELECTED, new WaveData(PropertiesWaveItem.EVENT_OBJECT, getModel().getEventModel()));
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseDragged(final MouseEvent mouseEvent) {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseEnteredTarget(final MouseEvent mouseEvent) {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseExitedTarget(final MouseEvent mouseEvent) {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseMoved(final MouseEvent mouseEvent) {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
        // Nothing to do yet

    }

}
