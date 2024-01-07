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
package org.jrebirth.af.showcase.undoredo.command;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.jrebirth.af.api.concurrent.RunInto;
import org.jrebirth.af.api.concurrent.RunType;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.WaveBean;
import org.jrebirth.af.showcase.undoredo.beans.ShapeType;
import org.jrebirth.af.showcase.undoredo.beans.UndoAppWaves;
import org.jrebirth.af.showcase.undoredo.ui.UndoModel;
import org.jrebirth.af.undoredo.command.DefaultUndoableCommand;

/**
 * The Class CreateShapeCommand is used to create a node and add it to the graphical editor.
 */
@RunInto(RunType.JAT)
public class CreateShapeCommand extends DefaultUndoableCommand<WaveBean> {

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
                this.createdNode = new Circle();
                ((Circle)this.createdNode).setFill(Color.BLUE);
                this.createdNode.setLayoutY(60);
                this.createdNode.setLayoutY(60);
                ((Circle)this.createdNode).setRadius(30);
                break;
            case Square:
                this.createdNode = new Rectangle();
                ((Rectangle) this.createdNode).setFill(Color.BLUEVIOLET);
                this.createdNode.setLayoutX(50);
                this.createdNode.setLayoutY(100);
                ((Rectangle) this.createdNode).setWidth(30);
                ((Rectangle) this.createdNode).setHeight(30);
                break;
            case Rectangle:
                this.createdNode = new Rectangle();
                ((Rectangle) this.createdNode).setFill(Color.AZURE);
                this.createdNode.setLayoutX(100);
                this.createdNode.setLayoutY(150);
                ((Rectangle) this.createdNode).setWidth(30);
                ((Rectangle) this.createdNode).setHeight(15);
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

}
