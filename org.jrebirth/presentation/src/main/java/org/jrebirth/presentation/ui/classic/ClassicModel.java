package org.jrebirth.presentation.ui.classic;

import org.jrebirth.presentation.ui.base.SlideStep;
import org.jrebirth.presentation.ui.template.AbstractTemplateModel;

/**
 * The class <strong>ClassicModel</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public final class ClassicModel extends AbstractTemplateModel<ClassicModel, ClassicView, SlideStep> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected SlideStep[] initializeSlideStep() {
        return new SlideStep[0];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showSlideStep(final SlideStep slideStep) {
        // Nothing to do yet
    }

}
