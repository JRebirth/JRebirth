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
package org.jrebirth.af.api.component.factory;

import java.util.List;

import org.jrebirth.af.api.exception.CoreException;

/**
 * The interface <strong>ComponentFactory</strong> is used to build EnhancedComponent (Model, Service, Command) classes.
 *
 * @author Sébastien Bordes
 */
public interface ComponentFactory {

    /**
     * Define a Component contract.
     *
     * @param item the registration point item
     */
    void define(final RegistrationPointItem item);

    /**
     * Register an implementation class for an interface.
     *
     * @param item the registration item
     */
    void register(final RegistrationItem item);

    /**
     * Build a fresh instance of a component.<br />
     * (that implements {@link org.jrebirth.af.api.facade.FacadeReady.FacadeReady} interface)
     *
     * @param clazz the component class
     *
     * @return a list of fresh instance of components
     *
     * @param <R> the ReadyObject, it's a component class
     *
     * @throws CoreException CoreException if an error has occurred
     */
    <R extends Object> List<R> buildComponents(final Class<R> clazz) throws CoreException;

}
