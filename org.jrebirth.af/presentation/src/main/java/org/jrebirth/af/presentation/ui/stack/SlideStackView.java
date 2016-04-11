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

import javafx.geometry.Pos;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.ui.annotation.OnKey;
import org.jrebirth.af.api.ui.annotation.OnMouse;
import org.jrebirth.af.api.ui.annotation.OnSwipe;
import org.jrebirth.af.api.ui.annotation.OnTouch;
import org.jrebirth.af.api.ui.annotation.RootNodeId;
import org.jrebirth.af.api.ui.annotation.type.Key;
import org.jrebirth.af.api.ui.annotation.type.Mouse;
import org.jrebirth.af.api.ui.annotation.type.Swipe;
import org.jrebirth.af.api.ui.annotation.type.Touch;
import org.jrebirth.af.core.ui.DefaultView;

/**
 *
 * The class <strong>SlideMenuView</strong>.
 *
 * The main slide stack view of the Presentation engine.
 *
 * @author Sébastien Bordes
 */
@RootNodeId("SlideStack")
@OnKey(Key.Pressed)
@OnMouse(Mouse.Released)
@OnSwipe({ Swipe.Left, Swipe.Right })
@OnTouch(Touch.Released)
public final class SlideStackView extends DefaultView<SlideStackModel, StackPane, SlideStackController> {

    // private Button circle;

    /**
     * Default Constructor.
     *
     * @param model the view model
     *
     * @throws CoreException if build fails
     */
    public SlideStackView(final SlideStackModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {

        node().setFocusTraversable(true);

        node().setPrefSize(1024, 768);
        node().setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        node().setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        node().setAlignment(Pos.CENTER);

        // this.circle = new Button();

        // getRootNode().setOnKeyPressed(getController()::onKeyPressed);

        // getRootNode().setPadding(new Insets(5, 5, 5, 5));

        // Blend blend = new Blend();
        // blend.setMode(BlendMode.HARD_LIGHT);
        //
        // ColorInput colorInput = new ColorInput();
        // colorInput.setPaint(PrezColors.BACKGROUND_INPUT.get());
        // colorInput.setX(0);
        // colorInput.setY(0);
        // colorInput.setWidth(1024);
        // colorInput.setHeight(768);
        // blend.setTopInput(colorInput);
        //
        // getRootNode().setEffect(blend);
    }

    // /**
    // * @return Returns the circle.
    // */
    // protected Button getCircle() {
    // return this.circle;
    // }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {
        // Nothing to do yet
        super.hide();

    }

}
