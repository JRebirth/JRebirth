package org.jrebirth.af.component.ui.tab;

import javafx.scene.control.Button;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

import org.jrebirth.af.component.ui.CustomDataFormat;
import org.jrebirth.af.core.ui.adapter.AbstractDefaultAdapter;
import org.jrebirth.af.core.ui.adapter.MouseAdapter;

class TabSourceDragAdapter
		extends AbstractDefaultAdapter<TabController> implements MouseAdapter {

	@Override
	public void mouseDragDetected(MouseEvent mouseEvent) {

		Button b = ((Button)mouseEvent.getSource());
		
		Dragboard db = b.startDragAndDrop(TransferMode.MOVE);

		// Put a string on a dragboard
		ClipboardContent content = new ClipboardContent();
		content.put(CustomDataFormat.TAB, b.getUserData());
		db.setContent(content);

		mouseEvent.consume();
	}

}
