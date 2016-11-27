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

import org.jrebirth.af.api.annotation.PriorityLevel;
import org.jrebirth.af.api.component.basic.Component;
import org.jrebirth.af.api.component.factory.RegistrationItem;

/**
 * The class <strong>RegistrationItem</strong> allows to store component registration information.
 *
 * @author Sébastien Bordes
 */
public class RegistrationItemBase implements RegistrationItem {

    /** The interface implemented by the registered class. */
    private Class<? extends Component<?>> interfaceClass;

    /** The registered class. */
    private Class<? extends Component<?>> implClass;

    /** The priority level of this registration. */
    private PriorityLevel priority;

    /** The registration priority weight. */
    private int weight;

    /**
     * CReate a new RegistrationItem instance.
     *
     * @return a fresh instance of RegistrationItem
     */
    public static RegistrationItemBase create() {
        return new RegistrationItemBase();
    }

    /**
     * Return the interface class implemented by the registered class.
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
    public RegistrationItemBase interfaceClass(final Class<? extends Component<?>> interfaceClass) {
        this.interfaceClass = interfaceClass;
        return this;
    }

    /**
     * Return the implementation class.
     *
     * @return Returns the implClass.
     */
    @Override
    public Class<? extends Component<?>> implClass() {
        return this.implClass;
    }

    /**
     * Set the implementation class.
     *
     * @param implClass The implementation Class to set.
     *
     * @return the current RegistrationItem
     */
    @Override
    public RegistrationItemBase implClass(final Class<? extends Component<?>> implClass) {
        this.implClass = implClass;
        return this;
    }

    /**
     * Return the registration priority level.
     *
     * @return Returns the priority.
     */
    @Override
    public PriorityLevel priority() {
        return this.priority;
    }

    /**
     * Set the registration priority level.
     *
     * @param priority The priority level to set.
     *
     * @return the current RegistrationItem
     */
    @Override
    public RegistrationItemBase priority(final PriorityLevel priority) {
        this.priority = priority;
        return this;
    }

    /**
     * return the registration priority weight
     *
     * @return Returns the weight.
     */
    @Override
    public int weight() {
        return this.weight;
    }

    /**
     * Set the registration priority weight (strictly less than 1000).
     *
     * @param weight The weight to set.
     *
     * @return the current RegistrationItem
     */
    @Override
    public RegistrationItemBase weight(final int weight) {
        this.weight = weight % 1000;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final RegistrationItem o) {
        int priorityDiff = 0;
        if (priority() != null) {
            if (o.priority() != null) {
                priorityDiff = priority().level(weight()) - o.priority().level(o.weight());
            } else {
                priorityDiff = 1;
            }
        } else {
            if (o.priority() != null) {
                priorityDiff = -1;
            }
        }
        return priorityDiff == 0 ? this.implClass().getName().compareTo(o.implClass().getName()) : priorityDiff;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.implClass == null ? 0 : this.implClass.getName().hashCode());
        result = prime * result + (this.interfaceClass == null ? 0 : this.interfaceClass.getName().hashCode());
        result = prime * result + (this.priority == null ? 0 : this.priority.hashCode());
        result = prime * result + this.weight;
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
        final RegistrationItemBase other = (RegistrationItemBase) obj;
        if (this.implClass == null) {
            if (other.implClass != null) {
                return false;
            }
        } else if (!this.implClass.equals(other.implClass)) {
            return false;
        }
        if (this.interfaceClass == null) {
            if (other.interfaceClass != null) {
                return false;
            }
        } else if (!this.interfaceClass.equals(other.interfaceClass)) {
            return false;
        }
        if (this.priority != other.priority) {
            return false;
        }
        if (this.weight != other.weight) {
            return false;
        }
        return true;
    }

}
