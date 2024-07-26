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

/**
 * The class <strong>Stack</strong> used to link a Model using its @UniqueKey.
 *
 * Could be implemented by an @enum.
 * 
 * Each enumerated value shall return a UniqueKey
 *
 * @author Sébastien Bordes
 */
public interface StackItem {

    /**
     * Return the unique key used to attach a {@link Model}.
     *
     * @return the model key associated to this item
     */
    UniqueKey<? extends Model> getModelKey();

}
