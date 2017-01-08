package org.jrebirth.af.core.ui;

import static org.jrebirth.af.core.wave.WBuilder.waveTypeDo;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.WaveConstants;
import org.jrebirth.af.api.wave.contract.WaveType;

/**
 * The interface <strong>Showable</strong> defines the contract for model that can be shown and hidden.
 * 
 * @author Sébastien Bordes
 */
public interface Showable {

    /** The waveType used to show a view (start or reload). */
    WaveType SHOW_VIEW_WT = waveTypeDo(WaveConstants.SHOW_VIEW);

    /** The waveType used to hide a view. */
    WaveType HIDE_VIEW_WT = waveTypeDo(WaveConstants.HIDE_VIEW);

    /**
     * 
     * @param wave the wave carrying the wave type
     */
    void doShowView(Wave wave);

    /**
     * 
     * @param wave the wave carrying the wave type
     */
    void doHideView(Wave wave);

}
