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
package org.jrebirth.af.api.behavior;

import org.jrebirth.af.api.component.BehavioredComponent;
import org.jrebirth.af.api.component.Component;

/**
 * The Interface <b>Behavior</b> is used to define basic methods that each Behavior shall implements.
 *
 * A Behavior represents a piece of code that could be plugged and unplugged to any {@link BehavioredComponent} at any time.
 *
 * A behavior is also a {@link Component} and is stored into the {@link BehaviorFacade}.
 *
 * @param <D> the generic type of the behavior data
 */
public interface Behavior<D extends BehaviorData> extends Component<Behavior<?>> {

    /**
     * Gets the {@link BehaviorData} used by this Behavior.
     *
     * @return the behavior data
     */
    D getData();

    /**
     * Gets the {@link Component} concerned by this Behavior.
     *
     * @return the component
     */
    BehavioredComponent<?> getComponent();

}