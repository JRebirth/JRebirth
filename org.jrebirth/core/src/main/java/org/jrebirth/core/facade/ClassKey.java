/**
 * Copyright JRebirth.org © 2011-2012 
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
package org.jrebirth.core.facade;

/**
 * The class <strong>ClassKey</strong>.
 * 
 * @param <C> the class type of the registered key
 * 
 * @author Sébastien Bordes
 */
public class ClassKey<C> implements UniqueKey {

    /** The class definition of the component registered by the current key. */
    private final Class<C> classField;

    /**
     * Default Constructor.
     * 
     * @param classField the class type of the registered component
     */
    public ClassKey(final Class<C> classField) {
        super();
        this.classField = classField;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getKey() {
        return this.getClassField().getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getValue() {
        return this.getClassField();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return this.getClassField().hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        return obj != null && this.getClassField().equals(((ClassKey<C>) obj).getClassField());
    }

    /**
     * @return Returns the classField.
     */
    public Class<C> getClassField() {
        return this.classField;
    }
}
