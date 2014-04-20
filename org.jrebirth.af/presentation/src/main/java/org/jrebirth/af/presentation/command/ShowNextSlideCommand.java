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
package org.jrebirth.af.presentation.command;

import org.jrebirth.af.core.command.DefaultUICommand;
import org.jrebirth.af.core.wave.Wave;
import org.jrebirth.af.presentation.ui.stack.SlideStackModel;

/**
 * The class <strong>ShowNextSlideCommand</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public final class ShowNextSlideCommand extends DefaultUICommand {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void perform(final Wave wave) {
        getModel(SlideStackModel.class).next();
    }

}
