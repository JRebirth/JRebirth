package org.jrebirth.af.showcase.diagram.ui.statemachine;


import org.jrebirth.af.component.behavior.movable.Movable;
import org.jrebirth.af.component.behavior.resizable.Resizable;
import org.jrebirth.af.component.behavior.resizable.ResizableModel;
import org.jrebirth.af.component.behavior.selectable.Selectable;
import org.jrebirth.af.component.behavior.swipable.SwipableBehavior;
import org.jrebirth.af.core.ui.simple.DefaultSimpleModel;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;


public class StateMachineModel extends DefaultSimpleModel<Group> implements ResizableModel{

	private Rectangle r;
	private Pane pane;

	@Override
	protected void bind() {
		
		addBehavior(Resizable.of().model(this).shape(this.pane));
		addBehavior(Selectable.of().model(this).shape(this.r));
		addBehavior(Movable.of().model(this));
		
	}

	@Override
	protected void initSimpleView() {
		
		super.initSimpleView();
		
		
		
		pane = new Pane();
		node().getChildren().add(pane);
		
		
		
		pane.relocate(10, 10);
		pane.resize(800, 600);
		
		
		Text t = new Text();
		t.setText(getFirstKeyPart().toString());
		t.setLayoutX(0);
		t.setLayoutY(0);
		t.wrappingWidthProperty().bind(pane.widthProperty());
		
//		StackPane.setAlignment(t, Pos.TOP_CENTER);
//		StackPane.setMargin(t, new Insets(0, 0, 0, 0));
		
		Line l = new Line();
		l.setStartX(0);
		l.setStartY(20);
		//l.setEndX(600);
		l.endXProperty().bind(pane.widthProperty());
		l.setEndY(20);
		l.setStroke(Color.BLACK);
//		StackPane.setAlignment(l, Pos.TOP_CENTER);
//		StackPane.setMargin(l, new Insets(20, 0, 0, 0));
		
		r = new Rectangle();
		r.setFill(Color.TRANSPARENT);
		r.setStroke(Color.BLACK);
		r.setArcHeight(10.0);
		r.setArcWidth(10.0);
				
		r.setLayoutX(0);
		r.setLayoutY(0);
		
		r.widthProperty().bind(pane.widthProperty());
		r.heightProperty().bind(pane.heightProperty());
		
//		StackPane.setAlignment(t, Pos.CENTER);
//		StackPane.setMargin(t, new Insets(0, 0, 0, 0));
		
		
		pane.getChildren().addAll(r, l, t);
	}

	@Override
	public Node shape() {
		return pane;
	}

	@Override
	public String toString() {
		return key().key();
	}
}
