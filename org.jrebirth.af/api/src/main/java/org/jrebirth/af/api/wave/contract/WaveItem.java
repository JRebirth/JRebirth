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
package org.jrebirth.af.api.wave.contract;

import java.lang.reflect.Type;

/**
 * The interface <strong>WaveItem</strong>.
 *
 * Wave item is used to identify an object into a wave or a parameter.
 *
 * @author Sébastien Bordes
 *
 * @param <T> the type of the object mapped by this WaveItem
 */
public interface WaveItem<T> {

    /**
     * @return Returns the name.
     */
    String getName();

    /**
     * @return Returns the uid.
     */
    int getUid();

    /**
     * @return Returns the itemType.
     */
    Type getItemType();

    /**
     * @return Returns the isParameter.
     */
    boolean isParameter();

    /**
     * @param isParameter The isParameter to set.
     */
    void setParameter(final boolean isParameter);

}
