package org.jrebirth.af.showcase.wave.ui.second;

import javafx.event.ActionEvent;

import org.jrebirth.af.api.ui.View;
import org.jrebirth.af.core.ui.fxml.AbstractFXMLController;
import org.jrebirth.af.showcase.wave.ui.WWaves;
import org.jrebirth.af.showcase.wave.ui.main.MainController;
import org.jrebirth.af.showcase.wave.ui.main.MainModel;

public class SecondController extends AbstractFXMLController<SecondModel, View<SecondModel, ?, ?>> {

    public void onSayHello(ActionEvent actionEvent) {
        final MainController controller = model().getModel(MainModel.class).getFXMLController();
        controller.txtMessage.setText("Hello, world");
    }

    public void onButton2(ActionEvent actionEvent) {
        model().sendWave(WWaves.CHANGE_WT);
    }
}
