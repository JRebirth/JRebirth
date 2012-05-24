package ui;

import org.jrebirth.core.link.Wave;
import org.jrebirth.core.ui.AbstractModel;
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
        return service2;
    }

    /**
     * @return Returns the service.
     */
    public ImageSlicerService getService() {
        return service;
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

        service = getService(ImageSlicerService.class, "Properties");

        service.setImage(getView().loadImage("Properties.png"));
        service.setTileHeight(600);
        service.setTileWidth(10);

        service.doIt();

        // service2 = getService(RandomFadingService.class, "Properties");
        service2 = getService(SlidingDoorService.class, "Properties");
        service2.setNodes(service.getSlices());
        service2.doIt();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processAction(final Wave wave) {
        // Nothing to do yet
    }
}
