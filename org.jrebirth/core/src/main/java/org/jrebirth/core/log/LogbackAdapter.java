package org.jrebirth.core.log;

import org.jrebirth.core.resource.i18n.MessageItem;

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
    private final Logger logbackLogger;

    /**
     * Instantiates a new logger adapter.
     * 
     * @param logger the wrapped logger
     */
    public LogbackAdapter(final Logger logger) {
        logbackLogger = logger;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void trace(MessageItem messageItem) {
        logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.TRACE_INT, messageItem.get(), null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void trace(MessageItem messageItem, Throwable t) {
        logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.TRACE_INT, messageItem.get(), null, t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void trace(MessageItem messageItem, Throwable t, Object... parameters) {
        logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.TRACE_INT, messageItem.get(parameters), null, t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void trace(MessageItem messageItem, Object... parameters) {
        logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.TRACE_INT, messageItem.get(parameters), null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(MessageItem messageItem) {
        logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.DEBUG_INT, messageItem.get(), null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(MessageItem messageItem, Throwable t) {
        logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.DEBUG_INT, messageItem.get(), null, t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(MessageItem messageItem, Throwable t, Object... parameters) {
        logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.DEBUG_INT, messageItem.get(parameters), null, t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(MessageItem messageItem, Object... parameters) {
        logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.DEBUG_INT, messageItem.get(parameters), null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void info(MessageItem messageItem) {
        logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.INFO_INT, messageItem.get(), null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void info(MessageItem messageItem, Throwable t) {
        logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.INFO_INT, messageItem.get(), null, t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void info(MessageItem messageItem, Throwable t, Object... parameters) {
        logbackLogger.info(messageItem.getMarker(), messageItem.get(parameters), t);
        logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.INFO_INT, messageItem.get(parameters), null, t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void info(MessageItem messageItem, Object... parameters) {
        logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.INFO_INT, messageItem.get(parameters), null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void warn(MessageItem messageItem) {
        logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.WARN_INT, messageItem.get(), null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void warn(MessageItem messageItem, Throwable t) {
        logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.WARN_INT, messageItem.get(), null, t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void warn(MessageItem messageItem, Throwable t, Object... parameters) {
        logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.WARN_INT, messageItem.get(parameters), null, t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void warn(MessageItem messageItem, Object... parameters) {
        logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.WARN_INT, messageItem.get(parameters), null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void error(MessageItem messageItem) {
        logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.ERROR_INT, messageItem.get(), null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void error(MessageItem messageItem, Throwable t) {
        logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.ERROR_INT, messageItem.get(), null, t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void error(MessageItem messageItem, Throwable t, Object... parameters) {
        logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.ERROR_INT, messageItem.get(parameters), null, t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void error(MessageItem messageItem, Object... parameters) {
        logbackLogger.log(messageItem.getMarker(), FQCN, LocationAwareLogger.ERROR_INT, messageItem.get(parameters), null, null);
    }

}
