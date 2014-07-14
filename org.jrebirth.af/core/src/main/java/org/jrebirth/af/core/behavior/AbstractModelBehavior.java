package org.jrebirth.af.core.behavior;

import javafx.scene.Node;

import org.jrebirth.af.core.ui.Model;

public abstract class AbstractModelBehavior<D extends BehaviorData> extends AbstractBehavior<D> {

    public Node getRootNode() {
        return ((Model) getComponent()).getRootNode();
    }

}
