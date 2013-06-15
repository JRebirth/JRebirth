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
package org.jrebirth.core.key;

import java.util.ArrayList;
import java.util.List;

/**
 * The class <strong>MultitonKey</strong>.
 * 
 * The key used to discriminate any multiton part in order to guarantee component unicity.
 * 
 * @author Sébastien Bordes
 * 
 * @param <R> the type of the object registered by this key
 */
public class MultitonKey<R> extends ClassKey<R> {

    /** The key formatted into a string. */
    private String key;

    /** List of keys that are part of the main key. */
    private final List<Object> keyPartList = new ArrayList<>();

    /**
     * Default Constructor.
     * 
     * @param classField the descriptive class object
     * @param keyPart a list of immutable objects that guarantee component unicity
     */
    public MultitonKey(final Class<R> classField, final Object... keyPart) {
        super(classField);

        // Store all keys
        for (final Object k : keyPart) {
            this.keyPartList.add(k);
        }
        rebuildKey();
    }

    /**
     * (Re)-Build the string key by reading the keys list content.
     */
    private void rebuildKey() {

        final StringBuffer sb = new StringBuffer();

        sb.append(getClassField().getCanonicalName());
        for (final Object keyObject : this.keyPartList) {
            sb.append(buildObjectKey(keyObject)).append('|');
        }
        this.key = sb.toString();
    }

    /**
     * Generate the string key for an object.
     * 
     * @param keyObject the object which is part of the global key
     * 
     * @return the unique string for this object
     */
    private String buildObjectKey(final Object keyObject) {

        // FIXME ADD VISITOR + FACTORY PATTERN

        return keyObject.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object object) {
        return object != null && this.toString().equals(object.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return getKey().hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getKey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getKey() {
        if (this.key == null) {
            rebuildKey();
        }
        return this.key;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getValue() {
        Object obj = null;
        if (this.keyPartList.size() > 1) {
            obj = this.keyPartList;
        } else if (this.keyPartList.size() == 1) {
            obj = this.keyPartList.get(0);
        }
        return obj;
    }
}
