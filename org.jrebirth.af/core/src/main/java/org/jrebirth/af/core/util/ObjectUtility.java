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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;

/**
 * The class <strong>ObjectUtility</strong>.
 *
 * Some Useful class utilities to perform basic object tasks.
 *
 * @author Sébastien Bordes
 */
public final class ObjectUtility {

    /**
     * Private Constructor.
     */
    private ObjectUtility() {
        // Nothing to do
    }

    /**
     * Return true if the object are equals or both null.
     *
     * @param object1 first object to compare
     * @param object2 second object to compare
     *
     * @return true if the object are equals or both null
     */
    public static boolean equalsOrBothNull(final Object object1, final Object object2) {
        return object1 == null && object2 == null || object1 != null && object1.equals(object2);
    }

    /**
     * Return true if the object are NOT equals.<br />
     * This method is NullPointerException-proof
     *
     * @param object1 first object to compare
     * @param object2 second object to compare
     *
     * @return true if the object are NOT equals
     */
    public static boolean notEquals(final Object object1, final Object object2) {
        return object1 != null && !object1.equals(object2) || object1 == null && object2 != null;
    }

    /**
     * Lower case te first char of a string.
     *
     * @param upperCasedString the string to modify
     * @return a new string with a first character lower cased
     */
    public static String lowerFirstChar(final String upperCasedString) {
        return upperCasedString.substring(0, 1).toLowerCase(Locale.getDefault()) + upperCasedString.substring(1);
    }

    /**
     * Check if the string is null or empty.
     *
     * @param str the string to check
     *
     * @return true if the string is null or empty
     */
    public static boolean nullOrEmpty(final String str) {
        return str == null || str.isEmpty();
    }

    /**
     * Check if all given methods return true or if the list is empty.
     *
     * @param instance the context object
     * @param methods the list of method to check
     *
     * @return true if all method return true or if the list is empty
     */
    public static boolean checkAllMethodReturnTrue(final Object instance, final List<Method> methods) {
        boolean res = true;

        if (!methods.isEmpty()) {
            for (final Method method : methods) {
                Object returnValue;
                try {
                    returnValue = method.invoke(instance);
                    res &= returnValue instanceof Boolean && ((Boolean) returnValue).booleanValue();
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    res = false;
                }
            }
        }
        return res;
    }

}
