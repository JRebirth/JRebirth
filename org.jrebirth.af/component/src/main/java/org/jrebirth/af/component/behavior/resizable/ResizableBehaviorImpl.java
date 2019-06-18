package org.jrebirth.af.component.behavior.resizable;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import org.jrebirth.af.api.annotation.Link;
import org.jrebirth.af.api.annotation.PriorityLevel;
import org.jrebirth.af.api.module.Register;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.component.service.SelectionService;
import org.jrebirth.af.core.component.behavior.AbstractModelBehavior;

@Register(value = ResizableBehavior.class, priority = PriorityLevel.Normal)
public class ResizableBehaviorImpl extends AbstractModelBehavior<Resizable> implements ResizableBehavior {

    @Link
    private SelectionService selectionService;

    private final List<Rectangle> handles = new ArrayList<>();

    double sourceX, sourceY;

    @Override
    public void initBehavior() {

    }

    public void mousePressed(final MouseEvent event) {
        this.sourceX = event.getSceneX();
        this.sourceY = event.getSceneY();
        event.consume();
    }

    public void mouseDragged(final MouseEvent e) {

        final double tx = e.getSceneX() - this.sourceX;
        final double ty = e.getSceneY() - this.sourceY;
        this.sourceX = e.getSceneX();
        this.sourceY = e.getSceneY();
        this.selectionService.getResizableModel().stream()
                             .forEach(m -> resize(m, tx, ty, (Pos) ((Node) e.getSource()).getUserData()));

        e.consume();
    }

    @Override
    public void resize(final Model model, final double deltaWidth, final double deltaHeight, final Pos position) {
        ((Rectangle) ((ResizableModel) model).shape()).setWidth(((Rectangle) data().shape()).getWidth() + deltaWidth);
        ((Rectangle) ((ResizableModel) model).shape()).setHeight(((Rectangle) data().shape()).getHeight() + deltaHeight);
    }

    public void mouseReleased(final MouseEvent t) {
        // selectionService.getResizableModel().stream().forEach(m -> validate(m));
    }

    private void validate(final Model model) {
        // double x = model.node().getLayoutX();
        // double tx = model.node().getTranslateX();
        // model.node().setLayoutX(x + tx);
        // model.node().setTranslateX(0);
        //
        // double y = model.node().getLayoutY();
        // double ty = model.node().getTranslateY();
        // model.node().setLayoutY(y + ty);
        // model.node().setTranslateY(0);
        //
        // model.getBehavior(ResizableBehavior.class).resize(x + tx, y + ty, Pos.CENTER);
    }

    @Override
    protected void initInnerComponents() {
    }

    @Override
    public void showHandles() {

        if (this.handles.isEmpty()) {
            // double x = data().shape().getLayoutX();
            // double y = data().shape().getLayoutY();
            // double w = ((Rectangle) data().shape()).getWidth();
            // double h = ((Rectangle) data().shape()).getHeight();

            final DoubleProperty x = data().shape().layoutXProperty();
            final DoubleProperty y = data().shape().layoutYProperty();
            final DoubleProperty w = ((Rectangle) data().shape()).widthProperty();
            final DoubleProperty h = ((Rectangle) data().shape()).heightProperty();

            buildHandle(Pos.TOP_LEFT, x, y);
            buildHandle(Pos.TOP_CENTER, x.add(w.divide(2)), y);
            buildHandle(Pos.TOP_RIGHT, x.add(w), y);

            buildHandle(Pos.CENTER_LEFT, x, y.add(h.divide(2)));
            buildHandle(Pos.CENTER_RIGHT, x.add(w), y.add(h.divide(2)));

            buildHandle(Pos.BOTTOM_LEFT, x, y.add(h));
            buildHandle(Pos.BOTTOM_CENTER, x.add(w.divide(2)), y.add(h));
            buildHandle(Pos.BOTTOM_RIGHT, x.add(w), y.add(h));

            ((Group) data().model().node()).getChildren().addAll(this.handles);
        }

    }

    @Override
    public void hideHandles() {

        this.handles.stream().forEach(h -> {
            h.setOnMousePressed(null);
            h.setOnMouseDragged(null);
        });
        ((Group) data().model().node()).getChildren().removeAll(this.handles);
        this.handles.clear();
    }

    private void buildHandle(final Pos pos, final DoubleExpression x, final DoubleExpression y) {
        final Rectangle handle = new Rectangle(5, 5);
        handle.setUserData(pos);
        handle.setStrokeWidth(1.0);
        handle.setStroke(Color.WHITE);
        handle.setFill(Color.BLACK);

        handle.layoutXProperty().bind(x);
        handle.layoutYProperty().bind(y);

        handle.setOnMousePressed(this::mousePressed);
        handle.setOnMouseDragged(this::mouseDragged);
        // data().model().node().setOnMouseReleased(this::mouseReleased);

        this.handles.add(handle);
    }

}
