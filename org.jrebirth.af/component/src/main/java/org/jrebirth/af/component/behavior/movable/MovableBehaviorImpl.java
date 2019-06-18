package org.jrebirth.af.component.behavior.movable;

import javafx.scene.input.MouseEvent;

import org.jrebirth.af.api.annotation.Link;
import org.jrebirth.af.api.annotation.PriorityLevel;
import org.jrebirth.af.api.module.Register;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.component.service.SelectionService;
import org.jrebirth.af.core.component.behavior.AbstractModelBehavior;

@Register(value = MovableBehavior.class, priority = PriorityLevel.Normal)
public class MovableBehaviorImpl extends AbstractModelBehavior<Movable> implements MovableBehavior {

    @Link
    private SelectionService selectionService;

    double sourceX, sourceY;

    @Override
    public void initBehavior() {

        data().model().node().setOnMousePressed(this::mousePressed);
        data().model().node().setOnMouseDragged(this::mouseDragged);
        data().model().node().setOnMouseReleased(this::mouseReleased);
    }

    public void mousePressed(final MouseEvent t) {
        this.sourceX = t.getSceneX();
        this.sourceY = t.getSceneY();
    }

    public void mouseDragged(final MouseEvent e) {
        final double tx = e.getSceneX() - this.sourceX;
        final double ty = e.getSceneY() - this.sourceY;
        this.selectionService.getMovableModel().stream().forEach(m -> translate(m, tx, ty));
    }

    private void translate(final Model model, final double tx, final double ty) {
        model.node().setTranslateX(tx);
        model.node().setTranslateY(ty);
    }

    public void mouseReleased(final MouseEvent t) {
        this.selectionService.getMovableModel().stream().forEach(m -> validate(m));
    }

    private void validate(final Model model) {
        final double x = model.node().getLayoutX();
        final double tx = model.node().getTranslateX();
        model.node().setLayoutX(x + tx);
        model.node().setTranslateX(0);

        final double y = model.node().getLayoutY();
        final double ty = model.node().getTranslateY();
        model.node().setLayoutY(y + ty);
        model.node().setTranslateY(0);

        model.getBehavior(MovableBehavior.class).move(x + tx, y + ty);
    }

    @Override
    protected void initInnerComponents() {
    }

    @Override
    public void move(final double x, final double y) {

    }

}
