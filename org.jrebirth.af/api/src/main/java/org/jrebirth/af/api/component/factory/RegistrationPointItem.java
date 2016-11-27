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
package org.jrebirth.af.api.component.factory;

import org.jrebirth.af.api.component.basic.Component;

/**
 * The class <strong>RegistrationPointItem</strong> allows to store registration point information.
 *
 * @author Sébastien Bordes
 */
public interface RegistrationPointItem {

    /**
     * Return the interface class implemented by the registered class.
     *
     * @return Returns the interfaceClass.
     */
    Class<? extends Component<?>> interfaceClass();

    /**
     * Set the interface class implemented by the registered class.
     *
     * @param interfaceClass The interfaceClass to set.
     *
     * @return the current RegistrationPointItem
     */
    RegistrationPointItem interfaceClass(final Class<? extends Component<?>> interfaceClass);

    /**
     * Return the exclusive flag, true when only one component shall be built.
     *
     * @return Returns the exclusive.
     */
    boolean exclusive();

    /**
     * Set the exclusive flag, true when only one component shall be built.
     *
     * @param exclusive The exclusive to set.
     *
     * @return the current RegistrationItem
     */
    RegistrationPointItem exclusive(final boolean exclusive);

    /**
     * Return the reverse flag, true when lower priority shall be retrieved first.
     *
     * @return Returns the reverse.
     */
    boolean reverse();

    /**
     * Set the reverse flag, true when lower priority shall be retrieved first.
     *
     * @param reverse The reverse to set.
     *
     * @return the current RegistrationItem
     */
    RegistrationPointItem reverse(final boolean reverse);

}
