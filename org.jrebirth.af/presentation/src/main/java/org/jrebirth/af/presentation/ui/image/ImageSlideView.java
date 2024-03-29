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
package org.jrebirth.af.presentation.ui.image;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.core.resource.Resources;
import org.jrebirth.af.core.resource.image.RelImage;
import org.jrebirth.af.presentation.model.AnimationType;
import org.jrebirth.af.presentation.ui.base.AbstractSlideView;

/**
 *
 * The class <strong>ImageSlideView</strong>.
 *
 * @author Sébastien Bordes
 *
 */
public final class ImageSlideView extends
        AbstractSlideView<ImageSlideModel, StackPane, ImageSlideController> {

    /**
     * The <code>RANDOM</code> field is used to build a random integer.
     */
    private static final Random RANDOM = new Random();

    /** The tile transition. */
    private Animation tileTransition;

    /** The fade transition. */
    private Animation fadeTransition;

    /** The image to display. */
    private Image image;

    /** The number of tiles per row and column. */
    private int tilePerRow = 50;

    private Label slideLabel;

    /**
     * Default Constructor.
     *
     * @param model the splash view model
     *
     * @throws CoreException if build fails
     */
    public ImageSlideView(final ImageSlideModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {

        node().getStyleClass().add("ImageSlide");
        if (model().getSlide().getStyle() != null) {
            node().getStyleClass().add(model().getSlide().getStyle());
        }

        this.image = Resources.create(new RelImage(model().getImage())).get();

        if (model().getSlide().getShowAnimation() == null ||
                !"TileIn".equalsIgnoreCase(model().getSlide().getShowAnimation().value())
                        && !"TileIn60k".equalsIgnoreCase(model().getSlide().getShowAnimation().value())) {
            ImageView imageView = new ImageView();
            imageView.setImage(this.image);
            imageView.setLayoutX(0);
            imageView.setLayoutY(0);
            imageView.setFitWidth(this.image.getWidth());
            imageView.setFitHeight(this.image.getHeight());
            node().getChildren().add(imageView);

            // getRootNode().setOpacity(0);

        }

        if (model().getSlide().getShowAnimation() != null && AnimationType.TILE_IN == model().getSlide().getShowAnimation()) {
            this.tilePerRow = 5;
        } else if (model().getSlide().getShowAnimation() != null && AnimationType.TILE_IN_60_K == model().getSlide().getShowAnimation()) {
            this.tilePerRow = 50;
        }

        getTileTransition();
        getFadeTransition();

        if (model().getTitle() != null) {
            this.slideLabel = new Label();
            this.slideLabel.setPrefSize(1200, 80);
            this.slideLabel.setAlignment(Pos.CENTER);
            this.slideLabel.getStyleClass().add("ImageTitle");
            this.slideLabel.setText(model().getTitle());
            node().getChildren().add(this.slideLabel);
            this.slideLabel.setOpacity(0.0);

            StackPane.setAlignment(this.slideLabel, Pos.BOTTOM_CENTER);
            StackPane.setMargin(this.slideLabel, new Insets(0, 0, 100, 0));
        }

    }

    /**
     * Return the image node.
     *
     * @return the image node
     */
    Image getImage() {
        return this.image;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {

        if (model().getSlide().getShowAnimation() != null && AnimationType.TILE_IN == model().getSlide().getShowAnimation()) {
            for (final Node n : node().getChildren()) {
                n.setOpacity(0.0);
            }
            this.tilePerRow = 5;
            ParallelTransition st = new ParallelTransition();
            st.getChildren().setAll(getTileTransition(), getSlideLabelTransition(Duration.millis(1200)));
            st.play();
        } else if (model().getSlide().getShowAnimation() != null && AnimationType.TILE_IN_60_K == model().getSlide().getShowAnimation()) {
            for (final Node n : node().getChildren()) {
                n.setOpacity(0.0);
            }
            this.tilePerRow = 50;

            final ParallelTransition st = new ParallelTransition();
            st.getChildren().setAll(getTileTransition(), getSlideLabelTransition(Duration.millis(1200)));

            st.play();
        } else {
            getSlideLabelTransition(Duration.millis(0)).play();
        }
    }

    /**
     * TODO To complete.
     *
     * @return
     */
    private FadeTransition getSlideLabelTransition(final Duration gap) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setNode(this.slideLabel);
        fadeTransition.setDelay(gap);
        fadeTransition.setDuration(Duration.millis(1200));
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        return fadeTransition;
    }

    /**
     * @return Returns the fadeTransition.
     */
    Animation getFadeTransition() {
        if (this.fadeTransition == null) {
            this.fadeTransition = new FadeTransition();
            ((FadeTransition) this.fadeTransition).setNode(node());
            ((FadeTransition) this.fadeTransition).setFromValue(0);
            ((FadeTransition) this.fadeTransition).setToValue(1);
            ((FadeTransition) this.fadeTransition).setDuration(Duration.seconds(1));
        }
        return this.fadeTransition;
    }

    /**
     * @return Returns the tileTransition.
     */
    Animation getTileTransition() {
        if (this.tileTransition == null) {

            // Map<Point2D, ImageView> tiles = new HashMap<>();

            final double width = getImage().getWidth();
            final double height = getImage().getHeight();
            final double tileWidth = width / this.tilePerRow;
            final double tileHeight = height / this.tilePerRow;

            final List<Animation> fades = new ArrayList<>();

            for (double x = 0; x < width; x += tileWidth) {
                for (double y = 0; y < height; y += tileHeight) {

                    final ImageView iv = new ImageView();
                    iv.setImage(getImage());
                    Rectangle rectangle = new Rectangle();
                    rectangle.setX(x);
                    rectangle.setY(y);
                    rectangle.setWidth(tileWidth);
                    rectangle.setHeight(tileHeight);
                    iv.setClip(rectangle);
                    iv.setOpacity(0.0);
                    iv.setLayoutX(x);
                    iv.setLayoutY(y);

                    node().getChildren().add(iv);
                    PauseTransition pauseTransition = new PauseTransition();
                    pauseTransition.setDuration(getRandomDuration());
                    FadeTransition fadeTransition = new FadeTransition();
                    fadeTransition.setFromValue(0.0);
                    fadeTransition.setToValue(1.0);
                    fadeTransition.setDuration(getRandomDuration());
                    fades.add(new SequentialTransition(pauseTransition, fadeTransition));

                }

            }
            this.tileTransition = new ParallelTransition();
            ((ParallelTransition) this.tileTransition).getChildren().setAll(fades);

            this.tileTransition.setOnFinished(new EventHandler<ActionEvent>() {

                @Override
                public void handle(final ActionEvent arg0) {
                    for (final Node n : node().getChildren()) {
                        n.setOpacity(1.0);
                    }

                }
            });
        }
        return this.tileTransition;
    }

    /**
     * Get a random duration from the range [300-1200].
     *
     * @return a duration object
     */
    private Duration getRandomDuration() {
        return Duration.millis(RANDOM.nextInt(Integer.MAX_VALUE) % 900 + 300);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reload() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {
        // Nothing to do yet
    }

}
