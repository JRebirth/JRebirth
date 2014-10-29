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
package org.jrebirth.af.core.command.basic.stage;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.wave.WaveBean;

/**
 * The class <strong>StageWaveBean</strong>.
 *
 * @author Sébastien Bordes
 */
public class StageWaveBean implements WaveBean {

    /** The action. */
    private StageAction action;

    /** The root model class attached to the stage. */
    private Class<? extends Model> rootModelClass;

    /** The root pane of the scene. */
    private Region rootPane;

    /** The first scen to display into the stage. */
    private Scene scene;

    /** The stage to manage. */
    private Stage stage;

    /** The unique identifier for this stage. */
    private String stageKey;

    public static StageWaveBean create() {
        return new StageWaveBean();
    }

    /**
     * Gets the action.
     *
     * @return the action
     */
    public StageAction action() {
        return this.action;
    }

    /**
     * Gets the root model class.
     *
     * @return the root model class
     */
    public Class<? extends Model> rootModelClass() {
        return this.rootModelClass;
    }

    /**
     * Gets the root pane.
     *
     * @return the root pane
     */
    public Region rootPane() {
        return this.rootPane;
    }

    /**
     * Gets the scene.
     *
     * @return the scene
     */
    public Scene scene() {
        return this.scene;
    }

    /**
     * Gets the stage.
     *
     * @return the stage
     */
    public Stage stage() {
        return this.stage;
    }

    /**
     * Gets the stage key.
     *
     * @return the stage key
     */
    public String stageKey() {
        return this.stageKey;
    }

    /**
     * Sets the action.
     *
     * @param action the new action
     */
    public StageWaveBean action(final StageAction action) {
        this.action = action;
        return this;
    }

    /**
     * Sets the root model class.
     *
     * @param rootModelClass the root model class
     */
    public StageWaveBean rootModelClass(final Class<? extends Model> rootModelClass) {
        this.rootModelClass = rootModelClass;
        return this;
    }

    /**
     * Sets the root pane.
     *
     * @param rootPane the new root pane
     */
    public StageWaveBean rootPane(final Region rootPane) {
        this.rootPane = rootPane;
        return this;
    }

    /**
     * Sets the scene.
     *
     * @param scene the new scene
     */
    public StageWaveBean scene(final Scene scene) {
        this.scene = scene;
        return this;
    }

    /**
     * Sets the stage.
     *
     * @param stage the new stage
     */
    public StageWaveBean stage(final Stage stage) {
        this.stage = stage;
        return this;
    }

    /**
     * Sets the stage key.
     *
     * @param stageKey the new stage key
     */
    public StageWaveBean stageKey(final String stageKey) {
        this.stageKey = stageKey;
        return this;
    }

}
