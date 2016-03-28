package org.jrebirth.af.component.behavior.swipable.impl;

import javafx.event.EventType;
import javafx.scene.input.SwipeEvent;

import org.jrebirth.af.api.annotation.PriorityLevel;
import org.jrebirth.af.api.module.Register;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.component.behavior.swipable.SwipableBehavior;
import org.jrebirth.af.component.behavior.swipable.data.Swipable;
import org.jrebirth.af.core.component.behavior.AbstractModelBehavior;

@Register(value = SwipableBehavior.class, priority = PriorityLevel.Normal)
public class SwipableBehaviorImpl extends AbstractModelBehavior<Swipable> implements SwipableBehavior {

    // private final AtomicBoolean showing = new AtomicBoolean(false);

    @Override
    public void initBehavior() {

        getData().node().addEventHandler(SwipeEvent.ANY, this::onSwipe);

    }

    public void onSwipe(final SwipeEvent event) {

        if (event.getTouchCount() == getData().touchCount()) {
            System.err.println("SWIPEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE TWICE");

            final Model model = getLocalFacade().getGlobalFacade().getUiFacade().retrieve(getData().modelKey());

            if (model != null) {
                final EventType<SwipeEvent> et = event.getEventType();
                if (et == getData().redo()) {
                    // Show
                    model.doShowView(null);
                } else if (et == getData().undo()) {
                    // Hide
                    model.doHideView(null);
                }
            }
            // if (this.showing.get()) {
            //
            // // Hide
            //
            // } else {
            //
            // // Show
            //
            // }

        }

    }

    @Override
    protected void initInnerComponents() {
    }

}
