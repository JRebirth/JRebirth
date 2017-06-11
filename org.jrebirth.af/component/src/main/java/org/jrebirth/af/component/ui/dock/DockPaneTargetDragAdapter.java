package org.jrebirth.af.component.ui.dock;

import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import org.jrebirth.af.component.behavior.dockable.data.Dockable;
import org.jrebirth.af.component.ui.CustomDataFormat;
import org.jrebirth.af.component.ui.beans.TabbedPaneConfig;
import org.jrebirth.af.component.ui.beans.TabbedPaneOrientation;
import org.jrebirth.af.component.ui.tab.TabbedPaneModel;
import org.jrebirth.af.core.ui.adapter.AbstractDefaultAdapter;
import org.jrebirth.af.core.ui.adapter.DragAdapter;

public class DockPaneTargetDragAdapter extends AbstractDefaultAdapter<DockPaneController> implements DragAdapter {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dragOver(final DragEvent dragEvent) {

		if (dragEvent.getSource() instanceof Region) {
			if (dragEvent.getX() > 100
					&& dragEvent.getX() < ((Region) dragEvent.getSource()).getWidth() - 100
					|| dragEvent.getY() > 100
					&& dragEvent.getY() < ((Region) dragEvent.getSource()).getHeight() - 100) {

				System.out.println("drag OVER on => " + controller().model().key());

				controller().view().drawMarker(dragEvent.getX(), dragEvent.getY());
				dragEvent.consume();
			} else {
				controller().view().removeMarker();
			}
		}
	}

	// /**
	// * {@inheritDoc}
	// */
	@Override
	public void dragEntered(final DragEvent dragEvent) {
		// System.out.println("drag ENTERED on => " +
		// controller().model().key());
		//// if (/*dragEvent.getGestureSource() !=
		// getController().getView().getBox() &&*/
		//// dragEvent.getDragboard().hasContent(CustomDataFormat.DOCKABLE)) {
		////
		//// dragEvent.acceptTransferModes(TransferMode.MOVE);
		////
		//// controller().view().drawMarker(dragEvent.getX(), dragEvent.getY());
		//// }
		//// dragEvent.consume();
	}

	//
	// /**
	// * {@inheritDoc}
	// */
	@Override
	public void dragExited(final DragEvent dragEvent) {
		// System.out.println("drag EXITED on => " +
		// controller().model().key());
		//
		// if (/* dragEvent.getGestureSource() !=
		// getController().getView().getBox() && */
		// dragEvent.getDragboard().hasContent(CustomDataFormat.DOCKABLE)) {
		//
		// }
		// controller().view().removeMarker();
		//
		// //dragEvent.consume();
		
		controller().view().removeMarker();
		dragEvent.consume();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dragEnteredTarget(final DragEvent dragEvent) {
		//System.out.println("drag ENTERED TARGET on => " + controller().model().key());
		// if (/*dragEvent.getGestureSource() !=
		// getController().getView().getBox() &&*/
		// dragEvent.getDragboard().hasContent(CustomDataFormat.DOCKABLE)) {
		//
		// dragEvent.acceptTransferModes(TransferMode.MOVE);
		//
		// controller().view().drawMarker(dragEvent.getX(), dragEvent.getY());
		// }

		// controller().view().drawMarker(dragEvent.getX(), dragEvent.getY());

		// dragEvent.consume();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dragExitedTarget(final DragEvent dragEvent) {

		//System.out.println("drag EXIT TARGET => " + controller().model().key());
		controller().view().removeMarker();
		dragEvent.consume();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dragDropped(final DragEvent dragEvent) {

		System.out.println("drag DROPPED => " + controller().model().key());

		/* data dropped */
		/* if there is a string data on dragboard, read it and use it */
		final Dragboard db = dragEvent.getDragboard();
		boolean success = false;

		if (db.hasContent(CustomDataFormat.DOCKABLE)) {

			final Dockable serializedTab = (Dockable) db.getContent(CustomDataFormat.DOCKABLE);
			// final Button b =
			// getController().getView().getButtonByTab(serializedTab);

			final TabbedPaneModel model = controller().model().getModel(TabbedPaneModel.class, TabbedPaneConfig.create().id("ddddd").orientation(TabbedPaneOrientation.top));

			// getController().model().addPart(model, null);

			success = true;
		}
		/*
		 * let the source know whether the string was successfully transferred
		 * and used
		 */
		dragEvent.setDropCompleted(success);

		dragEvent.consume();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dragDone(final DragEvent dragEvent) {

		System.out.println("drag DONE => " + controller().model().key());
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
