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
package org.jrebirth.af.core.exception;

/***
 *
 * The class <strong>ServiceException</strong>.
 *
 * This is the exception that can be thrown by any service method when something goes wrong and we don't want to sent the return wave.
 *
 * @author Sébastien Bordes
 */
public class ServiceException extends RuntimeException {

    /**
     * The constant used for serialization.
     */
    private static final long serialVersionUID = 1838360765923168037L;

    /**
     * Constructor with message and throwable.
     *
     * @param explanation the explanation of the error.
     * @param t the base exception thrown
     */
    public ServiceException(final String explanation, final Throwable t) {
        super(explanation, t);
    }

    /**
     * Constructor without base exception.
     *
     * @param explanation the explanation of the error.
     */
    public ServiceException(final String explanation) {
        super(explanation);
    }

    /**
     * Constructor without custom message.
     *
     * @param t the base exception thrown
     */
    public ServiceException(final Throwable t) {
        super(t);
    }

    /**
     * Return the explanation of the exception.
     *
     * @return the exception explanation
     */
    public String getExplanation() {
        final StringBuilder sb = new StringBuilder();
        if (getMessage() != null) {
            sb.append(getMessage());
        }
        if (getCause() != null) {
            sb.append(getCause().getClass().getSimpleName());
        }

        return sb.toString();
    }

}
