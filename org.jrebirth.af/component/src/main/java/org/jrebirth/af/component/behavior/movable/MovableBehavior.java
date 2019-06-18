package org.jrebirth.af.component.behavior.movable;

import org.jrebirth.af.api.component.behavior.Behavior;
import org.jrebirth.af.api.module.RegistrationPoint;
import org.jrebirth.af.api.ui.Model;

@RegistrationPoint
public interface MovableBehavior extends Behavior<Movable, Model> {
    void move(double x, double y);
}
