package org.jrebirth.af.component.behavior.selectable;

import org.jrebirth.af.api.component.behavior.Behavior;
import org.jrebirth.af.api.module.RegistrationPoint;
import org.jrebirth.af.api.ui.Model;

@RegistrationPoint
public interface SelectableBehavior extends Behavior<Selectable, Model> {

    void unselect();

    void select();
}
