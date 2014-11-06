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
package org.jrebirth.af.core.key;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.jrebirth.af.api.key.KeyGenerator;
import org.jrebirth.af.api.log.JRLogger;
import org.jrebirth.af.core.log.JRLoggerFactory;
import org.jrebirth.af.core.util.ClassUtility;

/**
 * The class <strong>MultitonKey</strong>.
 *
 * The key used to discriminate any multiton part in order to guarantee component unicity.
 *
 * @author Sébastien Bordes
 *
 * @param <R> the type of the object registered by this key
 */
public class MultitonKey<R> extends ClassKey<R> implements KeyMessages {

    /**
     *
     */
    private static final long serialVersionUID = -6051573419682436386L;

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(MultitonKey.class);

    /** The key formatted into a string. */
    private String key;

    /** List of keys that are part of the main key. */
    private final List<Object> keyPartList = new ArrayList<>();

    /**
     * Default Constructor.
     *
     * @param classField the descriptive class object
     * @param keyPart a list of immutable objects that guarantee component unicity
     * @param optionalData the optional data to be transmit to the component
     */
    public MultitonKey(final Class<R> classField, final Object[] keyPart, final Object... optionalData) {
        super(classField, optionalData);

        // Store all keys
        for (final Object k : keyPart) {
            this.keyPartList.add(k);
        }
        rebuildKey();
    }

    /**
     * (Re)-Build the string key by reading the keys list content.
     */
    private void rebuildKey() {

        final StringBuilder sb = new StringBuilder();

        sb.append(getClassField().getCanonicalName()).append('|');
        for (final Object keyObject : this.keyPartList) {
            sb.append(buildObjectKey(keyObject)).append('|');
        }
        this.key = sb.toString();
    }

    /**
     * Generate the string key for an object.
     *
     * @param object the object which is part of the global key
     *
     * @return the unique string for this object
     */
    private String buildObjectKey(final Object object) {

        String objectKey = null;
        final Class<?> objectClass = object.getClass();

        final KeyGenerator typeGenerator = objectClass.getAnnotation(KeyGenerator.class);
        if (typeGenerator == null) {
            objectKey = generateAggregatedKey(object);
        } else {
            objectKey = generateTypeKey(object, typeGenerator);
        }

        // If no Type keyGenerator neither Method Generator were used, use the default toString method
        if (objectKey == null) {
            objectKey = object.toString();
        }

        return objectKey;
    }

    /**
     * Generate Type key by using the class-level annotation.
     *
     * @param object the source object
     * @param typeGenerator the annotation that expressed how generate the string unique key
     *
     * @return the unique key or null if an error occurred
     */
    private String generateTypeKey(final Object object, final KeyGenerator typeGenerator) {
        String objectKey = null;
        Method method = null;
        try {
            method = object.getClass().getMethod(typeGenerator.value());
            objectKey = (String) method.invoke(object);
        } catch (final NoSuchMethodException e) {
            LOGGER.log(METHOD_NOT_FOUND, typeGenerator.value(), object.getClass().getSimpleName(), e);
        } catch (final SecurityException e) {
            LOGGER.log(NO_KEY_GENERATOR_METHOD, typeGenerator.value(), object.getClass().getSimpleName(), e);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            LOGGER.log(KEY_GENERATOR_FAILURE, typeGenerator.value(), object.getClass().getSimpleName(), e);
        }

        return objectKey;
    }

    /**
     * Generate unique key by using the method-level annotations.
     *
     * @param object the source object
     *
     * @return the unique key or null if an error occurred or no method annotation was found
     */
    private String generateAggregatedKey(final Object object) {
        // Store the aggregated key
        final StringBuilder sb = new StringBuilder();

        KeyGenerator methodGenerator;
        // Search for method annotation
        for (final Method m : object.getClass().getMethods()) {
            methodGenerator = m.getAnnotation(KeyGenerator.class);

            if (methodGenerator != null) {
                Object returnedValue = null;
                try {
                    returnedValue = m.invoke(object);

                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    LOGGER.log(METHOD_KEY_GENERATOR_FAILURE, m.getName(), object.getClass().getSimpleName(), e);
                }
                if (returnedValue == null) {
                    // returned value is null
                    LOGGER.log(NULL_METHOD_KEY, m.getName(), object.getClass().getSimpleName());
                } else {
                    sb.append(convertMethodKeyObject(methodGenerator, returnedValue));
                }

            }
        }
        return sb.toString().isEmpty() ? null : sb.toString();
    }

    /**
     * Convert the key returned by an annotated method to a string (using annotation value or not).
     *
     * @param methodGenerator the method annotation
     * @param keyValue the returned key value
     *
     * @return the key converted from object
     */
    private String convertMethodKeyObject(final KeyGenerator methodGenerator, final Object keyValue) {
        String res = "";
        if (keyValue instanceof String) {
            res = (String) keyValue;
        } else {
            try {

                // The default annotation value is toString
                final List<Method> converterList = ClassUtility.retrieveMethodList(keyValue.getClass(), methodGenerator.value());

                Method converter = null;
                for (final Method m : converterList) {
                    if (m.getParameterTypes().length == 0) {
                        converter = m;
                    }
                }

                if (converter == null) {
                    LOGGER.log(METHOD_NOT_FOUND, methodGenerator.value(), keyValue.getClass().getSimpleName());
                } else {
                    // The toString will be called for a string
                    final Object localKey = converter.invoke(keyValue);

                    if (localKey == null) {
                        LOGGER.log(NULL_METHOD_KEY_STRING, converter.getName(), keyValue.getClass().getSimpleName());
                    } else {
                        res = localKey.toString();
                    }
                }
            } catch (final SecurityException e) {
                LOGGER.log(NO_TOSTRING_KEY_METHOD, methodGenerator.value(), keyValue.getClass().getSimpleName(), e);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                LOGGER.log(METHOD_KEY_TOSTRING_FAILURE, methodGenerator.value(), keyValue.getClass().getSimpleName(), e);
            }
        }
        return res + "|";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object object) {
        return object != null && this.toString().equals(object.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return getKey().hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getKey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getKey() {
        if (this.key == null) {
            rebuildKey();
        }
        return this.key;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getValue() {
        Object obj = null;
        if (this.keyPartList.size() > 1) {
            obj = this.keyPartList;
        } else if (this.keyPartList.size() == 1) {
            obj = this.keyPartList.get(0);
        }
        return obj;
    }

}
