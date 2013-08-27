package org.jrebirth.core.facade;

import org.jrebirth.core.exception.CoreException;

/**
 * The class <strong>DefaultComponentFactory</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class DefaultComponentFactory implements ComponentFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> R buildComponent(final Class<R> clazz) throws CoreException {

        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new CoreException("Impossible to build the the component " + clazz.getName(), e);
        }
    }

}
