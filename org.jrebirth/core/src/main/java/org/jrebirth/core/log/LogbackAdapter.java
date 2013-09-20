package org.jrebirth.core.log;

import ch.qos.logback.classic.Logger;

import org.jrebirth.core.resource.i18n.MessageItem;

import org.slf4j.spi.LocationAwareLogger;

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

}
