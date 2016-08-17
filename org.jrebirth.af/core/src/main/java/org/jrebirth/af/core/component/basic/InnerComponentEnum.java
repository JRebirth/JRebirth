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
package org.jrebirth.af.core.component.basic;

import java.util.HashMap;
import java.util.Map;

import org.jrebirth.af.api.component.basic.Component;
import org.jrebirth.af.api.component.basic.InnerComponent;

/**
 * The interface <strong>InnerComponentEnum</strong>.
 *
 * @author Sébastien Bordes
 */
public interface InnerComponentEnum {

    /** The map that stores an InnerComponent wrapper per enum entry. */
    static Map<InnerComponentEnum, InnerComponent<?>> map = new HashMap<>();

    /**
     * Define an inner component.
     *
     * @param <C> the generic type
     * @param clazz the clazz
     * @param optionalData the optional data
     * @param keyPart the key part
     */
    default <C extends Component<C>> void set(final Class<C> clazz, final Object[] optionalData, final Object... keyPart) {
        map.put(this, CBuilder.innerComponent(clazz, keyPart));
    }

    /**
     * Define an inner component.
     *
     * @param <C> the generic type
     * @param clazz the clazz
     * @param keyPart the key part
     */
    default <C extends Component<C>> void set(final Class<C> clazz, final Object... keyPart) {
        map.put(this, CBuilder.innerComponent(clazz, keyPart));
    }

    /**
     * Gets the innerComponent wrapper for the enumeration entry.
     *
     * @return the inner component
     */
    default InnerComponent<?> get() {
        return map.get(this);
    }

}
