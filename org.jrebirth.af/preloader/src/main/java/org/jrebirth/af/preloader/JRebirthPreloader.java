/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
 * Contact : sebastien.bordes@jrebirth.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.af.preloader;

import java.io.IOException;
import java.io.InputStream;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * The class <strong>JRebirthPreloader</strong> is the default implementation that show a JRebirth logo with a progress bar.
 *
 * @author Sébastien Bordes
 */
public class JRebirthPreloader extends AbstractJRebirthPreloader {

    /**
     * {@inheritDoc}
     */
    @Override
    protected Scene createPreloaderScene() {

        final StackPane p = new StackPane();
        p.setStyle("-fx-background-color: transparent;");

    	final ImageView logo = new ImageView(new Image("org/jrebirth/af/preloader/images/JRebirth_Title.png"));
    	p.getChildren().add(logo);
    	StackPane.setAlignment(logo, Pos.CENTER);

        this.progressBar = new ProgressBar(0.0);
        this.progressBar.setPrefSize(460, 20);
        p.getChildren().add(this.progressBar);
        StackPane.setAlignment(this.progressBar, Pos.BOTTOM_CENTER);
        StackPane.setMargin(this.progressBar, new Insets(30));

        this.messageText = new Text("Loading");
        p.getChildren().add(this.messageText);
        StackPane.setAlignment(this.messageText, Pos.BOTTOM_CENTER);
        StackPane.setMargin(this.messageText, new Insets(10));

        final Scene scene = new Scene(p, 600, 200, Color.TRANSPARENT);
        return scene;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void hideStage() {
        final Stage stage = this.preloaderStage;

        final ScaleTransition st = new ScaleTransition();
        st.setFromX(1.0);
        st.setToX(0.0);
        st.setDuration(Duration.millis(400));
        st.setNode(stage.getScene().getRoot());
        st.setOnFinished(actionEvent -> stage.hide());
        st.play();
    }

    public static void main(String... args) {
        launch(args);
    }

}
