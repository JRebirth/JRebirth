package org.jrebirth.core.log;

import org.jrebirth.core.resource.i18n.MessageItem;

/**
 * The Interface JRebirthLogger.
 */
public interface JRLogger {

    /**
     * Trace.
     * 
     * @param messageItem the message item
     */
    void trace(MessageItem messageItem);

    /**
     * Trace.
     * 
     * @param messageItem the message item
     * @param t the t
     */
    void trace(MessageItem messageItem, Throwable t);

    /**
     * Trace.
     * 
     * @param messageItem the message item
     * @param parameters the parameters
     */
    void trace(MessageItem messageItem, Object... parameters);

    /**
     * Trace.
     * 
     * @param messageItem the message item
     * @param t the t
     * @param parameters the parameters
     */
    void trace(MessageItem messageItem, Throwable t, Object... parameters);

    /**
     * Debug.
     * 
     * @param messageItem the message item
     */
    void debug(MessageItem messageItem);

    /**
     * Debug.
     * 
     * @param messageItem the message item
     * @param t the t
     */
    void debug(MessageItem messageItem, Throwable t);

    /**
     * Debug.
     * 
     * @param messageItem the message item
     * @param parameters the parameters
     */
    void debug(MessageItem messageItem, Object... parameters);

    /**
     * Debug.
     * 
     * @param messageItem the message item
     * @param t the t
     * @param parameters the parameters
     */
    void debug(MessageItem messageItem, Throwable t, Object... parameters);

    /**
     * Info.
     * 
     * @param messageItem the message item
     */
    void info(MessageItem messageItem);

    /**
     * Info.
     * 
     * @param messageItem the message item
     * @param t the t
     */
    void info(MessageItem messageItem, Throwable t);

    /**
     * Info.
     * 
     * @param messageItem the message item
     * @param parameters the parameters
     */
    void info(MessageItem messageItem, Object... parameters);

    /**
     * Info.
     * 
     * @param messageItem the message item
     * @param t the t
     * @param parameters the parameters
     */
    void info(MessageItem messageItem, Throwable t, Object... parameters);

    /**
     * Warn.
     * 
     * @param messageItem the message item
     */
    void warn(MessageItem messageItem);

    /**
     * Warn.
     * 
     * @param messageItem the message item
     * @param t the t
     */
    void warn(MessageItem messageItem, Throwable t);

    /**
     * Warn.
     * 
     * @param messageItem the message item
     * @param parameters the parameters
     */
    void warn(MessageItem messageItem, Object... parameters);

    /**
     * Warn.
     * 
     * @param messageItem the message item
     * @param t the t
     * @param parameters the parameters
     */
    void warn(MessageItem messageItem, Throwable t, Object... parameters);

    /**
     * Error.
     * 
     * @param messageItem the message item
     */
    void error(MessageItem messageItem);

    /**
     * Error.
     * 
     * @param messageItem the message item
     * @param t the t
     */
    void error(MessageItem messageItem, Throwable t);

    /**
     * Error.
     * 
     * @param messageItem the message item
     * @param parameters the parameters
     */
    void error(MessageItem messageItem, Object... parameters);

    /**
     * Error.
     * 
     * @param messageItem the message item
     * @param t the t
     * @param parameters the parameters
     */
    void error(MessageItem messageItem, Throwable t, Object... parameters);

}
