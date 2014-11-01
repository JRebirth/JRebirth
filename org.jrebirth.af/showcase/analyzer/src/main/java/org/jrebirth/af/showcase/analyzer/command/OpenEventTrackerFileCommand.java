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
package org.jrebirth.af.showcase.analyzer.command;

import java.io.File;

import javafx.stage.FileChooser;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.command.single.ui.DefaultUICommand;
import org.jrebirth.af.core.wave.Builders;
import org.jrebirth.af.showcase.analyzer.service.LoadEdtFileService;
import org.jrebirth.af.showcase.analyzer.ui.editor.EditorWaves;

/**
 * The class <strong>OpenEventTrackerFileCommand</strong>.
 *
 * @author Sébastien Bordes
 */
public final class OpenEventTrackerFileCommand extends DefaultUICommand {

    /**
     * {@inheritDoc}
     */
    @Override
    public void perform(final Wave wave) {

        File selected = wave.get(EditorWaves.EVENTS_FILE);

        if (selected == null) {

            final FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("log file", "*"));
            selected = fc.showOpenDialog(null);

        }

        if (selected != null) {
            // if nothing is selected
            // Process the file
            // getService(LoadEdtFileService.class).processEventFile(selected);

            returnData(LoadEdtFileService.class,
                       LoadEdtFileService.DO_LOAD_EVENTS,
                       Builders.waveData(EditorWaves.EVENTS_FILE, selected));

        }
    }

}
