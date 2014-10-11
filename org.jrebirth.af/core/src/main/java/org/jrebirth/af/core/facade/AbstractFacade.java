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

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.stream.Collectors;

import org.jrebirth.af.core.command.Command;
import org.jrebirth.af.core.exception.CoreException;
import org.jrebirth.af.core.exception.CoreRuntimeException;
import org.jrebirth.af.core.exception.JRebirthThreadException;
import org.jrebirth.af.core.key.UniqueKey;
import org.jrebirth.af.core.log.JRLogger;
import org.jrebirth.af.core.log.JRLoggerFactory;
import org.jrebirth.af.core.service.Service;
import org.jrebirth.af.core.ui.Model;

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
    private final Map<UniqueKey<? extends R>, WeakReference<R>> componentMap;

    /**
     * Default Constructor.
     *
     * @param globalFacade the global facade of the application
     */
    public AbstractFacade(final GlobalFacade globalFacade) {
        super(globalFacade);
        // Initialize the synchronized map for singletons
        this.componentMap = Collections.synchronizedMap(new WeakHashMap<UniqueKey<? extends R>, WeakReference<R>>());
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
            readyObject.setKey((UniqueKey<R>) uniqueKey);

            // Attach the facade to allow to retrieve any components
            readyObject.setLocalFacade(this);

            // Store the component into the singleton map
            this.componentMap.put(readyObject.getKey(), new WeakReference<R>(readyObject));
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <E extends R> void register(final E readyObject, final Object... keyPart) {

        register(UniqueKey.key((Class<R>) readyObject.getClass(), keyPart), readyObject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends R> void unregister(final UniqueKey<E> uniqueKey) {

        synchronized (this.componentMap) {

            final R readyObject = this.componentMap.get(uniqueKey).get();

            if (readyObject != null) {

                // Unlisten all previously listened WaveType
                if (readyObject instanceof Component<?>) {
                    try {
                        getGlobalFacade().getNotifier().unlistenAll((Component<?>) readyObject);
                    } catch (final JRebirthThreadException e) {
                        e.printStackTrace();
                    }
                }

                // Release the key
                readyObject.setKey(null);

                // Release the facade link
                readyObject.setLocalFacade(null);
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

        unregister(UniqueKey.key((Class<R>) readyObject.getClass(), keyPart));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends R> boolean exists(final UniqueKey<E> uniqueKey) {
        boolean res;

        synchronized (this.componentMap) {
            // Check from singleton map it he key exists and if the weak reference is not null
            res = this.componentMap.containsKey(uniqueKey) && this.componentMap.get(uniqueKey).get() != null;
        }

        return res;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends R> boolean exists(final Class<E> clazz, final Object... keyPart) {
        return exists(UniqueKey.key(clazz, keyPart));
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <E extends R> List<E> retrieveAll(final UniqueKey<E> uniqueKey) {

        // TODO evaluate performances !!!!!

        return (List<E>) this.componentMap.entrySet().stream()
                .filter(entry -> entry.getKey().getClassField() == uniqueKey.getClassField())
                .map(e -> e.getValue().get()).filter(e -> e != null)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <E extends R> E retrieve(final UniqueKey<E> uniqueKey) {

        E readyObject = null;

        synchronized (this.componentMap) {
            // retrieve the component from the singleton map
            // It the component is already registered, get it to return it
            if (exists(uniqueKey)) {

                // If no key is provided retrieve from the singleton map
                // Extract the value from the weak reference
                readyObject = (E) this.componentMap.get(uniqueKey).get();
            }
        }

        if (readyObject == null) {
            // If the component isn't contained into the component map, create and register it
            try {

                // Build the new instance of the component
                readyObject = buildComponent(uniqueKey);

                // Register it
                register(uniqueKey, readyObject);

                // The component is accessible from facade, let's start its initialization
                readyObject.setup();

            } catch (final CoreException ce) {
                LOGGER.error(COMPONENT_RETRIEVAL_ERROR, ce);
                throw new CoreRuntimeException(ce); // Pop up the exception wrapped into a runtime exception
            }
        }

        return readyObject;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends R> E retrieve(final Class<E> clazz, final Object... keyPart) {

        return retrieve(UniqueKey.key(clazz, keyPart));
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
    protected <E extends R> E buildComponent(final UniqueKey<E> uniqueKey) throws CoreException {

        // Build a new instance of the component
        final E readyObject = getGlobalFacade().getComponentFactory().buildComponent(uniqueKey.getClassField());

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
        readyObject.setLocalFacade(this);

        // Create the unique key
        readyObject.setKey((UniqueKey<R>) uniqueKey);

        // Component Ready !
        return readyObject;
    }

}
