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
package org.jrebirth.af.component.ui.tab;

import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import org.jrebirth.af.component.behavior.dockable.data.Dockable;
import org.jrebirth.af.component.ui.CustomDataFormat;
import org.jrebirth.af.core.ui.adapter.AbstractDefaultAdapter;
import org.jrebirth.af.core.ui.adapter.DragAdapter;

/**
 * The Class TabTargetDragAdapter.
 */
class TabbedPaneTargetDragAdapter extends AbstractDefaultAdapter<TabbedPaneController> implements DragAdapter {

    /**
     * {@inheritDoc}
     */
    @Override
    public void dragOver(final DragEvent dragEvent) {
        // System.out.println("drag OVER");

        if (dragEvent.getGestureSource() != controller().view().getBox()
                && dragEvent.getDragboard().hasContent(CustomDataFormat.DOCKABLE)) {

            dragEvent.acceptTransferModes(TransferMode.MOVE);

            controller().view().drawMarker((ToggleButton) dragEvent.getGestureSource(), dragEvent.getX(), dragEvent.getY());

            dragEvent.consume();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dragEnteredTarget(final DragEvent dragEvent) {
        // System.out.println("drag ENTERED TARGET");

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dragEntered(final DragEvent dragEvent) {
        // System.out.println("drag ENTERED");
        if (dragEvent.getGestureSource() != controller().view().getBox()
                && dragEvent.getDragboard().hasContent(CustomDataFormat.DOCKABLE)) {

            // getController().getView().drawMarker(dragEvent.getX(), dragEvent.getY());

            // getController().getView().getBox().setBorder(new Border(new BorderStroke(Color.AQUAMARINE, BorderStrokeStyle.SOLID, new CornerRadii(1.0), BorderStroke.THICK)));
        }

        dragEvent.consume();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dragExited(final DragEvent dragEvent) {
        // System.out.println("drag EXITED");
        if (dragEvent.getGestureSource() != controller().view().getBox() &&
                dragEvent.getDragboard().hasContent(CustomDataFormat.DOCKABLE)) {

            controller().view().removeMarker();
            // getController().getView().getBox().setBorder(null);
        }

        dragEvent.consume();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dragExitedTarget(final DragEvent dragEvent) {
        // System.out.println("drag EXIT TARGET");
        // if (/*
        // * dragEvent.getGestureSource() != getController().getView().getBox() &&
        // */dragEvent.getDragboard().hasContent(CustomDataFormat.TAB)) {
        //
        // getController().getView().removeMarker();
        // // getController().getView().getBox().setBorder(null);
        // }
        //
        // dragEvent.consume();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dragDropped(final DragEvent dragEvent) {

        // System.out.println("drag DROPPED");

        /* data dropped */
        /* if there is a string data on dragboard, read it and use it */
        final Dragboard db = dragEvent.getDragboard();
        boolean success = false;

        if (db.hasContent(CustomDataFormat.DOCKABLE)) {

            final Dockable serializedTab = (Dockable) db.getContent(CustomDataFormat.DOCKABLE);
            final ToggleButton b = controller().view().getButtonByTab(serializedTab);

            final Pane targetBox = controller().view().getBox();

            int idx = controller().view().removeMarker();

            System.out.println("Add tab " + serializedTab.name() + " at " + idx);

            if (targetBox.getChildren().contains(b)) {

                final Dockable realTab = (Dockable) b.getUserData();
                final int currentIdx = targetBox.getChildren().indexOf(b);
                
                // Same tab is moved at the end so we have to remove one to the marker position
                if (currentIdx < idx) {
                	 idx--;
                }
                // targetBox.getChildren().remove(b);
                // targetBox.getChildren().add(idx, b);
                // getController().getView().removeMarker();
                if (currentIdx != idx) {
                    controller().model().object().tabs().remove(realTab);

                    controller().model().object().tabs().add(idx, realTab);

                    b.fire();
                }
            } else {

                final Node n = (Node) dragEvent.getGestureSource();

                final ScaleTransition st = new ScaleTransition(Duration.millis(600));
                st.setNode(n);
                st.setFromX(1.0);
                st.setToX(0.0);
                st.setOnFinished(event -> ((Pane) n.getParent()).getChildren().remove(n));

                st.play();

                controller().model().doInsertTab(idx, serializedTab, null);
            }
            success = true;
        }
        /*
         * let the source know whether the string was successfully transferred and used
         */
        dragEvent.setDropCompleted(success);

        dragEvent.consume();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dragDone(final DragEvent dragEvent) {

        // System.out.println("drag DONE");

        /* the drag and drop gesture ended */
        /* if the data was successfully moved, clear it */
        // if (dragEvent.getTransferMode() == TransferMode.MOVE) {
        // Button b = ((Button) dragEvent.getGestureSource());
        // ((Pane) b.getParent()).getChildren().remove(b);
        // }
        dragEvent.consume();
    }

}
