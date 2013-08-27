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
package org.jrebirth.showcase.undoredo.command;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.CircleBuilder;
import javafx.scene.shape.RectangleBuilder;

import org.jrebirth.core.concurrent.RunInto;
import org.jrebirth.core.concurrent.RunType;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.showcase.undoredo.beans.ShapeType;
import org.jrebirth.showcase.undoredo.beans.UndoAppWaves;
import org.jrebirth.showcase.undoredo.ui.UndoModel;
import org.jrebirth.undoredo.command.AbstractUndoableCommand;

/**
 * The Class CreateShapeCommand is used to create a node and add it to the graphical editor.
 */
@RunInto(RunType.JAT)
public class CreateShapeCommand extends AbstractUndoableCommand {

    /** The shape type. */
    private ShapeType shapeType;

    /** The created node. */
    private Node createdNode;

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final Wave wave) {
        this.shapeType = wave.get(UndoAppWaves.SHAPE_TYPE);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void redo() {

        // Build a node according to the shape type
        switch (this.shapeType) {
            case Circle:
                this.createdNode = CircleBuilder.create().fill(Color.BLUE).layoutX(60).layoutY(60).radius(30).build();
                break;
            case Square:
                this.createdNode = RectangleBuilder.create().fill(Color.BLUEVIOLET).layoutX(50).layoutY(100).width(30).height(30).build();
                break;
            case Rectangle:
                this.createdNode = RectangleBuilder.create().fill(Color.AZURE).layoutX(100).layoutY(150).width(30).height(15).build();
                break;
        }

        // Add to editor
        getModel(UndoModel.class).addShape(this.createdNode);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void undo() {

        // Remove from editor
        getModel(UndoModel.class).removeShape(this.createdNode);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void ready() throws CoreException {
        // Nothing to do yet
    }

}
