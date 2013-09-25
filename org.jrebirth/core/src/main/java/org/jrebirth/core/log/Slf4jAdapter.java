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

import org.slf4j.Logger;
import org.slf4j.Marker;

/**
 * The Class Slf4jAdapter.
 * 
 * @author Sébastien Bordes
 */
public class Slf4jAdapter implements JRLogger {

    /** The internal logger. */
    private final Logger slf4jLogger; // NOSONAR this is a wrapped logger

    /**
     * Default Constructor.
     * 
     * @param logger the logger to wrap
     */
    public Slf4jAdapter(final org.slf4j.Logger logger) {
        this.slf4jLogger = logger;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void trace(final MessageItem messageItem) {
        this.slf4jLogger.trace(messageItem.getMarker(), messageItem.get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void trace(final MessageItem messageItem, final Throwable t) {
        this.slf4jLogger.trace(messageItem.getMarker(), messageItem.get(), t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void trace(final MessageItem messageItem, final Object... parameters) {
        this.slf4jLogger.trace(messageItem.getMarker(), messageItem.get(parameters));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void trace(final MessageItem messageItem, final Throwable t, final Object... parameters) {
        this.slf4jLogger.trace(messageItem.getMarker(), messageItem.get(parameters), t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(final MessageItem messageItem) {
        this.slf4jLogger.debug(messageItem.getMarker(), messageItem.get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(final MessageItem messageItem, final Throwable t) {
        this.slf4jLogger.debug(messageItem.getMarker(), messageItem.get(), t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(final MessageItem messageItem, final Object... parameters) {
        this.slf4jLogger.debug(messageItem.getMarker(), messageItem.get(parameters));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(final MessageItem messageItem, final Throwable t, final Object... parameters) {
        this.slf4jLogger.debug(messageItem.getMarker(), messageItem.get(parameters), t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void info(final MessageItem messageItem) {
        this.slf4jLogger.info(messageItem.getMarker(), messageItem.get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void info(final MessageItem messageItem, final Throwable t) {
        this.slf4jLogger.info(messageItem.getMarker(), messageItem.get(), t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void info(final MessageItem messageItem, final Object... parameters) {
        this.slf4jLogger.info(messageItem.getMarker(), messageItem.get(parameters));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void info(final MessageItem messageItem, final Throwable t, final Object... parameters) {
        this.slf4jLogger.info(messageItem.getMarker(), messageItem.get(parameters), t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void warn(final MessageItem messageItem) {
        this.slf4jLogger.warn(messageItem.getMarker(), messageItem.get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void warn(final MessageItem messageItem, final Throwable t) {
        this.slf4jLogger.warn(messageItem.getMarker(), messageItem.get(), t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void warn(final MessageItem messageItem, final Object... parameters) {
        this.slf4jLogger.warn(messageItem.getMarker(), messageItem.get(parameters));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void warn(final MessageItem messageItem, final Throwable t, final Object... parameters) {
        this.slf4jLogger.warn(messageItem.getMarker(), messageItem.get(parameters), t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void error(final MessageItem messageItem) {
        this.slf4jLogger.error(messageItem.getMarker(), messageItem.get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void error(final MessageItem messageItem, final Throwable t) {
        this.slf4jLogger.error(messageItem.getMarker(), messageItem.get(), t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void error(final MessageItem messageItem, final Object... parameters) {
        this.slf4jLogger.error(messageItem.getMarker(), messageItem.get(parameters));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void error(final MessageItem messageItem, final Throwable t, final Object... parameters) {
        this.slf4jLogger.error(messageItem.getMarker(), messageItem.get(parameters), t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDebugEnabled() {
        return this.slf4jLogger.isDebugEnabled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDebugEnabled(final Marker marker) {
        return this.slf4jLogger.isDebugEnabled(marker);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isErrorEnabled() {
        return this.slf4jLogger.isErrorEnabled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isErrorEnabled(final Marker marker) {
        return this.slf4jLogger.isErrorEnabled(marker);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInfoEnabled() {
        return this.slf4jLogger.isInfoEnabled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInfoEnabled(final Marker marker) {
        return this.slf4jLogger.isInfoEnabled(marker);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTraceEnabled() {
        return this.slf4jLogger.isTraceEnabled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTraceEnabled(final Marker marker) {
        return this.slf4jLogger.isTraceEnabled(marker);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWarnEnabled() {
        return this.slf4jLogger.isWarnEnabled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWarnEnabled(final Marker marker) {
        return this.slf4jLogger.isWarnEnabled(marker);
    }

}
