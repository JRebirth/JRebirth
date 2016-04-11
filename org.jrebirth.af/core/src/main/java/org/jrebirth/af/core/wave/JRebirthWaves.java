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
package org.jrebirth.af.core.wave;

import static org.jrebirth.af.core.wave.Builders.waveType;

import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;

import org.jrebirth.af.api.annotation.Preload;
import org.jrebirth.af.api.command.Command;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.WaveBean;
import org.jrebirth.af.api.wave.WaveConstants;
import org.jrebirth.af.api.wave.contract.WaveData;
import org.jrebirth.af.api.wave.contract.WaveItem;
import org.jrebirth.af.api.wave.contract.WaveType;
import org.jrebirth.af.core.service.ServiceTaskBase;

/**
 * The class <strong>JRebirthWaveItem</strong>.
 *
 * @author Sébastien Bordes
 */
@Preload
public interface JRebirthWaves {

    /** The waveItem that hold the attached node. */
    WaveItemBase<ObjectProperty<Node>> ATTACH_UI_NODE_PLACEHOLDER = new WaveItemBase<ObjectProperty<Node>>() {
    };

    /** The waveItem that hold the children list of the parent node. */
    WaveItemBase<ObservableList<Node>> ADD_UI_CHILDREN_PLACEHOLDER = new WaveItemBase<ObservableList<Node>>() {
    };

    /** . */
    WaveItemBase<Class<? extends Command>> SHOW_MODEL_COMMAND = new WaveItemBase<Class<? extends Command>>() {
    };

    /** . */
    WaveItemBase<List<WaveBean>> EXTRA_WAVE_BEANS = new WaveItemBase<List<WaveBean>>() {
    };

    /** The waveItem that hold the list of wave to be executed back to back. */
    WaveItemBase<List<Wave>> CHAINED_WAVES = new WaveItemBase<List<Wave>>() {
    };

    /** The waveItem that indicates if command instance must be reused or if another must created. DEfault value is false. */
    WaveItemBase<Boolean> REUSE_COMMAND = new WaveItemBase<Boolean>() {
    };

    /** . */
    WaveData<Boolean> REUSE = Builders.waveData(REUSE_COMMAND, true);

    /******************************/
    /** WaveType related to Model */
    /******************************/

    /** The waveType used to show a view (start or reload). */
    WaveType SHOW_VIEW_WT = waveType(WaveConstants.SHOW_VIEW);

    /** The waveType used to hide a view. */
    WaveType HIDE_VIEW_WT = waveType(WaveConstants.HIDE_VIEW);

    /********************************/
    /** WaveItem related to Service */
    /********************************/

    /** This wave item will be used only into a WaveData to pass the current Service task handled by the wave. */
    WaveItem<ServiceTaskBase<?>> SERVICE_TASK = new WaveItemBase<ServiceTaskBase<?>>(false) {
    };

    /** This wave item will be used only into a WaveData to pass the right progress bar used by service task. */
    WaveItem<ProgressBar> PROGRESS_BAR = new WaveItemBase<ProgressBar>(false) {
    };

    /** This wave item will be used only into a WaveData to pass the right string property used to display the task title. */
    WaveItem<StringProperty> TASK_TITLE = new WaveItemBase<StringProperty>(false) {
    };

    /** This wave item will be used only into a WaveData to pass the right string property used to display the task message. */
    WaveItem<StringProperty> TASK_MESSAGE = new WaveItemBase<StringProperty>(false) {
    };

    /********************************/
    /** WaveType related to Service */
    /********************************/

    /** WaveType used to receive an empty wave when the return action returns void. */
    WaveType RETURN_VOID_WT = waveType(WaveConstants.RETURN_VOID).items(JRebirthItems.voidItem);

}
