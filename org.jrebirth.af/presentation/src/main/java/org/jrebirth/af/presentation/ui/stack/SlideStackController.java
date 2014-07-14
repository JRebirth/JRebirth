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

import javafx.event.ActionEvent;
import javafx.scene.control.Control;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.web.WebView;

import org.jrebirth.af.core.exception.CoreException;
import org.jrebirth.af.core.ui.DefaultController;
import org.jrebirth.af.core.ui.adapter.ActionAdapter;
import org.jrebirth.af.presentation.command.ShowNextSlideCommand;
import org.jrebirth.af.presentation.command.ShowPreviousSlideCommand;
import org.jrebirth.af.presentation.command.ShowSlideMenuCommand;

/**
 * The class <strong>SlideStackController</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public final class SlideStackController extends DefaultController<SlideStackModel, SlideStackView> implements ActionAdapter {

    /**
     * Default Constructor.
     * 
     * @param view the view to control
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public SlideStackController(final SlideStackView view) throws CoreException {
        super(view);
    }

    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // protected void initEventAdapters() throws CoreException {
    //
    // // Use the inner class
    // addAdapter(new SlideKeyAdapter());
    // // Use the inner class
    // addAdapter(new SlideMouseAdapter());
    // }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initEventHandlers() throws CoreException {

        // Listen keys event on the root node
        getView().getCircle().setOnAction(getHandler(ActionEvent.ACTION));

    }

    // Listen mouse event on the root node
    // getRootNode().setOnMouseReleased(getHandler(MouseEvent.MOUSE_RELEASED));
    //
    // // getRootNode().setOnSwipeLeft(new EventHandler<SwipeEvent>() {
    // //
    // // @Override
    // // public void handle(final SwipeEvent swipeEvent) {
    // // getModel().callCommand(ShowNextSlideCommand.class);
    // // swipeEvent.consume();
    // // }
    // // });
    // //
    // // getRootNode().setOnSwipeRight(new EventHandler<SwipeEvent>() {
    // //
    // // @Override
    // // public void handle(final SwipeEvent swipeEvent) {
    // // getModel().callCommand(ShowPreviousSlideCommand.class);
    // // swipeEvent.consume();
    // // }
    // // });
    // }

    /**
     * .
     * 
     * @param keyEvent
     */
    protected void onKeyPressed(final KeyEvent keyEvent) {
        System.out.println("Key " + keyEvent);
        if (keyEvent.getCode() == KeyCode.PAGE_DOWN) {
            getModel().callCommand(ShowNextSlideCommand.class);
            keyEvent.consume();
        } else if (keyEvent.getCode() == KeyCode.PAGE_UP) {
            getModel().callCommand(ShowPreviousSlideCommand.class);
            keyEvent.consume();
        }
    }

    /**
     * .
     * 
     * @param mouseEvent
     */
    protected void onMouseReleased(final MouseEvent mouseEvent) {
        System.out.println("Mouse " + mouseEvent);
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
     * .
     * 
     * @param swipeEvent
     */
    protected void onSwipeLeft(SwipeEvent swipeEvent) {

        System.out.println("swipe left");
        getView().getCircle().fireEvent(new ActionEvent());

        getModel().callCommand(ShowNextSlideCommand.class);
        swipeEvent.consume();
    }

    /**
     * .
     * 
     * @param swipeEvent
     */
    protected void onSwipeRight(SwipeEvent swipeEvent) {
        System.out.println("swipe right");
        getModel().callCommand(ShowPreviousSlideCommand.class);
        swipeEvent.consume();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void action(ActionEvent actionEvent) {
        System.err.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
    }

    // /**
    // *
    // * The class <strong>SlideKeyAdapter</strong>.
    // *
    // * @author Sébastien Bordes
    // */
    // private class SlideKeyAdapter extends DefaultKeyAdapter<SlideStackController> {
    //
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public void keyPressed(final KeyEvent keyEvent) {
    //
    // if (keyEvent.getCode() == KeyCode.PAGE_DOWN) {
    // getModel().callCommand(ShowNextSlideCommand.class);
    // keyEvent.consume();
    // } else if (keyEvent.getCode() == KeyCode.PAGE_UP) {
    // getModel().callCommand(ShowPreviousSlideCommand.class);
    // keyEvent.consume();
    // }
    // }
    // }
    //
    // /**
    // *
    // * The class <strong>SlideMouseAdapter</strong>.
    // *
    // * @author Sébastien Bordes
    // */
    // private class SlideMouseAdapter extends DefaultMouseAdapter<SlideStackController> {
    //
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public void mouseReleased(final MouseEvent mouseEvent) {
    // if (!(mouseEvent.getTarget() instanceof Control) && !(mouseEvent.getTarget() instanceof WebView)) {
    // if (mouseEvent.getButton() == MouseButton.PRIMARY) {
    // getModel().callCommand(ShowNextSlideCommand.class);
    // mouseEvent.consume();
    // } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
    // getModel().callCommand(ShowPreviousSlideCommand.class);
    // mouseEvent.consume();
    // } else if (mouseEvent.getButton() == MouseButton.MIDDLE) {
    // getModel().callCommand(ShowSlideMenuCommand.class);
    // mouseEvent.consume();
    // }
    // }
    // }
    //
    // }

}
