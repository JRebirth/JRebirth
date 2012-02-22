package org.jrebirth.core.ui.adapter;

import javafx.scene.input.DragEvent;

/**
 * The class <strong>DragAdapter</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public interface DragAdapter {

    /**
     * Manage drag ANY events. Common supertype for all drag event types.
     * 
     * @param dragEvent the event to manage
     */
    void drag(DragEvent dragEvent);

    /**
     * Manage drag done events. This event occurs on drag-and-drop gesture source after its data has been dropped on a drop target.
     * 
     * @param dragEvent the event to manage
     */
    void dragDone(DragEvent dragEvent);

    /**
     * Manage drag dropped events. This event occurs when the mouse button is released during drag and drop gesture on a drop target.
     * 
     * @param dragEvent the event to manage
     */
    void dragDropped(DragEvent dragEvent);

    /**
     * Manage drag entered events. This event occurs when drag gesture enters a node.
     * 
     * @param dragEvent the event to manage
     */
    void dragEntered(DragEvent dragEvent);

    /**
     * Manage drag entered target events. This event occurs when drag gesture enters a node.
     * 
     * @param dragEvent the event to manage
     */
    void dragEnteredTarget(DragEvent dragEvent);

    /**
     * Manage drag exited events. This event occurs when drag gesture exits a node.
     * 
     * @param dragEvent the event to manage
     */
    void dragExited(DragEvent dragEvent);

    /**
     * Manage drag exited target events. This event occurs when drag gesture exitenters a node.
     * 
     * @param dragEvent the event to manage
     */
    void dragExitedTarget(DragEvent dragEvent);

    /**
     * Manage drag over events. his event occurs when drag gesture progresses within this node.
     * 
     * @param dragEvent the event to manage
     */
    void dragOver(DragEvent dragEvent);

}
