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
package org.jrebirth.af.showcase.analyzer.ui.workbench;

import org.jrebirth.af.api.annotation.Link;
import org.jrebirth.af.api.component.basic.InnerComponent;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.ui.DefaultModel;
import org.jrebirth.af.showcase.analyzer.ui.controls.ControlsModel;
import org.jrebirth.af.showcase.analyzer.ui.editor.EditorModel;
import org.jrebirth.af.showcase.analyzer.ui.properties.PropertiesModel;

/**
 * The class <strong>WorkbenchModel</strong>.
 *
 * @author Sébastien Bordes
 */
public final class WorkbenchModel extends DefaultModel<WorkbenchModel, WorkbenchView> {

    @Link
    private InnerComponent<ControlsModel> controlsModel;

    @Link
    private InnerComponent<PropertiesModel> propertiesModel;

    @Link
    private InnerComponent<EditorModel> editorModel;

    // @formatter:off
//    enum Components implements InnerComponentEnum {
//        CONTROLS {{ set(ControlsModel.class); }},
//        PROPERTIES {{ set(PropertiesModel.class); }},
//        EDITOR {{ set(EditorModel.class); }}
//    }
    // @formatter:on

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc} /
     * 
     * @Override protected void initInnerComponents() {
     * 
     *           // Do stuff on the model ! addInnerComponent(CONTROLS); addInnerComponent(PROPERTIES);
     * 
     *           }
     */

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processWave(final Wave wave) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void showView() {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void hideView() {
        // Nothing to do yet

    }

    /**
     * @return Returns the controlsModel.
     */
    InnerComponent<ControlsModel> controlsModel() {
        return controlsModel;
    }

    /**
     * @return Returns the propertiesModel.
     */
    InnerComponent<PropertiesModel> propertiesModel() {
        return propertiesModel;
    }

    /**
     * @return Returns the editorModel.
     */
    InnerComponent<EditorModel> editorModel() {
        return editorModel;
    }

}
