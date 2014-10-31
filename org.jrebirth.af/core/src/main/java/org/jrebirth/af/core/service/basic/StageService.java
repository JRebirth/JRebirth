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
package org.jrebirth.af.core.service.basic;

import static org.jrebirth.af.core.wave.Builders.waveType;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPaneBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.WaveType;
import org.jrebirth.af.core.command.basic.stage.StageWaveBean;
import org.jrebirth.af.core.service.DefaultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>StageService</strong>.
 *
 * @author Sébastien Bordes
 */
public class StageService extends DefaultService {

    /** Wave type use to load events. */
    public static final WaveType DO_OPEN_STAGE = waveType("OPEN_STAGE").returnAction("STAGE_OPENED");

    /** Wave type to return events loaded. */
    // public static final WaveType RE_STAGE_OPENED = WaveType.create("STAGE_OPENED");

    /** Wave type use to load events. */
    public static final WaveType DO_CLOSE_STAGE = waveType("CLOSE_STAGE").returnAction("STAGE_CLOSED");

    /** Wave type to return events loaded. */
    // public static final WaveType RE_STAGE_CLOSED = WaveType.create("STAGE_CLOSED");

    /** Wave type use to load events. */
    public static final WaveType DO_DESTROY_STAGE = waveType("DESTROY_STAGE").returnAction("STAGE_DESTROYED");

    /** Wave type to return events loaded. */
    // public static final WaveType RE_STAGE_DESTROYED = WaveType.create("STAGE_DESTROYED");

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(StageService.class);

    /** The map that stores all stages. */
    private final Map<String, Stage> stageMap = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void initService() {

        listen(DO_OPEN_STAGE/* , RE_STAGE_OPENED */);
        listen(DO_CLOSE_STAGE/* , RE_STAGE_CLOSED */);
        listen(DO_DESTROY_STAGE/* , RE_STAGE_DESTROYED */);
    }

    /**
     * Open a stage.
     *
     * @param wave the source wave
     */
    public void doOpenStage(final Wave wave) {

        LOGGER.trace("Open a stage.");

        final StageWaveBean swb = getWaveBean(wave);
        final String stageKey = swb.stageKey();

        if (this.stageMap.containsKey(stageKey) && this.stageMap.get(stageKey) != null) {
            // Show the stage
            this.stageMap.get(stageKey).show();

        } else {

            final Region rootPane = getRootPane(swb);
            final Scene scene = getScene(swb, rootPane);
            final Stage stage = getStage(swb, scene);

            // Show the stage
            this.stageMap.put(stageKey, stage);

            stage.show();
        }
    }

    /**
     * Gets the root pane.
     *
     * @param swb the swb
     * @return the root pane
     */
    private Region getRootPane(final StageWaveBean swb) {

        return swb.rootPane() == null
                ? StackPaneBuilder.create()
                                  .build()
                : swb.rootPane();
    }

    /**
     * Gets the scene.
     *
     * @param swb the swb
     * @param region the region
     * @return the scene
     */
    private Scene getScene(final StageWaveBean swb, final Region region) {
        Scene scene = swb.scene();
        if (scene == null) {
            scene = SceneBuilder.create()
                                .root(region)
                                .build();
        } else {
            scene.setRoot(region);
        }
        return scene;
    }

    /**
     * Gets the stage.
     *
     * @param swb the swb
     * @param scene the scene
     * @return the stage
     */
    private Stage getStage(final StageWaveBean swb, final Scene scene) {

        Stage stage = swb.stage();
        if (swb.stage() == null) {
            stage = StageBuilder.create()
                                .scene(scene)
                                .build();
        } else {
            stage.setScene(scene);
        }
        return stage;
    }

    /**
     * Close a stage. (Hide it)
     *
     * @param wave the source wave
     */
    public void doCloseStage(final Wave wave) {
        final String stageKey = getWaveBean(wave).stageKey();
        this.stageMap.get(stageKey).close();
    }

    /**
     * Destroy the stage and dereference it.
     *
     * @param wave the source wave
     */
    public void doDestroyStage(final Wave wave) {
        final String stageKey = getWaveBean(wave).stageKey();
        this.stageMap.get(stageKey).close();
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
        return (StageWaveBean) wave.waveBean();
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
