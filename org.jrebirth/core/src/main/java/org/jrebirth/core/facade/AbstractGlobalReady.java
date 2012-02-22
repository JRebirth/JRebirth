package org.jrebirth.core.facade;

/**
 * The class <strong>AbstractGlobalReady</strong>.
 * 
 * The base class that wrap access to the global facade.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public abstract class AbstractGlobalReady implements GlobalReady {

    /** The main global facade. */
    private final GlobalFacade globalFacade;

    /**
     * Default Constructor.
     * 
     * @param globalFacade the unique global facade of the application
     */
    public AbstractGlobalReady(final GlobalFacade globalFacade) {
        super();
        this.globalFacade = globalFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final GlobalFacade getGlobalFacade() {
        return this.globalFacade;
    }
}
