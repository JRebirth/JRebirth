package org.jrebirth.presentation.ui.stack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.ParallelTransitionBuilder;
import javafx.event.ActionEvent;

import org.jrebirth.core.ui.AbstractModel;
import org.jrebirth.core.ui.Model;
import org.jrebirth.core.wave.Wave;
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

    private final Map<String, Double> animationRate = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitialize() {
        // Load the Presentation content
        final List<Slide> slideList = getPresentationService().getPresentation().getSlides().getSlide();
        if (!slideList.isEmpty()) {
            this.slidePosition = 0;
            displaySlide(slideList.get(this.slidePosition), false);
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
    @SuppressWarnings("unchecked")
    private void displaySlide(final Slide slide, final boolean isReverse) {
        try {

            SlideModel<SlideStep> previousSlideModel = null;

            if (this.selectedSlide != null) {
                final Class<Model> previousClass = (Class<Model>) Class.forName(this.selectedSlide.getModelClass());
                previousSlideModel = (SlideModel<SlideStep>) getModel(previousClass, this.selectedSlide);
            }

            // Define the slide number
            slide.setPage(this.slidePosition);

            final Class<Model> nextClass = (Class<Model>) Class.forName(slide.getModelClass());
            this.selectedSlideModel = (SlideModel<SlideStep>) getModel(nextClass, slide);

            // Add the new slide to the stack
            getView().getRootNode().getChildren().add(this.selectedSlideModel.getRootNode());

            final String animationKey = isReverse ? this.slidePosition + "_" + (this.slidePosition + 1) : this.slidePosition - 1 + "_" + this.slidePosition;

            // Play the animation<
            final ParallelTransition slideAnimation = getSlideTransition(this.slidePosition, isReverse, previousSlideModel, this.selectedSlideModel);

            if (isReverse) {
                slideAnimation.setRate(-1);
                slideAnimation.playFrom(slideAnimation.getCycleDuration());
            } else {
                slideAnimation.setRate(1);
                slideAnimation.playFromStart();
            }

            // Store the new default slide
            this.selectedSlide = slide;

            // Define the current position
            // setSlidePosition(slide.getPage().intValue());

        } catch (final ClassNotFoundException e) {
            getLocalFacade().getGlobalFacade().getLogger().error(e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * TODO To complete.
     * 
     * @param slidePosition2
     * @param isReverse
     * @return
     */
    private ParallelTransition getSlideTransition(final int slidePosition2, final boolean isReverse, final SlideModel<SlideStep> previousSlideModel, final SlideModel<SlideStep> selectedSlideModel) {
        final ParallelTransition slideAnimation = ParallelTransitionBuilder.create().build();

        if (previousSlideModel != null) {
            final Animation a = isReverse ? previousSlideModel.getShowAnimation() : previousSlideModel.getHideAnimation();
            if (a != null) {
                slideAnimation.getChildren().add(a);
            }
        }
        if (this.selectedSlideModel != null) {
            final Animation a = isReverse ? this.selectedSlideModel.getHideAnimation() : this.selectedSlideModel.getShowAnimation();
            if (a != null) {
                slideAnimation.getChildren().add(a);
            }
        }

        final SlideModel<SlideStep> psm = previousSlideModel;
        slideAnimation.setOnFinished(new javafx.event.EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent arg0) {
                // Hide previous slide
                if (psm != null) {
                    getView().getRootNode().getChildren().removeAll(psm.getRootNode());
                }
            }
        });

        return slideAnimation;
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
            displaySlide(getPresentationService().getPresentation().getSlides().getSlide().get(this.slidePosition), false);
        }
    }

    /**
     * Go to previous slide.
     */
    public void previous() {
        if (this.selectedSlideModel.previousStep()) {
            this.slidePosition = Math.max(this.slidePosition - 1, 0);
            displaySlide(getPresentationService().getPresentation().getSlides().getSlide().get(this.slidePosition), true);
        }
    }

    /**
     * Display the slide menu to navigate.
     */
    public void showSlideMenu() {
        // Nothing to do yet
    }
}
