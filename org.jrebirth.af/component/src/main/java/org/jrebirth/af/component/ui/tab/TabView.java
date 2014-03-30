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
package org.jrebirth.af.component.ui.tab;

import javafx.scene.control.TabPane;

import org.jrebirth.af.core.exception.CoreException;
import org.jrebirth.af.core.log.JRLogger;
import org.jrebirth.af.core.log.JRLoggerFactory;
import org.jrebirth.af.core.ui.DefaultController;
import org.jrebirth.af.core.ui.DefaultView;
import org.jrebirth.af.core.ui.annotation.RootNodeId;

/**
 * The Class TabView only creates a TabPane like component that will handle Model Components.
 * 
 * No Controller is needed.
 * 
 * @author Sébastien Bordes
 */
@RootNodeId("DockPanel")
public class TabView extends DefaultView<TabModel, TabPane, DefaultController<TabModel, TabView>> {

    /** The Constant LOGGER. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(TabView.class);

    /**
     * Instantiates a new Dock View.
     * 
     * @param model the model
     * 
     * @throws CoreException the core exception
     */
    public TabView(final TabModel model) throws CoreException {
        super(model);
    }

}