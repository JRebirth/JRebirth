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
package ui;

import org.jrebirth.core.ui.DefaultModel;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.transition.command.slicer.NodeSlicerCommand;
import org.jrebirth.transition.slicer.RandomFadingService;
import org.jrebirth.transition.slicer.SlidingDoorService;

/**
 * The class <strong>TransitionModel</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public final class TransitionModel extends DefaultModel<TransitionModel, TransitionView> {

    private NodeSlicerCommand imageSlicerService;

    private RandomFadingService randomFadingService;

    private SlidingDoorService slidingDoorService;

    /**
     * @return Returns the imageSlicerService.
     */
    public NodeSlicerCommand getImageSlicerService() {
        return this.imageSlicerService;
    }

    /**
     * @return Returns the randomFadingService.
     */
    public RandomFadingService getRandomFadingService() {
        return this.randomFadingService;
    }

    /**
     * @return Returns the slidingDoorService.
     */
    public SlidingDoorService getSlidingDoorService() {
        return this.slidingDoorService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitialize() {
        // Load the Presentation content
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeInnerModels() {

        this.imageSlicerService = getService(NodeSlicerCommand.class, "Properties");

        this.imageSlicerService.setImage(getView().loadImage("Properties.png"));
        this.imageSlicerService.setTileHeight(600);
        this.imageSlicerService.setTileWidth(4);

        this.imageSlicerService.sliceNode(null, null);

        this.randomFadingService = getService(RandomFadingService.class, "Properties");
        this.randomFadingService.setNodes(this.imageSlicerService.getSlices());
        this.randomFadingService.doIt();

        this.slidingDoorService = getService(SlidingDoorService.class, "Properties");
        this.slidingDoorService.setNodeDelay(4);
        this.slidingDoorService.setNodes(this.imageSlicerService.getSlices());
        this.slidingDoorService.doIt();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processAction(final Wave wave) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customShowView() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customHideView() {
        // Nothing to do yet
    }
}
