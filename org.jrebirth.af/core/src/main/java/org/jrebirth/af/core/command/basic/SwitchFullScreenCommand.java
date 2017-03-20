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
package org.jrebirth.af.core.command.basic;

import javafx.stage.Stage;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.command.single.ui.DefaultUICommand;

/**
 * The class <strong>SwitchFullScreenCommand</strong>.
 *
 * @author Sébastien Bordes
 */
public class SwitchFullScreenCommand extends DefaultUICommand {

    /**
     * {@inheritDoc}
     */
    @Override
    public void perform(final Wave wave) {

        final Stage stage = localFacade().globalFacade().application().stage();

        // Switch the full screen state
        stage.setFullScreen(!stage.isFullScreen());

    }
}
