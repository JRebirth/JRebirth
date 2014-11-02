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
package org.jrebirth.af.api.component;

import org.jrebirth.af.api.behavior.Behavior;
import org.jrebirth.af.api.behavior.BehaviorData;

/**
 * The interface <strong>BehavioredComponent</strong>.
 *
 * Define the contract used to let {@link Component} manage {@link Behavior}s.
 *
 * @author Sébastien Bordes
 *
 * @param <C> A type that implements Component
 */
public interface BehavioredComponent<C extends Component<C>> extends Component<C> {

    /**
     * Return true if the component contains the {@link Behavior} class provided.
     *
     * @param behaviorClass the class of the {@link Behavior} to check
     *
     * @return true if the component has got this behavior
     */
    boolean hasBehavior(final Class<Behavior<?>> behaviorClass);

    /**
     * Add a behavior to the component by providing the {@link Behavior} class.
     *
     * @param behaviorClass the class of the behavior to add
     */
    <BD extends BehaviorData, B extends Behavior<BD>> C addBehavior(final Class<B> behaviorClass);

    /**
     * Add a behavior to the component by providing a {@link BehaviorData}.
     *
     * The BehaviorData allow to add several {@link Behavior} if many of them rare reference by the BehaviorData.
     *
     * @param data the Behavior Data that references Behavior classes to add
     */
    <BD extends BehaviorData> C addBehavior(final BD data);

    /**
     * Return the {@link Behavior} instance created for the given Behavior type.
     *
     * @param behaviorClass the Behavior type to get
     *
     * @return the Behavior instance attached to the component
     */
    <BD extends BehaviorData, B extends Behavior<BD>> B getBehavior(final Class<B> behaviorClass);

    /**
     * Return the {@link BehaviorData} used by the given Behavior type.
     *
     * @param behaviorClass the Behavior type that use the searched data
     *
     * @return the Behavior Data used by the given Behavior
     */
    <BD extends BehaviorData, B extends Behavior<BD>> BD getBehaviorData(final Class<B> behaviorClass);

}
