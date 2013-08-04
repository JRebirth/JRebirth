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

@RunInto(RunType.JAT)
public class CreateShapeCommand extends AbstractUndoableCommand {

    private ShapeType shapeType;

    private Node createdNode;

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(Wave wave) {
        shapeType = wave.get(UndoAppWaves.shapeType);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void redo() {

        switch (shapeType) {
            case Circle:
                createdNode = CircleBuilder.create().fill(Color.BLUE).layoutX(60).layoutY(60).radius(30).build();
                break;
            case Square:
                createdNode = RectangleBuilder.create().fill(Color.BLUEVIOLET).layoutX(50).layoutY(100).width(30).height(30).build();
                break;
            case Rectangle:
                createdNode = RectangleBuilder.create().fill(Color.AZURE).layoutX(100).layoutY(150).width(30).height(15).build();
                break;
        }

        getModel(UndoModel.class).addShape(createdNode);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void undo() {

        getModel(UndoModel.class).removeShape(createdNode);

    }

    @Override
    public void ready() throws CoreException {
        // Nothing to do yet

    }

}
