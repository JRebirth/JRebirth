package org.jrebirth.af.core.facade;

import org.jrebirth.af.core.exception.CoreException;

/**
 * The class <strong>DefaultComponentFactory</strong>.
 *
 * @author Sébastien Bordes
 */
public final class DefaultComponentFactory implements ComponentFactory, FacadeMessages {

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> R buildComponent(final Class<R> clazz) throws CoreException {

        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new CoreException(COMPONENT_BUILD_ERROR.getText(clazz.getName()), e);
        }
    }

}
