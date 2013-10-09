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
package org.jrebirth.preloader;

import javafx.animation.ScaleTransitionBuilder;
import javafx.application.Preloader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * Hello world!.
 */
public class JRebirthPreloader extends Preloader
{

    /** The bar. */
    ProgressBar bar;

    /** The stage. */
    Stage stage;

    /** The text. */
    private Text text;

    private boolean jrebirthInit = false;

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() throws Exception {
        super.init();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage stage) throws Exception {
        this.stage = stage;
        stage.centerOnScreen();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(createPreloaderScene());
        stage.show();
    }

    /**
     * Creates the preloader scene.
     * 
     * @return the scene
     */
    private Scene createPreloaderScene() {

        final StackPane p = new StackPane();

        final ImageView logo = new ImageView(new Image("JRebirth_Title.png"));
        p.getChildren().add(logo);
        StackPane.setAlignment(logo, Pos.CENTER);

        this.bar = new ProgressBar(0.0);
        this.bar.setPrefSize(460, 20);
        p.getChildren().add(this.bar);
        StackPane.setAlignment(this.bar, Pos.BOTTOM_CENTER);
        StackPane.setMargin(this.bar, new Insets(30));

        this.text = new Text("Loading");
        p.getChildren().add(this.text);
        StackPane.setAlignment(this.text, Pos.BOTTOM_CENTER);
        StackPane.setMargin(this.text, new Insets(10));

        return new Scene(p, 600, 200, Color.TRANSPARENT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() throws Exception {
        super.stop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleProgressNotification(final ProgressNotification pn) {
        if (jrebirthInit && pn.getProgress() >= 0.0 && pn.getProgress() <= 1.0) {
            this.bar.setProgress(pn.getProgress());
        } else {
            this.text.setText(getMessageFromCode((int) pn.getProgress()));
        }
    }

    /**
     * Gets the message from code.
     * 
     * @param messageCode the message code
     * @return the message from code
     */
    private String getMessageFromCode(int messageCode) {
        String res = "";

        switch (messageCode) {
            case 100:
                res = "Initializing";
                break;
            case 200:
                res = "";// Provisioned for custom pre-init
                break;
            case 300:
                res = "";// Provisioned for custom pre-init
                break;
            case 400:
                res = "Loading Messages Properties";
                break;
            case 500:
                res = "Loading Parameters Properties";
                break;
            case 600:
                res = "Preparing Core Engine";
                break;
            case 700:
                res = "Preloading Fonts";
                break;
            case 800:
                res = "";// Provisioned for custom post-init
                break;
            case 900:
                res = "";// Provisioned for custom post-init
                break;
            case 1000:
                res = "Starting";
                break;
            default:
        }
        return res;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleApplicationNotification(final PreloaderNotification n) {
        if (n instanceof MessageNotification) {
            this.text.setText(((MessageNotification) n).getMessage());
        } else if (n instanceof ProgressNotification) {
            handleProgressNotification((ProgressNotification) n);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleStateChangeNotification(final StateChangeNotification event) {
        switch (event.getType()) {
            case BEFORE_LOAD:
                break;
            case BEFORE_INIT:
                jrebirthInit = true;
                break;
            case BEFORE_START:
                beforeStart();
                break;
            default:
        }
    }

    /**
     * TODO To complete.
     * 
     * @throws InterruptedException
     */
    private void beforeStart() {
        final Stage stage = this.stage;

        ScaleTransitionBuilder.create()
                .fromX(1.0)
                .toX(0.0)
                .duration(Duration.millis(400))
                .node(stage.getScene().getRoot())
                .onFinished(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent arg0) {
                        stage.hide();

                    }
                })
                .build().play();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean handleErrorNotification(final ErrorNotification arg0) {
        return super.handleErrorNotification(arg0);
    }

}
