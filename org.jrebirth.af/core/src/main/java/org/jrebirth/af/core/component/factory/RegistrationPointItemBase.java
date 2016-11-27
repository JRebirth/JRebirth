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
package org.jrebirth.af.core.component.factory;

import org.jrebirth.af.api.component.basic.Component;
import org.jrebirth.af.api.component.factory.RegistrationPointItem;

/**
 * The class <strong>RegistrationPointItem</strong> allows to store component registration point information.
 *
 * @author Sébastien Bordes
 */
public class RegistrationPointItemBase implements RegistrationPointItem {

    /** The interface implemented defined for this component contract. */
    private Class<? extends Component<?>> interfaceClass;

    /** The registration point exclusive flag. */
    private boolean exclusive;

    /** The registration point reverse flag. */
    private boolean reverse;

    /**
     * CReate a new RegistrationPointItemBase instance.
     *
     * @return a fresh instance of RegistrationPointItemBase
     */
    public static RegistrationPointItemBase create() {
        return new RegistrationPointItemBase();
    }

    /**
     * Return the interface class defined for this component contract
     *
     * @return Returns the interfaceClass.
     */
    @Override
    public Class<? extends Component<?>> interfaceClass() {
        return this.interfaceClass;
    }

    /**
     * Set the interface class implemented by the registered class.
     *
     * @param interfaceClass The interfaceClass to set.
     *
     * @return the current RegistrationItem
     */
    @Override
    public RegistrationPointItemBase interfaceClass(final Class<? extends Component<?>> interfaceClass) {
        this.interfaceClass = interfaceClass;
        return this;
    }

    /**
     * Return the exclusive flag.
     *
     * @return Returns the exclusive flag.
     */
    @Override
    public boolean exclusive() {
        return this.exclusive;
    }

    /**
     * Set the exclusive flag.
     *
     * @param exclusive The exclusive flag to set.
     *
     * @return the current RegistrationItem
     */
    @Override
    public RegistrationPointItemBase exclusive(final boolean exclusive) {
        this.exclusive = exclusive;
        return this;
    }

    /**
     * Return the reverse flag.
     *
     * @return Returns the reverse flag.
     */
    @Override
    public boolean reverse() {
        return this.reverse;
    }

    /**
     * Set the reverse flag.
     *
     * @param reverse The reverse flag to set.
     *
     * @return the current RegistrationItem
     */
    @Override
    public RegistrationPointItemBase reverse(final boolean reverse) {
        this.reverse = reverse;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.interfaceClass == null ? 0 : this.interfaceClass.getName().hashCode());
        result = prime * result + (this.exclusive ? 1 : 3);
        result = prime * result + (this.reverse ? 5 : 7);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RegistrationPointItemBase other = (RegistrationPointItemBase) obj;
        if (this.interfaceClass == null) {
            if (other.interfaceClass != null) {
                return false;
            }
        } else if (!this.interfaceClass.equals(other.interfaceClass)) {
            return false;
        }
        if (this.exclusive != other.exclusive) {
            return false;
        }
        if (this.reverse != other.reverse) {
            return false;
        }
        return true;
    }

}
