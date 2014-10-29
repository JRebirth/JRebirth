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
package org.jrebirth.af.showcase.analyzer.ui.properties;

import org.jrebirth.af.api.facade.JRebirthEvent;
import org.jrebirth.af.core.wave.WaveItemBase;

/**
 * The class <strong>EditorWaves</strong>.
 *
 * @author Sébastien Bordes
 */
public interface PropertiesWaves {

    /** The event object. */
    WaveItemBase<JRebirthEvent> EVENT_OBJECT = new WaveItemBase<JRebirthEvent>() {
    };

    /** The type of the node. */
    WaveItemBase<JRebirthEvent> NODE_TYPE = new WaveItemBase<JRebirthEvent>() {
    };

}
