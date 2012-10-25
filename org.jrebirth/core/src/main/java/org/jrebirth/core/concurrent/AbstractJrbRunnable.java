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
package org.jrebirth.core.concurrent;

import org.jrebirth.core.event.JRebirthLogger;
import org.jrebirth.core.exception.JRebirthThreadException;

/**
 * The class <strong>AbstractJrbRunnable</strong>.
 * 
 * @author Sébastien Bordes
 */
public abstract class AbstractJrbRunnable implements Runnable {

    /**
     * {@inheritDoc}
     */
    @Override
    @Override
    public final void run() {
        try {
            runInto();
        } catch (final JRebirthThreadException jte) {
            JRebirthLogger.getInstance().logException(jte);
        }

    }

    /**
     * Run task and allow to throw a JRebirthException.
     * 
     * @throws JRebirthThreadException it thread error
     */
    protected abstract void runInto() throws JRebirthThreadException;

}
