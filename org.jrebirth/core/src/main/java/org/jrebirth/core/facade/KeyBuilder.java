package org.jrebirth.core.facade;

/**
 * The class <strong>KeyBuilder</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class KeyBuilder {

    /**
     * Private constructor to avoid instantiation.
     */
    private KeyBuilder() {
        // Nothing to initialize
    }

    /**
     * Build an unique key.
     * 
     * @param clazz the class type of the component
     * @param keyPart all complementary part of the key
     * 
     * @return the unique key for the given class and keyParts array
     */
    public static UniqueKey buildKey(final Class<?> clazz, final Object... keyPart) {

        UniqueKey uniqueKey;
        if (keyPart.length == 0) {
            uniqueKey = buildClassKey(clazz);
        } else {
            uniqueKey = buildMultitonKey(clazz, keyPart);
        }
        return uniqueKey;
    }

    /**
     * Build a singleton key.
     * 
     * @param clazz the class type of the component
     * 
     * @return the unique key for a singleton
     */
    private static UniqueKey buildClassKey(final Class<?> clazz) {
        return new ClassKey(clazz);
    }

    /**
     * Build a multiton key.
     * 
     * @param clazz the class type of the component
     * @param keyPart all complementary part of the key
     * 
     * @return the unique key for a multiton
     */
    private static UniqueKey buildMultitonKey(final Class clazz, final Object... keyPart) {
        return new MultitonKey(clazz, keyPart);
    }
}
