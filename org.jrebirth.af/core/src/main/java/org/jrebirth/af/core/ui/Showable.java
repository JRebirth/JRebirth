package org.jrebirth.af.core.ui;

import static org.jrebirth.af.core.wave.WBuilder.waveTypeDo;

import org.jrebirth.af.api.ui.View;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.WaveConstants;
import org.jrebirth.af.api.wave.contract.WaveType;

/**
 * The interface <strong>Showable</strong> defines the contract for model that can be shown and hidden.
 *
 * @author Sébastien Bordes
 */
public interface Showable extends WaveConstants {

    /** The waveType used to show a view (start or reload). */
    WaveType SHOW_VIEW_WT = waveTypeDo(SHOW_VIEW);

    /** The waveType used to hide a view. */
    WaveType HIDE_VIEW_WT = waveTypeDo(HIDE_VIEW);

    /**
     * Called when a {@link View} is shown.
     *
     * @param wave the wave carrying the wave type
     */
    void doShowView(Wave wave);

    /**
     * Called when a {@link View} is hidden.
     *
     * @param wave the wave carrying the wave type
     */
    void doHideView(Wave wave);

}
