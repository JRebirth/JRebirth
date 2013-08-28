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

import org.jrebirth.core.resource.color.ResourceParams;

/**
 * The class <strong>AbstractBaseParams</strong>.
 * 
 * @author Sébastien Bordes
 */
public abstract class AbstractBaseParams implements ResourceParams {

    /** The flag that indicates if the resource params has changed. */
    private boolean changed;

    /** The dynamic key used to store the resource into a map. */
    private String dynamicKey;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasChanged() {
        // Nothing to do yet
        return this.changed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hasChanged(final boolean changed) {
        this.changed = changed;
    }

    /**
     * @return Returns the dynamicKey.
     */
    @Override
    public String getDynamicKey() {
        return this.dynamicKey;
    }

    /**
     * @param dynamicKey The dynamicKey to set.
     */
    @Override
    public void setDynamicKey(final String dynamicKey) {
        this.dynamicKey = dynamicKey;
    }

}
