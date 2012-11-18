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
package org.jrebirth.presentation.ui.base;

import javafx.animation.Animation;

import org.jrebirth.core.ui.Model;

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
    void setSlideNumber(int slideNumber);

    /**
     * Show the step.
     * 
     * @param slideStep the step to show
     */
    void showSlideStep(final S slideStep);

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
     * TODO To complete.
     * 
     * @return
     */
    Animation getHideAnimation();

    /**
     * TODO To complete.
     * 
     * @return
     */
    Animation getShowAnimation();
}
