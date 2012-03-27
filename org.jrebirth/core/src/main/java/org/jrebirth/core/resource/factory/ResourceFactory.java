package org.jrebirth.core.resource.factory;

/**
 * The interface <strong>ResourceFactory</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 * 
 * @param <E> The enumeration used to wrap the resource
 * @param <P> The params object that store primitive resources values
 * @param <R> The resource managed
 */
public interface ResourceFactory<E, P, R> {

    /**
     * .
     * 
     * @param key
     * @param params
     */
    void storeParams(E key, P params);

    /**
     * .
     * 
     * @param key
     * @return
     */
    public P getParam(final E key);

    /**
     * Retrieve the resource. And build it if it didn't be done before.
     * 
     * @param key the enum as a key
     * 
     * @return the resource
     */
    R get(final E key);

    /**
     * Store a new resource.
     * 
     * @param key the enum used as a key
     * @param resource the resource to weakly store
     */
    void set(final E key, final R resource);
}
