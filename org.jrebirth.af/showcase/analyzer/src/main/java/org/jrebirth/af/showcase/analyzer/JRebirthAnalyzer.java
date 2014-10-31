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
package org.jrebirth.af.showcase.analyzer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.jrebirth.af.api.resource.ResourceItem;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.WaveGroup;
import org.jrebirth.af.core.application.DefaultApplication;
import org.jrebirth.af.core.wave.Builders;
import org.jrebirth.af.showcase.analyzer.service.LoadEdtFileService;
import org.jrebirth.af.showcase.analyzer.ui.editor.EditorWaves;
import org.jrebirth.af.showcase.analyzer.ui.workbench.WorkbenchModel;

/**
 * The class <strong>JRebirthAnalyzer</strong>.
 *
 * The application that demonstrate how to use JRebirth Framework.
 *
 * It also allows showing all JRebirth events.
 *
 * @author Sébastien Bordes
 */
public final class JRebirthAnalyzer extends DefaultApplication<StackPane> {

    /**
     * Application launcher.
     *
     * @param args the command line arguments
     */
    public static void main(final String... args) {
        Application.launch(JRebirthAnalyzer.class, args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Model> getFirstModelClass() {
        return WorkbenchModel.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getApplicationTitle() {
        return "JavaFX 2.0 - JRebirth Event Analyzer";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customizeScene(final Scene scene) {
        scene.getStylesheets().add("css/analyzer.css");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customizeStage(final Stage stage) {
        // Center the stage
        stage.centerOnScreen();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Wave> getPostBootWaveList() {

        final List<Wave> waveList = new ArrayList<>();

        // Get Java command line parameters
        final Parameters p = getParameters();
        if (p.getRaw().size() >= 1) {

            // The first parameter must contains the log file to parse
            final String logFileName = p.getRaw().get(0);
            final File logFile = new File(logFileName);

            if (logFile.exists()) {

                // Call the service that will load and parse the log file
                waveList.add(
                        Builders.wave()
                        .waveGroup(WaveGroup.RETURN_DATA)
                        .waveType(LoadEdtFileService.DO_LOAD_EVENTS)
                        .componentClass(LoadEdtFileService.class)
                        .addDatas(Builders.waveData(EditorWaves.EVENTS_FILE, logFile))
                        );

                // Start the animation to show all components creation
                waveList.add(Builders.wave().waveType(EditorWaves.DO_PLAY));
            }
        }
        return waveList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<? extends ResourceItem<?, ?, ?>> getResourceToPreload() {
        return Collections.emptyList();
    }
}
