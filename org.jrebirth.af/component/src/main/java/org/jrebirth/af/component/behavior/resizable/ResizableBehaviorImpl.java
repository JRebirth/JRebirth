package org.jrebirth.af.component.behavior.resizable;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

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

	private Rectangle r;

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
        e.consume();
        this.selectionService.getResizableModel().stream()
                             .forEach(m -> resize(m, tx, ty, (Pos) ((Node) e.getSource()).getUserData()));

    }

    @Override
    public void resize(final Model model, final double deltaWidth, final double deltaHeight, final Pos position) {
    	Node node = ((ResizableModel)model).shape();
    	System.err.println("layout x="+ node.getLayoutX() +  "  y="+ node.getLayoutY());
    	System.err.println("layout w="+ node.getLayoutBounds().getWidth() +  "  h="+ node.getLayoutBounds().getHeight());
    	//System.err.println("local w="+ node.getBoundsInLocal().getWidth() +  "  h="+ node.getBoundsInLocal().getHeight());
    	//System.err.println("parent w="+ node.getBoundsInParent().getWidth() +  "  h="+ node.getBoundsInParent().getHeight());
    	System.err.println("dw="+ deltaWidth +  "  dh="+ deltaHeight);
    	
    	switch (position) {
            case TOP_LEFT:
                ((Pane)node).setPrefSize(node.getLayoutBounds().getWidth() - deltaWidth, node.getLayoutBounds().getHeight()-deltaHeight);
                ((Pane)node).setLayoutX(node.getLayoutX() + deltaWidth);
                ((Pane)node).setLayoutY(node.getLayoutY() + deltaHeight);
                break;
            case TOP_CENTER:
                ((Pane)node).setPrefSize(node.getLayoutBounds().getWidth(), node.getLayoutBounds().getHeight()-deltaHeight);
                ((Pane)node).setLayoutY(node.getLayoutY() + deltaHeight);
                break;
            case TOP_RIGHT:
                ((Pane)node).setPrefSize(node.getLayoutBounds().getWidth() +deltaWidth, node.getLayoutBounds().getHeight()-deltaHeight);
                ((Pane)node).setLayoutY(node.getLayoutY() + deltaHeight);
                break;
            case CENTER_LEFT:
                ((Pane)node).setPrefSize(node.getLayoutBounds().getWidth() - deltaWidth, node.getLayoutBounds().getHeight());
                ((Pane)node).setMinSize(node.getLayoutBounds().getWidth() - deltaWidth, node.getLayoutBounds().getHeight());
                ((Pane)node).setMaxSize(node.getLayoutBounds().getWidth() - deltaWidth, node.getLayoutBounds().getHeight());
                ((Pane)node).setLayoutX(node.getLayoutX() + deltaWidth);
                break;
            case CENTER_RIGHT:
                ((Pane)node).setPrefSize(node.getLayoutBounds().getWidth() + deltaWidth, node.getLayoutBounds().getHeight());
                break;

            case BOTTOM_LEFT:
                ((Pane)node).setPrefSize(node.getLayoutBounds().getWidth() - deltaWidth, node.getLayoutBounds().getHeight()+deltaHeight);
                ((Pane)node).setLayoutX(node.getLayoutX() + deltaWidth);
                break;
            case BOTTOM_CENTER:
                ((Pane)node).setPrefSize(node.getLayoutBounds().getWidth(), node.getLayoutBounds().getHeight()+deltaHeight);
                break;
            case BOTTOM_RIGHT:
                ((Pane)node).setPrefSize(node.getLayoutBounds().getWidth() + deltaWidth, node.getLayoutBounds().getHeight() + deltaHeight);
                break;

                
            default:
                break;
        }
    	
    	
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

            final DoubleProperty x = data().shape().layoutXProperty();
            final DoubleProperty y = data().shape().layoutYProperty();
            final ReadOnlyDoubleProperty w = data().shape().widthProperty();
            final ReadOnlyDoubleProperty h = data().shape().heightProperty();

            buildHandle(Pos.TOP_LEFT, x.add(-10), y.add(-10));
            buildHandle(Pos.TOP_CENTER, x.add(w.divide(2).add(-5)), y.add(-10));
            buildHandle(Pos.TOP_RIGHT, x.add(w).add(4), y.add(-10));

            buildHandle(Pos.CENTER_LEFT, x.add(-10), y.add(h.divide(2)).add(-5));
            buildHandle(Pos.CENTER_RIGHT, x.add(w).add(4), y.add(h.divide(2)).add(-5));

            buildHandle(Pos.BOTTOM_LEFT, x.add(-10), y.add(h).add(4));
            buildHandle(Pos.BOTTOM_CENTER, x.add(w.divide(2)).add(-5), y.add(h).add(4));
            buildHandle(Pos.BOTTOM_RIGHT, x.add(w).add(4), y.add(h).add(4));

            r = new Rectangle();
            r.setFill(Color.TRANSPARENT);
            r.setStroke(Color.GRAY);
            r.setStrokeWidth(1.0);
            r.setStrokeType(StrokeType.CENTERED);
            r.setStrokeDashOffset(2.0);
            
            r.layoutXProperty().bind(x.add(-6));
            r.layoutYProperty().bind(y.add(-6));
            r.widthProperty().bind(w.add(12));
            r.heightProperty().bind(h.add(12));
            
            ((Group) data().model().node()).getChildren().add(r);
            ((Group) data().model().node()).getChildren().addAll(this.handles);
        }

    }

    @Override
    public void hideHandles() {

        this.handles.stream().forEach(h -> {
            h.setOnMousePressed(null);
            h.setOnMouseDragged(null);
        });
        
        ((Group) data().model().node()).getChildren().remove(r);
        ((Group) data().model().node()).getChildren().removeAll(this.handles);
        this.handles.clear();
    }

    private void buildHandle(final Pos pos, final DoubleExpression x, final DoubleExpression y) {
        final Rectangle handle = new Rectangle(6, 6);
        handle.setUserData(pos);
        handle.setStrokeWidth(2.0);
        handle.setStroke(Color.TRANSPARENT);
        handle.setFill(Color.BLACK);

        handle.layoutXProperty().bind(x);
        handle.layoutYProperty().bind(y);

        handle.setOnMousePressed(this::mousePressed);
        handle.setOnMouseDragged(this::mouseDragged);

        this.handles.add(handle);
    }

}
