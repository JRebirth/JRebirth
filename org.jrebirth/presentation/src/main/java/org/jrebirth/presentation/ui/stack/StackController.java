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
package org.jrebirth.presentation.ui.stack;

import javafx.event.ActionEvent;
import javafx.scene.control.Control;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.AbstractController;
import org.jrebirth.core.ui.adapter.ActionAdapter;
import org.jrebirth.core.ui.adapter.DefaultKeyAdapter;
import org.jrebirth.core.ui.adapter.DefaultMouseAdapter;
import org.jrebirth.presentation.command.ShowNextSlideCommand;
import org.jrebirth.presentation.command.ShowPreviousSlideCommand;
import org.jrebirth.presentation.command.ShowSlideMenuCommand;

/**
 * The class <strong>StackController</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public final class StackController extends AbstractController<StackModel, StackView> implements ActionAdapter {

    /**
     * Default Constructor.
     * 
     * @param view the view to control
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public StackController(final StackView view) throws CoreException {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeEventAdapters() throws CoreException {

        // Use the inner class
        buildKeyHandler(new SlideKeyAdapter());
        // Use the inner class
        buildMouseHandler(new SlideMouseAdapter());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeEventHandlers() throws CoreException {

        // Listen keys event on the root node
        getRootNode().setOnKeyPressed(getKeyHandler());

        // Listen mouse event on the root node
        getRootNode().setOnMousePressed(getMouseHandler());
    }

    /**
     * 
     * The class <strong>SlideKeyAdapter</strong>.
     * 
     * @author Sébastien Bordes
     */
    private class SlideKeyAdapter extends DefaultKeyAdapter<StackController> {

        /**
         * {@inheritDoc}
         */
        @Override
        public void keyPressed(final KeyEvent keyEvent) {

            if (keyEvent.getCode() == KeyCode.PAGE_DOWN) {
                getModel().callCommand(ShowNextSlideCommand.class);
                keyEvent.consume();
            } else if (keyEvent.getCode() == KeyCode.PAGE_UP) {
                getModel().callCommand(ShowPreviousSlideCommand.class);
                keyEvent.consume();
            }
        }

    }

    /**
     * 
     * The class <strong>SlideMouseAdapter</strong>.
     * 
     * @author Sébastien Bordes
     */
    private class SlideMouseAdapter extends DefaultMouseAdapter<StackController> {

        /**
         * {@inheritDoc}
         */
        @Override
        public void mousePressed(final MouseEvent mouseEvent) {
            if (!(mouseEvent.getTarget() instanceof Control) && !(mouseEvent.getTarget() instanceof WebView)) {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    getModel().callCommand(ShowNextSlideCommand.class);
                    mouseEvent.consume();
                } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                    getModel().callCommand(ShowPreviousSlideCommand.class);
                    mouseEvent.consume();
                } else if (mouseEvent.getButton() == MouseButton.MIDDLE) {
                    getModel().callCommand(ShowSlideMenuCommand.class);
                    mouseEvent.consume();
                }
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void mouseClicked(final MouseEvent mouseEvent) {

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void action(final ActionEvent actionEvent) {
        // Nothing to do yet

    }

}
