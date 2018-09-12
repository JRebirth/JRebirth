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

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javafx.application.Application;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.exception.CoreRuntimeException;
import org.jrebirth.af.api.log.JRLogger;
import org.jrebirth.af.core.log.JRLoggerFactory;

/**
 * The class <strong>ClassUtility</strong>.
 *
 * Some Useful class utilities to perform introspection.
 *
 * @author Sébastien Bordes
 */
public final class ClassUtility implements UtilMessages {

    /** The separator used for serialization. */
    public static final String SEPARATOR = "|";

    /** The separator use in upper case to simulate a acamleCase change. */
    private static final String CASE_SEPARATOR = "_";

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(ClassUtility.class);

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
     * @param assignableClass the parent type of the generic to build
     * @param parameters used by the constructor of the generic type
     *
     * @return a new instance of the generic type
     *
     * @throws CoreException if the instantiation fails
     */
    public static Object buildGenericType(final Class<?> mainClass, final Class<?> assignableClass, final Object... parameters) throws CoreException {
        return buildGenericType(mainClass, new Class<?>[] { assignableClass }, parameters);
    }

    /**
     * Build the generic type according to assignable class.
     *
     * @param mainClass The main class used (that contain at least one generic type)
     * @param assignableClasses if the array contains only one class it define the type of the generic to build, otherwise it defines the types to skip to find the obejct to build
     * @param constructorParameters used by the constructor of the generic type
     *
     * @return a new instance of the generic type
     *
     * @throws CoreException if the instantiation fails
     */
    public static Object buildGenericType(final Class<?> mainClass, final Class<?>[] assignableClasses, final Object... constructorParameters) throws CoreException {
        Class<?> genericClass = null;
        // Copy parameters type to find the right constructor
        final Class<?>[] constructorParameterTypes = new Class<?>[constructorParameters.length];

        try {

            int i = 0;
            for (final Object obj : constructorParameters) {
                constructorParameterTypes[i] = obj.getClass();
                i++;
            }

            genericClass = findGenericClass(mainClass, assignableClasses);

            // Find the right constructor and use arguments to create a new instance
            final Constructor<?> constructor = getConstructor(genericClass, constructorParameterTypes);

            return constructor.newInstance(constructorParameters);

        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | SecurityException e) {

            final StringBuilder sb = new StringBuilder("[");
            for (final Class<?> assignableClass : assignableClasses) {
                sb.append(assignableClass.getName()).append(", ");
            }
            sb.append("]");

            if (genericClass == null) {
                LOGGER.log(GENERIC_TYPE_ERROR_1, e, sb.toString(), mainClass.getName());
            } else {
                LOGGER.log(GENERIC_TYPE_ERROR_2, e, genericClass.getName(), mainClass.getName());
            }

            if (e instanceof IllegalArgumentException) {
                LOGGER.log(ARGUMENT_LIST);
                for (int i = 0; i < constructorParameterTypes.length; i++) {
                    LOGGER.log(ARGUMENT_DETAIL, constructorParameterTypes[i].toString(), constructorParameters[i].toString());
                }
            }
            if (genericClass == null) {
                throw new CoreException(GENERIC_TYPE_ERROR_1.getText(sb.toString(), mainClass.getName()), e);
            } else {
                throw new CoreException(GENERIC_TYPE_ERROR_2.getText(genericClass.getName(), mainClass.getName()), e);
            }
        }
    }

    /**
     * Find and Build the generic type according to assignable and excluded classes.
     *
     * @param mainClass The main class used (that contains at least one generic type)
     * @param assignableClasses if the array contains only one class it define the type of the generic to build, otherwise it defines the types to skip to find the obejct to build
     * @param excludedClass the class that shouldn't be retrieved (ie: NullXX class)
     * @param parameters used by the constructor of the generic type
     *
     * @return the first generic type of a class that is compatible with provided assignable class.
     *
     * @throws CoreException if the class cannot be found and is not an excluded class
     */
    public static Object findAndBuildGenericType(final Class<?> mainClass, final Class<?> assignableClass, final Class<?> excludedClass, final Object... parameters) throws CoreException {

        Object object = null;
        final Class<?> objectClass = ClassUtility.findGenericClass(mainClass, assignableClass);

        if (objectClass != null && (excludedClass == null || !excludedClass.equals(objectClass))) {
            object = buildGenericType(mainClass, new Class<?>[] { assignableClass }, parameters);
        }
        return object;
    }

