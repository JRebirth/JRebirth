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
import java.io.InputStream;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.ParallelTransitionBuilder;
import javafx.animation.TranslateTransition;
import javafx.animation.TranslateTransitionBuilder;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.DropShadowBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.RectangleBuilder;
import javafx.stage.Stage;
import javafx.util.Duration;

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

        final DropShadow shadow = DropShadowBuilder.create()
                .radius(4)
                .color(Color.GREY)
                .build();

        Image image = null;
        final InputStream imageInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Properties.png");
        if (imageInputStream != null) {
            image = new Image(imageInputStream);
        }

        for (int i = 0; i < 40; i++) {
            p.getChildren().add(
                    ImageViewBuilder.create()
                            .image(image)
                            .clip(RectangleBuilder.create()
                                    .x(i * 20)
                                    .y(0)
                                    .width(20)
                                    .height(600)
                                    .build())
                            // RectangleBuilder.create()
                            // .x(i * 40)
                            // .y(0)
                            // .fitWidth(40)
                            // .fitHeight(600)
                            // .width(40)
                            // .height(600)
                            // .fill(Color.AZURE)
                            .effect(shadow)
                            // .strokeDashArray(2.0, 4.0)
                            // .stroke(Color.CHOCOLATE)
                            .build()
                    );
        }
        stage.show();

        final EventHandler<javafx.event.ActionEvent> shadowRemover = new EventHandler<javafx.event.ActionEvent>() {

            @Override
            public void handle(final javafx.event.ActionEvent event) {
                ((TranslateTransition) event.getSource()).getNode().setEffect(null);
            }

        };

        final ParallelTransition st = ParallelTransitionBuilder.create().delay(Duration.seconds(1)).autoReverse(true).cycleCount(10).build();
        int i = 0;
        for (final Node n : p.getChildren()) {

            st.getChildren().add(
                    TranslateTransitionBuilder.create()
                            .delay(Duration.millis(i * 50))
                            .node(n)
                            .fromY(0)
                            .toY(1000)
                            .duration(Duration.millis(1000))
                            .interpolator(Interpolator.EASE_IN)
                            .onFinished(shadowRemover)
                            .build()
                    );
            i++;
        }

        st.play();
    }
}
