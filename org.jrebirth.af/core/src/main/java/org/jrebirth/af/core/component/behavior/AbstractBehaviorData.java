/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2015
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

import java.util.ArrayList;
import java.util.List;

import org.jrebirth.af.api.component.behavior.Behavior;
import org.jrebirth.af.api.component.behavior.BehaviorData;
import org.jrebirth.af.api.component.behavior.annotation.BehaviorDataFor;

/**
 * The Class AbstractBehaviorData is the base class for implementing {@link BehaviorData} interface.
 */
public abstract class AbstractBehaviorData implements BehaviorData {

    /** The list of related behaviors. */
    private List<Class<? extends Behavior<?>>> behaviors;

    /**
     * Default Constructor..
     */
    public AbstractBehaviorData() {
        super();

        // Manage Behavior added by method overriding
        final List<? extends Class<? extends Behavior<?>>> list = getCustomBehaviors();
        if (!list.isEmpty()) {
            getBehaviors().addAll(list);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Class<? extends Behavior<?>>> getBehaviors() {

        if (this.behaviors == null) {
            this.behaviors = new ArrayList<Class<? extends Behavior<?>>>();
        }

        for (final BehaviorDataFor annotation : getClass().getAnnotationsByType(BehaviorDataFor.class)) {
            this.behaviors.add(annotation.value());
        }

        return this.behaviors;
    }

    /**
     * Gets the custom behaviors.
     *
     * @return the custom behaviors
     */
    protected abstract List<? extends Class<? extends Behavior<?>>> getCustomBehaviors();
}
