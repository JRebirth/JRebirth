package org.jrebirth.af.core.component.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jrebirth.af.api.component.factory.ComponentFactory;
import org.jrebirth.af.api.component.factory.RegistrationItem;
import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.exception.CoreRuntimeException;
import org.jrebirth.af.core.facade.FacadeMessages;
import org.jrebirth.af.core.util.MultiMap;

/**
 * The class <strong>DefaultComponentFactory</strong>.
 *
 * @author Sébastien Bordes
 */
public final class DefaultComponentFactory implements ComponentFactory, FacadeMessages {

    /** The MultiMap that stores all implementations per interface. */
    private final MultiMap<Class<?>, RegistrationItem> implementations = new MultiMap<>();

    /**
     * Default Constructor.
     */
    public DefaultComponentFactory() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void register(final RegistrationItem item) {

        if (item.interfaceClass().isAssignableFrom(item.implClass())) {

            this.implementations.add(item.interfaceClass(), item);

        } else {
            throw new CoreRuntimeException("");
        }

    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <R> List<R> buildComponents(final Class<R> clazz) throws CoreException {

        try {

            final List<R> components = new ArrayList<>();

            if (clazz.isInterface()) {

                final List<RegistrationItem> items = this.implementations.get(clazz);
                Collections.sort(items);// FIXME Manage NPE

                for (final RegistrationItem item : items) {
                    components.add((R) item.implClass().newInstance());
                }

            } else {
                components.add(clazz.newInstance());
            }

            return components;

        } catch (InstantiationException | IllegalAccessException e) {
            throw new CoreException(COMPONENT_BUILD_ERROR.getText(clazz.getName()), e);
        }
    }

}
