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
package org.jrebirth.core.ui;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.facade.JRebirthEventType;
import org.jrebirth.core.ui.adapter.EventAdapter;

/**
 * The abstract class <strong>AbstractBaseController</strong>.
 * 
 * Base implementation of the controller.
 * 
 * @author Sébastien Bordes
 * 
 * @param <M> the class type of the model of the view controlled
 * @param <V> the class type of the view controlled
 */
public abstract class AbstractBaseController<M extends Model, V extends View<M, ?, ?>> implements Controller<M, V>, EventAdapter {

    /** The view component. */
    private final transient V view;

    /** Store all event handler according to the event type. */
    private final Map<EventType<? extends Event>, EventHandler<? extends Event>> eventHandlerMap = new HashMap<>();

    /**
     * Default Constructor.
     * 
     * @param view the controlled view
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public AbstractBaseController(final V view) throws CoreException {
        // Attach the view
        this.view = view;

        // Track this controller creation
        getModel().getLocalFacade().getGlobalFacade().trackEvent(org.jrebirth.core.facade.JRebirthEventType.CREATE_CONTROLLER, getView().getClass(), this.getClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void activate() throws CoreException {
        // Initialize event adapters
        initializeEventAdapters();

        // Initialize event handlers
        initializeEventHandlers();
    }

    /**
     * Initialize event Adapters.
     * 
     * You must implement the customInitializeEventHandlers method to prepare your controller.
     * 
     * @throws CoreException if an error occurred while creating event adapters
     */
    protected final void initializeEventAdapters() throws CoreException {
        // Do generic stuff

        // Do custom stuff
        customInitializeEventAdapters();
    }

    /**
     * Custom method used to initialize event Adapters.
     * 
     * @throws CoreException if an error occurred while creating event adapters
     */
    protected abstract void customInitializeEventAdapters() throws CoreException;

    /**
     * Initialize event Handlers.
     * 
     * You must implement the customInitializeEventHandlers method to prepare your controller.
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    protected final void initializeEventHandlers() throws CoreException {
        // Do generic stuff

        // Do custom stuff
        customInitializeEventHandlers();
    }

    /**
     * Custom method used to initialize event handlers.
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    protected abstract void customInitializeEventHandlers() throws CoreException;

    /**
     * @return Returns the view.
     */
    @Override
    public final V getView() {
        return this.view;
    }

    /**
     * @return Returns the model.
     */
    @Override
    public final M getModel() {
        return getView().getModel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Node getRootNode() {
        return getView().getRootNode();
    }

    /**
     * Add an event adapter and automatically generate the associated event handler. It could be accessed by calling {@link #getHandler(EventType)}
     * 
     * @param eventAdapter the {@link EventAdapter} used to route event
     * 
     * @throws CoreException it the local api contract is not respected
     */
    @SuppressWarnings("unchecked")
    protected final void addAdapter(final EventAdapter eventAdapter) throws CoreException {

        // Parse all event to find the right to manage
        for (final EventAdapter.Linker linker : EventAdapter.Linker.values()) {
            if (linker.adapterClass().isAssignableFrom(eventAdapter.getClass())) {
                // Create and store the event handler
                this.eventHandlerMap.put(linker.eventType(), wrapbuildHandler(eventAdapter, (Class<? extends EventHandler<Event>>) linker.handlerClass()));
            }
        }
    }

    /**
     * Return an {@link EventHandler}.
     * 
     * @return the right event handler
     * 
     * @throws CoreException an exception if the current class doesn't implement the right EventAdapter interface.
     */
    @SuppressWarnings("unchecked")
    protected final <E extends Event> EventHandler<E> getHandler(final EventType<E> eventType) throws CoreException {

        // final EventType<? extends Event> et = extractFirstAnyEventType(eventType);

        EventHandler<E> handler = (EventHandler<E>) this.eventHandlerMap.get(eventType);

        // Check if the handler has been created or not
        if (handler == null) {

            for (final EventAdapter.Linker linker : EventAdapter.Linker.values()) {
                if (isEventType(eventType, linker.eventType())) {
                    handler = buildEventHandler(linker.adapterClass(), (Class<? extends EventHandler<E>>) linker.handlerClass());
                }
            }

            if (handler != null) {
                // store the handler
                this.eventHandlerMap.put(eventType, handler);
            }
        }
        return handler;
    }

    /**
     * Build an event handler by reflection to wrap the event adapter given.
     * 
     * @param eventAdapter the instance of an eventAdapter
     * @param handlerClass the handler class to build
     * 
     * @return the required event handler
     * 
     * @throws CoreException if an error occurred while creating the event handler
     */
    private <E extends Event> EventHandler<E> wrapbuildHandler(final EventAdapter eventAdapter, final Class<? extends EventHandler<E>> handlerClass) throws CoreException {
        try {
            return handlerClass.getDeclaredConstructor(eventAdapter.getClass()).newInstance(this);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            throw new CoreException("Impossible to build event handler " + handlerClass.getName() + " for the class " + this.getClass().getName());
        }
    }

    /**
     * Build an event handler by reflection using the Controller object as eventAdapter.
     * 
     * @param adapterClass the event adapter class to used
     * @param handlerClass the event handler class to used
     * 
     * @return the required event handler
     * 
     * @throws CoreException if the local api contract is not respected
     */
    private <E extends Event> EventHandler<E> buildEventHandler(final Class<? extends EventAdapter> adapterClass, final Class<? extends EventHandler<E>> handlerClass) throws CoreException {
        // final Class<? extends EventAdapter<?>> adapterClass, final Class<? extends EventHandler<Event>> handlerClass

        // final Class<?> adapterClass, final Class<?> handlerClass
        EventHandler<E> eventHandler = null;
        // Build the mouse handler instance
        if (adapterClass.isAssignableFrom(this.getClass())) {
            eventHandler = wrapbuildHandler(this, handlerClass);
        } else {
            throw new CoreException(this.getClass().getName() + " must implement " + adapterClass.getName() + " interface");
        }
        return eventHandler;
    }

    /**
     * Check the tow eventype given and check the super level if necessary to always return the ANy event type.
     * 
     * @param testEventType the sub event type or any instance
     * @param anyEventType the eventype.ANY instance
     * 
     * @return true if the ANY event type is the same for both objects
     */
    private boolean isEventType(final EventType<? extends Event> testEventType, final EventType<? extends Event> anyEventType) {
        return testEventType == anyEventType || testEventType.getSuperType() == anyEventType;
    }

    // /**
    // * TODO To complete.
    // *
    // * @param eventType
    // * @return
    // */
    // private EventType<? extends Event> extractFirstAnyEventType(final EventType<? extends Event> eventType) {
    // EventType<?> et = eventType;
    // while (et.getName().contains("_")) {
    // et = et.getSuperType();
    // }
    // return et;
    // }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void finalize() throws Throwable {
        getModel().getLocalFacade().getGlobalFacade().trackEvent(JRebirthEventType.DESTROY_CONTROLLER, null, this.getClass());
        super.finalize();
    }

}
