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
package org.jrebirth.af.core.resource.color;

/**
 * The class <strong>ResourceParams</strong>.
 * 
 * @author Sébastien Bordes
 */
public interface ResourceParams {

    /** The separator used between parameters into serialized string. */
    String PARAMETER_SEPARATOR = "\\|\\|";

    /**
     * Activate AutoRefresh that will mark the {@link ResourceParams} as 'Updated' to force the rebuild of the resource on next call.
     */
    void activateAutoRefresh();

    /**
     * Checks for resource changes.
     * 
     * @return true, if resource has changed and need to be reloaded
     */
    boolean hasChanged();

    /**
     * Define if the resource has changed or not.
     * 
     * @param changed the changed argument
     */
    void hasChanged(final boolean changed);

    /**
     * Return the dynamic key used to hold resources without static field.
     * 
     * @return the unique dynamic key
     */
    String getDynamicKey();

    /**
     * Define the dynamic key used to hold resources without static field.
     * 
     * @param dynamicKey the unique dynamic key
     */
    void setDynamicKey(final String dynamicKey);

    /**
     * Parse a string to initialize fields.
     * 
     * @param parameters split from resource properties file
     */
    void parse(final String[] parameters);

}
