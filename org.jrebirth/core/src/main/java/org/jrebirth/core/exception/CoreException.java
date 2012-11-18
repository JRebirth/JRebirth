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
package org.jrebirth.core.exception;

/***
 * 
 * The class <strong>CoreException</strong>.
 * 
 * This is the exception that can be thrown by the JRebirth core module.
 * 
 * @author Sébastien Bordes
 */
public class CoreException extends Exception {

    /**
     * The constant used for serialization.
     */
    private static final long serialVersionUID = 112036992331510469L;

    /**
     * Constructor with message and throwable.
     * 
     * @param message the message to display.
     * @param t the base exception thrown
     */
    public CoreException(final String message, final Throwable t) {
        super(message, t);
    }

    /**
     * Constructor without base exception.
     * 
     * @param message the message to display.
     */
    public CoreException(final String message) {
        super(message);
    }

    /**
     * Constructor without custom message.
     * 
     * @param t the base exception thrown
     */
    public CoreException(final Throwable t) {
        super(t);
    }

}
