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
package org.jrebirth.af.api.ui.object;

import org.jrebirth.af.api.ui.Model;

/**
 * The interface <strong>ModelObject</strong> is used to support a main object for a Model.
 * 
 * @author Sébastien Bordes
 * 
 * @param <O> the type of the bindable object
 */
public interface ModelObject<O> extends Model {

    /**
     * Return the bindable object and create it if null.
     *
     * @return the bindable object
     */
    O getObject();

    /**
     * Set the bindable object.
     * 
     * @param object The bindable object to set.
     */
    void setObject(final O object);

}
