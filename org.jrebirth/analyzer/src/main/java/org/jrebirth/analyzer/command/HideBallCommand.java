package org.jrebirth.analyzer.command;

import org.jrebirth.analyzer.ui.editor.EditorWaveItem;
import org.jrebirth.analyzer.ui.editor.ball.BallModel;
import org.jrebirth.core.command.impl.CommandImpl;
import org.jrebirth.core.event.Event;
import org.jrebirth.core.link.Wave;

/**
 * The class <strong>HideBallCommand</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public final class HideBallCommand extends CommandImpl {

    /**
     * {@inheritDoc}
     */
    @Override
    public void run(final Wave wave) {
        super.run(wave);

        final Event event = (Event) wave.get(EditorWaveItem.EVENT).getValue();
        final BallModel targetBallModel = getModel(BallModel.class, event);
        targetBallModel.hide();
    }

}
