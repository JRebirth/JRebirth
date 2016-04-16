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
package org.jrebirth.af.core.component.basic;

import org.jrebirth.af.api.component.basic.Component;
import org.jrebirth.af.api.key.UniqueKey;

/**
 * The Interface CBuilders used as a convenient wrapper to build component related JRebirth Internal objects.
 */
public interface CBuilder {

    /**
     * TODO To complete.
     *
     * @param modelClass
     * @param keyPart
     *
     * @param <C>
     *
     * @return
     */
    static <C extends Component<?>> InnerComponentBase<C> innerComponent(final Class<C> modelClass, final Object... keyPart) {
        return new InnerComponentBase<C>(modelClass, keyPart);
    }

    /**
     * TODO To complete.
     *
     * @param key
     * @param <C>
     *
     * @return
     */
    static <C extends Component<?>> InnerComponentBase<C> innerComponent(final UniqueKey<C> key) {
        return new InnerComponentBase<C>(key);
    }

}
