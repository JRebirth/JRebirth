package org.jrebirth.af.core.facade.factory;

import org.jrebirth.af.core.exception.CoreException;
import org.jrebirth.af.core.facade.FacadeReady;
import org.jrebirth.af.core.facade.WaveReady;

/**
 * The interface <strong>ComponentFactory</strong> is used to build Component (Model, Service, Command) classes.
 *
 * @author Sébastien Bordes
 */
public interface ComponentFactory {

    /**
     * .
     *
     * @param interfaceClass
     * @param implClass
     */
    void register(Class<? extends WaveReady<?>> interfaceClass, Class<? extends WaveReady<?>> implClass);

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
     * @throws CoreException CoreException if an error has occurred
     */
    <R extends Object> R buildComponent(final Class<R> clazz) throws CoreException;

}