    /**
     * Retrieve the constructor of a Type.
     *
     * @param genericClass the type of the object
     * @param constructorParameterTypes an array of parameters' type to find the right constructor
     *
     * @return the right constructor that matchers parameters
     */
    private static Constructor<?> getConstructor(final Class<?> genericClass, final Class<?>[] constructorParameterTypes) {
        Constructor<?> constructor = null;
        try {
            constructor = genericClass.getConstructor(constructorParameterTypes);
        } catch (final NoSuchMethodException e) {
            // Return the first constructor as a workaround
            constructor = genericClass.getConstructors()[0];
        } catch (final SecurityException e) {
            LOGGER.log(NO_CONSTRUCTOR, e);
            throw e; // Pop up the exception to let it managed by the caller method
        }
        return constructor;
    }

    /**
     * Return the generic class for the given parent class and index.
     *
     * @param mainClass the parent class
     * @param assignableClass the parent type of the generic to build
     *
     * @return the class of the generic type according to the index provided or null if not found
     */
    public static Class<?> findGenericClass(final Class<?> mainClass, final Class<?> assignableClass) {
        return findGenericClass(mainClass, new Class<?>[] { assignableClass });
    }

    /**
     * Return the generic class for the given parent class and index.
     *
     * @param mainClass the parent class
     * @param excludedClasses if the array contains only one class it define the type of the generic to build, otherwise it defines the types to skip to find the object to build
     *
     * @return the class of the generic type according to the index provided or null if not found
     */
    public static Class<?> findGenericClass(final Class<?> mainClass, final Class<?>[] excludedClasses) {

        final boolean excludeMode = excludedClasses.length > 1;

        // Retrieve the generic super class Parameterized type
        final ParameterizedType paramType = (ParameterizedType) mainClass.getGenericSuperclass();

        Class<?> genericClass = null;
        Class<?> tempClass = null;
        for (int i = 0; genericClass == null && i < paramType.getActualTypeArguments().length; i++) {
            tempClass = getClassFromType(paramType.getActualTypeArguments()[i]);
            if (!excludeMode && excludedClasses[0].isAssignableFrom(tempClass)) {
                genericClass = tempClass;
            }
            if (excludeMode) {
                boolean excludeIt = false;
                for (final Class<?> excludeClass : excludedClasses) {
                    if (excludeClass.isAssignableFrom(tempClass)) {
                        excludeIt = true;
                    }
                }
                if (!excludeIt) {
                    genericClass = tempClass;
                }
            }
        }

        return genericClass;
    }

    /**
     * Convert A_STRING_UNDESCORED into aStringUnderscored.
     *
     * @param undescoredString the string to convert
     *
     * @return the string with camelCase
     */
    public static String underscoreToCamelCase(final String undescoredString) {

        // Split the string for each underscore
        final String[] parts = undescoredString.split(CASE_SEPARATOR);
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
     * Convert aStringUnderscored into A_STRING_UNDESCORED.
     *
     * @param camelCaseString the string to convert
     *
     * @return the underscored string
     */
    public static String camelCaseToUnderscore(final String camelCaseString) {

        final StringBuilder sb = new StringBuilder();
        for (final String camelPart : camelCaseString.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])")) {
            if (sb.length() > 0) {
                sb.append(CASE_SEPARATOR);
            }
            sb.append(camelPart.toUpperCase(Locale.getDefault()));
        }
        return sb.toString();
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
     * List all properties for the given class.
     *
     * @param cls the class to inspect by reflection
     *
     * @return the field list
     */
    public static List<Field> retrievePropertyList(final Class<?> cls) {
        final List<Field> propertyList = new ArrayList<>();
        for (final Field f : cls.getFields()) {
            propertyList.add(f);
        }
        return propertyList;
    }

