package org.jrebirth.af.showcase.todos.ui;

import org.jrebirth.af.api.annotation.Preload;
import org.jrebirth.af.api.wave.contract.WaveType;
import org.jrebirth.af.core.wave.WBuilder;

@Preload
public interface WWaves {

    String CHANGE = "CHANGE";

    WaveType CHANGE_WT = WBuilder.waveType(CHANGE);

}
