package org.jrebirth.af.component.ui.dock;

import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import org.jrebirth.af.component.behavior.dockable.data.Dockable;
import org.jrebirth.af.component.ui.CustomDataFormat;
import org.jrebirth.af.component.ui.beans.TabConfig;
import org.jrebirth.af.component.ui.beans.TabOrientation;
import org.jrebirth.af.component.ui.tab.TabModel;
import org.jrebirth.af.core.ui.adapter.AbstractDefaultAdapter;
import org.jrebirth.af.core.ui.adapter.DragAdapter;

public class DockTargetDragAdapter extends AbstractDefaultAdapter<DockController> implements DragAdapter {

    /**
     * {@inheritDoc}
     */
    @Override
    public void dragOver(final DragEvent dragEvent) {
        // System.out.println("drag OVER");

        if (/*
             * dragEvent.getGestureSource() != getController().getView().getBox() &&
             */dragEvent.getDragboard().hasContent(CustomDataFormat.DOCKABLE)) {
            //
            dragEvent.acceptTransferModes(TransferMode.MOVE);
            //
            // getController().getView().drawMarker((Button) dragEvent.getGestureSource(), dragEvent.getX(), dragEvent.getY());
        }

        dragEvent.consume();
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
        if (/*
             * dragEvent.getGestureSource() != getController().getView().getBox() &&
             */dragEvent.getDragboard().hasContent(CustomDataFormat.DOCKABLE)) {

            // getController().getView().drawMarker(dragEvent.getX(), dragEvent.getY());

            getController().view().node().setBorder(new Border(new BorderStroke(Color.AQUAMARINE, BorderStrokeStyle.SOLID, new CornerRadii(1.0), BorderStroke.THICK)));
        }

        dragEvent.consume();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dragExited(final DragEvent dragEvent) {
        // System.out.println("drag EXITED");
        if (/* dragEvent.getGestureSource() != getController().getView().getBox() && */
        dragEvent.getDragboard().hasContent(CustomDataFormat.DOCKABLE)) {

            // getController().getView().removeMarker();
            getController().view().node().setBorder(null);
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
            // final Button b = getController().getView().getButtonByTab(serializedTab);

            final TabModel model = getController().model().getModel(TabModel.class, TabConfig.create().tabKey("ddddd").orientation(TabOrientation.top));

            getController().model().addContainer(model, null);

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
