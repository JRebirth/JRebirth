package org.jrebirth.presentation.ui.base;

import org.jrebirth.core.wave.WaveItem;

/**
 * The class <strong>ShowSlideWaveItem</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public interface ShowSlideWaves {

    /** The slide model class concerned. */
    WaveItem<Class<SlideModel<SlideStep>>> SLIDE_MODEL_CLASS = new WaveItem<>();

    /** The Slide step type to show. */
    WaveItem<SlideStep> SLIDE_STEP_TYPE = new WaveItem<>();

}
