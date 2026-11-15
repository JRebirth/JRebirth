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
package org.jrebirth.af.presentation.javafx.ui.slides.place;

import java.util.Random;

import javafx.animation.FadeTransitionBuilder;
import javafx.animation.ParallelTransition;
import javafx.animation.ParallelTransitionBuilder;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.util.Duration;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.presentation.javafx.resources.JFXImages;
import org.jrebirth.af.presentation.ui.base.AbstractSlideView;

/**
 * 
 * The class <strong>IntroView</strong>.
 * 
 * The custom introduction slide.
 * 
 * @author Sébastien Bordes
 */
public final class PlaceView extends AbstractSlideView<PlaceModel, Pane, PlaceController> {

    /** The r. */
    private final Random r = new Random();

    /** The bubble animation. */
    private ParallelTransition bubbleAnimation;

    /**
     * Default Constructor.
     * 
     * @param model the IntroModel view model
     * 
     * @throws CoreException if build fails
     */
    public PlaceView(final PlaceModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {

        final ImageView toulouse = ImageViewBuilder.create()
                                                   .image(JFXImages.BZH.get())
                                                   // .fitHeight(Double.MAX_VALUE)
                                                   // .fitWidth(Double.MAX_VALUE)
                                                   .build();

        node().getChildren().add(toulouse);

        node().getStyleClass().add(model().getSlide().getStyle());

        // bulle jaune (52x52px) x:909 y:358 #FBE43D
        final Circle yellowCircle = getCircleBuilder().radius(26).layoutX(935).layoutY(384).style("-fx-fill:#FBE43D").build();
        // bulle verte (76x76px) x:752 y:251 #65AF23
        final Circle greenCircle = getCircleBuilder().radius(38).layoutX(790).layoutY(289).style("-fx-fill:#65AF23").build();
        // bulle rose (52x52px) x:863 y:225 #C13C8B
        final Circle pinkCircle = getCircleBuilder().radius(26).layoutX(889).layoutY(251).style("-fx-fill:#C13C8B").build();
        // bulle bleue (70x70px) x:777 y:125 #1C9A9A
        final Circle blueCircle = getCircleBuilder().radius(35).layoutX(812).layoutY(160).style("-fx-fill:#1C9A9A").build();
        // bulle orange clair (70x70px) x:889 y:130 #EA661D
        final Circle lightOrangeCircle = getCircleBuilder().radius(35).layoutX(924).layoutY(165).style("-fx-fill:#EA661D").build();
        // bulle orange fonce (50x50px) x:848 y: 36 #E53B20
        final Circle darkOrangeCircle = getCircleBuilder().radius(25).layoutX(873).layoutY(61).style("-fx-fill:#E53B20").build();

        node().getChildren().addAll(yellowCircle, greenCircle, pinkCircle, blueCircle, lightOrangeCircle, darkOrangeCircle);

        this.bubbleAnimation = ParallelTransitionBuilder.create().children(

                                                                           getFadeTransitionBuilder().node(yellowCircle).build(),
                                                                           getFadeTransitionBuilder().node(greenCircle).build(),
                                                                           getFadeTransitionBuilder().node(pinkCircle).build(),
                                                                           getFadeTransitionBuilder().node(blueCircle).build(),
                                                                           getFadeTransitionBuilder().node(lightOrangeCircle).build(),
                                                                           getFadeTransitionBuilder().node(darkOrangeCircle).build()

                                                        ).build();
    }

    /**
     * TODO To complete.
     * 
     * @return the circle builder
     */
    private CircleBuilder<?> getCircleBuilder() {
        return CircleBuilder.create();
    }

    /**
     * TODO To complete.
     * 
     * @return the fade transition builder
     */
    private FadeTransitionBuilder getFadeTransitionBuilder() {
        return FadeTransitionBuilder.create()
                                    .fromValue(0)
                                    .toValue(1.0)
                                    // .cycleCount(Animation.INDEFINITE)
                                    .autoReverse(true)
                                    .duration(Duration.millis(this.r.nextInt(Integer.MAX_VALUE) % 2000 + 1000))
                                    .delay(Duration.millis(this.r.nextInt(Integer.MAX_VALUE) % 400 + 400));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.bubbleAnimation.play(); // final TO CHECK
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reload() {
        this.bubbleAnimation.playFromStart();
    }

}
