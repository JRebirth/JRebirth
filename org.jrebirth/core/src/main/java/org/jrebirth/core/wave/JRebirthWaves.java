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
package org.jrebirth.core.wave;

import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;

/**
 * The class <strong>JRebirthWaveItem</strong>.
 * 
 * @author Sébastien Bordes
 */
public interface JRebirthWaves {

    // WaveItem<ObjectProperty<Node>> HHH = WaveItem.build(ObjectProperty.class, Node.class);

    /** The waveItem that hold the attached node. */
    WaveItem<ObjectProperty<Node>> ATTACH_UI_NODE_PLACEHOLDER = new WaveItem<ObjectProperty<Node>>() {
    };

    /** The waveItem that hold the children list of the parent node. */
    WaveItem<ObservableList<Node>> ADD_UI_CHILDREN_PLACEHOLDER = new WaveItem<ObservableList<Node>>() {
    };

    /** The waveItem that hold the list of wave to be executed back to back. */
    WaveItem<List<Wave>> CHAINED_WAVES = new WaveItem<List<Wave>>() {
    };

    /** The waveItem that indicates if command instance must be reused or if another must created. DEfault value is false. */
    WaveItem<Boolean> REUSE_COMMAND = new WaveItem<Boolean>() {
    };

    /** . */
    WaveData<Boolean> REUSE = WaveData.build(REUSE_COMMAND, true);

    /******************************/
    /** WaveType related to Model */
    /******************************/

    /** The waveType used to show a view (start or reload). */
    WaveType SHOW_VIEW = WaveTypeBase.build("SHOW_VIEW");

    /** The waveType used to hide a view. */
    WaveType HIDE_VIEW = WaveTypeBase.build("HIDE_VIEW");

    /******************************/
    /** WaveType related to Service */
    /******************************/

    /** . */
    WaveItem<ProgressBar> PROGRESS_BAR = new WaveItem<ProgressBar>() {
    };
}
