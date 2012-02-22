package org.jrebirth.core.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Locale;

import org.jrebirth.core.exception.CoreException;

/**
 * The class <strong>ClassUtility</strong>.
 * 
 * Some Useful class utilities to perform introspection.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public final class ClassUtility {

    /** The separtor used for serialization. */
    public static final String SEPARATOR = "|";

    /**
     * Private Constructor.
     */
    private ClassUtility() {
        // Nothing to do
    }

    /**
     * Build the nth generic type of a class.
     * 
     * @param mainClass The main class used (that contain at least one generic type)
     * @param index the index of the generic type we want to instantiate
     * @param parameters used by the constructor of the generic type
     * 
     * @return a new instance of the generic type
     * 
     * @throws CoreException if the instantiation fails
     */
    public static Object buildGenericType(final Class<?> mainClass, final int index, final Object... parameters) throws CoreException {
        try {

            // Copy parameters type to find the right constructor
            final Class<?>[] parameterTypes = new Class<?>[parameters.length];
            // final int i = 0;
            for (final Object obj : parameters) {
                parameterTypes[0] = obj.getClass();
            }

            // Retrieve the generic super class Parameterized type
            final ParameterizedType paramType = (ParameterizedType) mainClass.getGenericSuperclass();

            // Retrieve the right generic type we want to instantiate
            final Class<?> genericClass = (Class<?>) paramType.getActualTypeArguments()[index];

            // Find the right constructor and use arguments to create a new instance
            return genericClass.getConstructor(parameterTypes).newInstance(parameters);

        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException e) {
            throw new CoreException("Impossible to build the dedicated " + index + " th type of the class " + mainClass.getName(), e);
        }
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
}
