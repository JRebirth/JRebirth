package org.jrebirth.af.component.ui.tab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import org.jrebirth.af.component.ui.CustomDataFormat;
import org.jrebirth.af.component.ui.beans.Tab;
import org.jrebirth.af.core.ui.AbstractBaseController;
import org.jrebirth.af.core.ui.adapter.AbstractDefaultAdapter;
import org.jrebirth.af.core.ui.adapter.DefaultDragAdapter;
import org.jrebirth.af.core.ui.adapter.DragAdapter;
import org.jrebirth.af.core.ui.adapter.MouseAdapter;

class TabTargetDragAdapter extends AbstractDefaultAdapter<TabController> implements DragAdapter {

	//http://stackoverflow.com/questions/18929161/how-to-move-items-with-in-vboxchange-order-by-dragging-in-javafx
	
	
	@Override
	public void dragOver(DragEvent dragEvent) {
	        /* data is dragged over the target */
	        /* accept it only if it is not dragged from the same node 
	         * and if it has a string data */
	        if (dragEvent.getGestureSource() != getController().getView().getBox() &&
	        		dragEvent.getDragboard().hasContent(CustomDataFormat.TAB)) {
	            /* allow for both copying and moving, whatever user chooses */
	        	
	        	dragEvent.acceptTransferModes(TransferMode.MOVE);
	        }
	        
	        dragEvent.consume();
	}

	@Override
	public void dragEntered(DragEvent dragEvent) {
	    /* the drag-and-drop gesture entered the target */
	    /* show to the user that it is an actual gesture target */
	         if (dragEvent.getGestureSource() != getController().getView().getBox() &&
	        		 dragEvent.getDragboard().hasString()) {
	        	// box.setFill(Color.GREEN);
	         }
	                
	         dragEvent.consume();
	    }

	@Override
	public void dragExited(DragEvent dragEvent) {
        /* mouse moved away, remove the graphical cues */
    	//box.setFill(Color.BLACK);

		dragEvent.consume();
    }

	@Override
	public void dragDropped(DragEvent dragEvent) {
        /* data dropped */
        /* if there is a string data on dragboard, read it and use it */
        Dragboard db = dragEvent.getDragboard();
        boolean success = false;
        
        if (db.hasContent(CustomDataFormat.TAB)) {
        	
        	Tab t = (Tab)db.getContent(CustomDataFormat.TAB);
        	Button b = getController().getView().getButtonByTab(t);
        	
        	Pane box = getController().getView().getBox();
        	
//        	Button b = null;
//        	for(Node n : box.getChildren()){
//        		if(n instanceof Button && n.getUserData().equals(t)){
//        			b =(Button) n;
//        		}
//        	}
        	
        	if(box.getChildren().contains(b)){
        		//box.getChildren().remove(b);
        		
               
                Object source = dragEvent.getGestureSource();
                int sourceIndex = box.getChildren().indexOf(source);
                int targetIndex = box.getChildren().indexOf(dragEvent.getTarget());
                List<Node> nodes = new ArrayList<Node>(box.getChildren());
                if (sourceIndex < targetIndex) {
                    Collections.rotate(
                            nodes.subList(sourceIndex, targetIndex + 1), -1);
                } else {
                    Collections.rotate(
                            nodes.subList(targetIndex, sourceIndex + 1), 1);
                }
                box.getChildren().clear();
                box.getChildren().addAll(nodes);
        		
        		
//        		int idx = box.getChildren().indexOf(dragEvent.getTarget());
//        		if(idx>=0 && idx < box.getChildren().size()){
//            		box.getChildren().add(idx, b);
//            		box.requestLayout();
//            			
//        		}
        		
        		b.fire();
        	}else{
        		getController().getModel().addTab(getController().getModel().getLocalFacade().retrieve(t.modelKey()), null);
        	}
        	
        	
           success = true;
        }
        /* let the source know whether the string was successfully 
         * transferred and used */
        dragEvent.setDropCompleted(success);
        
        dragEvent.consume();
     }
	
	@Override
	public void dragDone(DragEvent dragEvent) {
        /* the drag and drop gesture ended */
        /* if the data was successfully moved, clear it */
        if (dragEvent.getTransferMode() == TransferMode.MOVE) {
        	Button b = ((Button)dragEvent.getGestureSource());
        	//((Pane)b.getParent()).getChildren().remove(b);
        }
        dragEvent.consume();
    }

}
