package org.jrebirth.presentation.ui.stack;

import java.util.List;

import org.jrebirth.core.link.Wave;
import org.jrebirth.core.ui.AbstractModel;
import org.jrebirth.core.ui.Model;
import org.jrebirth.presentation.model.Slide;
import org.jrebirth.presentation.service.PresentationService;
import org.jrebirth.presentation.ui.base.SlideModel;
import org.jrebirth.presentation.ui.base.SlideStep;

/**
 * The class <strong>StackModel</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public final class StackModel extends AbstractModel<StackModel, StackView> {

    /** The Current Slide Position. */
    private int slidePosition;

    /** The current slide selected. */
    private Slide selectedSlide;

    /** The current slide model selected. */
    private SlideModel<SlideStep> selectedSlideModel;

    /** Store a strong reference to the service to avoid reloading. */
    private PresentationService presentationService;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitialize() {
        // Load the Presentation content
        final List<Slide> slideList = getPresentationService().getPresentation().getSlides().getSlide();
        if (!slideList.isEmpty()) {
            this.slidePosition = 0;
            displaySlide(slideList.get(this.slidePosition));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeInnerModels() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processAction(final Wave wave) {
        // Nothing to do yet
    }

    /**
     * @return Returns the presentationService.
     */
    public PresentationService getPresentationService() {
        if (this.presentationService == null) {
            this.presentationService = getService(PresentationService.class);
        }
        return this.presentationService;
    }

    /**
     * Display a slide.
     * 
     * @param slide the slide to display
     */
    private void displaySlide(final Slide slide) {
        try {
            if (this.selectedSlide != null) {
                final Class<Model> selectedModelClass = (Class<Model>) Class.forName(this.selectedSlide.getModelClass());
                // Hide previous slide
                getView().getRootNode().getChildren().removeAll(getModel(selectedModelClass, this.selectedSlide).getRootNode());
            }

            final Class<Model> modelClass = (Class<Model>) Class.forName(slide.getModelClass());
            // Show current slide

            slide.setPage(this.slidePosition);
            this.selectedSlideModel = (SlideModel<SlideStep>) getModel(modelClass, slide);
            // this.selectedSlideModel.setSlideNumber(this.slidePosition);
            getView().getRootNode().getChildren().add(this.selectedSlideModel.getRootNode());

            // this.selectedSlideModel.getView().animate();

            this.selectedSlide = slide;
            // Define the current position
            // setSlidePosition(slide.getPage().intValue());

        } catch (final ClassNotFoundException e) {
            // TODO
            getLocalFacade().getGlobalFacade().getLogger();
        }

    }

    /**
     * @return Returns the slidePosition.
     */
    public int getSlidePosition() {
        return this.slidePosition;
    }

    /**
     * @param slidePosition The slidePosition to set.
     */
    public void setSlidePosition(final int slidePosition) {
        this.slidePosition = slidePosition;
    }

    /**
     * Got to next slide.
     */
    public void next() {
        if (this.selectedSlideModel.nextStep()) {
            this.slidePosition = Math.min(this.slidePosition + 1, getPresentationService().getPresentation().getSlides().getSlide().size() - 1);
            displaySlide(getPresentationService().getPresentation().getSlides().getSlide().get(this.slidePosition));
        }
    }

    /**
     * Go to previous slide.
     */
    public void previous() {
        if (this.selectedSlideModel.previousStep()) {
            this.slidePosition = Math.max(this.slidePosition - 1, 0);
            displaySlide(getPresentationService().getPresentation().getSlides().getSlide().get(this.slidePosition));
        }
    }

    /**
     * Display the slide menu to navigate.
     */
    public void showSlideMenu() {
        // Nothing to do yet
    }
}
