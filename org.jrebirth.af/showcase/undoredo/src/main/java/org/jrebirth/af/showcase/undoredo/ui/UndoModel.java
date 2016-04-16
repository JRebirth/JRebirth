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

import javafx.scene.Node;

import org.jrebirth.af.api.module.Register;
import org.jrebirth.af.api.ui.ModuleModel;
import org.jrebirth.af.api.wave.contract.WaveData;
import org.jrebirth.af.core.ui.DefaultModel;
import org.jrebirth.af.core.wave.WBuilder;
import org.jrebirth.af.undoredo.command.UndoRedoWaves;
import org.jrebirth.af.undoredo.service.UndoRedoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>UndoModel</strong>.
 *
 * The mai UI of the Undo/Redo showcase application.
 *
 * @author Sébastien Bordes
 */
@Register(value = ModuleModel.class)
public final class UndoModel extends DefaultModel<UndoModel, UndoView> implements ModuleModel {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(UndoModel.class);

    /** The Constant stackName. */
    static final WaveData<String> stackName = WBuilder.waveData(UndoRedoWaves.STACK_NAME, "main");

    /** The undo redo service. */
    private UndoRedoService undoRedoService;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {
        super.initModel();

        // Get a strong reference to service to avoid any garbage collection.
        // Be careful it can hold a lot of objects
        this.undoRedoService = getService(UndoRedoService.class, stackName.value());
    }

    /**
     * Adds the shape.
     *
     * @param createdNode the created node
     */
    public void addShape(final Node createdNode) {
        view().getEditor().getChildren().add(createdNode);
    }

    /**
     * Removes the shape.
     *
     * @param createdNode the created node
     */
    public void removeShape(final Node createdNode) {
        view().getEditor().getChildren().remove(createdNode);
    }

    @Override
    public String moduleName() {
        return "Undo Redo";
    }

}
