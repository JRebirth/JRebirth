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
package org.jrebirth.core.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.jrebirth.core.exception.CoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>ClassUtility</strong>.
 * 
 * Some Useful class utilities to perform introspection.
 * 
 * @author Sébastien Bordes
 */
public final class ClassUtility {

    /** The separator used for serialization. */
    public static final String SEPARATOR = "|";

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtility.class);

    /**
     * Private Constructor.
     */
    private ClassUtility() {
        // Nothing to do
    }

    // public static boolean isGenericType(final Class<?> mainClass, final int index, final Class<?> genericTypeClass) {
    //
    // // Retrieve the generic super class Parameterized type
    // final ParameterizedType paramType = (ParameterizedType) mainClass.getGenericInterfaces();
    //
    // // Retrieve the right generic type we want to instantiate
    // final Class<?> genericClass = (Class<?>) paramType.getActualTypeArguments()[index];
    //
    // return genericTypeClass.equals(genericClass);
    // }

    /**
     * Build the nth generic type of a class.
     * 
     * @param mainClass The main class used (that contain at least one generic type)
     * @param superTypeIndex the index of the generic type we want to instantiate set to the parent extended
     * @param parameters used by the constructor of the generic type
     * 
     * @return a new instance of the generic type
     * 
     * @throws CoreException if the instantiation fails
     */
    public static Object buildGenericType(final Class<?> mainClass, final int superTypeIndex, final Object... parameters) throws CoreException {
        try {

            // Copy parameters type to find the right constructor
            final Class<?>[] parameterTypes = new Class<?>[parameters.length];

            int i = 0;
            for (final Object obj : parameters) {
                parameterTypes[i] = obj.getClass();
                i++;
            }

            final Class<?> genericClass = getGenericClass(mainClass, superTypeIndex);

            // Find the right constructor and use arguments to create a new
            // instance
            return genericClass.getConstructor(parameterTypes).newInstance(parameters);

        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException e) {
            final String message = "Impossible to build the dedicated " + superTypeIndex + " th type of the class " + mainClass.getName();
            LOGGER.error(message, e);
            throw new CoreException(message, e);
        }
    }

    /**
     * Return the generic class for the given parent class and index.
     * 
     * @param mainClass the parent class
     * @param superTypeIndex the index of the generic type to return
     * 
     * @return the class of the generic type according to the index provided
     */
    public static Class<?> getGenericClass(final Class<?> mainClass, final int superTypeIndex) {

        // Retrieve the generic super class Parameterized type
        final ParameterizedType paramType = (ParameterizedType) mainClass.getGenericSuperclass();

        // Retrieve the right generic type we want to instantiate
        final Class<?> genericClass = getClassFromType(paramType.getActualTypeArguments()[superTypeIndex]);

        return genericClass;
    }

    /**
     * Concert A_STRING_UNDESCORED into aStringUnderscored.
     * 
     * @param undescoredString the string to convert
     * 
     * @return the string with camelCase
     */
    public static String underscoreToCamelCase(final String undescoredString) {

        // Split the string for each underscore
        final String[] parts = undescoredString.split("_");
        final StringBuilder camelCaseString = new StringBuilder(undescoredString.length());
        camelCaseString.append(parts[0].toLowerCase(Locale.getDefault()));

        // Skip the first part already added
        for (int i = 1; i < parts.length; i++) {
            // First letter to upper case
            camelCaseString.append(parts[i].substring(0, 1).toUpperCase(Locale.getDefault()));
            // Others to lower case
            camelCaseString.append(parts[i].substring(1).toLowerCase(Locale.getDefault()));
        }
        return camelCaseString.toString();

    }

    /**
     * Return the method that exactly match the action name. The name must be unique into the class.
     * 
     * @param cls the class which contain the searched method
     * @param action the name of the method to find
     * @return the method
     * @throws NoSuchMethodException if no method was method
     */
    public static Method getMethodByName(final Class<?> cls, final String action) throws NoSuchMethodException {
        for (final Method m : cls.getMethods()) {
            if (m.getName().equals(action)) {
                return m;
            }
        }
        throw new NoSuchMethodException(action);
    }

    /**
     * Check if the given method exists in the given class.
     * 
     * @param cls the class to search into
     * @param methodName the name of the method to check (camelCased or in upper case with underscore separator)
     * 
     * @return true if the method exists
     */
    public static List<Method> retrieveMethodList(final Class<?> cls, final String methodName) {
        final List<Method> methodList = new ArrayList<>();
        final String camelCasedMethodName = underscoreToCamelCase(methodName);
        for (final Method m : cls.getMethods()) {
            if (m.getName().equals(camelCasedMethodName) || m.getName().equals(methodName)) {
                methodList.add(m);
            }
        }
        return methodList;
    }

    /**
     * Return the Class object for the given type.
     * 
     * @param type the given type to cast into Class
     * 
     * @return the Class casted object
     */
    public static Class<?> getClassFromType(final Type type) {
        Class<?> returnClass = null;
        if (type instanceof Class<?>) {
            returnClass = (Class<?>) type;
        } else if (type instanceof ParameterizedType) {
            returnClass = getClassFromType(((ParameterizedType) type).getRawType());
        }
        return returnClass;
    }

    /**
     * Extract the first annotation requested found into the class hierarchy.<br />
     * Interfaces are not yet supported.
     * 
     * @param sourceClass the class (wit its parent classes) to inspect
     * @param annotationClass the annotation to find
     * 
     * @param <A> the type of the requested annotation
     * 
     * @return the request annotation or null if none have been found into the class hierarchy
     */
    public static <A extends Annotation> A extractAnnotation(final Class<?> sourceClass, final Class<A> annotationClass) {
        A annotation = null;
        Class<?> currentClass = sourceClass;
        while (annotation == null && currentClass != null) {
            annotation = currentClass.getAnnotation(annotationClass);
            currentClass = currentClass.getSuperclass();
        }
        return annotation;
    }

    /**
     * Retrieve an annotation property dynamically by reflection.
     * 
     * @param annotation the annotation to explore
     * @param attributeName the name of the method to call
     * 
     * @return the property value
     */
    public static Object getAnnotationAttribute(final Annotation annotation, final String attributeName) {
        Object object = null;
        try {
            // Get the annotation method for the given name
            final Method attributeMethod = annotation.annotationType().getDeclaredMethod(attributeName);

            // Call the method to gets the value
            object = attributeMethod.invoke(annotation);

        } catch (NoSuchMethodException | SecurityException e) {
            LOGGER.error("Impossible to find the annotation property : " + attributeName, e);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            LOGGER.error("Impossible to retrieve value for the annotation property : " + attributeName, e);
        }
        return object;
    }

}
