package org.jrebirth.af.core.component.factory;

import org.jrebirth.af.api.component.basic.Component;
import org.jrebirth.af.api.component.factory.ComponentFactory;
import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.exception.CoreRuntimeException;
import org.jrebirth.af.core.facade.FacadeMessages;
import org.jrebirth.af.core.util.MultiMap;

/**
 * The class <strong>AnnotatedComponentFactory</strong>.
 *
 * @author Sébastien Bordes
 */
public final class AnnotatedComponentFactory implements ComponentFactory, FacadeMessages {

    /** The MultiMap taht sotore all implementation per interface. */
    private final MultiMap<Class<?>, Class<?>> implementations = new MultiMap<>();

    /**
     * Default Constructor.
     */
    public AnnotatedComponentFactory() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void register(final Class<? extends Component<?>> interfaceClass, final Class<? extends Component<?>> implClass) {

        if (interfaceClass.isAssignableFrom(implClass)) {

            this.implementations.add(interfaceClass, implClass);

        } else {
            throw new CoreRuntimeException("");
        }

    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <R> R buildComponent(final Class<R> clazz) throws CoreException {

        try {

            R component = null;

            if (clazz.isInterface()) {

                component = (R) this.implementations.get(clazz).stream().findFirst().get().newInstance();

            } else {
                component = clazz.newInstance();
            }

            return component;

        } catch (InstantiationException | IllegalAccessException e) {
            throw new CoreException(COMPONENT_BUILD_ERROR.getText(clazz.getName()), e);
        }
    }

}
