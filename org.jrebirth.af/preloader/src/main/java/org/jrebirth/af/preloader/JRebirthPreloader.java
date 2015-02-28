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
 *
 * The class <strong>JRebirthPreloader</strong>.
 *
 * @author Sébastien Bordes
 */
public class JRebirthPreloader extends Preloader {

    /** The Progress Bar. */
    private ProgressBar progressBar;

    /** The preloader Stage. */
    private Stage preloaderStage;

    /** The text that will display message received. */
    private Text messageText;

    /** Flag that indicate if the application is initialized. */
    private boolean jrebirthInitialized = false;

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() throws Exception {
        super.init(); // Nothing to do for the preloader
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage stage) throws Exception {
        // Store the preloader stage to reuse it later
        this.preloaderStage = stage;

        // Configure the stage
        stage.centerOnScreen();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(createPreloaderScene());

        // Let's start the show
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

        this.progressBar = new ProgressBar(0.0);
        this.progressBar.setPrefSize(460, 20);
        p.getChildren().add(this.progressBar);
        StackPane.setAlignment(this.progressBar, Pos.BOTTOM_CENTER);
        StackPane.setMargin(this.progressBar, new Insets(30));

        this.messageText = new Text("Loading");
        p.getChildren().add(this.messageText);
        StackPane.setAlignment(this.messageText, Pos.BOTTOM_CENTER);
        StackPane.setMargin(this.messageText, new Insets(10));

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
        if (this.jrebirthInitialized && pn.getProgress() >= 0.0 && pn.getProgress() <= 1.0) {
            this.progressBar.setProgress(pn.getProgress());
        } else {
            this.messageText.setText(getMessageFromCode((int) pn.getProgress()));
        }
    }

    /**
     * Gets the message from code.
     *
     * @param messageCode the message code
     *
     * @return the message from code
     */
    private String getMessageFromCode(final int messageCode) {
        String res = "";

        switch (messageCode) {
            case 100:
                res = "Initializing";
                break;
            case 200:
                res = "";// Provisioned for custom pre-init task
                break;
            case 300:
                res = "";// Provisioned for custom pre-init task
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
                res = "Preloading Resources";
                break;
            case 800:
                res = "Preloading Modules";
                break;
            case 900:
                res = "";// Provisioned for custom post-init task
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
            this.messageText.setText(((MessageNotification) n).getMessage());
        } else if (n instanceof ProgressNotification) {
            handleProgressNotification((ProgressNotification) n);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean handleErrorNotification(final ErrorNotification arg0) {
        return super.handleErrorNotification(arg0);
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
                this.jrebirthInitialized = true;
                break;
            case BEFORE_START:
                beforeStart();
                break;
            default:
        }
    }

    /**
     * Perform actions before the application start.
     *
     * @throws InterruptedException
     */
    private void beforeStart() {
        final Stage stage = this.preloaderStage;

        ScaleTransitionBuilder.create()
                              .fromX(1.0)
                              .toX(0.0)
                              .duration(Duration.millis(400))
                              .node(stage.getScene().getRoot())
                              .onFinished(new EventHandler<ActionEvent>() {

                                  @Override
                                  public void handle(final ActionEvent arg0) {
                                      stage.hide();

                                  }
                              })
                              .build().play();

    }

}
