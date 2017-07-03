package org.jrebirth.af.showcase.wave.ui.main;

import org.jrebirth.af.api.module.Register;
import org.jrebirth.af.api.ui.ModuleModel;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.annotation.OnWave;
import org.jrebirth.af.core.ui.fxml.DefaultFXMLModel;
import org.jrebirth.af.showcase.wave.ui.WWaves;

@Register(value = ModuleModel.class)
public class MainModel extends DefaultFXMLModel<MainModel> implements ModuleModel {

    @OnWave(WWaves.CHANGE)
    protected void whatyouwant(Wave wave) {
        System.out.println("MainModel " + wave.waveType().action());
    }

    @Override
    public String moduleName() {
        return "Wave";
    }

}
