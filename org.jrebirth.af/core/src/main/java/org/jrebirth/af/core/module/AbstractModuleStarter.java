/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2016
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
package org.jrebirth.af.core.module;

import org.jrebirth.af.api.annotation.PriorityLevel;
import org.jrebirth.af.api.command.Command;
import org.jrebirth.af.api.component.basic.Component;
import org.jrebirth.af.api.exception.CoreRuntimeException;
import org.jrebirth.af.api.facade.GlobalFacade;
import org.jrebirth.af.api.log.JRLogger;
import org.jrebirth.af.api.module.ModuleStarter;
import org.jrebirth.af.api.service.Service;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.core.component.factory.RegistrationItemBase;
import org.jrebirth.af.core.component.factory.RegistrationPointItemBase;
import org.jrebirth.af.core.concurrent.JRebirthThread;
import org.jrebirth.af.core.log.JRLoggerFactory;

/**
 * The Class AbstractModuleStarter.
 */
public abstract class AbstractModuleStarter implements ModuleStarter, ModuleMessages {

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(AbstractModuleStarter.class);

    /**
     * Define a new component interface definition.
     *
     * @param interfaceClass the interface class implemented
     * @param exclusive the exclusive flag
     * @param reverse the reverse flag
     */
    protected void define(final Class<? extends Component<?>> interfaceClass, final boolean exclusive, final boolean reverse) {

        preloadClass(interfaceClass);

        getFacade().componentFactory().define(
                                              RegistrationPointItemBase.create()
                                                                       .interfaceClass(interfaceClass)
                                                                       .exclusive(exclusive)
                                                                       .reverse(reverse));
    }

    /**
     * Register a new component implementation.
     *
     * @param interfaceClass the interface class implemented
     * @param implClass the implementation class
     */
    protected void register(final Class<? extends Component<?>> interfaceClass, final Class<? extends Component<?>> implClass) {
        register(interfaceClass, implClass, PriorityLevel.Normal, 0);
    }

    /**
     * Register a new component implementation.
     *
     * @param interfaceClass the interface class implemented
     * @param implClass the implementation class
     * @param priority the priority level
     */
    protected void register(final Class<? extends Component<?>> interfaceClass, final Class<? extends Component<?>> implClass, final PriorityLevel priority) {
        register(interfaceClass, implClass, priority, 0);
    }

    /**
     * Register a new component implementation.
     *
     * @param interfaceClass the interface class implemented
     * @param implClass the implementation class
     * @param priority the priority level
     * @param weight the priority weight strictly less than 1000
     */
    protected void register(final Class<? extends Component<?>> interfaceClass, final Class<? extends Component<?>> implClass, final PriorityLevel priority, final int weight) {

        preloadClass(interfaceClass);

        getFacade().componentFactory().register(
                                                RegistrationItemBase.create()
                                                                    .interfaceClass(interfaceClass)
                                                                    .implClass(implClass)
                                                                    .priority(priority)
                                                                    .weight(weight));
    }

    /**
     * Boot component by retrieving it from its facade.
     *
     * @param componentClass the component class to boot
     */
    @SuppressWarnings("unchecked")
    protected void bootComponent(final Class<? extends Component<?>> componentClass) {

        preloadClass(componentClass);

        try {
            if (Command.class.isAssignableFrom(componentClass)) {
                getFacade().commandFacade().retrieve((Class<Command>) componentClass);
            } else if (Service.class.isAssignableFrom(componentClass)) {
                getFacade().serviceFacade().retrieve((Class<Service>) componentClass);
            } else if (Model.class.isAssignableFrom(componentClass)) {
                getFacade().uiFacade().retrieve((Class<Model>) componentClass);
            }
        } catch (final CoreRuntimeException e) {
            LOGGER.error(BOOT_COMPONENT_ERROR, e, componentClass.getName());
        }
    }

    /**
     * Preload class, useful to preload field initialized with JRebirth Builder.
     *
     * @param objectClass the object class to load from classloader
     */
    protected void preloadClass(final Class<?> objectClass) {
        try {
            Class.forName(objectClass.getName());
        } catch (final ClassNotFoundException e) {
            LOGGER.error(CLASS_NOT_FOUND, e, objectClass.getName());
        }
    }

    /**
     * Gets the global facade.
     *
     * @return the global facade
     */
    protected GlobalFacade getFacade() {
        return JRebirthThread.getThread().getFacade();
    }
}
