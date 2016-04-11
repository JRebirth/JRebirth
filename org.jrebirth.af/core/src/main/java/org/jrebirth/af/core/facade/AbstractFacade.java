/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
 * Contact : sebastien.bordes@jrebirth.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.af.core.facade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.stream.Collectors;

import org.jrebirth.af.api.command.Command;
import org.jrebirth.af.api.component.basic.Component;
import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.exception.CoreRuntimeException;
import org.jrebirth.af.api.exception.JRebirthThreadException;
import org.jrebirth.af.api.facade.FacadeReady;
import org.jrebirth.af.api.facade.GlobalFacade;
import org.jrebirth.af.api.facade.JRebirthEventType;
import org.jrebirth.af.api.facade.LocalFacade;
import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.api.log.JRLogger;
import org.jrebirth.af.api.service.Service;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.core.key.Key;
import org.jrebirth.af.core.log.JRLoggerFactory;

/**
 * The class <strong>AbstractFacade</strong>.
 *
 * An abstract facade can manage singleton of object which implements the FacadeReady interface
 *
 * @author Sébastien Bordes
 *
 * @param <R> A type that implements FacadeReady
 */
public abstract class AbstractFacade<R extends FacadeReady<R>> extends AbstractGlobalReady implements LocalFacade<R>, FacadeMessages {

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(AbstractFacade.class);

    /** The map that store FacadeReady singletons. */
    private final Map<UniqueKey<? extends R>, Set<R>> componentMap;

