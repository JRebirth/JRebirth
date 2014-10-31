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
package org.jrebirth.af.showcase.analyzer.ui.editor;

import java.io.File;
import java.util.List;

import org.jrebirth.af.api.facade.JRebirthEvent;
import org.jrebirth.af.api.wave.WaveType;
import org.jrebirth.af.core.wave.Builders;
import org.jrebirth.af.core.wave.WaveItemBase;

/**
 * The class <strong>EditorWaves</strong>.
 *
 * @author Sébastien Bordes
 */
public interface EditorWaves {

    /*************************************************************************/
    /** Wave Types **/
    /*************************************************************************/

    /** Trigger a Unload wave. */
    WaveType DO_UNLOAD = Builders.waveType("UNLOAD");

    /** Trigger a Play wave. */
    WaveType DO_PLAY = Builders.waveType("PLAY");

    /** Trigger a Next wave. */
    WaveType DO_NEXT = Builders.waveType("NEXT");

    /** Trigger a Previous wave. */
    WaveType DO_PREVIOUS = Builders.waveType("PREVIOUS");

    /** Trigger a Stop wave. */
    WaveType DO_STOP = Builders.waveType("STOP");

    /** The WaveType key usabe by OnWave annotation. */
    String DO_SELECT_EVENT_ACTION = "EVENT_SELECTED";

    /** Wave used to display info into the properties view. */
    WaveType DO_SELECT_EVENT = Builders.waveType(DO_SELECT_EVENT_ACTION);

    /** Wave type used to return the event currently processed. */
    WaveType RE_EVENT_PROCESSED = Builders.waveType("EVENT_PROCESSED");

    /*************************************************************************/
    /** Wave Items **/
    /*************************************************************************/

    /** The file containing all events serialized. */
    WaveItemBase<File> EVENTS_FILE = new WaveItemBase<File>() {
    };

    /** The name of the events. */
    WaveItemBase<List<JRebirthEvent>> EVENTS = new WaveItemBase<List<JRebirthEvent>>() {
    };

    /** An event unserialized. */
    WaveItemBase<JRebirthEvent> EVENT = new WaveItemBase<JRebirthEvent>() {
    };

}
