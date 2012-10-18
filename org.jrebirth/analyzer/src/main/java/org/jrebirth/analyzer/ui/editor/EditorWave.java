/**
 * Copyright JRebirth.org © 2011-2012 
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
package org.jrebirth.analyzer.ui.editor;

import org.jrebirth.core.util.ClassUtility;
import org.jrebirth.core.wave.WaveType;

/**
 * The class <strong>EditorWave</strong>.
 * 
 * @author Sébastien Bordes
 */
public enum EditorWave implements WaveType {

    /** Wave used when the events are loaded. */
    EVENTS_LOADED,

    /** . */
    UNLOAD,

    /** . */
    PLAY,

    /** . */
    NEXT,

    /** . */
    PREVIOUS,

    /** . */
    STOP,

    /** Wave used to display info into the properties view. */
    EVENT_SELECTED;

    /** The action to launch. */
    private String action;

    /**
     * Default Constructor.
     */
    private EditorWave() {
        this.action = ClassUtility.underscoreToCamelCase(name());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAction() {
        return this.action;
    }

}
