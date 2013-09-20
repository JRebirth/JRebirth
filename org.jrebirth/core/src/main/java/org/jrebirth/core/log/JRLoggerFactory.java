package org.jrebirth.core.log;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;

/**
 * A factory for creating JRebirth Logger objects.
 */
public final class JRLoggerFactory {

    /** The logger map. */
    private static ConcurrentMap<String, JRLogger> loggerMap = new ConcurrentHashMap<>();

    /**
     * Return a logger named corresponding to the class passed as parameter, using the statically bound {@link ILoggerFactory} instance.
     * 
     * @param clazz the returned logger will be named after clazz
     * 
     * @return the logger adapter
     */
    public static JRLogger getLogger(final Class<?> clazz) {

        JRLogger logger = loggerMap.get(clazz.getName());
        if (logger == null) {
            JRLogger newInstance = null;

            final org.slf4j.Logger innerLogger = LoggerFactory.getLogger(clazz);

            if ("ch.qos.logback.classic.Logger".equals(innerLogger.getClass().getName())) {
                newInstance = new LogbackAdapter((ch.qos.logback.classic.Logger) innerLogger);
            } else {

                newInstance = new Slf4jAdapter(innerLogger);
            }

            final JRLogger oldInstance = loggerMap.putIfAbsent(clazz.getName(), newInstance);
            logger = oldInstance == null ? newInstance : oldInstance;
        }
        return logger;
    }
}
