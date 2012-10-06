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
