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
package org.jrebirth.af.core.component.behavior;

import org.jrebirth.af.api.annotation.SkipAnnotation;
import org.jrebirth.af.api.component.behavior.Behavior;
import org.jrebirth.af.api.component.behavior.BehaviorData;
import org.jrebirth.af.api.component.behavior.BehavioredComponent;
import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.api.log.JRLogger;
import org.jrebirth.af.core.component.basic.AbstractComponent;
import org.jrebirth.af.core.key.Key;
import org.jrebirth.af.core.link.LinkMessages;
import org.jrebirth.af.core.log.JRLoggerFactory;
import org.jrebirth.af.core.util.MultiMap;

/**
 *
 * The class <strong>AbstractBehavioredComponent</strong>.
 *
 * This is the base class for all EnhancedComponent that want to use Behavior features.<br />
 *
 * @author Sébastien Bordes
 *
 * @param <C> the class type of the subclass
 */
@SkipAnnotation(false)
public abstract class AbstractBehavioredComponent<C extends BehavioredComponent<C>> extends AbstractComponent<C> implements BehavioredComponent<C>, LinkMessages {

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(AbstractBehavioredComponent.class);

    /** A map that store one or many behavior implementations by their type. */
    private MultiMap<Class<? extends Behavior<?>>, Behavior<?>> behaviors;

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected void manageOptionalData() {

        // Parse optional data provided to search Behavior Class or BehaviorData
        for (final Object data : getKey().getOptionalData()) {

            if (data instanceof BehaviorData) {

                addBehavior((BehaviorData) data);

            } else if (data instanceof Class && ((Class<?>) data).isAssignableFrom(Behavior.class)) {

                addBehavior((Class<Behavior<BehaviorData>>) data);

            }

        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasBehavior(final Class<Behavior<?>> behaviorClass) {
        return this.behaviors != null && this.behaviors.containsKey(behaviorClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <BD extends BehaviorData, B extends Behavior<BD>> C addBehavior(final Class<B> behaviorClass) {

        final UniqueKey<B> key = Key.create(behaviorClass, new Object[] { this }, getKey());

        final B behavior = getLocalFacade().getGlobalFacade().getBehaviorFacade().retrieve(key);

        addBehavior(behavior);

        return (C) this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <BD extends BehaviorData> C addBehavior(final BD data) {

        for (final Class<? extends Behavior<?>> behaviorClass : data.getBehaviors()) {

            final Object[] optionalData = new Object[] { data, this };

            final UniqueKey<? extends Behavior<?>> key = Key.create(behaviorClass, optionalData, getKey());

            addBehavior(getLocalFacade().getGlobalFacade().getBehaviorFacade().retrieve(key));
        }
        return (C) this;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    private <B extends Behavior<?>> void addBehavior(final B behavior) {

        if (this.behaviors == null) {
            this.behaviors = new MultiMap<Class<? extends Behavior<?>>, Behavior<?>>();
        }

        LOGGER.log(ADD_BEHAVIOR, behavior.getClass().getCanonicalName(), this.getClass().getCanonicalName());

        this.behaviors.add((Class<Behavior<?>>) behavior.getClass(), behavior);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <BD extends BehaviorData, B extends Behavior<BD>> B getBehavior(final Class<B> behaviorClass) {
        return this.behaviors == null ? null : (B) this.behaviors.get(behaviorClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <BD extends BehaviorData, B extends Behavior<BD>> BD getBehaviorData(final Class<B> behaviorClass) {
        return this.behaviors == null ? null : (BD) this.behaviors.get(behaviorClass).get(0).getData();
    }

}
