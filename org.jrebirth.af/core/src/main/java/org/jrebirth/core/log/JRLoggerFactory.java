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

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;

/**
 * A factory for creating JRebirth Logger objects.
 * 
 * @author Sébastien Bordes
 */
public final class JRLoggerFactory {

    /** The logger map. */
    private static ConcurrentMap<String, JRLogger> loggerMap = new ConcurrentHashMap<>();

    /**
     * Private Constructor.
     */
    private JRLoggerFactory() {
        super();
    }

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

            // Get the logger instance accordinf to slf4j configuration
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
