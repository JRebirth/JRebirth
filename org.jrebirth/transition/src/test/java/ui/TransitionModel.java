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

import org.jrebirth.core.ui.AbstractModel;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.transition.slicer.ImageSlicerService;
import org.jrebirth.transition.slicer.SlidingDoorService;

/**
 * The class <strong>TransitionModel</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public final class TransitionModel extends AbstractModel<TransitionModel, TransitionView> {

    private SlidingDoorService service2;

    /**
     * @return Returns the service2.
     */
    public SlidingDoorService getService2() {
        return this.service2;
    }

    /**
     * @return Returns the service.
     */
    public ImageSlicerService getService() {
        return this.service;
    }

    private ImageSlicerService service;

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
        // Nothing to do yet

        this.service = getService(ImageSlicerService.class, "Properties");

        this.service.setImage(getView().loadImage("Properties.png"));
        this.service.setTileHeight(600);
        this.service.setTileWidth(10);

        this.service.doIt();

        // service2 = getService(RandomFadingService.class, "Properties");
        this.service2 = getService(SlidingDoorService.class, "Properties");
        this.service2.setNodes(this.service.getSlices());
        this.service2.doIt();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processAction(final Wave wave) {
        // Nothing to do yet
    }
}
