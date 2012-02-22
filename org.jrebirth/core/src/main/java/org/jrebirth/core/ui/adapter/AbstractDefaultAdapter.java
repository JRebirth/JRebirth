package org.jrebirth.core.ui.adapter;

import org.jrebirth.core.ui.impl.AbstractController;

/**
 * The class <strong>AbstractDefaultAdapter</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 * 
 * @param <C> The controller class which manage this event adapter
 */
public abstract class AbstractDefaultAdapter<C extends AbstractController<?, ?>> implements EventAdapter<C> {

    /** The controller that manage these events. */
    private C controller;

    /**
     * @param controller The controller to set.
     */
    @Override
    public void setController(final C controller) {
        this.controller = controller;
    }

    /**
     * @return Returns the controller.
     */
    public C getController() {
        return this.controller;
    }

}
