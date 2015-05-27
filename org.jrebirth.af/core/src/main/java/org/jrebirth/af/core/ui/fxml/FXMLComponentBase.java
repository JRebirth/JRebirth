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
package org.jrebirth.af.core.ui.fxml;

import java.util.Set;

import javafx.scene.Node;

import org.jrebirth.af.api.ui.fxml.FXMLComponent;
import org.jrebirth.af.api.ui.fxml.FXMLController;

/**
 * The class <strong>FXMLComponent</strong> that wraps the fxml root node and its controller.
 *
 * @author Sébastien Bordes
 */
public class FXMLComponentBase implements FXMLComponent {

    /** The FXML node. */
    private final Node node;

    /**
     * The controller of the FXML component.<br />
     * Be careful the controller could be null.
     */
    private final FXMLController controller;

    /**
     * Default Constructor.
     *
     * @param node the loaded fxml node, must not be null
     * @param controller the attached fxml controller (could be null)
     */
    public FXMLComponentBase(final Node node, final FXMLController controller) {
        this.node = node;
        this.controller = controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Node getNode() {
        return this.node;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FXMLController getController() {
        return this.controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Node lookup(String selector) {
        return getNode().lookup(selector);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Node> lookupAll(String selector) {
        return getNode().lookupAll(selector);
    }

}
