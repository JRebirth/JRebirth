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
    public Slf4jAdapter(org.slf4j.Logger logger) {
        slf4jLogger = logger;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void trace(MessageItem messageItem) {
        slf4jLogger.trace(messageItem.getMarker(), messageItem.get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void trace(MessageItem messageItem, Throwable t) {
        slf4jLogger.trace(messageItem.getMarker(), messageItem.get(), t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void trace(MessageItem messageItem, Object... parameters) {
        slf4jLogger.trace(messageItem.getMarker(), messageItem.get(parameters));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void trace(MessageItem messageItem, Throwable t, Object... parameters) {
        slf4jLogger.trace(messageItem.getMarker(), messageItem.get(parameters), t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(MessageItem messageItem) {
        slf4jLogger.debug(messageItem.getMarker(), messageItem.get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(MessageItem messageItem, Throwable t) {
        slf4jLogger.debug(messageItem.getMarker(), messageItem.get(), t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(MessageItem messageItem, Object... parameters) {
        slf4jLogger.debug(messageItem.getMarker(), messageItem.get(parameters));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(MessageItem messageItem, Throwable t, Object... parameters) {
        slf4jLogger.debug(messageItem.getMarker(), messageItem.get(parameters), t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void info(MessageItem messageItem) {
        slf4jLogger.info(messageItem.getMarker(), messageItem.get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void info(MessageItem messageItem, Throwable t) {
        slf4jLogger.info(messageItem.getMarker(), messageItem.get(), t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void info(MessageItem messageItem, Object... parameters) {
        slf4jLogger.info(messageItem.getMarker(), messageItem.get(parameters));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void info(MessageItem messageItem, Throwable t, Object... parameters) {
        slf4jLogger.info(messageItem.getMarker(), messageItem.get(parameters), t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void warn(MessageItem messageItem) {
        slf4jLogger.warn(messageItem.getMarker(), messageItem.get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void warn(MessageItem messageItem, Throwable t) {
        slf4jLogger.warn(messageItem.getMarker(), messageItem.get(), t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void warn(MessageItem messageItem, Object... parameters) {
        slf4jLogger.warn(messageItem.getMarker(), messageItem.get(parameters));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void warn(MessageItem messageItem, Throwable t, Object... parameters) {
        slf4jLogger.warn(messageItem.getMarker(), messageItem.get(parameters), t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void error(MessageItem messageItem) {
        slf4jLogger.error(messageItem.getMarker(), messageItem.get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void error(MessageItem messageItem, Throwable t) {
        slf4jLogger.error(messageItem.getMarker(), messageItem.get(), t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void error(MessageItem messageItem, Object... parameters) {
        slf4jLogger.error(messageItem.getMarker(), messageItem.get(parameters));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void error(MessageItem messageItem, Throwable t, Object... parameters) {
        slf4jLogger.error(messageItem.getMarker(), messageItem.get(parameters), t);
    }

}
