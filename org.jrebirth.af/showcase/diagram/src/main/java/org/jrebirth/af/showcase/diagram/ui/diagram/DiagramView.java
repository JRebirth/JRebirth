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
package org.jrebirth.af.showcase.diagram.ui.diagram;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.core.ui.AbstractView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>UndoView</strong>. hold a graphical editor and a toolbar with undo/redo buttons.
 *
 * @author Sébastien Bordes
 */
public final class DiagramView extends AbstractView<DiagramModel, BorderPane, DiagramController> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DiagramView.class);

    /** The undo button. */
    private Button undoButton;

    /** The redo button. */
    private Button redoButton;

    /** The add circle button. */
    private Button addSMButton;

    /** The add square button. */
    private Button addRegionButton;

    /** The add rectangle button. */
    private Button addStateButton;

    /** The editor. */
    private Pane editor;

    /**
     * Default Constructor.
     *
     * @param model the controls view model
     *
     * @throws CoreException if build fails
     */
    public DiagramView(final DiagramModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {

        this.undoButton = new Button("Undo");
        this.redoButton = new Button("Redo");

        this.addSMButton = new Button("Add StateMachine");
        this.addRegionButton = new Button("Add Region");
        this.addStateButton = new Button("Add State");

        FlowPane fp = new FlowPane();
        fp.getChildren().addAll(this.undoButton, this.redoButton, this.addSMButton, this.addRegionButton, this.addStateButton);
        node().setTop(fp);

        this.editor = new Pane();
        this.editor.setStyle("-fx-background-color:beige");

        node().setCenter(this.editor);

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

    protected Button getUndoButton() {
        return this.undoButton;
    }

    protected Button getRedoButton() {
        return this.redoButton;
    }

    Pane getEditor() {
        return this.editor;
    }

    protected Button getAddSMButton() {
        return this.addSMButton;
    }

    protected Button getAddRegionButton() {
        return this.addRegionButton;
    }

    protected Button getAddStateButton() {
        return this.addStateButton;
    }

    @Override
    protected void bootView() {
        // Nothing to do yet

    }

}
