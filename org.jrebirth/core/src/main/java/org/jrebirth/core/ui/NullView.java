package org.jrebirth.core.ui;

import javafx.scene.Node;

/**
 * The class <strong>NullView</strong>.
 * 
 * @author Sébastien Bordes
 */
public interface NullView extends View<Model, Node, Controller<?, ?>> {

    /** The default view used when no view is required. */
    View<?, ?, ?> NULL_VIEW = null;
}
