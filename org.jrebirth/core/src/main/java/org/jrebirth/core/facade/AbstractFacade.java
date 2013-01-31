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
package org.jrebirth.core.facade;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import org.jrebirth.core.command.Command;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.exception.CoreRuntimeException;
import org.jrebirth.core.key.ClassKey;
import org.jrebirth.core.key.KeyBuilder;
import org.jrebirth.core.key.UniqueKey;
import org.jrebirth.core.service.Service;
import org.jrebirth.core.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>AbstractFacade</strong>.
 * 
 * An abstract facade can manage singleton of object which implements the FacadeReady interface
 * 
 * @author Sébastien Bordes
 * 
 * @param <R> A type that implements FacadeReady
 */
public abstract class AbstractFacade<R extends FacadeReady<R>> extends AbstractGlobalReady implements LocalFacade<R> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractFacade.class);

    /** The map that store FacadeReady singletons. */
    private final Map<ClassKey<? extends R>, R> singletonMap;

    /**
     * Default Constructor.
     * 
     * @param globalFacade the global facade of the application
     */
    public AbstractFacade(final GlobalFacade globalFacade) {
        super(globalFacade);
        // Initialize the synchronized map for singletons
        this.singletonMap = Collections.synchronizedMap(new WeakHashMap<ClassKey<? extends R>, R>());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <E extends R> void register(final E readyObject, final Object... keyPart) {

        // Synchronize the registration
        synchronized (this.singletonMap) {

            // Check if the class of the object is already stored into the singleton map
            // if (!this.singletonMap.containsKey(readyObject.getClass())) {

            // reload the key
            readyObject.setKey(buildKey((Class<? extends R>) readyObject.getClass(), keyPart));

            // Attach the facade to allow to retrieve any components
            readyObject.setLocalFacade(this);

            // Store the component into the singleton map
            this.singletonMap.put((ClassKey<E>) readyObject.getKey(), readyObject);
            // }
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <E extends R> void unregister(final E readyObject, final Object... keyPart) {

        synchronized (this.singletonMap) {
            // Release the key
            readyObject.setKey(null);

            // Release the facade link
            readyObject.setLocalFacade(null);

            // Remove the component from the singleton map
            this.singletonMap.remove(buildKey((Class<? extends R>) readyObject.getClass(), keyPart));
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <E extends R> E retrieve(final Class<E> clazz, final Object... keyPart) {

        E readyObject;

        // retrieve the component from the singleton map

        // It the component is already registered, get it to return it
        if (exists(clazz, keyPart)) {

            synchronized (this.singletonMap) {
                // FIXME OPTIMIZE KEY CREATION

                // If no key is provided retrieve from the singleton map
                // Extract the value from the weak reference
                readyObject = (E) this.singletonMap.get(buildKey(clazz, keyPart));

            }

        } else {
            // If the component isn't contained into the singleton map, create and register it
            try {

                // Build the new instance of the component
                readyObject = build(clazz, keyPart);

                // Register it
                register(readyObject, keyPart);

                // The component is accessible from facade, let's start its initialization
                readyObject.ready();

            } catch (final CoreException ce) {
                LOGGER.error(ce.getMessage());
                final String msg = "Error while building " + clazz.getCanonicalName() + " instance";
                LOGGER.error(msg);
                throw new CoreRuntimeException(msg, ce);
            }
        }

        /*
         * } else {
         * 
         * synchronized (this.multitonMap) { // If the component isn't contained into a map, create and register it if (!exists(clazz, key)) { try { // Build an instance and register it
         * register(build(clazz, key), key); } catch (final CoreException ce) { getGlobalFacade().getLogger().error(ce.getMessage()); getGlobalFacade().getLogger().error("Error while building " +
         * clazz.getCanonicalName() + " instance"); throw new CoreRuntimeException("Error while building " + clazz.getCanonicalName() + " instance", ce); } }
         * 
         * // otherwise retrieve from the multiton map (which cannot be null) readyObject = (E) this.multitonMap.get(clazz).get(key[0]); } }
         */

        return readyObject;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean exists(final Class<? extends R> clazz, final Object... keyPart) {
        boolean res;
        /*
         * if (key.length > 0) { // Check from multiton map final Map<UniqueKey, R> mMap = this.multitonMap.get(clazz); res = mMap != null && mMap.containsKey(key[0]);// && mMap.get(key[0]) != null; }
         * else {
         */
        synchronized (this.singletonMap) {
            // Check from singleton map it he key exists and if the weak reference is not null
            res = this.singletonMap.containsKey(buildKey(clazz, keyPart)); // && this.singletonMap.get(clazz) != null;
        }

        // }
        return res;
    }

    /**
     * Build a key object to register this object.
     * 
     * @param clazz the class of the object to build its key
     * @param keyPart the unique key (could be composed of many keyPart) or null for singleton
     * 
     * @return the key built
     */
    private UniqueKey buildKey(final Class<? extends R> clazz, final Object... keyPart) {
        return KeyBuilder.buildKey(clazz, keyPart);
    }

    /**
     * Build a new instance of the ready object class.
     * 
     * @param clazz the class to build
     * @param keyPart the unique key (could be composed of many keyPart) or null for singleton
     * 
     * @return a new instance of the given clazz and key
     * 
     * @param <E> the type of the ready object to retrieve
     * 
     * @throws CoreException if an error occurred
     */
    @SuppressWarnings("unchecked")
    protected <E extends R> E build(final Class<E> clazz, final Object... keyPart) throws CoreException {
        try {
            // Build a new instance of the component
            final E readyObject = clazz.newInstance();

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
            readyObject.setLocalFacade(this);

            // Create the unique key
            readyObject.setKey(buildKey((Class<R>) readyObject.getClass(), keyPart));

            // Component Ready !
            return readyObject;

        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException e) {
            final String msg = "Impossible to create the class " + clazz.getName();
            LOGGER.error(msg, e);
            throw new CoreException(msg, e);
        }
    }

}
