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
package org.jrebirth.core.service.basic;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPaneBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

import org.jrebirth.core.command.basic.stage.StageWaveBean;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.service.ServiceBase;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveTypeBase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>StageService</strong>.
 * 
 * @author Sébastien Bordes
 */
public class StageService extends ServiceBase {

    /** Wave type use to load events. */
    public static final WaveTypeBase DO_OPEN_STAGE = WaveTypeBase.build("OPEN_STAGE");

    /** Wave type to return events loaded. */
    public static final WaveTypeBase RE_STAGE_OPENED = WaveTypeBase.build("STAGE_OPENED");

    /** Wave type use to load events. */
    public static final WaveTypeBase DO_CLOSE_STAGE = WaveTypeBase.build("CLOSE_STAGE");

    /** Wave type to return events loaded. */
    public static final WaveTypeBase RE_STAGE_CLOSED = WaveTypeBase.build("STAGE_CLOSED");

    /** Wave type use to load events. */
    public static final WaveTypeBase DO_DESTROY_STAGE = WaveTypeBase.build("DESTROY_STAGE");

    /** Wave type to return events loaded. */
    public static final WaveTypeBase RE_STAGE_DESTROYED = WaveTypeBase.build("STAGE_DESTROYED");

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(StageService.class);

    /** The map that stores all stages. */
    private final Map<String, Stage> stageMap = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void ready() throws CoreException {
        super.ready();

        registerCallback(DO_OPEN_STAGE, RE_STAGE_OPENED);
        registerCallback(DO_CLOSE_STAGE, RE_STAGE_CLOSED);
        registerCallback(DO_DESTROY_STAGE, RE_STAGE_DESTROYED);
    }

    /**
     * Open a stage.
     * 
     * @param wave the source wave
     */
    public void openStage(final Wave wave) {

        LOGGER.trace("Open a stage.");

        final StageWaveBean swb = getWaveBean(wave);
        final String stageKey = swb.getStageKey();

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

        return swb.getRootPane() == null
                ? StackPaneBuilder.create()
                        .build()
                : swb.getRootPane();
    }

    /**
     * Gets the scene.
     * 
     * @param swb the swb
     * @param region the region
     * @return the scene
     */
    private Scene getScene(final StageWaveBean swb, final Region region) {
        Scene scene = swb.getScene();
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

        Stage stage = swb.getStage();
        if (swb.getStage() == null) {
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
    public void closeStage(final Wave wave) {
        final String stageKey = getWaveBean(wave).getStageKey();
        this.stageMap.get(stageKey).close();
    }

    /**
     * Destroy the stage and dereference it.
     * 
     * @param wave the source wave
     */
    public void destroyStage(final Wave wave) {
        final String stageKey = getWaveBean(wave).getStageKey();
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
        return (StageWaveBean) wave.getWaveBean();
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
