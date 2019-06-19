package org.jrebirth.af.showcase.diagram.ui.state;


import java.nio.file.attribute.PosixFilePermission;

import org.jrebirth.af.component.behavior.movable.Movable;
import org.jrebirth.af.component.behavior.resizable.Resizable;
import org.jrebirth.af.component.behavior.resizable.ResizableModel;
import org.jrebirth.af.component.behavior.selectable.Selectable;
import org.jrebirth.af.core.ui.simple.DefaultSimpleModel;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class StateModel extends DefaultSimpleModel<Group> implements ResizableModel {

	
	
	private Pane pane;
	private Rectangle shape;

	@Override
	protected void initModel() {
		super.initModel();
	}
	
	@Override
	protected void bind() {
		addBehavior(Resizable.of().model(this).shape(this.pane));
		addBehavior(Selectable.of().model(this).shape(this.shape));
		addBehavior(Movable.of().model(this));
	}

	@Override
	protected void initSimpleView() {
		super.initSimpleView();
		
		pane = new Pane();
		node().getChildren().add(pane);
		
		pane.relocate(100, 100);
		pane.resize(300, 200);
		
		Label label = new Label();
		label.setMouseTransparent(true);
		//label.setStyle("-fx-background-color:#CCC");
		label.setText(getFirstKeyPart().toString());
		label.setTextAlignment(TextAlignment.CENTER);
		label.setAlignment(Pos.CENTER);
		
		label.relocate(0, 0);
		label.setPrefWidth(pane.getWidth());
		
		pane.widthProperty().addListener( (obs , oldValue, newValue) -> label.setPrefWidth((Double)newValue));
		//label.widthProperty().bind(pane.widthProperty());
		//t.setHeight(20);
		
		
//		Line l = new Line();
//		l.setStartX(100);
//		l.setStartY(20);
//		l.setEndX(400);
//		l.setEndY(20);
//		l.setStroke(Color.BLACK);;
		
		shape = new Rectangle();
		shape.setFill(Color.TRANSPARENT);
		shape.setStroke(Color.BLACK);
		shape.setStrokeWidth(1.0);
		((Rectangle)shape).setArcHeight(10.0);
		((Rectangle)shape).setArcWidth(10.0);
		
		
		((Rectangle)shape).setLayoutX(0);
		((Rectangle)shape).setLayoutY(0);
		
		shape.widthProperty().bind(pane.widthProperty());
		shape.heightProperty().bind(pane.heightProperty());
		
		
		
		pane.getChildren().addAll(shape, label);
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