    /**
     * Retrieve a field according to the item name first, then according to searched class.
     *
     * @param sourceClass the class to inspect
     * @param itemName the item name to find (LIKE_THIS)
     * @param searchedClass the property class to find if item name query has failed
     *
     * @return the source class field that match provided criterion
     */
    public static Field findProperty(final Class<?> sourceClass, final String itemName, final Class<?> searchedClass) {

        Field found = null;

        if (itemName != null && !itemName.trim().isEmpty()) {
            try {
                found = sourceClass.getField(ClassUtility.underscoreToCamelCase(itemName));
            } catch (NoSuchFieldException | SecurityException e) {
                found = null;
            }
        }
        if (found == null) {
            Field f = null;
            for (int i = 0; found == null && i < sourceClass.getFields().length; i++) {
                f = sourceClass.getFields()[i];
                if (f.getClass().equals(searchedClass)) {
                    found = f;
                }
            }
        }
        return found;
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
     * Extract the last annotation requested found into the class hierarchy.<br />
     * Interfaces are not yet supported.
     *
     * @param sourceClass the class (wit its parent classes) to inspect
     * @param annotationClass the annotation to find
     *
     * @param <A> the type of the requested annotation
     *
     * @return the request annotation or null if none have been found into the class hierarchy
     */
    public static <A extends Annotation> A getLastClassAnnotation(final Class<?> sourceClass, final Class<A> annotationClass) {
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
            LOGGER.log(NO_ANNOTATION_PROPERTY, e, attributeName);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            LOGGER.log(NO_ANNOTATION_PROPERTY_VALUE, e, attributeName);
        }
        return object;
    }

    /**
     * Return the class used by the Nth level of the current call stack.
     *
     * @param classDeepLevel the deep level to use to find the right class
     *
     * @return the Nth level ancestor class
     */
    @SuppressWarnings("unchecked")
    public static Class<? extends Application> getClassFromStaticMethod(final int classDeepLevel) {
        Class<? extends Application> clazz = null;
        try {
            clazz = (Class<? extends Application>) Class.forName(Thread.currentThread().getStackTrace()[classDeepLevel].getClassName());
        } catch (final ClassNotFoundException e) {
            clazz = null;
        }
        return clazz;
    }

    /**
     * Retrieve all annotation of the given type from the given class.
     *
     * @param cls the class that we want to inspect
     * @param annotationClass the type of Annotation to find
     *
     * @return the list of annotated method
     */
    public static List<Method> getAnnotatedMethods(final Class<?> cls, final Class<? extends Annotation> annotationClass) {
        final List<Method> methodList = new ArrayList<>();

        // Retrieve annotation even when they are wrapped into OnWaves
        for (final Method m : cls.getDeclaredMethods()) {
            if (m.getAnnotationsByType(annotationClass).length > 0) {
                methodList.add(m);
            }
        }

        // Add implemented interfaces methods
        for (final Class<?> i : cls.getInterfaces()) {
            methodList.addAll(getAnnotatedMethods(i, annotationClass));
        }

        // Add super class methods
        if (cls.getSuperclass() != null) {
            methodList.addAll(getAnnotatedMethods(cls.getSuperclass(), annotationClass));
        }

        return methodList;
    }

    /**
     * Retrieve all annotation of the given type from the given class.
     *
     * @param cls the class that we want to inspect
     * @param annotationClass the type of Annotation to find
     *
     * @return the list of annotated fields
     */
    public static List<Field> getAnnotatedFields(final Class<?> cls, final Class<? extends Annotation> annotationClass) {

        final List<Field> fieldList = new ArrayList<>();

        for (final Field f : cls.getDeclaredFields()) {
            if (f.getAnnotation(annotationClass) != null) {
                fieldList.add(f);
            }
        }

        // Add super class methods
        if (cls.getSuperclass() != null) {
            fieldList.addAll(getAnnotatedFields(cls.getSuperclass(), annotationClass));
        }

        return fieldList;
    }

