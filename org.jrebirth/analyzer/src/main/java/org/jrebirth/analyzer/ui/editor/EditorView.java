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
package org.jrebirth.analyzer.ui.editor;

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
public final class EditorView extends DefaultView<EditorModel, Pane, EditorController> {

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

        getRootNode().setPrefSize(600, 500);

        getRootNode().getStyleClass().add("editor");

        final Circle c = new Circle(30, Color.BEIGE);
        c.setCenterX(100);
        c.setCenterY(10);

    }

}
