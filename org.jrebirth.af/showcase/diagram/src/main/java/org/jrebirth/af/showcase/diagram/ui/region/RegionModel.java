package org.jrebirth.af.showcase.diagram.ui.region;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import org.jrebirth.af.core.ui.simple.DefaultSimpleModel;

public class RegionModel extends DefaultSimpleModel<Pane> {

    @Override
    protected void initSimpleView() {
        super.initSimpleView();

        final Rectangle r = new Rectangle();
        r.setFill(Color.TRANSPARENT);
        r.setStroke(Color.BLACK);
        r.setArcHeight(10.0);
        r.setArcWidth(10.0);

        r.setWidth(600);
        r.setHeight(400);

        node().getChildren().add(r);
    }

}
