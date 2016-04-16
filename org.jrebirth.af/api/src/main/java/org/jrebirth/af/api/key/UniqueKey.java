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
package org.jrebirth.af.api.key;

import java.io.Serializable;
import java.util.List;

/**
 * The class <strong>UniqueKey</strong>.
 *
 * @author Sébastien Bordes
 *
 * @param <R> the type of the classfield managed by this key
 */
public interface UniqueKey<R> extends Serializable {

    /**
     * Return the unique key.
     *
     * @return the unique key
     */
    String key();

    /**
     * Return the unique object if any or null.
     *
     * @return the unique object or null if none
     */
    Object value();

    /**
     * Return the class type of the component.
     *
     * @return Returns the classField.
     */
    Class<R> classField();

    /**
     * Return the list of optional data associated to the key.
     *
     * @return the optional data list
     */
    List<Object> optionalData();

}
