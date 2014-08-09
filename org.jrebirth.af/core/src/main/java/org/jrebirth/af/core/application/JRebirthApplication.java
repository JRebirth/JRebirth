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
package org.jrebirth.af.core.application;

import java.util.List;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.jrebirth.af.core.ui.Model;
import org.jrebirth.af.core.wave.Wave;

/**
 * The interface <strong>JRebirthApplication</strong> define the contract to respect in order to launch correctly the underlying JRebirth Application Framework.
 *
 * @author Sébastien Bordes
 *
 * @param <P> the type of the root node of the scene
 */
public interface JRebirthApplication<P extends Pane> {

    /**
     * Return the main stage of this application.
     *
     * @return Returns the stage.
     */
    Stage getStage();

    /**
     * Return the scene of the main stage of this application.
     *
     * @return Returns the scene.
     */
    Scene getScene();

    /**
     * Return the root node of the main scene of this application.
     *
     * @return Returns the rootNode.
     */
    P getRootNode();

    /**
     * This method must be implemented to declare which JRebirth UI Model will be displayed first.
     *
     * @return the class of the first UI Model to launch it must extend the {@link Model}
     */
    Class<? extends Model> getFirstModelClass();

    /**
     * Return the Pre-Boot Waves called after displaying the first view.
     *
     * @return the list of wave to be run before the boot
     */
    List<Wave> getPreBootWaveList();

    /**
     * Return the Post-Boot Waves called after displaying the first view.
     *
     * @return the list of wave to be run after the boot
     */
    List<Wave> getPostBootWaveList();

    /**
     * Preload JRebirth wrapped fonts to allow them to be used directly by CSS.
     */
    void preloadFonts();

}
