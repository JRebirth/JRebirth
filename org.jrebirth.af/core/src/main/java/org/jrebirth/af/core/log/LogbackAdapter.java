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
package org.jrebirth.af.core.log;

import org.jrebirth.af.api.resource.i18n.JRLevel;
import org.jrebirth.af.api.resource.i18n.MessageItem;

import org.slf4j.Marker;
import org.slf4j.spi.LocationAwareLogger;

import ch.qos.logback.classic.Logger;

/**
 * The Class LogbackAdapter.
 */
public class LogbackAdapter extends AbstractLogAdapter { // NOSONAR lot of methods !!

    /**
     * The fully qualified name of this class. Used in gathering caller information.
     */
    public static final String FQCN = LogbackAdapter.class.getName();

    /**
     * Instantiates a new logger adapter.
     *
     * @param logger the wrapped logger
     */
    public LogbackAdapter(final Logger logger) {
        super(logger);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Logger getLogger() {
        return (Logger) super.getLogger();
    }

    /**
     * Convert JRebirth LogLevel to Logback one.
     *
     * @param level the JRebirth log level to convert
     *
     * @return the logback log level
     */
    private int convertLevel(final JRLevel level) {
        int logbackLevel = 0;
        switch (level) {
            case Trace:
                logbackLevel = LocationAwareLogger.TRACE_INT;
                break;
            case Debug:
                logbackLevel = LocationAwareLogger.DEBUG_INT;
                break;
            case Warn:
                logbackLevel = LocationAwareLogger.WARN_INT;
                break;
            case Error:
                logbackLevel = LocationAwareLogger.ERROR_INT;
                break;
            case Info:
                logbackLevel = LocationAwareLogger.INFO_INT;
                break;
            default:
                break;
        }
        return logbackLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void log(final MessageItem messageItem) {
        getLogger().log(messageItem.getMarker(), FQCN, convertLevel(messageItem.getLevel()), messageItem.getText(), null, null);
        throwError(messageItem, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void log(final MessageItem messageItem, final Throwable t) {
        getLogger().log(messageItem.getMarker(), FQCN, convertLevel(messageItem.getLevel()), messageItem.getText(), null, t);
        throwError(messageItem, t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void log(final MessageItem messageItem, final Object... parameters) {
        getLogger().log(messageItem.getMarker(), FQCN, convertLevel(messageItem.getLevel()), messageItem.getText(parameters), null, null);
        throwError(messageItem, null, parameters);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void log(final MessageItem messageItem, final Throwable t, final Object... parameters) {
        getLogger().log(messageItem.getMarker(), FQCN, convertLevel(messageItem.getLevel()), messageItem.getText(parameters), null, t);
        throwError(messageItem, t, parameters);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void trace(final MessageItem messageItem) {
        getLogger().log(messageItem.getMarker(), FQCN, LocationAwareLogger.TRACE_INT, messageItem.getText(), null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void trace(final MessageItem messageItem, final Throwable t) {
        getLogger().log(messageItem.getMarker(), FQCN, LocationAwareLogger.TRACE_INT, messageItem.getText(), null, t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void trace(final MessageItem messageItem, final Throwable t, final Object... parameters) {
        if (getLogger().isTraceEnabled(messageItem.getMarker())) {
            getLogger().log(messageItem.getMarker(), FQCN, LocationAwareLogger.TRACE_INT, messageItem.getText(parameters), null, t);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void trace(final MessageItem messageItem, final Object... parameters) {
        if (getLogger().isTraceEnabled(messageItem.getMarker())) {
            getLogger().log(messageItem.getMarker(), FQCN, LocationAwareLogger.TRACE_INT, messageItem.getText(parameters), null, null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(final MessageItem messageItem) {
        getLogger().log(messageItem.getMarker(), FQCN, LocationAwareLogger.DEBUG_INT, messageItem.getText(), null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(final MessageItem messageItem, final Throwable t) {
        getLogger().log(messageItem.getMarker(), FQCN, LocationAwareLogger.DEBUG_INT, messageItem.getText(), null, t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(final MessageItem messageItem, final Throwable t, final Object... parameters) {
        if (getLogger().isDebugEnabled(messageItem.getMarker())) {
            getLogger().log(messageItem.getMarker(), FQCN, LocationAwareLogger.DEBUG_INT, messageItem.getText(parameters), null, t);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(final MessageItem messageItem, final Object... parameters) {
        if (getLogger().isDebugEnabled(messageItem.getMarker())) {
            getLogger().log(messageItem.getMarker(), FQCN, LocationAwareLogger.DEBUG_INT, messageItem.getText(parameters), null, null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void info(final MessageItem messageItem) {
        getLogger().log(messageItem.getMarker(), FQCN, LocationAwareLogger.INFO_INT, messageItem.getText(), null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void info(final MessageItem messageItem, final Throwable t) {
        getLogger().log(messageItem.getMarker(), FQCN, LocationAwareLogger.INFO_INT, messageItem.getText(), null, t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void info(final MessageItem messageItem, final Throwable t, final Object... parameters) {
        if (getLogger().isInfoEnabled(messageItem.getMarker())) {
            getLogger().log(messageItem.getMarker(), FQCN, LocationAwareLogger.INFO_INT, messageItem.getText(parameters), null, t);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void info(final MessageItem messageItem, final Object... parameters) {
        if (getLogger().isInfoEnabled(messageItem.getMarker())) {
            getLogger().log(messageItem.getMarker(), FQCN, LocationAwareLogger.INFO_INT, messageItem.getText(parameters), null, null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void warn(final MessageItem messageItem) {
        getLogger().log(messageItem.getMarker(), FQCN, LocationAwareLogger.WARN_INT, messageItem.getText(), null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void warn(final MessageItem messageItem, final Throwable t) {
        getLogger().log(messageItem.getMarker(), FQCN, LocationAwareLogger.WARN_INT, messageItem.getText(), null, t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void warn(final MessageItem messageItem, final Throwable t, final Object... parameters) {
        if (getLogger().isWarnEnabled(messageItem.getMarker())) {
            getLogger().log(messageItem.getMarker(), FQCN, LocationAwareLogger.WARN_INT, messageItem.getText(parameters), null, t);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void warn(final MessageItem messageItem, final Object... parameters) {
        if (getLogger().isWarnEnabled(messageItem.getMarker())) {
            getLogger().log(messageItem.getMarker(), FQCN, LocationAwareLogger.WARN_INT, messageItem.getText(parameters), null, null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void error(final MessageItem messageItem) {
        getLogger().log(messageItem.getMarker(), FQCN, LocationAwareLogger.ERROR_INT, messageItem.getText(), null, null);
        throwError(messageItem, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void error(final MessageItem messageItem, final Throwable t) {
        getLogger().log(messageItem.getMarker(), FQCN, LocationAwareLogger.ERROR_INT, messageItem.getText(), null, t);
        throwError(messageItem, t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void error(final MessageItem messageItem, final Throwable t, final Object... parameters) {
        if (getLogger().isErrorEnabled(messageItem.getMarker())) {
            getLogger().log(messageItem.getMarker(), FQCN, LocationAwareLogger.ERROR_INT, messageItem.getText(parameters), null, t);
        }
        throwError(messageItem, t, parameters);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void error(final MessageItem messageItem, final Object... parameters) {
        if (getLogger().isErrorEnabled(messageItem.getMarker())) {
            getLogger().log(messageItem.getMarker(), FQCN, LocationAwareLogger.ERROR_INT, messageItem.getText(parameters), null, null);
        }
        throwError(messageItem, null, parameters);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDebugEnabled() {
        return getLogger().isDebugEnabled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDebugEnabled(final Marker marker) {
        return getLogger().isDebugEnabled(marker);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isErrorEnabled() {
        return getLogger().isErrorEnabled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isErrorEnabled(final Marker marker) {
        return getLogger().isErrorEnabled(marker);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInfoEnabled() {
        return getLogger().isInfoEnabled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInfoEnabled(final Marker marker) {
        return getLogger().isInfoEnabled(marker);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTraceEnabled() {
        return getLogger().isTraceEnabled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTraceEnabled(final Marker marker) {
        return getLogger().isTraceEnabled(marker);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWarnEnabled() {
        return getLogger().isWarnEnabled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWarnEnabled(final Marker marker) {
        return getLogger().isWarnEnabled(marker);
    }

}
