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
package org.jrebirth.af.core.resource.parameter;

/**
 * The Interface ParameterEnum is used to define parameter into an enumeration.
 *
 * @param <T> the generic type
 */
public interface ParameterEnum<T extends Object> extends ParameterItemReal<T> {

    /**
     * Return the enum name.
     * 
     * @return the name
     */
    String name();

    /**
     * Default Constructor.
     *
     * @param <O> the type of the object paramter to create, shall be a subtype of enum generic type
     * @param object the parameter object
     */
    default <O extends T> void param(final O object) {
        set(new ObjectParameter<>(name(), object));
    }

    /**
     * Default Constructor.
     *
     * @param <O> the type of the object paramter to create, shall be a subtype of enum generic type
     * @param parameterName the name of the parameter
     * @param object the parameter object
     */
    default <O extends T> void param(final String parameterName, final O object) {
        set(new ObjectParameter<>(parameterName, object));
    }

}
