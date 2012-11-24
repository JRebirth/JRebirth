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
package org.jrebirth.presentation.ui.image;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.FadeTransitionBuilder;
import javafx.animation.ParallelTransitionBuilder;
import javafx.animation.PauseTransitionBuilder;
import javafx.animation.SequentialTransitionBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.RectangleBuilder;
import javafx.util.Duration;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.presentation.model.AnimationType;
import org.jrebirth.presentation.ui.base.AbstractSlideView;

/**
 * 
 * The class <strong>ImageSlideView</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public final class ImageSlideView extends
        AbstractSlideView<ImageSlideModel, StackPane, ImageSlideController> {

    /** The tile transition. */
    private Animation tileTransition;

    /** The fade transition. */
    private Animation fadeTransition;

    /** The image to display. */
    private Image image;

    /** The number of tiles per row and column. */
    private int tilePerRow = 50;

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
    protected void customInitializeComponents() {

        this.image = loadImage(getModel().getImage());

        if (getModel().getSlide().getShowAnimation() == null || !"Tile".equalsIgnoreCase(getModel().getSlide().getShowAnimation().name())
                && !"Tile_60_k".equalsIgnoreCase(getModel().getSlide().getShowAnimation().name())) {
            getRootNode().getChildren().add(ImageViewBuilder.create().image(this.image).layoutX(0).layoutY(0).fitWidth(this.image.getWidth()).fitHeight(this.image.getHeight()).build());
        }
        getTileTransition();
        getFadeTransition();

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
    public void doStart() {

        if (getModel().getSlide().getShowAnimation() != null && AnimationType.TILE_IN == getModel().getSlide().getShowAnimation()) {
            for (final Node n : getRootNode().getChildren()) {
                n.setOpacity(0.0);
            }
            this.tilePerRow = 5;
            getTileTransition().play();
        } else if (getModel().getSlide().getShowAnimation() != null && AnimationType.TILE_IN_60_K == getModel().getSlide().getShowAnimation()) {
            for (final Node n : getRootNode().getChildren()) {
                n.setOpacity(0.0);
            }
            this.tilePerRow = 50;
            getTileTransition().play();
        }

        else {
            getRootNode().setOpacity(0);
            getFadeTransition().play();
        }
    }

    /**
     * @return Returns the fadeTransition.
     */
    Animation getFadeTransition() {
        if (this.fadeTransition == null) {
            this.fadeTransition = FadeTransitionBuilder.create().node(getRootNode()).fromValue(0).toValue(1).duration(Duration.seconds(1)).build();
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

                    final ImageView iv = ImageViewBuilder
                            .create()
                            .image(getImage())
                            .clip(RectangleBuilder.create().x(x).y(y)
                                    .width(tileWidth).height(tileHeight)
                                    .build()).opacity(0.0).layoutX(x)
                            .layoutY(y).build();

                    getRootNode().getChildren().add(iv);

                    fades.add(SequentialTransitionBuilder
                            .create()
                            .children(
                                    PauseTransitionBuilder.create()
                                            .duration(getRandomDuration())
                                            .build(),
                                    FadeTransitionBuilder.create().node(iv)
                                            .fromValue(0.0).toValue(1.0)
                                            .duration(getRandomDuration())
                                            .build()).build());

                }

            }
            this.tileTransition = ParallelTransitionBuilder.create()
                    .children(fades).build();

            this.tileTransition.setOnFinished(new EventHandler<ActionEvent>() {

                @Override
                public void handle(final ActionEvent arg0) {
                    for (final Node n : getRootNode().getChildren()) {
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
        return Duration.millis(new Random().nextLong() % 900 + 300);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doReload() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doHide() {
        // Nothing to do yet
    }

}
