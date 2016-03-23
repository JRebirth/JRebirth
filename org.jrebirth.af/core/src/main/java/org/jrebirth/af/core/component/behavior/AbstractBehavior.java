/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2014
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
package org.jrebirth.af.core.component.behavior;

import org.jrebirth.af.api.component.behavior.Behavior;
import org.jrebirth.af.api.component.behavior.BehaviorData;
import org.jrebirth.af.api.component.behavior.BehavioredComponent;
import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.component.basic.AbstractComponent;

/**
 * The class AbstractBehavior is the base class for implementing {@link Behavior} interface.
 *
 * @param <D> the generic type extending {@link BehaviorData}
 */
public abstract class AbstractBehavior<D extends BehaviorData, C extends BehavioredComponent<C>> extends AbstractComponent<Behavior<?, ?>> implements Behavior<D, C> {

    /** The behavior data. */
    private D data;

    /** The behaviored component on which the behavior will be used. */
    private C component;

    /**
     * {@inheritDoc}
     */
    @Override
    public D getData() {
        return this.data;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public C getComponent() {
        return this.component;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processWave(final Wave wave) {

    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void ready() throws CoreException {

        // FIXME fix leaks
        this.data = (D) getKey().getOptionalData()
                                .stream()
                                .filter(d -> d instanceof BehaviorData)
                                .findFirst()
                                .get();

        this.component = (C) getKey().getOptionalData()
                                     .stream()
                                     .filter(d -> d instanceof BehavioredComponent<?>)
                                     .findFirst()
                                     .get();

        initBehavior();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void manageOptionalData() {
        // Nothing to do
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initInnerComponents() {
        // Nothing to do
    }

    /**
     * Inits the behavior.
     */
    protected abstract void initBehavior();

}
