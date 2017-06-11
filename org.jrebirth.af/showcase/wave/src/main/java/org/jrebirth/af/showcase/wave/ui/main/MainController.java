package org.jrebirth.af.showcase.wave.ui.main;

import java.time.LocalDateTime;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.jrebirth.af.api.ui.View;
import org.jrebirth.af.core.ui.fxml.AbstractFXMLController;
import org.jrebirth.af.showcase.wave.ui.second.SecondModel;

public final class MainController extends AbstractFXMLController<MainModel, View<MainModel, ?, ?>> {

    public TextField txtMessage;

    public void onOk(ActionEvent actionEvent) {
        final SecondModel model = model().getModel(SecondModel.class, LocalDateTime.now());
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) model.node(), 600, 400));
        stage.showAndWait();
    }

}
