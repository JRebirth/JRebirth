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

import javafx.scene.input.MouseEvent;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.core.ui.DefaultController;
import org.jrebirth.af.core.wave.WBuilder;
import org.jrebirth.af.showcase.diagram.bean.SMWaves;
import org.jrebirth.af.showcase.diagram.bean.ShapeType;
import org.jrebirth.af.showcase.diagram.command.CreateShapeCommand;
import org.jrebirth.af.undoredo.command.RedoCommand;
import org.jrebirth.af.undoredo.command.UndoCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>UndoController</strong>.
 *
 * @author Sébastien Bordes
 */
public final class DiagramController extends DefaultController<DiagramModel, DiagramView> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DiagramController.class);

    /**
     * Default Constructor.
     *
     * @param view the view to control
     *
     * @throws CoreException if an error occurred while creating event handlers
     */
    public DiagramController(final DiagramView view) throws CoreException {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initEventAdapters() throws CoreException {

        // Manage Ui Command Button
        linkCommand(view().getUndoButton(), MouseEvent.MOUSE_CLICKED, UndoCommand.class, DiagramModel.stackName);
        linkCommand(view().getRedoButton(), MouseEvent.MOUSE_CLICKED, RedoCommand.class, DiagramModel.stackName);

        linkCommand(view().getAddSMButton(), MouseEvent.MOUSE_CLICKED, CreateShapeCommand.class, WBuilder.waveData(SMWaves.SHAPE_TYPE, ShapeType.StateMachine), DiagramModel.stackName);
        linkCommand(view().getAddRegionButton(), MouseEvent.MOUSE_CLICKED, CreateShapeCommand.class, WBuilder.waveData(SMWaves.SHAPE_TYPE, ShapeType.Region), DiagramModel.stackName);
        linkCommand(view().getAddStateButton(), MouseEvent.MOUSE_CLICKED, CreateShapeCommand.class, WBuilder.waveData(SMWaves.SHAPE_TYPE, ShapeType.State), DiagramModel.stackName);

    }

}
