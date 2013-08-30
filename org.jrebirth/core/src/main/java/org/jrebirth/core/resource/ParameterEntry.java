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
package org.jrebirth.core.resource;

/**
 * The class <strong>ParameterEntry</strong>.
 * 
 * @param <O> the generic type of the unserialized object
 * 
 * @author Sébastien Bordes
 */
public class ParameterEntry<O extends Object> {

    /** The serialized. */
    private final String serializedString;

    /** The wrapped object unserialized. */
    private O object;

    /**
     * Instantiates a new parameter entry.
     * 
     * @param serializedString the serialized string
     */
    public ParameterEntry(final String serializedString) {
        super();
        this.serializedString = serializedString;
    }

    /**
     * Instantiates a new parameter entry.
     * 
     * @param serializedString the serialized string
     * @param unserializedObject the object2
     */
    public ParameterEntry(final String serializedString, final O unserializedObject) {
        this(serializedString);
        this.object = unserializedObject;
    }

    /**
     * Gets the serialized.
     * 
     * @return Returns the serializedString.
     */
    public String getSerializedString() {
        return this.serializedString;
    }

    /**
     * Gets the wrapped object unserialized.
     * 
     * @return Returns the wrapped object unserialized.
     */
    public O getObject() {
        return this.object;
    }

}
