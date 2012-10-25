package org.jrebirth.presentation.command;

import org.jrebirth.core.command.DefaultUICommand;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.presentation.ui.base.ShowSlideWaves;
import org.jrebirth.presentation.ui.base.SlideModel;
import org.jrebirth.presentation.ui.base.SlideStep;

/**
 * The class <strong>ShowSlideStepCommand</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public final class ShowSlideStepCommand extends DefaultUICommand {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void execute(final Wave wave) {
        // Retrieve the slide model
        final SlideModel<SlideStep> model = getModel(wave.get(ShowSlideWaves.SLIDE_MODEL_CLASS));

        // Show the the next slide step
        model.showSlideStep(wave.get(ShowSlideWaves.SLIDE_STEP_TYPE));
    }

}
