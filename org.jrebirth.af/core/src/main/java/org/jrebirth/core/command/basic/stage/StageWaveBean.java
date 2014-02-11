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
package org.jrebirth.core.command.basic.stage;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import org.jrebirth.core.ui.Model;
import org.jrebirth.core.wave.WaveBean;

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

    /**
     * Gets the action.
     * 
     * @return the action
     */
    public StageAction getAction() {
        return this.action;
    }

    /**
     * Gets the root model class.
     * 
     * @return the root model class
     */
    public Class<? extends Model> getRootModelClass() {
        return this.rootModelClass;
    }

    /**
     * Gets the root pane.
     * 
     * @return the root pane
     */
    public Region getRootPane() {
        return this.rootPane;
    }

    /**
     * Gets the scene.
     * 
     * @return the scene
     */
    public Scene getScene() {
        return this.scene;
    }

    /**
     * Gets the stage.
     * 
     * @return the stage
     */
    public Stage getStage() {
        return this.stage;
    }

    /**
     * Gets the stage key.
     * 
     * @return the stage key
     */
    public String getStageKey() {
        return this.stageKey;
    }

    /**
     * Sets the action.
     * 
     * @param action the new action
     */
    public void setAction(final StageAction action) {
        this.action = action;
    }

    /**
     * Sets the root model class.
     * 
     * @param rootModelClass the root model class
     */
    public void setRootModelClass(final Class<? extends Model> rootModelClass) {
        this.rootModelClass = rootModelClass;
    }

    /**
     * Sets the root pane.
     * 
     * @param rootPane the new root pane
     */
    public void setRootPane(final Region rootPane) {
        this.rootPane = rootPane;
    }

    /**
     * Sets the scene.
     * 
     * @param scene the new scene
     */
    public void setScene(final Scene scene) {
        this.scene = scene;
    }

    /**
     * Sets the stage.
     * 
     * @param stage the new stage
     */
    public void setStage(final Stage stage) {
        this.stage = stage;
    }

    /**
     * Sets the stage key.
     * 
     * @param stageKey the new stage key
     */
    public void setStageKey(final String stageKey) {
        this.stageKey = stageKey;
    }

}
