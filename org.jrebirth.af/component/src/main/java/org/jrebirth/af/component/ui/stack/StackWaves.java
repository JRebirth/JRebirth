/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2024
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
package org.jrebirth.af.component.ui.stack;

import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.wave.contract.WaveType;
import org.jrebirth.af.core.wave.WBuilder;
import org.jrebirth.af.core.wave.WaveItemBase;

/**
 * The class <strong>StackWaves</strong>.
 *
 * All {@link WaveItemBase} and {@link WaveType} used to manage the stack model.
 *
 * @author Sébastien Bordes
 */
public interface StackWaves {

    /*****************************************************************************************************/
    /** _________________________________________Wave Items.____________________________________________ */
    /*****************************************************************************************************/

    /** The name of the stack concerned. */
    WaveItemBase<String> STACK_NAME = new WaveItemBase<String>() {
    };

    /** The page to display (model class descriptor). */
    WaveItemBase<UniqueKey<? extends Model>> STACK_ITEM_KEY = new WaveItemBase<UniqueKey<? extends Model>>() {
    };

    /** The page to display (enum descriptor). */
    WaveItemBase<StackItem> STACK_ITEM = new WaveItemBase<StackItem>() {
    };

    /*****************************************************************************************************/
    /** _________________________________________Wave Types.____________________________________________ */
    /*****************************************************************************************************/

    /** Show Stack element using Model action. */
    WaveType SHOW_STACK_MODEL = WBuilder.waveType("SHOW_STACK_MODEL").items(STACK_ITEM_KEY, STACK_NAME);

    /** Show Stack Item action. */
    WaveType SHOW_STACK_ITEM = WBuilder.waveType("SHOW_STACK_ITEM").items(STACK_ITEM);

}
