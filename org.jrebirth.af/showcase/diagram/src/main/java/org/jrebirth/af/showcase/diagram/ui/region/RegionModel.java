package org.jrebirth.af.showcase.diagram.ui.region;



import org.jrebirth.af.component.behavior.movable.Movable;
import org.jrebirth.af.component.behavior.selectable.Selectable;
import org.jrebirth.af.core.ui.simple.DefaultSimpleModel;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RegionModel extends DefaultSimpleModel<Pane> {

	private Rectangle shape;
	
	@Override
	protected void initModel() {
		super.initModel();
		
	}
	
	

	@Override
	protected void bind() {
		addBehavior(Selectable.of().model(this).shape(this.shape));
		addBehavior(Movable.of().model(this));
	}



	@Override
	protected void initSimpleView() {
		super.initSimpleView();
		
		shape = new Rectangle();
		shape.setFill(Color.TRANSPARENT);
		shape.setStroke(Color.BLACK);
		shape.setArcHeight(10.0);
		shape.setArcWidth(10.0);
		
		shape.setWidth(600);
		shape.setHeight(400);
		
		
		node().getChildren().add(shape);
	}
	
	@Override
	public String toString() {
		return key().key();
	}


}
