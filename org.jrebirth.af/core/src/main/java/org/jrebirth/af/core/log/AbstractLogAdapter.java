package org.jrebirth.af.core.log;

import org.jrebirth.af.api.exception.CoreRuntimeException;
import org.jrebirth.af.api.log.JRLogger;
import org.jrebirth.af.api.resource.i18n.JRLevel;
import org.jrebirth.af.api.resource.i18n.MessageItem;
import org.jrebirth.af.core.resource.provided.JRebirthParameters;
import org.slf4j.Logger;

/**
 * The class <strong>AbstractLogAdapter</strong> shares common code used by {@link LogbackAdapter} and {@link Slf4jAdapter}.
 *
 * @author Sébastien Bordes
 */
public abstract class AbstractLogAdapter implements JRLogger {

    /** The internal logger. */
    private final Logger logger; // NOSONAR this is a wrapped logger with custom fqcn to still have the right line number

    /**
     * Default Constructor.
     * 
     * @param logger the underlying logger to use
     */
    public AbstractLogAdapter(final Logger logger) {
        this.logger = logger;
    }

    /**
     * @return Returns the logger.
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * If an error is logged when running in Developer Mode, Throw a Runtime Exception.
     *
     * When an exception is logged and when an error is logged and we are running in Developer Mode
     *
     * @param messageItem the message to display for the exception thrown
     * @param t the throwable source (could be null)
     * @param parameters the message parameters
     */
    protected void throwError(final MessageItem messageItem, final Throwable t, final Object... parameters) {
        if (messageItem.getLevel() == JRLevel.Exception ||
                messageItem.getLevel() == JRLevel.Error && JRebirthParameters.DEVELOPER_MODE.get()) {
            throw new CoreRuntimeException(messageItem, t, parameters);
        }
    }

}
