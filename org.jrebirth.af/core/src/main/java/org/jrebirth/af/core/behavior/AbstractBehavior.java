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
package org.jrebirth.af.core.behavior;

import org.jrebirth.af.core.behavior.data.BehaviorData;
import org.jrebirth.af.core.exception.CoreException;
import org.jrebirth.af.core.facade.Component;
import org.jrebirth.af.core.link.AbstractComponent;
import org.jrebirth.af.core.wave.Wave;

/**
 * The Class AbstractBehavior.
 *
 * @param <D> the generic type
 */
public abstract class AbstractBehavior<D extends BehaviorData> extends AbstractComponent<Behavior<?>> implements Behavior<D> {

    /** The data. */
    private D data;

    /** The component. */
    private Component<?> component;

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
    public Component<?> getComponent() {
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

        this.component = (Component<?>) getKey().getOptionalData()
                .stream()
                .filter(d -> d instanceof Component<?>)
                .findFirst()
                .get();

        initBehavior();

    }

    /**
     * Inits the behavior.
     */
    public abstract void initBehavior();

}
