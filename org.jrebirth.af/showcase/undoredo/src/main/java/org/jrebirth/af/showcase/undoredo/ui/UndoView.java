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
package org.jrebirth.af.showcase.undoredo.ui;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPaneBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.layout.PaneBuilder;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.core.ui.AbstractView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>UndoView</strong>. hold a graphical editor and a toolbar with undo/redo buttons.
 * 
 * @author Sébastien Bordes
 */
public final class UndoView extends AbstractView<UndoModel, BorderPane, UndoController> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(UndoView.class);

    /** The undo button. */
    private Button undoButton;

    /** The redo button. */
    private Button redoButton;

    /** The add circle button. */
    private Button addCircleButton;

    /** The add square button. */
    private Button addSquareButton;

    /** The add rectangle button. */
    private Button addRectangleButton;

    /** The editor. */
    private Pane editor;

    /**
     * Default Constructor.
     * 
     * @param model the controls view model
     * 
     * @throws CoreException if build fails
     */
    public UndoView(final UndoModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {

        this.undoButton = new Button("Undo");
        this.redoButton = new Button("Redo");

        this.addCircleButton = new Button("Add Circle");
        this.addSquareButton = new Button("Add Square");
        this.addRectangleButton = new Button("Add Rectangle");

        getRootNode().setTop(FlowPaneBuilder.create()
                .children(this.undoButton, this.redoButton, this.addCircleButton, this.addSquareButton, this.addRectangleButton)
                .build());

        this.editor = PaneBuilder.create().style("-fx-background-color:beige").build();

        getRootNode().setCenter(this.editor);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        LOGGER.debug("Start the Sample View");
        // Custom code to process when the view is displayed the first time
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reload() {
        LOGGER.debug("Reload the Sample View");
        // Custom code to process when the view is displayed the 1+n time
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {
        LOGGER.debug("Hide the Sample View");
        // Custom code to process when the view is hidden
    }

    /**
     * Gets the undo button.
     * 
     * @return Returns the undoButton.
     */
    Button getUndoButton() {
        return this.undoButton;
    }

    /**
     * Gets the redo button.
     * 
     * @return Returns the redoButton.
     */
    Button getRedoButton() {
        return this.redoButton;
    }

    /**
     * Gets the editor.
     * 
     * @return Returns the editor.
     */
    Pane getEditor() {
        return this.editor;
    }

    /**
     * Gets the adds the circle button.
     * 
     * @return Returns the addCircleButton.
     */
    Button getAddCircleButton() {
        return this.addCircleButton;
    }

    /**
     * Gets the adds the square button.
     * 
     * @return Returns the addSquareButton.
     */
    Button getAddSquareButton() {
        return this.addSquareButton;
    }

    /**
     * Gets the adds the rectangle button.
     * 
     * @return Returns the addRectangleButton.
     */
    Button getAddRectangleButton() {
        return this.addRectangleButton;
    }

}