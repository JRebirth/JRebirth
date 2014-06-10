package org.jrebirth.af.core.log;

import org.jrebirth.af.core.exception.CoreRuntimeException;
import org.jrebirth.af.core.resource.i18n.MessageItem;
import org.jrebirth.af.core.resource.provided.JRebirthParameters;

/**
 * The class <strong>AbstractLogAdapter</strong> shares common code used by {@link LogbackAdapter} and {@link Slf4jAdapter}.
 *
 * @author Sébastien Bordes
 */
public abstract class AbstractLogAdapter implements JRLogger {

    /**
     * If an error is logged when running in Developer Mode, Throw a Runtime Exception.
     *
     * @param messageItem the message to display for the exception thrown
     * @param t the throwable source (could be null)
     * @param parameters the message parameters
     */
    protected void throwError(final MessageItem messageItem, final Throwable t, final Object... parameters) {
        if (JRebirthParameters.DEVELOPER_MODE.get()) {
            throw new CoreRuntimeException(messageItem, t, parameters);
        }
    }

}