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
package org.jrebirth.af.component.ui.workbench;

import javafx.scene.layout.Pane;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.log.JRLogger;
import org.jrebirth.af.api.ui.annotation.RootNodeClass;
import org.jrebirth.af.core.log.JRLoggerFactory;
import org.jrebirth.af.core.ui.DefaultController;
import org.jrebirth.af.core.ui.DefaultView;

/**
 * The Class WorkbenchView .
 *
 * No Controller is needed.
 *
 * @author Sébastien Bordes
 */
@RootNodeClass("Workbench")
public class WorkbenchView extends DefaultView<WorkbenchModel, Pane, DefaultController<WorkbenchModel, WorkbenchView>> {

    /** The Constant LOGGER. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(WorkbenchView.class);

    /**
     * Instantiates a new Workbench View.
     *
     * @param model the workbench model
     *
     * @throws CoreException the core exception
     */
    public WorkbenchView(final WorkbenchModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {

        // Region layoutNode = LayoutFactory.build(getModel().getLayout());

        // getRootNode().add(layoutNode);

    }

}
