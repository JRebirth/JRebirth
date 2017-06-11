package org.jrebirth.af.showcase.wave.ui.second;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.ui.fxml.DefaultFXMLModel;
import org.jrebirth.af.showcase.wave.ui.WWaves;

public class SecondModel extends DefaultFXMLModel<SecondModel> {

    @Override
    protected void initModel() {
        listen(WWaves.CHANGE_WT);
    }

    public void doChange(final Wave wave) {
        System.out.println("SecondPageModel " + wave.waveType().action());
    }
}
