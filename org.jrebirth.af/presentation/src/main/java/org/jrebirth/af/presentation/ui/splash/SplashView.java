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
package org.jrebirth.af.presentation.ui.splash;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.scene.layout.BorderPane;
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
 *
 */
public class SplashView extends AbstractSlideView<SplashModel, BorderPane, SplashController> {

    /** The Splashed text. */
    private Text splashText;

    /** The text transition. */
    private ScaleTransition textTransition;

    /**
     * Default Constructor.
     *
     * @param model the splash view model
     *
     * @throws CoreException if build fails
     */
    public SplashView(final SplashModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {

        node().getStyleClass().add(model().getStyleClass() == null ? "splashContainer" : model().getStyleClass());

        this.splashText = new Text();

        this.splashText.setText(model().getTitle());
        this.splashText.getStyleClass().add("splashText");
        this.splashText.setTextAlignment(TextAlignment.JUSTIFY);
        this.splashText.setWrappingWidth(600);
        this.splashText.setSmooth(true);
        this.splashText.setFill(model().getStyleClass() == null ? Color.WHITE : PrezColors.SPLASH_TEXT.get());
        this.splashText.setScaleX(0);
        this.splashText.setScaleY(0);

        node().setCenter(this.splashText);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {

        getTextTransition().playFromStart();
    }

    /**
     * @return Returns the splashText.
     */
    Text getSplashText() {
        return this.splashText;
    }

    /**
     * @return Returns the textTransition.
     */
    ScaleTransition getTextTransition() {
        if (this.textTransition == null) {
            this.textTransition = new ScaleTransition();
            this.textTransition.setNode(this.splashText);
            this.textTransition.setDuration(Duration.millis(800));
            this.textTransition.setInterpolator(Interpolator.EASE_IN);
            this.textTransition.setFromX(0);
            this.textTransition.setFromY(0);
            this.textTransition.setByX(2);
            this.textTransition.setByY(2);
        }
        return this.textTransition;
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
