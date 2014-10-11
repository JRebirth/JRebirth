package org.jrebirth.af.component.behavior.swipable.impl;

import java.util.concurrent.atomic.AtomicBoolean;

import javafx.event.EventType;
import javafx.scene.input.SwipeEvent;

import org.jrebirth.af.component.behavior.dockable.data.Dockable;
import org.jrebirth.af.component.behavior.swipable.SwipableBehavior;
import org.jrebirth.af.component.behavior.swipable.data.Swipable;
import org.jrebirth.af.core.behavior.AbstractModelBehavior;
import org.jrebirth.af.core.ui.Model;
import org.jrebirth.af.processor.annotation.Register;
import org.jrebirth.af.processor.annotation.RegistrationPriority;

@Register(value = SwipableBehavior.class, priority = RegistrationPriority.Normal)
public class SwipableBehaviorImpl extends AbstractModelBehavior<Swipable> implements SwipableBehavior {

    // private final AtomicBoolean showing = new AtomicBoolean(false);

    @Override
    public void initBehavior() {

        getData().node().addEventHandler(SwipeEvent.ANY, this::onSwipe);

    }

    public void onSwipe(final SwipeEvent event) {

        if (event.getTouchCount() == getData().touchCount()) {
            System.err.println("SWIPEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE TWICE");

            Model model = getLocalFacade().getGlobalFacade().getUiFacade().retrieve(getData().modelKey());

            if (model != null) {
                EventType<SwipeEvent> et = event.getEventType();
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

    protected void initInnerComponents() {
    }

}
