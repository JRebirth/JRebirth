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
package org.jrebirth.af.core.ui.annotation;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.layout.VBox;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.ui.annotation.OnRotate;
import org.jrebirth.af.api.ui.annotation.OnSwipe;
import org.jrebirth.af.api.ui.annotation.type.Rotate;
import org.jrebirth.af.api.ui.annotation.type.Swipe;
import org.jrebirth.af.core.ui.DefaultView;

/**
 *
 * The class <strong>PropertiesView</strong>.
 *
 * The view used to display properties of a selected node.
 *
 * @author Sébastien Bordes
 */
public final class AnnotationView extends DefaultView<AnnotationModel, VBox, AnnotationController> {

    /********************/
    /** Swipe Buttons. */
    /********************/
    @OnSwipe({ Swipe.Up, Swipe.Down })
    private Button swipeVerticalButton;

    @OnSwipe({ Swipe.Left, Swipe.Right })
    private Button swipeHorizontalButton;

    @OnSwipe
    private Button swipeAllButton;

    /********************/
    /** Rotate Buttons. */
    /********************/
    @OnRotate
    private Button rotateAllButton;

    @OnRotate(Rotate.Rotate)
    private Button rotateButton;

    @OnRotate({ Rotate.Started, Rotate.Finished })
    private Button rotateStartFinishButton;

    /**
     * Default Constructor.
     *
     * @param model the controls view model
     *
     * @throws CoreException if build fails
     */
    public AnnotationView(final AnnotationModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {

        node().setMinWidth(200);

        this.swipeVerticalButton = ButtonBuilder.create().text("Swipe Vertical Button").build();
        this.swipeHorizontalButton = ButtonBuilder.create().text("Swipe Horizontal Button").build();
        this.swipeAllButton = ButtonBuilder.create().text("Swipe All Button").build();

        this.rotateAllButton = ButtonBuilder.create().text("Rotate All Button").build();
        this.rotateButton = ButtonBuilder.create().text("Rotate Button").build();
        this.rotateStartFinishButton = ButtonBuilder.create().text("Rotate Started & Finished Button").build();

        node().getChildren().add(this.swipeVerticalButton);
    }

    public Button getSwipeVerticalButton() {
        return this.swipeVerticalButton;
    }

    public Button getSwipeHorizontalButton() {
        return this.swipeHorizontalButton;
    }

    public Button getSwipeAllButton() {
        return this.swipeAllButton;
    }

    public Button getRotateAllButton() {
        return this.rotateAllButton;
    }

    public Button getRotateButton() {
        return this.rotateButton;
    }

    public Button getRotateStartFinishButton() {
        return this.rotateStartFinishButton;
    }

}
