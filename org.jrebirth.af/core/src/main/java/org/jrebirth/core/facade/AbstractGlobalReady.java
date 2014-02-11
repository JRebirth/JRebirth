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
package org.jrebirth.core.facade;

/**
 * The class <strong>AbstractGlobalReady</strong>.
 * 
 * The base class that wrap access to the global facade.
 * 
 * @author Sébastien Bordes
 */
public abstract class AbstractGlobalReady implements GlobalReady {

    /** The main global facade. */
    private final GlobalFacade globalFacade;

    /**
     * Default Constructor.
     * 
     * @param globalFacade the unique global facade of the application
     */
    public AbstractGlobalReady(final GlobalFacade globalFacade) {
        super();
        this.globalFacade = globalFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final GlobalFacade getGlobalFacade() {
        return this.globalFacade;
    }
}
