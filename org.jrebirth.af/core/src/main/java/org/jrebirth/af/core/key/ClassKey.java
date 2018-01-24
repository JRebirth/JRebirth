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
package org.jrebirth.af.core.key;

import java.util.ArrayList;
import java.util.List;

import org.jrebirth.af.api.key.UniqueKey;

/**
 * The class <strong>ClassKey</strong>.
 *
 * @param <R> the class type of the registered key
 *
 * @author Sébastien Bordes
 */
public class ClassKey<R> implements UniqueKey<R> {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6535088425529890897L;

    /** The class definition of the component registered by the current key. */
    private final Class<R> classField;

    /** List of optional data to be transmit to the component. */
    private final List<Object> optionalDatas;

    /** The registration key used when registered into a set of implementations. */
    private UniqueKey<? super R> registrationKey;

    /**
     * Default Constructor.
     *
     * @param classField the class type of the registered component
     * @param optionalData the optional data to be transmit to the component
     */
    public ClassKey(final Class<R> classField, final Object... optionalData) {
        super();
        this.classField = classField;
        if (optionalData.length > 0) {
            this.optionalDatas = new ArrayList<>();
            for (final Object data : optionalData) {
                this.optionalDatas.add(data);
            }
        } else {
            // No optional data
            this.optionalDatas = null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String key() {
        return this.classField().getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object value() {
        return this.classField();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.classField().toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return this.classField().hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(final Object obj) {
        return obj != null && obj.getClass() == this.getClass() && this.classField().equals(((ClassKey<R>) obj).classField());
    }

    /**
     * @return Returns the classField.
     */
    @Override
    public Class<R> classField() {
        return this.classField;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Object> optionalData() {
        return this.optionalDatas == null ? new ArrayList<>() : this.optionalDatas;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registrationKey(UniqueKey<? super R> uniqueKey) {
        this.registrationKey = uniqueKey;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<? super R> registrationKey() {
        return registrationKey;
    }

}
