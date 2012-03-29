package org.jrebirth.presentation.ui.base;

import org.jrebirth.core.ui.Model;

/**
 * The class <strong>SlideModel</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @param <S> The SlideStep to use
 */
public interface SlideModel<S extends SlideStep> extends Model {

    /**
     * @param slideNumber The slideNumber to set.
     */
    void setSlideNumber(int slideNumber);

    /**
     * Show the step.
     * 
     * @param slideStep the step to show
     */
    void showSlideStep(final S slideStep);

    /**
     * Check if the next step is available.
     * 
     * @return true if no more steps are available
     */
    boolean nextStep();

    /**
     * Check if the previous step is available.
     * 
     * @return true if no more steps are available
     */
    boolean previousStep();
}
