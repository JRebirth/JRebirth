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
package org.jrebirth.af.component.ui.dock;

import java.util.List;

import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.log.JRLogger;
import org.jrebirth.af.api.ui.annotation.RootNodeClass;
import org.jrebirth.af.component.ui.beans.TabConfig;
import org.jrebirth.af.component.ui.tab.TabModel;
import org.jrebirth.af.core.log.JRLoggerFactory;
import org.jrebirth.af.core.ui.DefaultView;

/**
 * The Class DockView only creates a TabPane like component that will handle Model Components.
 *
 * No Controller is needed.
 *
 * @author Sébastien Bordes
 */
@RootNodeClass("DockPanel")
public class DockView extends DefaultView<DockModel, SplitPane, DockController> {

    /** The Constant LOGGER. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(DockView.class);

    /**
     * Instantiates a new Dock View.
     *
     * @param model the model
     *
     * @throws CoreException the core exception
     */
    public DockView(final DockModel model) throws CoreException {
        super(model);
    }

    @Override
    protected void initView() {
        super.initView();

        switch (model().object().orientation()) {
            case horizontal:
                node().setOrientation(Orientation.HORIZONTAL);
                break;
            case vertical:
                node().setOrientation(Orientation.VERTICAL);
                break;
        }

    }

    void removeContainer(final List<TabConfig> removed) {

        for (final TabConfig tabConfig : removed) {
            final TabModel model = model().getModel(TabModel.class, tabConfig);

            node().getItems().remove(model.node());
        }
    }

    void addContainer(final int from, final TabConfig tabConfig) {

        final TabModel model = model().getModel(TabModel.class, tabConfig);

        node().getItems().add(model.node());
    }

}
