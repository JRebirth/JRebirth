package org.jrebirth.af.component.behavior.selectable;

import java.util.NoSuchElementException;

import javafx.scene.input.MouseEvent;

import org.jrebirth.af.api.annotation.Link;
import org.jrebirth.af.api.annotation.PriorityLevel;
import org.jrebirth.af.api.module.Register;
import org.jrebirth.af.component.behavior.resizable.ResizableBehavior;
import org.jrebirth.af.component.service.SelectionService;
import org.jrebirth.af.core.component.behavior.AbstractModelBehavior;

@Register(value = SelectableBehavior.class, priority = PriorityLevel.Normal)
public class SelectableBehaviorImpl extends AbstractModelBehavior<Selectable> implements SelectableBehavior {

    @Link
    private SelectionService selectionService;

    private ResizableBehavior resizableBehavior;

    @Override
    public void initBehavior() {
        try {
            this.resizableBehavior = getKeyPart(ResizableBehavior.class);
        } catch (final NoSuchElementException e) {
            this.resizableBehavior = data().model().getBehavior(ResizableBehavior.class);
        }
        data().shape().addEventFilter(MouseEvent.MOUSE_PRESSED, this::onToggle);

    }

    private void onToggle(final MouseEvent event) {

        final boolean isSelected = this.selectionService.toggleSelection(data().model(), event.isControlDown());
        if (isSelected) {
            select();
        } else {
            unselect();
        }

    }

    @Override
    protected void initInnerComponents() {
    }

    @Override
    public void unselect() {
        data().shape().setStrokeWidth(1.0);
        if (this.resizableBehavior != null) {
            this.resizableBehavior.hideHandles();
        }
    }

    @Override
    public void select() {
        data().shape().setStrokeWidth(3.0);
        if (this.resizableBehavior != null) {
            this.resizableBehavior.showHandles();
        }
    }

}
