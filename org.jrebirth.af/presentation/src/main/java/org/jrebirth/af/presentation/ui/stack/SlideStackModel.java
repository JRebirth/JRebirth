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
package org.jrebirth.af.presentation.ui.stack;

import java.util.List;

import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.ParallelTransitionBuilder;
import javafx.event.ActionEvent;
import javafx.scene.Node;

import org.jrebirth.af.core.ui.DefaultModel;
import org.jrebirth.af.core.ui.Model;
import org.jrebirth.af.core.wave.Wave;
import org.jrebirth.af.presentation.service.PresentationService;
import org.jrebirth.af.presentation.ui.base.SlideModel;
import org.jrebirth.af.presentation.ui.base.SlideStep;
import org.jrebirth.presentation.model.Slide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>SlideStackModel</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public final class SlideStackModel extends DefaultModel<SlideStackModel, SlideStackView> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SlideStackModel.class);

    /** The Current Slide Position. */
    private int slidePosition;

    /** The current slide selected. */
    private Slide selectedSlide;

    /** The current slide model selected. */
    private SlideModel<SlideStep> selectedSlideModel;

    /** Store a strong reference to the service to avoid reloading. */
    // @Singleton
    private PresentationService presentationService;

    // private final Map<String, Double> animationRate = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {
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
    protected void initInnerComponents() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void showView() {
        // Nothing to do generic
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void hideView() {
        // Nothing to do generic
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processWave(final Wave wave) {
        // Nothing to do yet
    }

    /**
     * @return Returns the presentationService.
     */
    protected PresentationService getPresentationService() {
        if (this.presentationService == null) {
            this.presentationService = getService(PresentationService.class);
        }
        return this.presentationService;
    }

    /**
     * Display a slide.
     * 
     * @param slide the slide to display
     * @param isReverse indicate the direction (next or previous slide)
     */
    @SuppressWarnings("unchecked")
    private void displaySlide(final Slide slide, final boolean isReverse) {
        synchronized (this) {
            try {

                LOGGER.trace("Display slide N°" + slide.getPage() + " reverse=" + isReverse);

                SlideModel<SlideStep> previousSlideModel = null;

                if (this.selectedSlide != null) {
                    final Class<Model> previousClass = (Class<Model>)
                            (this.selectedSlide.getModelClass() == null
                                    ? Class.forName(getPresentationService().getPresentation().getSlides().getDefaultModelClass())
                                    : Class.forName(this.selectedSlide.getModelClass())
                            );

                    previousSlideModel = (SlideModel<SlideStep>) getModel(previousClass, this.selectedSlide);
                }

                // Define the slide number
                slide.setPage(this.slidePosition);

                final Class<Model> nextClass = (Class<Model>)
                        (slide.getModelClass() == null
                                ? Class.forName(getPresentationService().getPresentation().getSlides().getDefaultModelClass())
                                : Class.forName(slide.getModelClass())
                        );
                this.selectedSlideModel = (SlideModel<SlideStep>) getModel(nextClass, slide);

                // Add the new slide to the stack
                if (!getView().getRootNode().getChildren().contains(this.selectedSlideModel.getRootNode())) {
                    getView().getRootNode().getChildren().add(this.selectedSlideModel.getRootNode());
                }

                // final String animationKey = isReverse ? this.slidePosition + "_" + (this.slidePosition + 1) : this.slidePosition - 1 + "_" + this.slidePosition;

                // Play the animation<
                final ParallelTransition slideAnimation = buildSlideTransition(isReverse, previousSlideModel, this.selectedSlideModel);

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
                LOGGER.error("Error while loading a slide", e);
            }
        }

    }

    /**
     * Get the animation to use between slides.
     * 
     * @param isReverse true for reverse mode
     * @param previousSlideModel the previous slide model
     * @param selectedSlideModel the current slide model
     * 
     * @return the animation to show
     */
    private ParallelTransition buildSlideTransition(final boolean isReverse, final SlideModel<SlideStep> previousSlideModel, final SlideModel<SlideStep> selectedSlideModel) {
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

        final SlideModel<SlideStep> csm = selectedSlideModel;
        final SlideModel<SlideStep> psm = previousSlideModel;
        slideAnimation.setOnFinished(new javafx.event.EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent arg0) {
                // // Hide previous slide
                // if (psm != null) {
                // getView().getRootNode().getChildren().removeAll(psm.getRootNode());
                // }

                // FIX ME NOT APPROPRIATED !!!
                if (previousSlideModel != null) {
                    previousSlideModel.doHideView(null);
                }
                if (selectedSlideModel != null) {
                    selectedSlideModel.doShowView(null);
                }

                // Hide all other slides
                if (psm != null) {
                    Node node;
                    for (int i = getView().getRootNode().getChildren().size() - 1; i > 0; i--) {
                        node = getView().getRootNode().getChildren().get(i);
                        if (csm.getRootNode() != node) {
                            getView().getRootNode().getChildren().remove(node);
                        }
                    }
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
        synchronized (this) {
            // Try to display the next slide step first
            // If no slide step is remaining, display the next slide
            if (this.selectedSlideModel.nextStep() && this.slidePosition < getPresentationService().getPresentation().getSlides().getSlide().size() - 1) {
                this.slidePosition = Math.min(this.slidePosition + 1, getPresentationService().getPresentation().getSlides().getSlide().size() - 1);
                displaySlide(getPresentationService().getPresentation().getSlides().getSlide().get(this.slidePosition), false);
            }
        }
    }

    /**
     * Go to previous slide.
     */
    public void previous() {
        synchronized (this) {
            // Try to display the previous slide step first
            // If no slide step is remaining, display the previous slide
            if (this.selectedSlideModel.previousStep() && this.slidePosition > 0) {
                this.slidePosition = Math.max(this.slidePosition - 1, 0);
                displaySlide(getPresentationService().getPresentation().getSlides().getSlide().get(this.slidePosition), true);
            }
        }
    }

    /**
     * Display the slide menu to navigate.
     */
    public void showSlideMenu() {
        // Nothing to do yet
    }
}
