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
import javafx.scene.Node;
import javafx.scene.control.SplitPane;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.log.JRLogger;
import org.jrebirth.af.api.ui.annotation.RootNodeClass;
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
public class DockPaneView extends DefaultView<DockPaneModel, SplitPane, DockPaneController> {

    /** The Constant LOGGER. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(DockPaneView.class);

    /**
     * Instantiates a new Dock View.
     *
     * @param model the model
     *
     * @throws CoreException the core exception
     */
    public DockPaneView(final DockPaneModel model) throws CoreException {
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

    void removeItem(final List<Node> removed) {

        for (final Node node : removed) {
            node().getItems().remove(node);
        }

    }

    void addItem(final int from, final Node node) {
        if (!node().getItems().contains(node)) {
            node().getItems().add(node);
        }

    }

}
