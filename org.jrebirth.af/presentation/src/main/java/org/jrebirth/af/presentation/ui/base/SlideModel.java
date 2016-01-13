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
package org.jrebirth.af.presentation.ui.base;

import javafx.animation.Animation;

import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.presentation.ui.base.AbstractSlideModel.SlideFlow;

/**
 * The class <strong>SlideModel</strong>.
 *
 * @author Sébastien Bordes
 *
 * @param <S> The SlideStep to use
 */
public interface SlideModel<S extends SlideStep> extends Model {

    /**
     * @param slideNumber The slideNumber to set.
     */
    void setSlideNumber(final int slideNumber);

    /**
     * Show the step.
     *
     * @param slideStep the step to show
     */
    void showSlideStep(final S slideStep);

    /**
     * @param currentFlow The currentFlow to set.
     */
    void setCurrentFlow(final SlideFlow currentFlow);

    /**
     * Check if the next step is available.
     *
     * @return true if no more steps are available
     */
    boolean nextStep();

    /**
     * Check if the previous step is available.
     *
     * @return true if no more steps are available
     */
    boolean previousStep();

    /**
     * Get the hide animation.
     *
     * @return the hide animation
     */
    Animation getHideAnimation();

    /**
     * Get the show animation.
     *
     * @return the show animation
     */
    Animation getShowAnimation();
}
