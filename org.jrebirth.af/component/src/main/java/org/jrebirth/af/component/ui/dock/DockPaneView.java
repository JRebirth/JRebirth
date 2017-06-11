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
package org.jrebirth.af.component.ui.dock;

import java.util.List;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.log.JRLogger;
import org.jrebirth.af.api.ui.annotation.RootNodeClass;
import org.jrebirth.af.core.log.JRLoggerFactory;
import org.jrebirth.af.core.ui.DefaultView;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;

/**
 * The Class DockView only creates a TabPane like component that will handle Model Components.
 *
 * No Controller is needed.
 *
 * @author Sébastien Bordes
 */
@RootNodeClass("DockPanel")
public class DockPaneView extends DefaultView<DockPaneModel, StackPane, DockPaneController> {

	private static final int PARENT = 100;


	public enum DropType{
		LEFT_RIGHT,
		TOP_BOTTOM
	}
	
    /** The Constant LOGGER. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(DockPaneView.class);
	
    private SplitPane splitpane;
    
	private Rectangle r1;
	private Rectangle r2;
	private Pane overlay;


	/**
     * Instantiates a new Dock View.
     *
     * @param model the model
     *
     * @throws CoreException the core exception
     */
    public DockPaneView(final DockPaneModel model) throws CoreException {
        super(model);
    }

    @Override
    protected void initView() {
        super.initView();
        
        splitpane = new SplitPane();
		overlay = new Pane();
		overlay.setMouseTransparent(true);

		double stroke = 2.0;

		r1 = buidlRectangle(stroke);
		r2 = buidlRectangle(stroke);
		
		Text t = new Text(model().key().toString());
		t.relocate(10, 10);
		
		overlay.setVisible(false);
		overlay.getChildren().addAll(t, r1, r2);
        
        node().getChildren().addAll(splitpane, overlay);

        switch (model().object().orientation()) {
            case horizontal:
                splitpane().setOrientation(Orientation.HORIZONTAL);
                break;
            case vertical:
            	splitpane().setOrientation(Orientation.VERTICAL);
                break;
        }

    }

    void removeItem(final List<Node> removed) {

        for (final Node node : removed) {
        	splitpane().getItems().remove(node);
        }

    }

    void addItem(final int from, final Node node) {
        if (!splitpane().getItems().contains(node)) {
        	splitpane().getItems().add(node);
        }

    }
    
	private Rectangle buidlRectangle(double stroke) {
		Rectangle r = new Rectangle();
		r.setStrokeType(StrokeType.INSIDE);
		r.setStrokeWidth(stroke);
		r.setStroke(Color.RED);
		r.setFill(Color.TRANSPARENT);
		return r;
	}
	
    /**
     * Draw marker.
     *
     * @param x the x
     * @param y the y
     */
    public void drawMarker(final double x, final double y) {
    	
    	//System.out.println("Add Dock Marker on => " + model().key());
    	
    	overlay.setVisible(true);
    	double space = 5.0;

		if (x > PARENT && x < node().getWidth() / 3 || x < node().getWidth() - PARENT && x > node().getWidth() * 2 / 3) {
			// left right
			r1.setLayoutX(5);
			r1.setLayoutY(5);
			r1.setWidth((overlay.getWidth() - space * 3) / 2);
			r1.setHeight((overlay.getHeight() - space * 2) / 1);

			r2.setLayoutX(overlay.getWidth() / 2 + space / 2);
			r2.setLayoutY(5);
			r2.setWidth((overlay.getWidth() - space * 3) / 2);
			r2.setHeight((overlay.getHeight() - space * 2 / 1));
			
			if (x < node().getWidth() / 3) {
				r1.setStrokeWidth(4.0);
				r2.setStrokeWidth(2.0);
			}else{
				r1.setStrokeWidth(2.0);
				r2.setStrokeWidth(4.0);
			}

		} else if (y > PARENT && y < node().getHeight() / 3 || y < node().getHeight() - PARENT && y > node().getHeight() * 2 / 3) {
			// topbottom
			r1.setLayoutX(5);
			r1.setLayoutY(5);
			r1.setWidth((overlay.getWidth() - space * 2) / 1);
			r1.setHeight((overlay.getHeight() - space * 3) / 2);

			r2.setLayoutX(5);
			r2.setLayoutY(overlay.getHeight() / 2 + space / 2);
			r2.setWidth((overlay.getWidth() - space * 2) / 1);
			r2.setHeight((overlay.getHeight() - space * 3) / 2);
			
			if (y < node().getHeight() / 3) {
				r1.setStrokeWidth(4.0);
				r2.setStrokeWidth(2.0);
			}else{
				r1.setStrokeWidth(2.0);
				r2.setStrokeWidth(4.0);
			}

		}
    	
//    	
//    	GridPane gp = new GridPane();
//    	gp.setHgap(5);
//    	gp.setVgap(5);
//    	
//    	Rectangle r1 = new Rectangle();
//    	
//    	GridPane.setRowIndex(r1, 0);
//        GridPane.setColumnIndex(r1, 0);
//        GridPane.setMargin(r1, new Insets(10));
//        
//        Rectangle r2 = new Rectangle();
//    	
//    	GridPane.setRowIndex(r2, 1);
//        GridPane.setColumnIndex(r1, 0);
//        GridPane.setMargin(r2, new Insets(10));
//        
//        gp.getChildren().addAll(r1, r2);
//        
//    	node().getChildren().add(gp);

//        final int draggedIdx = getBox().getChildren().indexOf(dragged);
//        final int markerIdx = getBox().getChildren().indexOf(this.marker);
//
//        int idx = 0;
//        Node tempHoverNode = null;
//        final int xx = 0;
//        for (final Node n : getBox().getChildren()) {
//
//            if (n.getBoundsInParent().contains(x, y)) {
//                tempHoverNode = n;
//                break;
//            }
//
//            if (n != this.marker) {
//                idx++;
//            }
//        }
//
//        if (tempHoverNode == this.hoverNode) {
//            return;
//        } else {
//            this.hoverNode = tempHoverNode;
//        }
//
//        System.out.println("marker" + markerIdx + "  idx " + idx);
//        if (markerIdx != idx) {
//
//            if (this.marker != null) {
//                getBox().getChildren().remove(this.marker);
//            }
//
//            if (draggedIdx != idx) {
//                this.marker = model().object().orientation() == TabbedPaneOrientation.bottom || model().object().orientation() == TabbedPaneOrientation.top ? new Rectangle(10, getBox().getHeight())
//                        : new Rectangle(getBox().getWidth(), 4);
//
//                this.marker.setFill(Color.LIGHTGREEN);
//
//                getBox().getChildren().add(idx, this.marker);
//            }
//        }

    }
    
    /**
     * Removes the marker.
     *
     * @return the int
     */
    public void removeMarker() {
        System.out.println("Remove Dock Marker");
        
        overlay.setVisible(false);
        // Only keep Splitpane
        //node().getChildren().removeIf(c -> c != splitpane());
    }
    

    /**
	 * @return the splitpane
	 */
	SplitPane splitpane() {
		return splitpane;
	}

}
