package org.jrebirth.af.core.component.factory;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jrebirth.af.api.component.factory.ComponentFactory;
import org.jrebirth.af.api.component.factory.RegistrationItem;
import org.jrebirth.af.api.component.factory.RegistrationPointItem;
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

    /** The Map that stores all settings for each registration point. */
    private final Map<Class<?>, RegistrationPointItem> definitions = new HashMap<>();

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
    public void define(final RegistrationPointItem item) {
        this.definitions.put(item.interfaceClass(), item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void register(final RegistrationItem item) {

        if (item.interfaceClass().isAssignableFrom(item.implClass())) {
            this.definitions.put(item.interfaceClass(), RegistrationPointItemBase.create()
                                                                                 .interfaceClass(item.interfaceClass())
                                                                                 .exclusive(false)
                                                                                 .reverse(false));
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

            if (clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers())) {

                final RegistrationPointItem definition = this.definitions.get(clazz);

                final List<RegistrationItem> items = this.implementations.get(clazz);
                if (items != null) {

                    Collections.sort(items);

                    // Reverse the order of registration items when we want o inverse the priorities
                    if (definition.reverse()) {
                        Collections.reverse(items);
                    }

                    // Limit the number of component to build to onl one when exclusive mode is activated
                    final int nbComponent = definition.exclusive() ? 1 : Integer.MAX_VALUE;

                    for (final RegistrationItem item : items.stream().limit(nbComponent).collect(Collectors.toList())) {
                        components.add((R) item.implClass().newInstance());
                    }
                }

            } else {
                // Simply build a instance of the given concrete class
                components.add(clazz.newInstance());
            }

            return components;

        } catch (InstantiationException | IllegalAccessException e) {
            throw new CoreException(COMPONENT_BUILD_ERROR.getText(clazz.getName()), e);
        }
    }

}
