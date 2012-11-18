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

/**
 * The class <strong>JRebirthWaveItem</strong>.
 * 
 * @author Sébastien Bordes
 */
public interface JRebirthWaves {

    /** The waveItem that hold the attached node. */
    WaveItem<ObjectProperty<Node>> ATTACH_UI_NODE_PLACEHOLDER = WaveItem.build();

    /** The waveItem that hold the children lsit of the parent node. */
    WaveItem<ObservableList<Node>> ADD_UI_CHILDREN_PLACEHOLDER = WaveItem.build();

    /** The waveItem that hold the list of wave to be executed back to back. */
    WaveItem<List<Wave>> CHAINED_WAVES = WaveItem.build();

}
