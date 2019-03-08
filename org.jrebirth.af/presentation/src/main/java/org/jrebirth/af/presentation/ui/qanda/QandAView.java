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

import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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

        node().getStyleClass().add(model().getStyleClass() == null ? "qandaContainer" : model().getStyleClass());

        this.firstText = new Text();
                                    // .text(getModel().getQuestion())
                                    // .styleClass("firstText")
        this.firstText.setTextAlignment(TextAlignment.JUSTIFY);
                                    // .wrappingWidth(600)
        this.firstText.setSmooth(true);
        this.firstText.setCache(true);
        this.firstText.setCacheHint(CacheHint.SPEED);
        this.firstText.setFill(model().getStyleClass() == null ? Color.WHITE : PrezColors.SPLASH_TEXT.get());
        this.firstText.setScaleX(1);
        this.firstText.setScaleY(1);

        this.secondText = new Text();
                                     // .text(getModel().getAnswer())
                                     // .styleClass("secondText")
        this.secondText.setTextAlignment(TextAlignment.JUSTIFY);
                                     // .wrappingWidth(600)
        this.secondText.setSmooth(true);
        this.secondText.setCache(true);
        this.secondText.setCacheHint(CacheHint.SPEED);
        this.secondText.setFill(model().getStyleClass() == null ? Color.WHITE : PrezColors.SPLASH_TEXT.get());
        this.secondText.setScaleX(0);
        this.secondText.setScaleY(0);

        final StackPane sp = new StackPane(this.firstText, this.secondText);

        StackPane.setAlignment(this.firstText, Pos.CENTER);
        StackPane.setAlignment(this.secondText, Pos.CENTER);

        BorderPane.setAlignment(sp, Pos.CENTER);

        node().setCenter(sp);
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
            ScaleTransition scaleTransition1 = new ScaleTransition();
            scaleTransition1.setNode(this.firstText);
            scaleTransition1.setDuration(Duration.millis(800));
            scaleTransition1.setInterpolator(Interpolator.EASE_IN);
            scaleTransition1.setFromX(1);
            scaleTransition1.setFromY(1);
            scaleTransition1.setToX(0);
            scaleTransition1.setToY(0);
            TranslateTransition translateTransition1 = new TranslateTransition();
            translateTransition1.setNode(this.firstText);
            translateTransition1.setDuration(Duration.millis(800));
            translateTransition1.setInterpolator(Interpolator.EASE_IN);
            translateTransition1.setFromY(0);
            translateTransition1.setToY(600);
            ScaleTransition scaleTransition2 = new ScaleTransition();
            scaleTransition2.setNode(this.secondText);
                    // .delay(Duration.millis(200))
            scaleTransition2.setDuration(Duration.millis(1000));
            scaleTransition2.setInterpolator(Interpolator.EASE_IN);
            scaleTransition2.setFromX(100);
            scaleTransition2.setFromY(100);
            scaleTransition2.setToX(1);
            scaleTransition2.setToY(1);
            TranslateTransition translateTransition2 = new TranslateTransition();
            translateTransition2.setNode(this.secondText);
            translateTransition2.setDuration(Duration.millis(800));
            translateTransition2.setInterpolator(Interpolator.EASE_IN);
            translateTransition2.setFromY(-600);
            translateTransition2.setToY(0);
            this.transition = new ParallelTransition(scaleTransition1,
                    translateTransition1, scaleTransition2, translateTransition2);
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
