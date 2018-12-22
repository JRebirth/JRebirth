package org.jrebirth.af.transition.tests;
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
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.InputStream;

/**
 * The class <strong>JRebirthAnalyzer</strong>.
 * 
 * The application that demonstrate how to use JRebirth Framework. It also allow to show all JRebirth events.
 * 
 * @author Sébastien Bordes
 * 
 */
public final class SlideTransition extends Application {

    /**
     * Application launcher.
     * 
     * @param args the command line arguments
     */
    public static void main(final String... args) {
        Application.launch(SlideTransition.class, args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage stage) throws Exception {

        final Pane p = new Pane();
        final Scene scene = new Scene(p, 800, 600);

        stage.setScene(scene);

        p.setMaxWidth(800);
        p.setMaxHeight(600);
        // p.getChildren().addAll(
        // RectangleBuilder.create().x(0).y(0).width(100).height(600).fill(Color.AZURE).build(),
        // RectangleBuilder.create().x(100).y(0).width(100).height(600).fill(Color.BEIGE).build(),
        // RectangleBuilder.create().x(200).y(0).width(100).height(600).fill(Color.AZURE).build(),
        // RectangleBuilder.create().x(300).y(0).width(100).height(600).fill(Color.BEIGE).build(),
        // RectangleBuilder.create().x(400).y(0).width(100).height(600).fill(Color.AZURE).build(),
        // RectangleBuilder.create().x(500).y(0).width(100).height(600).fill(Color.BEIGE).build(),
        // RectangleBuilder.create().x(600).y(0).width(100).height(600).fill(Color.AZURE).build(),
        // RectangleBuilder.create().x(700).y(0).width(100).height(600).fill(Color.BEIGE).build()
        // );

        final DropShadow shadow = new DropShadow();
                shadow.setRadius(4);
                shadow.setColor(Color.GREY);

        Image image = null;
        final InputStream imageInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Properties.png");
        if (imageInputStream != null) {
            image = new Image(imageInputStream);
        }

        for (int i = 0; i < 40; i++) {
            Rectangle rectangle = new Rectangle();
            rectangle.setX(i * 20);
            rectangle.setY(0);
            rectangle.setWidth(20);
            rectangle.setHeight(600);
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setClip(rectangle);
            imageView.setEffect(shadow);
            p.getChildren().add(imageView);
        }
        stage.show();

        final EventHandler<javafx.event.ActionEvent> shadowRemover = new EventHandler<javafx.event.ActionEvent>() {

            @Override
            public void handle(final javafx.event.ActionEvent event) {
                ((TranslateTransition) event.getSource()).getNode().setEffect(null);
            }

        };

        final ParallelTransition st = new ParallelTransition();
        st.setDelay(Duration.seconds(1));
        st.setAutoReverse(true);
        st.setCycleCount(10);
        int i = 0;
        for (final Node n : p.getChildren()) {
            TranslateTransition translateTransition = new TranslateTransition();
            translateTransition.setDelay(Duration.millis(i * 50));
            translateTransition.setNode(n);
            translateTransition.setFromY(0);
            translateTransition.setToY(1000);
            translateTransition.setDuration(Duration.millis(1000));
            translateTransition.setInterpolator(Interpolator.EASE_IN);
            translateTransition.setOnFinished(shadowRemover); ;
            st.getChildren().add(translateTransition);
            i++;
        }

        st.play();
    }
}
