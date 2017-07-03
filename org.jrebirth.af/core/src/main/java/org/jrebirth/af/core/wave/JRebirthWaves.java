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

import static org.jrebirth.af.core.wave.WBuilder.waveType;

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
import org.jrebirth.af.core.command.basic.showmodel.ShowModelCommand;
import org.jrebirth.af.core.service.ServiceTaskBase;

/**
 * The class <strong>JRebirthWaves</strong> stores some predefined {@link WaveItem} and {@link WaveType}.
 *
 * This class will be preloaded to allow registration of {@link WaveType} into {@link WaveTypeRegistry}.
 *
 * @author Sébastien Bordes
 */
@Preload
public interface JRebirthWaves {

    /********************************/
    /** WaveItem related to UI */
    /********************************/

    /** The waveItem that hold the attached node. */
    WaveItem<ObjectProperty<Node>> ATTACH_UI_NODE_PLACEHOLDER = new WaveItemBase<ObjectProperty<Node>>() {
    };

    /** The waveItem that hold the children list of the parent node. */
    WaveItem<ObservableList<Node>> ADD_UI_CHILDREN_PLACEHOLDER = new WaveItemBase<ObservableList<Node>>() {
    };

    /** This item allow to use another Command to attach a model into another one, it replaces the default {@link ShowModelCommand}. */
    WaveItem<Class<? extends Command>> SHOW_MODEL_COMMAND = new WaveItemBase<Class<? extends Command>>() {
    };

    /** This item allow to define Key Parts. */
    WaveItem<List<?>> KEY_PARTS = new WaveItemBase<List<?>>() {
    };

    /** This item is used to store several {@link WaveBean} into a wave. */
    WaveItem<List<WaveBean>> EXTRA_WAVE_BEANS = new WaveItemBase<List<WaveBean>>() {
    };

    /********************************/
    /** WaveItem related to Command */
    /********************************/

    /** The waveItem that hold the list of wave to be executed back to back. */
    WaveItem<List<Wave>> CHAINED_WAVES = new WaveItemBase<List<Wave>>() {
    };

    /** The waveItem that indicates if command instance must be reused or if another must created. Default value is false. */
    WaveItem<Boolean> REUSE_COMMAND = new WaveItemBase<Boolean>() {
    };

    /** The waveItem that indicates if command instance must be run synchronously. Default value is false. */
    WaveItem<Boolean> FORCE_SYNC_COMMAND = new WaveItemBase<Boolean>() {
    };

    /********************************/
    /** WaveData related to Command */
    /********************************/

    /** This WaveData allows to reuse a command instead of creating another one using an unique key timestamp. */
    WaveData<Boolean> REUSE = WBuilder.waveData(REUSE_COMMAND, true);

    /** This WaveData allows to force the exceution of a command synchronously into the required thread. */
    WaveData<Boolean> FORCE_SYNC = WBuilder.waveData(FORCE_SYNC_COMMAND, true);

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
