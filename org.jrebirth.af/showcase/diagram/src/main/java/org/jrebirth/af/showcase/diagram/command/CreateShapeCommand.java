package org.jrebirth.af.showcase.diagram.command;

import org.jrebirth.af.api.annotation.Link;
import org.jrebirth.af.api.concurrent.RunInto;
import org.jrebirth.af.api.concurrent.RunType;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.WaveBean;
import org.jrebirth.af.showcase.diagram.bean.SMWaves;
import org.jrebirth.af.showcase.diagram.bean.ShapeType;
import org.jrebirth.af.showcase.diagram.service.IncrementalNameService;
import org.jrebirth.af.showcase.diagram.ui.diagram.DiagramModel;
import org.jrebirth.af.showcase.diagram.ui.region.RegionModel;
import org.jrebirth.af.showcase.diagram.ui.state.StateModel;
import org.jrebirth.af.showcase.diagram.ui.statemachine.StateMachineModel;
import org.jrebirth.af.undoredo.command.DefaultUndoableCommand;

@RunInto(RunType.JAT)
public class CreateShapeCommand extends DefaultUndoableCommand<WaveBean> {



    private ShapeType shapeType;

    
    private Model createdModel;
    
    @Link
    private IncrementalNameService service;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final Wave wave) {
        this.shapeType = wave.get(SMWaves.SHAPE_TYPE);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void redo() {

        // Build a node according to the shape type
        switch (this.shapeType) {
            case StateMachine:
                this.createdModel = getModel(StateMachineModel.class, service.getIncrementedName("StateMachine"));
                break;
            case Region:
                this.createdModel = getModel(RegionModel.class, service.getIncrementedName("Region"));
                break;
            case State:
                this.createdModel = getModel(StateModel.class, service.getIncrementedName("State"));
                break;
        }

        // Add to editor
        getModel(DiagramModel.class).addShapeModel(this.createdModel);

    }

	/**
     * {@inheritDoc}
     */
    @Override
    public void undo() {

        // Remove from editor
        getModel(DiagramModel.class).removeShapeModel(this.createdModel);

    }

}
