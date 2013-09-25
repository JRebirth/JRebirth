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
package org.jrebirth.core.log;

import org.jrebirth.core.resource.i18n.MessageItem;

import org.slf4j.Marker;

/**
 * The Interface JRebirthLogger.
 */
public interface JRLogger {

    /**
     * Trace.
     * 
     * @param messageItem the message item
     */
    void trace(final MessageItem messageItem);

    /**
     * Trace.
     * 
     * @param messageItem the message item
     * @param t the t
     */
    void trace(final MessageItem messageItem, final Throwable t);

    /**
     * Trace.
     * 
     * @param messageItem the message item
     * @param parameters the parameters
     */
    void trace(final MessageItem messageItem, final Object... parameters);

    /**
     * Trace.
     * 
     * @param messageItem the message item
     * @param t the t
     * @param parameters the parameters
     */
    void trace(final MessageItem messageItem, final Throwable t, final Object... parameters);

    /**
     * Debug.
     * 
     * @param messageItem the message item
     */
    void debug(final MessageItem messageItem);

    /**
     * Debug.
     * 
     * @param messageItem the message item
     * @param t the t
     */
    void debug(final MessageItem messageItem, final Throwable t);

    /**
     * Debug.
     * 
     * @param messageItem the message item
     * @param parameters the parameters
     */
    void debug(final MessageItem messageItem, final Object... parameters);

    /**
     * Debug.
     * 
     * @param messageItem the message item
     * @param t the t
     * @param parameters the parameters
     */
    void debug(final MessageItem messageItem, final Throwable t, final Object... parameters);

    /**
     * Info.
     * 
     * @param messageItem the message item
     */
    void info(final MessageItem messageItem);

    /**
     * Info.
     * 
     * @param messageItem the message item
     * @param t the t
     */
    void info(final MessageItem messageItem, final Throwable t);

    /**
     * Info.
     * 
     * @param messageItem the message item
     * @param parameters the parameters
     */
    void info(final MessageItem messageItem, final Object... parameters);

    /**
     * Info.
     * 
     * @param messageItem the message item
     * @param t the t
     * @param parameters the parameters
     */
    void info(final MessageItem messageItem, final Throwable t, final Object... parameters);

    /**
     * Warn.
     * 
     * @param messageItem the message item
     */
    void warn(final MessageItem messageItem);

    /**
     * Warn.
     * 
     * @param messageItem the message item
     * @param t the t
     */
    void warn(final MessageItem messageItem, final Throwable t);

    /**
     * Warn.
     * 
     * @param messageItem the message item
     * @param parameters the parameters
     */
    void warn(final MessageItem messageItem, final Object... parameters);

    /**
     * Warn.
     * 
     * @param messageItem the message item
     * @param t the t
     * @param parameters the parameters
     */
    void warn(final MessageItem messageItem, final Throwable t, final Object... parameters);

    /**
     * Error.
     * 
     * @param messageItem the message item
     */
    void error(final MessageItem messageItem);

    /**
     * Error.
     * 
     * @param messageItem the message item
     * @param t the t
     */
    void error(final MessageItem messageItem, final Throwable t);

    /**
     * Error.
     * 
     * @param messageItem the message item
     * @param parameters the parameters
     */
    void error(final MessageItem messageItem, final Object... parameters);

    /**
     * Error.
     * 
     * @param messageItem the message item
     * @param t the t
     * @param parameters the parameters
     */
    void error(final MessageItem messageItem, final Throwable t, final Object... parameters);

    boolean isDebugEnabled();

    boolean isDebugEnabled(final Marker marker);

    boolean isErrorEnabled();

    boolean isErrorEnabled(final Marker marker);

    boolean isInfoEnabled();

    boolean isInfoEnabled(final Marker marker);

    boolean isTraceEnabled();

    boolean isTraceEnabled(final Marker marker);

    boolean isWarnEnabled();

    boolean isWarnEnabled(final Marker marker);

}
