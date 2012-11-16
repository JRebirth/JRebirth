/**
 * Copyright JRebirth.org © 2011-2012 
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

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Callback;

import org.jrebirth.core.command.Command;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.service.Service;
import org.jrebirth.core.wave.WaveData;
import org.jrebirth.core.wave.WaveTypeBase;

/**
 * The abstract class <strong>AbstractController</strong>.
 * 
 * Base implementation of the controller.
 * 
 * @author Sébastien Bordes
 * 
 * @param <M> the class type of the model of the view controlled
 * @param <V> the class type of the view controlled
 */
public abstract class AbstractController<M extends Model, V extends View<M, ?, ?>> extends AbstractBaseController<M, V> {

    /**
     * Default Constructor.
     * 
     * @param view the controlled view
     * 
     * @throws CoreException if an error occurred while creating event handlers
     */
    public AbstractController(final V view) throws CoreException {
        super(view);
    }

    /**
     * Link the creation of a wave to an event triggered on a node.
     * 
     * @param node the node to follow
     * @param eventType the type of the event to follow
     * @param waveType the type of the wave to create
     * @param waveData additional Wave data
     * 
     * @param <E> The type of JavaFX Event to track
     */
    protected <E extends Event> void linkWave(final Node node, final javafx.event.EventType<E> eventType, final WaveTypeBase waveType,
            final WaveData<?>... waveData) {

        linkWave(node, eventType, waveType, null, waveData);
    }

    /**
     * Link the creation of a wave to an event triggered on a node.
     * 
     * @param node the node to follow
     * @param eventType the type of the event to follow
     * @param waveType the type of the wave to create
     * @param callback the call back to use to check if the wave can be sent
     * @param waveData additional Wave data
     * 
     * @param <E> The type of JavaFX Event to track
     */
    protected <E extends Event> void linkWave(final Node node, final javafx.event.EventType<E> eventType, final WaveTypeBase waveType, final Callback<E, Boolean> callback,
            final WaveData<?>... waveData) {

        node.addEventHandler(eventType, new EventHandler<E>() {

            /**
             * Handle the triggered event.
             */
            @Override
            public void handle(final E event) {
                if (callback == null || callback.call(event)) {
                    getModel().sendWave(waveType, waveData);
                }
            }
        });
    }

    /**
     * Link a command to an event triggered on a node.
     * 
     * @param node the node to follow
     * @param eventType the type of the event to follow
     * @param commandClass the command to launch
     * @param waveData additional Wave data
     * 
     * @param <E> The type of JavaFX Event to track
     */
    protected <E extends Event> void linkCommand(final Node node, final javafx.event.EventType<E> eventType,
            final Class<? extends Command> commandClass, final WaveData<?>... waveData) {

        linkCommand(node, eventType, commandClass, null, waveData);
    }

    /**
     * Link a command to an event triggered on a node.
     * 
     * @param node the node to follow
     * @param eventType the type of the event to follow
     * @param commandClass the command to launch
     * @param callback the call back to use to check if the command can be called
     * @param waveData additional Wave data
     * 
     * @param <E> The type of JavaFX Event to track
     */
    protected <E extends Event> void linkCommand(final Node node, final javafx.event.EventType<E> eventType, final Class<? extends Command> commandClass, final Callback<E, Boolean> callback,
            final WaveData<?>... waveData) {

        node.addEventHandler(eventType, new EventHandler<E>() {
            /**
             * Handle the triggered event.
             */
            @Override
            public void handle(final E event) {
                if (callback == null || callback.call(event)) {
                    getModel().callCommand(commandClass, waveData);
                }
            }
        });
    }

    /**
     * Link an User Interface action to an event triggered on a node.
     * 
     * @param node the node to follow
     * @param eventType the type of the event to follow
     * @param modelClass the model to display
     * @param waveData additional Wave data
     * 
     * @param <E> The type of JavaFX Event to track
     */
    protected <E extends Event> void linkUi(final Node node, final javafx.event.EventType<E> eventType, final Class<? extends Model> modelClass,
            final WaveData<?>... waveData) {

        linkUi(node, eventType, modelClass, null, waveData);
    }

    /**
     * Link an User Interface action to an event triggered on a node.
     * 
     * @param node the node to follow
     * @param eventType the type of the event to follow
     * @param modelClass the model to display
     * @param callback the call back to use to check if the ui can be attached
     * @param waveData additional Wave data
     * 
     * @param <E> The type of JavaFX Event to track
     */
    protected <E extends Event> void linkUi(final Node node, final javafx.event.EventType<E> eventType, final Class<? extends Model> modelClass, final Callback<E, Boolean> callback,
            final WaveData<?>... waveData) {

        node.addEventHandler(eventType, new EventHandler<E>() {
            /**
             * Handle the triggered event.
             */
            @Override
            public void handle(final E event) {
                if (callback == null || callback.call(event)) {
                    getModel().attachUi(modelClass, waveData);
                }
            }
        });
    }

    /**
     * Link a Service to an event triggered on a node.
     * 
     * @param node the node to follow
     * @param eventType the type of the event to follow
     * @param serviceClass the service to call
     * @param waveType the method of the service call
     * @param waveData additional Wave data
     * 
     * @param <E> The type of JavaFX Event to track
     */
    protected <E extends Event> void linkService(final Node node, final javafx.event.EventType<E> eventType, final Class<? extends Service> serviceClass, final WaveTypeBase waveType,
            final WaveData<?>... waveData) {

        linkService(node, eventType, serviceClass, waveType, null, waveData);
    }

    /**
     * Link a Service to an event triggered on a node.
     * 
     * @param node the node to follow
     * @param eventType the type of the event to follow
     * @param serviceClass the service to call
     * @param waveType the method of the service call
     * @param callback the call back to use to check if the service can be called
     * @param waveData additional Wave data
     * 
     * @param <E> The type of JavaFX Event to track
     */
    protected <E extends Event> void linkService(final Node node, final javafx.event.EventType<E> eventType,
            final Class<? extends Service> serviceClass, final WaveTypeBase waveType, final Callback<E, Boolean> callback,
            final WaveData<?>... waveData) {

        node.addEventHandler(eventType, new EventHandler<E>() {

            /**
             * Handle the triggered event.
             */
            @Override
            public void handle(final E event) {
                if (callback == null || callback.call(event)) {
                    getModel().returnData(serviceClass, waveType, waveData);
                }
            }
        });
    }
}
