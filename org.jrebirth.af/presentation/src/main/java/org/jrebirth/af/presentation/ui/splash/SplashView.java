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
import javafx.animation.ScaleTransitionBuilder;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBuilder;
import javafx.util.Duration;

import org.jrebirth.af.core.exception.CoreException;
import org.jrebirth.af.presentation.resources.PrezColors;
import org.jrebirth.af.presentation.ui.base.AbstractSlideView;

/**
 * 
 * The class <strong>SplashView</strong>.
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

        getRootNode().getStyleClass().add(getModel().getStyleClass() == null ? "splashContainer" : getModel().getStyleClass());

        this.splashText = TextBuilder.create()
                .text(getModel().getTitle())
                .styleClass("splashText")
                .textAlignment(TextAlignment.JUSTIFY)
                .wrappingWidth(600)
                .smooth(true)
                .fill(getModel().getStyleClass() == null ? Color.WHITE : PrezColors.SPLASH_TEXT.get())
                .scaleX(0)
                .scaleY(0)
                .build();

        getRootNode().setCenter(this.splashText);
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
            this.textTransition = ScaleTransitionBuilder.create()
                    .node(this.splashText)
                    .duration(Duration.millis(800))
                    .interpolator(Interpolator.EASE_IN)
                    .fromX(0)
                    .fromY(0)
                    .byX(2)
                    .byY(2)
                    .build();
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
