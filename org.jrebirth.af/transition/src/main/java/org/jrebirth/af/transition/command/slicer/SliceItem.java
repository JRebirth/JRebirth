package org.jrebirth.af.transition.command.slicer;

import javafx.geometry.Bounds;
import javafx.scene.Node;

/**
 * The class <strong>SliceItem</strong>.
 *
 * @author Sébastien Bordes
 */
public class SliceItem {

    /** The node representing the slice. */
    private Node sliceNode;

    /** The original node bounds. */
    private Bounds nodeBounds;

    /**
     * Gets the slice node.
     *
     * @return the slice node
     */
    public Node getSliceNode() {
        return this.sliceNode;
    }

    /**
     * Sets the node representing the slice.
     *
     * @param sliceNode the new node representing the slice
     */
    public void setSliceNode(final Node sliceNode) {
        this.sliceNode = sliceNode;
    }

    /**
     * Gets the original node bounds.
     *
     * @return the original node bounds
     */
    public Bounds getNodeBounds() {
        return this.nodeBounds;
    }

    /**
     * Sets the original node bounds.
     *
     * @param nodeBounds the new original node bounds
     */
    public void setNodeBounds(final Bounds nodeBounds) {
        this.nodeBounds = nodeBounds;
    }

}
