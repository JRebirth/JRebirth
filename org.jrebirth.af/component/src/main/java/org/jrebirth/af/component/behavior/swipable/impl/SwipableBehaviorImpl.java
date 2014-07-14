package org.jrebirth.af.component.behavior.swipable.impl;

import java.util.concurrent.atomic.AtomicBoolean;

import javafx.scene.input.SwipeEvent;

import org.jrebirth.af.component.behavior.dockable.data.Dockable;
import org.jrebirth.af.component.behavior.swipable.SwipableBehavior;
import org.jrebirth.af.core.behavior.AbstractModelBehavior;

public class SwipableBehaviorImpl extends AbstractModelBehavior<Dockable> implements SwipableBehavior {

    private final AtomicBoolean showing = new AtomicBoolean(false);

    @Override
    public void initBehavior() {

        getRootNode().addEventHandler(SwipeEvent.ANY, this::onSwipe);

    }

    public void onSwipe(final SwipeEvent event) {

        if (event.getTouchCount() == 2) {
            System.err.println("SWIPEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE TWICE");

            if (this.showing.get()) {

                // Hide

            } else {

                // Show

            }

        }

    }

}
