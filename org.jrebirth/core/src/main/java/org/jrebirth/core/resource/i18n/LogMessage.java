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
package org.jrebirth.core.resource.i18n;

import org.slf4j.Marker;

/**
 * The interface <strong>Message</strong>.
 * 
 * @author Sébastien Bordes
 */
public class LogMessage extends Message implements LogMessageParams {

    /** The mandatory log marker used to sort log items. */
    private final Marker marker;

    /**
     * Default Constructor.
     * 
     * @param parameterName the name of the parameter
     * @param marker the log marker
     */
    public LogMessage(final String parameterName, final Marker marker) {
        super(parameterName);
        this.marker = marker;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Marker marker() {
        return this.marker;
    }

}
