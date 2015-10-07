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
package org.jrebirth.af.presentation.ui.qanda;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransitionBuilder;
import javafx.animation.ScaleTransitionBuilder;
import javafx.animation.TranslateTransitionBuilder;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBuilder;
import javafx.util.Duration;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.presentation.resources.PrezColors;
import org.jrebirth.af.presentation.ui.base.AbstractSlideView;

/**
 *
 * The class <strong>QandAView</strong>.
 *
 * @author Sébastien Bordes
 */
public class QandAView extends AbstractSlideView<QandAModel, BorderPane, QandAController> {

    /** The first text. */
    private Text firstText;

    /** The second text. */
    private Text secondText;

    /** The answer transition. */
    private Animation transition;

    /**
     * Default Constructor.
     *
     * @param model the splash view model
     *
     * @throws CoreException if build fails
     */
    public QandAView(final QandAModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {

        getRootNode().getStyleClass().add(getModel().getStyleClass() == null ? "qandaContainer" : getModel().getStyleClass());

        this.firstText = TextBuilder.create()
                                    // .text(getModel().getQuestion())
                                    // .styleClass("firstText")
                                    .textAlignment(TextAlignment.JUSTIFY)
                                    // .wrappingWidth(600)
                                    .smooth(true)
                                    .cache(true)
                                    .cacheHint(CacheHint.SPEED)
                                    .fill(getModel().getStyleClass() == null ? Color.WHITE : PrezColors.SPLASH_TEXT.get())
                                    .scaleX(1)
                                    .scaleY(1)
                                    .build();

        this.secondText = TextBuilder.create()
                                     // .text(getModel().getAnswer())
                                     // .styleClass("secondText")
                                     .textAlignment(TextAlignment.JUSTIFY)
                                     // .wrappingWidth(600)
                                     .smooth(true)
                                     .cache(true)
                                     .cacheHint(CacheHint.SPEED)
                                     .fill(getModel().getStyleClass() == null ? Color.WHITE : PrezColors.SPLASH_TEXT.get())
                                     .scaleX(0)
                                     .scaleY(0)
                                     .build();

        final StackPane sp = StackPaneBuilder.create()
                                             .children(this.firstText, this.secondText)
                                             .build();

        StackPane.setAlignment(this.firstText, Pos.CENTER);
        StackPane.setAlignment(this.secondText, Pos.CENTER);

        BorderPane.setAlignment(sp, Pos.CENTER);

        getRootNode().setCenter(sp);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {

    }

    /**
     * @return Returns the questionTransition.
     */
    Animation getTransition() {
        if (this.transition == null) {
            this.transition =

                    ParallelTransitionBuilder.create().children(
                                                                ScaleTransitionBuilder.create()
                                                                                      .node(this.firstText)
                                                                                      .duration(Duration.millis(800))
                                                                                      .interpolator(Interpolator.EASE_IN)
                                                                                      .fromX(1)
                                                                                      .fromY(1)
                                                                                      .toX(0)
                                                                                      .toY(0)
                                                                                      .build(),
                                                                TranslateTransitionBuilder.create()
                                                                                          .node(this.firstText)
                                                                                          .duration(Duration.millis(800))
                                                                                          .interpolator(Interpolator.EASE_IN)
                                                                                          .fromY(0)
                                                                                          .toY(600)
                                                                                          .build(),
                                                                ScaleTransitionBuilder.create()
                                                                                      .node(this.secondText)
                                                                                      // .delay(Duration.millis(200))
                                                                                      .duration(Duration.millis(1000))
                                                                                      .interpolator(Interpolator.EASE_IN)
                                                                                      .fromX(100)
                                                                                      .fromY(100)
                                                                                      .toX(1)
                                                                                      .toY(1)
                                                                                      .build(),
                                                                TranslateTransitionBuilder.create()
                                                                                          .node(this.secondText)
                                                                                          .duration(Duration.millis(800))
                                                                                          .interpolator(Interpolator.EASE_IN)
                                                                                          .fromY(-600)
                                                                                          .toY(0)
                                                                                          .build()
                                             )
                                             .build();

        }
        return this.transition;
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

    public void showNext(final String current, final String next) {

        if (current == null) {
            this.firstText.setText(next);
        } else {
            this.firstText.setText(current);
            this.secondText.setText(next);

            getTransition().setRate(1);
            getTransition().play();
        }
    }

    public void showPrevious(final String current, final String previous) {
        if (current == null) {
            this.firstText.setText(previous);
        } else {
            this.firstText.setText(previous);
            this.secondText.setText(current);
            getTransition().setRate(-1);
            getTransition().play();
        }
    }

}
