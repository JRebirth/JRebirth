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
package org.jrebirth.analyzer.ui.properties;

import org.jrebirth.analyzer.ui.editor.EditorWave;
import org.jrebirth.core.event.Event;
import org.jrebirth.core.ui.AbstractModel;
import org.jrebirth.core.wave.Wave;

/**
 * The class <strong>PropertiesModel</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class PropertiesModel extends AbstractModel<PropertiesModel, PropertiesView> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitialize() {
        listen(EditorWave.EVENT_SELECTED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeInnerModels() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processAction(final Wave wave) {
        if (EditorWave.EVENT_SELECTED == wave.getWaveType()) {

            final Event event = (Event) wave.get(PropertiesWaveItem.EVENT_OBJECT).getValue();
            getView().getNodeName().setText(event.getTarget().getSimpleName());
        }
    }
}
