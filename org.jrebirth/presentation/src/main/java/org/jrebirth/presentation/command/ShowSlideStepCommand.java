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
package org.jrebirth.presentation.command;

import org.jrebirth.core.command.DefaultUICommand;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.presentation.ui.base.ShowSlideWaves;
import org.jrebirth.presentation.ui.base.SlideModel;
import org.jrebirth.presentation.ui.base.SlideStep;

/**
 * The class <strong>ShowSlideStepCommand</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public final class ShowSlideStepCommand extends DefaultUICommand {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void execute(final Wave wave) {
        // Retrieve the slide model
        final SlideModel<SlideStep> model = getModel(wave.get(ShowSlideWaves.SLIDE_MODEL_CLASS));

        // Show the the next slide step
        model.showSlideStep(wave.get(ShowSlideWaves.SLIDE_STEP_TYPE));
    }

}
