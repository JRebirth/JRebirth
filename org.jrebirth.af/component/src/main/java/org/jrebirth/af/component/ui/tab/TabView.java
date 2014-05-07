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

import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import org.jrebirth.af.component.ui.CustomDataFormat;
import org.jrebirth.af.component.ui.beans.Tab;
import org.jrebirth.af.core.exception.CoreException;
import org.jrebirth.af.core.log.JRLogger;
import org.jrebirth.af.core.log.JRLoggerFactory;
import org.jrebirth.af.core.ui.DefaultController;
import org.jrebirth.af.core.ui.DefaultView;
import org.jrebirth.af.core.ui.annotation.RootNodeClass;

/**
 * The Class TabView only creates a TabPane like component that will handle Model Components.
 * 
 * No Controller is needed.
 * 
 * @author Sébastien Bordes
 */
@RootNodeClass("TabPanel")
public class TabView extends DefaultView<TabModel, BorderPane, TabController> {

    /** The Constant LOGGER. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(TabView.class);

    
    private StackPane stackPane;
    
    private Map<String, Button> buttonByTab = new HashMap<>();

	private Pane box;
    
	/**
     * Instantiates a new Dock View.
     * 
     * @param model the model
     * 
     * @throws CoreException the core exception
     */
    public TabView(final TabModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
	@Override
	protected void initView() {

		switch(getModel().getObject().orientation()){
		case top: 
			getRootNode().setTop(buildButtonBar(true));
			break;
		case bottom: getRootNode().setBottom(buildButtonBar(true));
			break;
		case left: getRootNode().setLeft(buildButtonBar(false));
			break;
		case right: getRootNode().setRight(buildButtonBar(false));
			break;
		}
		
		stackPane = new StackPane();
		
		getRootNode().setCenter(stackPane);
		
		
		
	}

	private Pane buildButtonBar(boolean isHorizontal) {
		
		box = (isHorizontal) ? new HBox(): new VBox();
		
//		box.setOnDragOver(new EventHandler<DragEvent>() {
//		    public void handle(DragEvent event) {
//		        /* data is dragged over the target */
//		        /* accept it only if it is not dragged from the same node 
//		         * and if it has a string data */
//		        if (event.getGestureSource() != box &&
//		                event.getDragboard().hasContent(CustomDataFormat.TAB)) {
//		            /* allow for both copying and moving, whatever user chooses */
//		            event.acceptTransferModes(TransferMode.MOVE);
//		        }
//		        
//		        event.consume();
//		    }
//		});
//		
//		
//		box.setOnDragEntered(new EventHandler<DragEvent>() {
//		    public void handle(DragEvent event) {
//		    /* the drag-and-drop gesture entered the target */
//		    /* show to the user that it is an actual gesture target */
//		         if (event.getGestureSource() != box &&
//		                 event.getDragboard().hasString()) {
//		        	// box.setFill(Color.GREEN);
//		         }
//		                
//		         event.consume();
//		    }
//		});
//
//		box.setOnDragExited(new EventHandler<DragEvent>() {
//		    public void handle(DragEvent event) {
//		        /* mouse moved away, remove the graphical cues */
//		    	//box.setFill(Color.BLACK);
//
//		        event.consume();
//		    }
//		});
//
//		box.setOnDragDropped(new EventHandler<DragEvent>() {
//		    public void handle(DragEvent event) {
//		        /* data dropped */
//		        /* if there is a string data on dragboard, read it and use it */
//		        Dragboard db = event.getDragboard();
//		        boolean success = false;
//		        if (db.hasContent(CustomDataFormat.TAB)) {
//		        	
//		        	Tab t = (Tab)db.getContent(CustomDataFormat.TAB);
//		        	Button b = buttonByTab.get(t);
//		        	
//		        	if(box.getChildren().contains(b)){
//		        		box.getChildren().remove(b);
//		        		box.getChildren().add(0, b);
//		        		b.fire();
//		        	}else{
//		        		getModel().addTab(getModel().getLocalFacade().retrieve(t.modelKey()), null);
//		        	}
//		        	
//		        	
//		           success = true;
//		        }
//		        /* let the source know whether the string was successfully 
//		         * transferred and used */
//		        event.setDropCompleted(success);
//		        
//		        event.consume();
//		     }
//		});

		
		return box;
	}

	public void addTab(Tab tab) {
		
		Button b = new Button(tab.name());
		b.setUserData(tab);
		
		buttonByTab.put(tab.name(), b);
		
		selectTab(tab);
		
		getController().initTabEventHandler(b);

//		b.setOnAction(new EventHandler<ActionEvent>() {
//			
//			@Override
//			public void handle(ActionEvent event) {
//				Tab t = (Tab)  ((Button)event.getSource()).getUserData();
//				
//				selectTab(t);
//				
//			}
//		});
		
//		b.setOnDragDetected(new EventHandler<MouseEvent>() 
//				
//				{
//
//					@Override
//					public void handle(MouseEvent event) {
//							
//						Dragboard db = b.startDragAndDrop(TransferMode.MOVE);
//				        
//				        // Put a string on a dragboard 
//				        ClipboardContent content = new ClipboardContent();
//				        content.put(CustomDataFormat.TAB, tab);
//				        db.setContent(content);
//				        
//				        event.consume();
//					}
//		});
//		
//		b.setOnDragDone(new EventHandler<DragEvent>() {
//		    public void handle(DragEvent event) {
//		        /* the drag and drop gesture ended */
//		        /* if the data was successfully moved, clear it */
//		        if (event.getTransferMode() == TransferMode.MOVE) {
//		            box.getChildren().remove(b);
//		        }
//		        event.consume();
//		    }
//		});
		
		box.getChildren().add(b);
		
	}

	void selectTab(Tab t) {
		stackPane.getChildren().clear();
		stackPane.getChildren().add(getModel().getLocalFacade().retrieve(t.modelKey()).getRootNode());
	}
    
    Pane getBox() {
		return box;
	}

	public Button getButtonByTab(Tab t) {
		return buttonByTab.get(t.name());
	}    

}