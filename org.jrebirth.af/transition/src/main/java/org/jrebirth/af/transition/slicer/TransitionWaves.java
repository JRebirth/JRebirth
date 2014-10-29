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
package org.jrebirth.af.transition.slicer;

import java.util.List;

import javafx.scene.Node;

import org.jrebirth.af.core.wave.WaveItemBase;
import org.jrebirth.af.transition.command.slicer.SliceItem;

/**
 * The class <strong>TransitionWaves</strong>.
 * 
 * @author Sébastien Bordes
 */
public interface TransitionWaves {

    /*************************************************************************/
    /** Wave Types **/
    /*************************************************************************/

    /*************************************************************************/
    /** Wave Items **/
    /*************************************************************************/

    /** The file containing all events serialized. */
    WaveItemBase<List<Node>> NODE = new WaveItemBase<List<Node>>() {
    };

    /** The list of slice item build from original node. */
    WaveItemBase<List<SliceItem>> SLICES = new WaveItemBase<List<SliceItem>>() {
    };

}
