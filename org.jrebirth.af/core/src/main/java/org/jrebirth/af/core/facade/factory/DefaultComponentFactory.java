package org.jrebirth.af.core.facade.factory;

import org.jrebirth.af.api.component.Component;
import org.jrebirth.af.api.component.factory.ComponentFactory;
import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.core.facade.FacadeMessages;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public void register(final Class<? extends Component<?>> interfaceClass, final Class<? extends Component<?>> implClass) {
        // Nothing to do yet
    }

}
