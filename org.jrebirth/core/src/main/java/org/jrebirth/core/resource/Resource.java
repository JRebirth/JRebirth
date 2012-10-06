package org.jrebirth.core.resource;

/**
 * The class <strong>Resource</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @param <R> The resource managed
 * @param <F> The factory to use
 */
public interface Resource<R, F> {

    /**
     * Return the resource unique instance.
     * 
     * @return the resource
     */
    R get();

    /**
     * Return the resource factory.
     * 
     * @return the resource factory
     */
    F factory();
}
