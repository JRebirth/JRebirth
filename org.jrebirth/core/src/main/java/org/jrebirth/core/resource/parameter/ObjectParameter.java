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
package org.jrebirth.core.resource.parameter;

import org.jrebirth.core.resource.AbstractBaseParams;

/**
 * The interface <strong>ObjectParameter</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @param <O> the parameter type
 */
public class ObjectParameter<O> extends AbstractBaseParams implements ParameterParams {

    /** The parameter object. */
    private final O object;

    /**
     * Default Constructor.
     * 
     * @param object the parameter object
     */
    public ObjectParameter(final O object) {
        super();
        this.object = object;
    }

    /**
     * Return the parameter object value.
     * 
     * @return Returns the object.
     */
    public O object() {
        return this.object;
    }

}
