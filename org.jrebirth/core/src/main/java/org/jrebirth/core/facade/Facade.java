package org.jrebirth.core.facade;

/**
 * The class <strong>Facade</strong>.
 * 
 * The interface to respect in order to implement a local facade (per pattern type)
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 * 
 * @param <R> A type that implements FacadeReady
 */
public interface Facade<R extends FacadeReady<R>> {

    /**
     * Get the unique global facade.
     * 
     * @return the global facade
     */
    GlobalFacade getGlobalFacade();

    /**
     * Register a new ready object component.
     * 
     * @param readyObject the component to register
     * @param keyPart the unique key for multiton FacadeReady element, could be omitted for singleton
     * 
     * @param <E> the type of the ready object used
     */
    <E extends R> void register(final E readyObject, Object... keyPart);

    /**
     * Unregister a new ready object component.
     * 
     * @param readyObject the component to unregister
     * @param keyPart the unique key for multiton FacadeReady element, could be omitted for singleton
     * 
     * @param <E> the type of the ready object used
     */
    <E extends R> void unregister(final E readyObject, Object... keyPart);

    /**
     * Retrieve a ready object component.<br />
     * May allow to build it if it hadn't been done previously
     * 
     * @param clazz the component class
     * @param keyPart the unique key for multiton FacadeReady element, could be omitted for singleton
     * 
     * @return the singleton of the component
     * 
     * @param <E> the type of the ready object to retrieve
     */
    <E extends R> E retrieve(final Class<E> clazz, Object... keyPart);

    /**
     * Check if the component has already been created and stored.
     * 
     * @param clazz the component class to check
     * @param keyPart the unique key for multiton FacadeReady element, could be omitted for singleton
     * 
     * @return true if the component exists
     */
    boolean exists(final Class<? extends R> clazz, Object... keyPart);

}
