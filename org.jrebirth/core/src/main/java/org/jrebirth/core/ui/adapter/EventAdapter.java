package org.jrebirth.core.ui.adapter;

import org.jrebirth.core.ui.impl.AbstractController;

/**
 * The class <strong>EventAdapter</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$.
 * 
 * @param <C> The controller class which manage this event adapter
 */
public interface EventAdapter<C extends AbstractController<?, ?>> {

    /**
     * @param controller The controller to set.
     */
    void setController(final C controller);
}
