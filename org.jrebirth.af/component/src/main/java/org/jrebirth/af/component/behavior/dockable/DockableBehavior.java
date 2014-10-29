package org.jrebirth.af.component.behavior.dockable;

import javafx.scene.image.Image;

import org.jrebirth.af.api.behavior.Behavior;
import org.jrebirth.af.component.behavior.dockable.data.Dockable;
import org.jrebirth.af.component.resources.ComponentImages;
import org.jrebirth.af.processor.annotation.RegistrationPoint;

@RegistrationPoint(exclusive = true)
public interface DockableBehavior extends Behavior<Dockable> {

    default String modelName() {
        return getClass().getSimpleName().replaceAll("Model", "");
    }

    default Image modelIcon() {
        return ComponentImages.DefaultDockableIcon.get();
    }
}