    /**
     * Call the given method for the instance object even if its visibility is private or protected.
     *
     * @param method the method to call
     * @param instance the object instance to use
     * @param parameters the list of method parameters to use
     *
     * @return the method return
     * @throws CoreException if the method call has failed
     */
    public static Object callMethod(final Method method, final Object instance, final Object... parameters) throws CoreException {

        Object res = null;

        try {
            // store current visibility
            final boolean accessible = method.isAccessible();

            // let it accessible anyway
            method.setAccessible(true);

            // Call this method with right parameters
            res = method.invoke(instance, parameters);

            // Reset default visibility
            method.setAccessible(accessible);

        } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
            final String methodName = instance.getClass().getSimpleName() + "." + method.getName();
            throw new CoreException("Impossible to call method " + methodName, e);
        }
        return res;
    }

    /**
     * Update an object field even if it has a private or protected visibility.
     *
     * @param field the field to update
     * @param instance the object instance that hold this field
     * @param value the value to set into this field
     *
     * @throws CoreException if the new value cannot be set
     */
    public static void setFieldValue(final Field field, final Object instance, final Object value) throws CoreException {

        try {
            // store current visibility
            final boolean accessible = field.isAccessible();

            // let it accessible anyway
            field.setAccessible(true);

            // Call this method with right parameters
            field.set(instance, value);

            // Reset default visibility
            field.setAccessible(accessible);

        } catch (IllegalAccessException | IllegalArgumentException e) {
            throw new CoreException(e);
        }
    }

    /**
     * Retrieve an object field even if it has a private or protected visibility.
     *
     * @param field the field to update
     * @param instance the object instance that hold this field
     * 
     * @return value the value stored into this field
     *
     * @throws CoreException if the new value cannot be set
     */
    public static Object getFieldValue(final Field field, final Object instance) throws CoreRuntimeException {
        Object value = null;
        try {
            // store current visibility
            final boolean accessible = field.isAccessible();

            // let it accessible anyway
            field.setAccessible(true);

            // Call this method with right parameters
            value = field.get(instance);

            // Reset default visibility
            field.setAccessible(accessible);

        } catch (IllegalAccessException | IllegalArgumentException e) {
            throw new CoreRuntimeException(e);
        }
        return value;
    }

    /**
     * Return the first generic type of the hierarchy that can be assigned to the type searched.
     *
     * @param fromClass the base class hosting the generic type
     * @param typeSearched the searched type, the returned class shall be a subclass of it
     *
     * @return a class that is a subclass of typeSearched or null
     */
    public static Class<?> getGenericClassAssigned(final Class<?> fromClass, final Class<?> typeSearched) {

        Class<?> realType = null;

        final Type superType = fromClass.getGenericSuperclass();

        realType = searchIntoParameterzedType(superType, typeSearched);

        if (realType == null) {
            for (final Type api : fromClass.getGenericInterfaces()) {
                realType = searchIntoParameterzedType(api, typeSearched);
            }
        }

        if (realType == null && superType instanceof Class<?>) {
            realType = getGenericClassAssigned((Class<?>) superType, typeSearched);
        }

        if (realType == null) {
            for (final Type api : fromClass.getGenericInterfaces()) {
                if (api instanceof Class<?>) {
                    realType = getGenericClassAssigned((Class<?>) api, typeSearched);
                }
            }
        }

        return realType;
    }

    /**
     * Extract the searched type from a ParamterizedType.
     *
     * @param superType the base class hosting the generic type
     * @param typeSearched the searched type, the returned class shall be a subclass of it
     *
     * @return the searched type or null
     */
    private static Class<?> searchIntoParameterzedType(final Type superType, final Class<?> typeSearched) {
        if (superType instanceof ParameterizedType) {
            for (final Type genericType : ((ParameterizedType) superType).getActualTypeArguments()) {
                if (genericType instanceof Class<?> && typeSearched.isAssignableFrom((Class<?>) genericType)) {
                    return (Class<?>) genericType;
                } else if (genericType instanceof ParameterizedType && typeSearched.isAssignableFrom((Class<?>) ((ParameterizedType) genericType).getRawType())) {
                    return (Class<?>) ((ParameterizedType) genericType).getRawType();
                }
            }
        }
        return null;
    }
}
