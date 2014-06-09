package org.jrebirth.af.component.ui.behavior;

import javafx.scene.image.Image;

import org.jrebirth.af.component.ui.beans.TabBB;
import org.jrebirth.af.core.behavior.Behavior;
import org.jrebirth.af.core.resource.provided.JRebirthImages;

public interface TabBehavior extends Behavior<TabBB> {

    default String modelName() {
        return getClass().getSimpleName().replaceAll("Model", "");
    }

    default Image modelIcon() {
        return JRebirthImages.NOT_AVAILABLE.get();
    }
}
