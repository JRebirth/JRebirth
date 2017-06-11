package org.jrebirth.af.showcase.wave.ui.main;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.annotation.OnWave;
import org.jrebirth.af.core.ui.fxml.DefaultFXMLModel;
import org.jrebirth.af.showcase.wave.ui.WWaves;

public class MainModel extends DefaultFXMLModel<MainModel> {

    @OnWave(WWaves.CHANGE)
    protected void whatyouwant(Wave wave) {
        System.out.println("MainModel " + wave.waveType().action());
    }

}
