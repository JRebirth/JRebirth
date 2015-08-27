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
package org.jrebirth.af.core.util;

import org.jrebirth.af.api.exception.CoreRuntimeException;
import org.jrebirth.af.api.log.JRLogger;
import org.jrebirth.af.api.resource.parameter.ParameterItem;
import org.jrebirth.af.core.log.JRLoggerFactory;

/**
 * The class <strong>ParameterUtility</strong>.
 *
 * Some Useful method used to manage Parameter.
 *
 * @author Sébastien Bordes
 */
public final class ParameterUtility implements UtilMessages {

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(ParameterUtility.class);

    /**
     * Private Constructor.
     */
    private ParameterUtility() {
        // Nothing to do
    }

    /**
     * Build a customizable class.
     *
     * @param parameter The parameter class to load
     * @param defaultObject the default object class to use as fallback
     * @param typeName the wanted type name for log purpose
     *
     * @param <D> the type wanted
     *
     * @return a new instance of the generic type
     */
    public static <D extends Object> Object buildCustomizableClass(final ParameterItem<Class<?>> parameter, final Class<D> defaultObject, final String typeName) {
        Object object = null;
        try {
            object = parameter.get().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error(CUSTOM_CLASS_LOADING_ERROR, e, typeName);
            try {
                object = defaultObject.newInstance();
            } catch (InstantiationException | IllegalAccessException e2) {
                throw new CoreRuntimeException("Impossible to build Default " + typeName, e2);
            }
        }
        return object;
    }

}
