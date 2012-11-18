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
package org.jrebirth.analyzer.ui.editor;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.DefaultView;

/**
 * 
 * The class <strong>EditorView</strong>.
 * 
 * The view used to display nodes.
 * 
 * @author Sébastien Bordes
 */
public final class EditorView extends DefaultView<EditorModel, ScrollPane, EditorController> {

    /** The editor panel that is scrollable. */
    private Pane panel;

    /**
     * Default Constructor.
     * 
     * @param model the controls view model
     * 
     * @throws CoreException if build fails
     */
    public EditorView(final EditorModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeComponents() {

        // getRootNode().setFitToWidth(false);
        // getRootNode().setFitToHeight(false);

        this.panel = new Pane();
        this.panel.setPrefSize(600, 500);

        this.panel.getStyleClass().add("editor");

        final Circle c = new Circle(200 + 25, Color.BEIGE);
        c.centerXProperty().bind(getRootNode().widthProperty().divide(2)/* .add(70) */);
        c.centerYProperty().bind(getRootNode().heightProperty().divide(2));

        this.panel.getChildren().add(c);

        getRootNode().setContent(this.panel);

        // this.panel.scaleXProperty().bind(this.panel.prefWidthProperty().divide(getRootNode().widthProperty()));
        // this.panel.scaleYProperty().bind(this.panel.prefHeightProperty().divide(getRootNode().heightProperty()));
    }

    /**
     * @return Returns the panel.
     */
    public Pane getPanel() {
        return this.panel;
    }
}
