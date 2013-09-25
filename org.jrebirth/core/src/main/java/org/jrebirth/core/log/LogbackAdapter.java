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
import org.slf4j.spi.LocationAwareLogger;

import ch.qos.logback.classic.Logger;

/**
 * The Class LogbackAdapter.
 */
public class LogbackAdapter implements JRLogger {

    /**
     * The fully qualified name of this class. Used in gathering caller information.
     */
    public static final String FQCN = LogbackAdapter.class.getName();

    /** The internal logger. */
    private final Logger logbackLogger; // NOSONAR this is a wrapped logger with custom fqcn to still have the right line number

    /**
     * Instantiates a new logger adapter.
     * 
     * @param logger the wrapped logger
     */
    public LogbackAdapter(final Logger logger) {
        this.logbackLogger = logger;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void trace(final MessageItem messageItem) {
        this.logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.TRACE_INT, messageItem.get(), null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void trace(final MessageItem messageItem, final Throwable t) {
        this.logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.TRACE_INT, messageItem.get(), null, t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void trace(final MessageItem messageItem, final Throwable t, final Object... parameters) {
        this.logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.TRACE_INT, messageItem.get(parameters), null, t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void trace(final MessageItem messageItem, final Object... parameters) {
        this.logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.TRACE_INT, messageItem.get(parameters), null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(final MessageItem messageItem) {
        this.logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.DEBUG_INT, messageItem.get(), null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(final MessageItem messageItem, final Throwable t) {
        this.logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.DEBUG_INT, messageItem.get(), null, t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(final MessageItem messageItem, final Throwable t, final Object... parameters) {
        this.logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.DEBUG_INT, messageItem.get(parameters), null, t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(final MessageItem messageItem, final Object... parameters) {
        this.logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.DEBUG_INT, messageItem.get(parameters), null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void info(final MessageItem messageItem) {
        this.logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.INFO_INT, messageItem.get(), null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void info(final MessageItem messageItem, final Throwable t) {
        this.logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.INFO_INT, messageItem.get(), null, t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void info(final MessageItem messageItem, final Throwable t, final Object... parameters) {
        this.logbackLogger.info(messageItem.getMarker(), messageItem.get(parameters), t);
        this.logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.INFO_INT, messageItem.get(parameters), null, t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void info(final MessageItem messageItem, final Object... parameters) {
        this.logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.INFO_INT, messageItem.get(parameters), null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void warn(final MessageItem messageItem) {
        this.logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.WARN_INT, messageItem.get(), null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void warn(final MessageItem messageItem, final Throwable t) {
        this.logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.WARN_INT, messageItem.get(), null, t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void warn(final MessageItem messageItem, final Throwable t, final Object... parameters) {
        this.logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.WARN_INT, messageItem.get(parameters), null, t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void warn(final MessageItem messageItem, final Object... parameters) {
        this.logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.WARN_INT, messageItem.get(parameters), null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void error(final MessageItem messageItem) {
        this.logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.ERROR_INT, messageItem.get(), null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void error(final MessageItem messageItem, final Throwable t) {
        this.logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.ERROR_INT, messageItem.get(), null, t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void error(final MessageItem messageItem, final Throwable t, final Object... parameters) {
        this.logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.ERROR_INT, messageItem.get(parameters), null, t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void error(final MessageItem messageItem, final Object... parameters) {
        this.logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.ERROR_INT, messageItem.get(parameters), null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDebugEnabled() {
        return this.logbackLogger.isDebugEnabled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDebugEnabled(final Marker marker) {
        return this.logbackLogger.isDebugEnabled(marker);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isErrorEnabled() {
        return this.logbackLogger.isErrorEnabled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isErrorEnabled(final Marker marker) {
        return this.logbackLogger.isErrorEnabled(marker);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInfoEnabled() {
        return this.logbackLogger.isInfoEnabled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInfoEnabled(final Marker marker) {
        return this.logbackLogger.isInfoEnabled(marker);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTraceEnabled() {
        return this.logbackLogger.isTraceEnabled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTraceEnabled(final Marker marker) {
        return this.logbackLogger.isTraceEnabled(marker);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWarnEnabled() {
        return this.logbackLogger.isWarnEnabled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWarnEnabled(final Marker marker) {
        return this.logbackLogger.isWarnEnabled(marker);
    }

}
