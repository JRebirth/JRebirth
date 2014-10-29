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

import javafx.scene.layout.BorderPane;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.core.ui.DefaultView;


/**
 *
 * The class <strong>WorkbenchView</strong>.
 *
 * The main view of the JRebirth Analyzer application.
 *
 * @author Sébastien Bordes
 */
public final class WorkbenchView extends DefaultView<WorkbenchModel, BorderPane, WorkbenchController> {

    /**
     * Default Constructor.
     *
     * @param model the view model
     *
     * @throws CoreException if build fails
     */
    public WorkbenchView(final WorkbenchModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {

        getRootNode().setPrefSize(800, 600);

        // Attach the controls view to the top place of the root border pane
        getRootNode().setTop(getModel().getInnerComponent(WorkbenchModel.CONTROLS).getRootNode());

        // Attach the properties view to the right place of the root border pane
        getRootNode().setRight(getModel().getInnerComponent(WorkbenchModel.PROPERTIES).getRootNode());

        // Attach the properties view to the center place of the root border
        // pane
        getRootNode().setCenter(getModel().getInnerComponent(WorkbenchModel.EDITOR).getRootNode());
    }

}
