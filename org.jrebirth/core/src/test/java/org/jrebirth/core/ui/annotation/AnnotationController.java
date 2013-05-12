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
package org.jrebirth.core.ui.annotation;

import javafx.scene.control.Button;
import javafx.scene.input.RotateEvent;
import javafx.scene.input.SwipeEvent;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.DefaultController;

/**
 * The class <strong>PropertiesController</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class AnnotationController extends DefaultController<AnnotationModel, AnnotationView> {

    /**
     * Default Constructor.
     * 
     * @param view the view to control
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public AnnotationController(final AnnotationView view) throws CoreException {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeEventHandlers() throws CoreException {
        // Nothing to do yet

    }

    void onSwipe(final SwipeEvent event) {
        System.out.println("SwipeAny on node " + ((Button) event.getSource()).getText());
    }

    void onSwipeUp(final SwipeEvent event) {
        System.out.println("SwipeUp on node " + ((Button) event.getSource()).getText());
    }

    void onSwipeDown(final SwipeEvent event) {
        System.out.println("SwipeDown on node " + ((Button) event.getSource()).getText());
    }

    void onSwipeLeft(final SwipeEvent event) {
        System.out.println("SwipeLeft on node " + ((Button) event.getSource()).getText());
    }

    void onSwipeRight(final SwipeEvent event) {
        System.out.println("SwipeRight on node " + ((Button) event.getSource()).getText());
    }

    void onRotate(final RotateEvent event) {
        System.out.println("Rotate on node " + ((Button) event.getSource()).getText());
    }

    void onRotateStarted(final RotateEvent event) {
        System.out.println("Rotate started on node " + ((Button) event.getSource()).getText());
    }

    void onRotateFinished(final RotateEvent event) {
        System.out.println("Rotate finished on node " + ((Button) event.getSource()).getText());
    }

    void onRotateRotate(final javafx.scene.input.RotateEvent event) {
    }

}
