package org.jrebirth.af.showcase.diagram.ui.statemachine;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import org.jrebirth.af.component.behavior.swipable.SwipableBehavior;
import org.jrebirth.af.core.ui.simple.DefaultSimpleModel;

public class StateMachineModel extends DefaultSimpleModel<StackPane> {

    @Override
    protected void initSimpleView() {
        super.initSimpleView();

        final Text t = new Text();
        t.setText("StateMachine1");
        t.setLayoutX(0);
        t.setLayoutY(0);
        StackPane.setAlignment(t, Pos.TOP_CENTER);
        StackPane.setMargin(t, new Insets(0, 0, 0, 0));

        final Line l = new Line();
        l.setStartX(0);
        l.setStartY(20);
        l.setEndX(600);
        l.setEndY(20);
        l.setStroke(Color.BLACK);
        StackPane.setAlignment(l, Pos.TOP_CENTER);
        StackPane.setMargin(l, new Insets(20, 0, 0, 0));

        final Rectangle r = new Rectangle();
        r.setFill(Color.TRANSPARENT);
        r.setStroke(Color.BLACK);
        r.setArcHeight(10.0);
        r.setArcWidth(10.0);

        r.setWidth(600);
        r.setHeight(400);

        StackPane.setAlignment(t, Pos.CENTER);
        StackPane.setMargin(t, new Insets(0, 0, 0, 0));

        node().getChildren().addAll(r, l, t);

        addBehavior(SwipableBehavior.class);
    }

}
