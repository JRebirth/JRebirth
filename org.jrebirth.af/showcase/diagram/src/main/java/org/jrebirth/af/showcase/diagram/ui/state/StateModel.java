package org.jrebirth.af.showcase.diagram.ui.state;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import org.jrebirth.af.core.ui.simple.DefaultSimpleModel;

public class StateModel extends DefaultSimpleModel<Pane> {

    @Override
    protected void initSimpleView() {
        super.initSimpleView();

        final Text t = new Text();
        t.setText("StateMachine1");

        final StackPane sp = new StackPane();

        final Line l = new Line();
        l.setStartX(0);
        l.setStartY(20);
        l.setEndX(600);
        l.setEndY(20);
        ;
        l.setStroke(Color.BLACK);
        ;

        final Rectangle r = new Rectangle();
        r.setFill(Color.TRANSPARENT);
        r.setStroke(Color.BLACK);
        r.setArcHeight(10.0);
        r.setArcWidth(10.0);

        r.setWidth(600);
        r.setHeight(400);

        sp.getChildren().addAll(r, l, t);

        node().getChildren().add(sp);
    }

}
