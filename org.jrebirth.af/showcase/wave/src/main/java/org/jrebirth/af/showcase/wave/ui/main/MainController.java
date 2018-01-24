package org.jrebirth.af.showcase.wave.ui.main;

import org.jrebirth.af.api.ui.View;
import org.jrebirth.af.core.ui.fxml.AbstractFXMLController;
import org.jrebirth.af.showcase.wave.ui.second.SecondModel;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public final class MainController extends AbstractFXMLController<MainModel, View<MainModel, ?, ?>> {

    public TextField txtMessage;

    private Stage stage;

    public void onOk(ActionEvent actionEvent) {
        if (stage == null) {
            stage = new Stage();
            stage.setScene(new Scene((Parent) model().getModel(SecondModel.class).node(), 600, 400));
        }
        stage.show();
    }

}
