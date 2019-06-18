package org.jrebirth.af.component.behavior.resizable;

import javafx.geometry.Pos;

import org.jrebirth.af.api.component.behavior.Behavior;
import org.jrebirth.af.api.module.RegistrationPoint;
import org.jrebirth.af.api.ui.Model;

@RegistrationPoint
public interface ResizableBehavior extends Behavior<Resizable, Model> {

    void resize(Model model, double deltaWidth, double deltaHeight, Pos position);

    void showHandles();

    void hideHandles();
}
