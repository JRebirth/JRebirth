package org.jrebirth.core.resource.factory;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * The class <strong>ResourceManager</strong>.
 * 
 * The base abstract class used to manage resource weakly.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 * 
 * @param <E> The enumeration used to wrap the resource
 * @param <R> The resource managed
 */
public abstract class AbstractResourceFactory<E, R> implements ResourceFactory<E, R> {

    /** The resource weak Map. */
    private final Map<E, R> resourceMap = new WeakHashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public R get(final E key) {
        // Retrieve the resource into the map
        R resource = this.resourceMap.get(key);
        // The resource may be null if nobody use it
        if (resource == null) {
            // So we must rebuild an instance
            resource = buildResource(key);
            // and then store it weakly
            set(key, resource);
        }
        return resource;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(final E key, final R resource) {
        this.resourceMap.put(key, resource);
    }

    /**
     * Build the resource requested.
     * 
     * @param key the enum used as a key
     * 
     * @return the resource built
     */
    protected abstract R buildResource(E key);

}
