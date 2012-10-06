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
package org.jrebirth.core.service;

import org.jrebirth.core.event.EventType;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.link.AbstractWaveReady;
import org.jrebirth.core.wave.Wave;

/**
 * 
 * The class <strong>ServiceImpl</strong>.
 * 
 * Base implementation of the service.
 * 
 * @author Sébastien Bordes
 */
public class ServiceImpl extends AbstractWaveReady<Service> implements Service {

    /**
     * {@inheritDoc}
     */
    @Override
    public void ready() throws CoreException {
        // Initialize Service with private method
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processAction(final Wave wave) {
        // Must be overridden to manage action handling by service
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void finalize() throws Throwable {
        getLocalFacade().getGlobalFacade().trackEvent(EventType.DESTROY_SERVICE, null, this.getClass());
        super.finalize();
    }
}
