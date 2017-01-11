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
package org.jrebirth.af.core.service.basic.impl;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.jrebirth.af.api.concurrent.RunInto;
import org.jrebirth.af.api.concurrent.RunType;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.command.basic.stage.StageWaveBean;
import org.jrebirth.af.core.service.DefaultService;
import org.jrebirth.af.core.service.basic.StageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>StageService</strong>.
 *
 * @author Sébastien Bordes
 */
public class StageServiceImpl extends DefaultService implements StageService {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(StageServiceImpl.class);

    /** The map that stores all stages. */
    private final Map<String, Stage> stageMap = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void initService() {

        // listen(DO_OPEN_STAGE);
        // listen(DO_CLOSE_STAGE);
        // listen(DO_DESTROY_STAGE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RunInto(RunType.JAT)
    public void doOpenStage(final Wave wave) {

        LOGGER.trace("Open a stage.");

        final StageWaveBean swb = getWaveBean(wave);
        final String stageKey = swb.stageKey();

        if (this.stageMap.containsKey(stageKey) && this.stageMap.get(stageKey) != null) {
            // Show the stage
            Platform.runLater(() -> this.stageMap.get(stageKey).show());
        } else {

            final Region rootPane = getRootPane(swb);
            final Scene scene = getScene(swb, rootPane);
            final Stage stage = getStage(swb, scene);
            // Show the stage
            this.stageMap.put(stageKey, stage);
            Platform.runLater(() -> stage.show());
        }

    }

    /**
     * Gets the root pane.
     *
     * @param swb the swb the waveBean holding defau!t values
     *
     * @return the root pane
     */
    private Region getRootPane(final StageWaveBean swb) {

        return swb.rootPane() == null
                ? new StackPane()
                : swb.rootPane();
    }

    /**
     * Gets the scene.
     *
     * @param swb the waveBean holding defaut values
     * @param region the region
     *
     * @return the scene
     */
    private Scene getScene(final StageWaveBean swb, final Region region) {

        Scene scene = swb.scene();
        if (scene == null) {
            scene = new Scene(region);
        } else {
            scene.setRoot(region);
        }

        return scene;
    }

    /**
     * Gets the stage.
     *
     * @param swb the waveBean holding default values
     * @param scene the scene
     *
     * @return the stage
     */
    private Stage getStage(final StageWaveBean swb, final Scene scene) {

        Stage stage = swb.stage();
        if (stage == null) {
            stage = new Stage();
        }
        stage.setScene(scene);

        return stage;
    }

    /**
     * Close a stage. (Hide it)
     *
     * @param wave the source wave
     */
    @Override
    public void doCloseStage(final Wave wave) {
        final String stageKey = getWaveBean(wave).stageKey();

        Platform.runLater(() -> this.stageMap.get(stageKey).close());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doDestroyStage(final Wave wave) {
        final String stageKey = getWaveBean(wave).stageKey();

        Platform.runLater(() -> this.stageMap.get(stageKey).close());

        this.stageMap.remove(stageKey);
    }

    /**
     * Get the wave bean and cast it.
     *
     * @param wave the wave that hold the bean
     *
     * @return the casted wave bean
     */
    public StageWaveBean getWaveBean(final Wave wave) {
        return wave.waveBean(StageWaveBean.class);
    }

    /**
     * Retrieve a stage according to its key.
     *
     * @param stageKey the unique key for the requested stage
     *
     * @return the stage or null if not mapped
     */
    public Stage getStage(final String stageKey) {
        return this.stageMap.get(stageKey);
    }

}
