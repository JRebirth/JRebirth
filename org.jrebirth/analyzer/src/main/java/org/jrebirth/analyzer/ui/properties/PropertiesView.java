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

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.DefaultView;

/**
 * 
 * The class <strong>PropertiesView</strong>.
 * 
 * The view used to display properties of a selected node.
 * 
 * @author Sébastien Bordes
 */
public final class PropertiesView extends DefaultView<PropertiesModel, VBox, PropertiesController> {

    /** The Node name. */
    private Text nodeName;

    /**
     * Default Constructor.
     * 
     * @param model the controls view model
     * 
     * @throws CoreException if build fails
     */
    public PropertiesView(final PropertiesModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeComponents() {

        getRootNode().setMinWidth(200);

        final HBox hbox = HBoxBuilder.create().build();
        final Label label = new Label("Node Name :");
        this.nodeName = new Text();
        hbox.getChildren().addAll(label, this.nodeName);

        getRootNode().getChildren().add(hbox);
    }

    /**
     * @return Returns the nodeName.
     */
    Text getNodeName() {
        return this.nodeName;
    }
}
