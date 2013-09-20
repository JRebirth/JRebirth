package org.jrebirth.core.log;

import org.jrebirth.core.resource.i18n.MessageItem;

import org.slf4j.Logger;

public class Slf4jAdapter implements JRLogger {

    /** The internal logger. */
    private final Logger slf4jLogger;

    /**
     * Default Constructor.
     * 
     * @param logger
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

}
