package org.jrebirth.af.presentation;

import org.jrebirth.af.api.wave.contract.WaveItem;
import org.jrebirth.af.core.wave.WaveItemBase;
import org.jrebirth.af.presentation.model.Slide;

public interface PrezWaves {

    WaveItem<Boolean> SKIP_SLIDE_STEP = new WaveItemBase<Boolean>() {
    };

    WaveItem<Slide> SLIDE = new WaveItemBase<Slide>() {
    };

}
