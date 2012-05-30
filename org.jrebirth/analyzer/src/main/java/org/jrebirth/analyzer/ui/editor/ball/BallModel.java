package org.jrebirth.analyzer.ui.editor.ball;

import org.jrebirth.analyzer.ui.editor.EditorWave;
import org.jrebirth.core.event.Event;
import org.jrebirth.core.link.Wave;
import org.jrebirth.core.ui.DefaultModel;

/**
 * The class <strong>BallModel</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision: 55 $ $Author: sbordes $
 * @since $Date: 2011-10-14 19:23:59 +0200 (Fri, 14 Oct 2011) $
 */
public final class BallModel extends DefaultModel<BallModel, BallView> {

    /** The ballModel reference. */
    private BallModel referenceBallModel;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitialize() {
        listen(EditorWave.EVENT_SELECTED);
    }

    /**
     * Call when event previous button is pressed.
     * 
     * @param eventSelected the selected event
     * @param wave the wave received
     */
    public void eventSelected(final Event eventSelected, final Wave wave) {
        // Same object (reference)
        if (getEventModel() == eventSelected) {
            getView().getScaleTransition().play();
        } else {
            getView().getScaleTransition().stop();
            getView().resetScale();
        }
    }

    /**
     * @return Returns the referenceBallModel.
     */
    BallModel getReferenceBallModel() {
        return this.referenceBallModel;
    }

    /**
     * @return Returns the eventModel.
     */
    public Event getEventModel() {
        return (Event) getModelObject();
    }

    /**
     * @param eventModel The eventModel to set.
     */
    public void setEventModel(final Event eventModel) {
        setModelObject(eventModel);
    }

    /**
     * Show the ball node.
     */
    public void show() {
        getView().setStyle(getEventModel().getEventType()); // TODO remove
        getView().doStart();
    }

    /**
     * TODO To complete.
     * 
     * @param i
     */
    public void hide() {
        getView().doHide();
    }

    /**
     * TODO To complete.
     */
    public void destroy() {
        getView().doHide();
    }
}
