package org.jrebirth.core.facade;

import org.jrebirth.core.exception.CoreException;

/**
 * The interface <strong>ComponentFactory</strong> is used to build Component (Model, Service, Command) classes.
 * 
 * @author Sébastien Bordes
 */
public interface ComponentFactory {

    /**
     * Build a fresh instance of a component.<br />
     * (that implements {@link FacadeReady} interface)
     * 
     * @param clazz the component class
     * 
     * @return a new fresh instance of component
     * 
     * @param <R> the ReadyObject, it's a component class
     * 
     * @throws a CoreException if an error has occurred
     */
    <R extends Object> R buildComponent(Class<R> clazz) throws CoreException;

}
