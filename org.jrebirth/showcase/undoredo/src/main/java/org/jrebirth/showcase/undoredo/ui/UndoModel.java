package org.jrebirth.showcase.undoredo.ui;

import javafx.scene.Node;

import org.jrebirth.core.ui.DefaultModel;
import org.jrebirth.core.wave.WaveData;
import org.jrebirth.undo.command.UndoRedoWaves;
import org.jrebirth.undo.service.UndoRedoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>SampleModel</strong>.
 * 
 * @author
 */
public final class UndoModel extends DefaultModel<UndoModel, UndoView> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(UndoModel.class);

    static final WaveData<String> stackName = WaveData.build(UndoRedoWaves.stackName, "main");

    private UndoRedoService undoRedoService;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {
        super.initModel();

        // Get a strong reference to service to avoid any garbage collection.
        // Be careful it can hold a lot of objects
        undoRedoService = getService(UndoRedoService.class, stackName.getValue());
    }

    public void addShape(Node createdNode) {
        getView().getEditor().getChildren().add(createdNode);
    }

    public void removeShape(Node createdNode) {
        getView().getEditor().getChildren().remove(createdNode);
    }

}
