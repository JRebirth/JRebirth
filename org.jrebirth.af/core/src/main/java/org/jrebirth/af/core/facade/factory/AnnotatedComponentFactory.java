package org.jrebirth.af.core.facade.factory;

import org.jrebirth.af.core.exception.CoreException;
import org.jrebirth.af.core.exception.CoreRuntimeException;
import org.jrebirth.af.core.facade.FacadeMessages;
import org.jrebirth.af.core.facade.WaveReady;
import org.jrebirth.af.core.util.MultiMap;

/**
 * The class <strong>DefaultComponentFactory</strong>.
 *
 * @author Sébastien Bordes
 */
public final class AnnotatedComponentFactory implements ComponentFactory, FacadeMessages {

    private final MultiMap<Class<?>, Class<?>> implementations = new MultiMap<>();

    /**
     *
     */
    public AnnotatedComponentFactory() {
        super();
    }

    @Override
    public void register(final Class<? extends WaveReady<?>> interfaceClass, final Class<? extends WaveReady<?>> implClass) {

        if (interfaceClass.isAssignableFrom(implClass)) {

            this.implementations.add(interfaceClass, implClass);

        } else {
            throw new CoreRuntimeException("");
        }

    }

    /**
     * {@inheritDoc}
     */
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
