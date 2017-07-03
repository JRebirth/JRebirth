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

import javafx.scene.layout.StackPane;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.core.ui.AbstractView;

/**
 * 
 * The class <strong>TransitionView</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class TransitionView extends AbstractView<TransitionModel, StackPane, TransitionController> {

    /**
     * Default Constructor.
     * 
     * @param model the view model
     * 
     * @throws CoreException if build fails
     */
    public TransitionView(final TransitionModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {

        node().setPrefWidth(model().getImageSlicerService().getImage().getWidth());
        node().setPrefHeight(model().getImageSlicerService().getImage().getHeight());

        node().getChildren().addAll(model().getImageSlicerService().getSlices());

        // getModel().getService2().getTimeline().play();

        // getModel().getRandomFadingService().getTimeline().play();
        model().getSlidingDoorService().getFullTransition().play();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reload() {
        node().setPrefWidth(model().getImageSlicerService().getImage().getWidth());
        node().setPrefHeight(model().getImageSlicerService().getImage().getHeight());

        node().getChildren().addAll(model().getImageSlicerService().getSlices());

        // getModel().getService2().getTimeline().play();

        // getModel().getRandomFadingService().getTimeline().play();
        model().getSlidingDoorService().getFullTransition().play();

    }

    @Override
    protected void bootView() {
        // Nothing to do yet

    }
}
