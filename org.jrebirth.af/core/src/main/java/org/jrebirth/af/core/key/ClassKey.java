/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
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
package org.jrebirth.af.core.key;

/**
 * The class <strong>ClassKey</strong>.
 * 
 * @param <R> the class type of the registered key
 * 
 * @author Sébastien Bordes
 */
public class ClassKey<R> implements UniqueKey<R> {

    /** The class definition of the component registered by the current key. */
    private final Class<R> classField;

    /**
     * Default Constructor.
     * 
     * @param classField the class type of the registered component
     */
    public ClassKey(final Class<R> classField) {
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
    public String toString() {
        return this.getClassField().toString();
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
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(final Object obj) {
        return obj instanceof ClassKey && this.getClassField().equals(((ClassKey<R>) obj).getClassField());
    }

    /**
     * @return Returns the classField.
     */
    @Override
    public Class<R> getClassField() {
        return this.classField;
    }
}