    /**
     * Default Constructor.
     *
     * @param globalFacade the global facade of the application
     */
    public AbstractFacade(final GlobalFacade globalFacade) {
        super(globalFacade);
        // Initialize the synchronized map for singletons
        this.componentMap = Collections.synchronizedMap(new WeakHashMap<UniqueKey<? extends R>, Set<R>>());
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <E extends R> void register(final UniqueKey<E> uniqueKey, final E readyObject) {

        // Synchronize the registration
        synchronized (this.componentMap) {

            // reload the key
            readyObject.key((UniqueKey<R>) uniqueKey);

            // Attach the facade to allow to retrieve any components
            readyObject.localFacade(this);
            if (!this.componentMap.containsKey(readyObject.key())) {
                final Set<R> weakHashSet = Collections.newSetFromMap(new WeakHashMap<R, Boolean>());
                // Store the component into the singleton map
                this.componentMap.put(readyObject.key(), weakHashSet);
            }
            this.componentMap.get(readyObject.key()).add(readyObject);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <E extends R> void register(final E readyObject, final Object... keyPart) {

        register(Key.create((Class<R>) readyObject.getClass(), keyPart), readyObject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends R> void unregister(final UniqueKey<E> uniqueKey) {

        synchronized (this.componentMap) {

            // Try to grab the object we want to unregister
            final List<E> readyObjectList = getReadyObjectList(uniqueKey);

            for (final E readyObject : readyObjectList) {

                // Unlisten all previously listened WaveType
                if (readyObject instanceof Component<?>) {
                    try {
                        getGlobalFacade().notifier().unlistenAll((Component<?>) readyObject);
                    } catch (final JRebirthThreadException e) {
                        LOGGER.error(UNLISTEN_ALL_ERROR, readyObject.getClass().getSimpleName(), e);
                    }
                }

                // Release the key
                readyObject.key(null);

                // Release the facade link
                readyObject.localFacade(null);
            }

            // Remove the component from the singleton map
            this.componentMap.remove(uniqueKey);
        }

    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <E extends R> void unregister(final E readyObject, final Object... keyPart) {

        unregister(Key.create((Class<R>) readyObject.getClass(), keyPart));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends R> boolean exists(final UniqueKey<E> uniqueKey) {
        boolean res;

        synchronized (this.componentMap) {
            // Check from singleton map it he key exists and if the weak reference is not null
            res = this.componentMap.containsKey(uniqueKey) && !this.componentMap.get(uniqueKey).isEmpty();
        }

        return res;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends R> boolean exists(final Class<E> clazz, final Object... keyPart) {
        return exists(Key.create(clazz, keyPart));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends R> E retrieve(final UniqueKey<E> uniqueKey) {

        return retrieveMany(uniqueKey).stream().findFirst().get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends R> E retrieve(final Class<E> clazz, final Object... keyPart) {

        return retrieveMany(Key.create(clazz, keyPart)).stream().findFirst().get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends R> List<E> retrieveMany(final UniqueKey<E> uniqueKey) {

        List<E> readyObjectList = getReadyObjectList(uniqueKey);

        // If the component isn't contained into the component map, create and register it
        // or has been collected by the gc
        if (readyObjectList == null || readyObjectList.isEmpty()) {
            try {

                // Build the new instance of the component
                readyObjectList = buildComponentList(uniqueKey);

                for (final E readyObject : readyObjectList) {
                    // Register it
                    register(uniqueKey, readyObject);
                    // The component is accessible from facade, let's start its initialization
                    readyObject.setup();
                }

            } catch (final CoreException ce) {
                LOGGER.error(COMPONENT_RETRIEVAL_ERROR, ce);
                throw new CoreRuntimeException(ce); // Pop up the exception wrapped into a runtime exception
            }
        }

        return readyObjectList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends R> List<E> retrieveMany(final Class<E> clazz, final Object... keyPart) {

        return retrieveMany(Key.create(clazz, keyPart));
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <E extends R> List<E> retrieveFilter(final UniqueKey<E> uniqueKey) {

        // TODO evaluate performances !!!!!
        return (List<E>) this.componentMap.entrySet().stream()
                                          .filter(entry -> entry.getKey().classField() == uniqueKey.classField())
                                          .flatMap(s -> s.getValue().stream())
                                          .collect(Collectors.toList());

    }

    /**
     * Check the presence of the Object and return it if possible otherwise return null.
     *
     * @param uniqueKey the unqiueKey of thesearch object
     *
     * @return the readyObject or null
     */
    @SuppressWarnings("unchecked")
    private <E extends R> List<E> getReadyObjectList(final UniqueKey<E> uniqueKey) {

        List<E> readyObjectList = null;
        synchronized (this.componentMap) {
            // retrieve the component from the singleton map
            // It the component is already registered, get it to return it
            if (exists(uniqueKey)) {

                readyObjectList = new ArrayList<E>((Set<E>) this.componentMap.get(uniqueKey));

                // // Check that the reference is not null
                // if (weakHashSet != null) {
                // // If no key is provided retrieve from the singleton map
                // // Extract the value from the weak reference
                // readyObject = (E) weakHashSet.get();
                // }
            }
        }
        return readyObjectList;
    }

    /**
     * Build a new instance of the ready object class.
     *
     * @param uniqueKey the unique key for the component to get
     *
     * @return a new instance of the given clazz and key
     *
     * @param <E> the type of the ready object to retrieve
     *
     * @throws CoreException if an error occurred
     */
    @SuppressWarnings("unchecked")
    protected <E extends R> List<E> buildComponentList(final UniqueKey<E> uniqueKey) throws CoreException {

        // Build a new instance of the component
        final List<E> readyObjectList = getGlobalFacade().componentFactory().buildComponents(uniqueKey.classField());

        for (final E readyObject : readyObjectList) {
            // Retrieve the right event type to track
            JRebirthEventType type = JRebirthEventType.NONE;
            if (readyObject instanceof Model) {
                type = JRebirthEventType.CREATE_MODEL;
            } else if (readyObject instanceof Service) {
                type = JRebirthEventType.CREATE_SERVICE;
            } else if (readyObject instanceof Command) {
                type = JRebirthEventType.CREATE_COMMAND;
            }

            // Track this instantiation event
            getGlobalFacade().trackEvent(type, this.getClass(), readyObject.getClass());

            // Attach the local facade
            // Already Done by register method
            readyObject.localFacade(this);

            // Create the unique key
            readyObject.key((UniqueKey<R>) uniqueKey);

        }

        // EnhancedComponent Ready !
        return readyObjectList;
    }

}
