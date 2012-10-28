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
     * 
     * @param <C> The type of the object registered by this ClassKey
     */
    private static <C> UniqueKey buildClassKey(final Class<C> clazz) {
        return new ClassKey<C>(clazz);
    }

    /**
     * Build a multiton key.
     * 
     * @param clazz the class type of the component
     * @param keyPart all complementary part of the key
     * 
     * @return the unique key for a multiton
     * 
     * @param <C> The type of the object registered by this ClassKey
     */
    private static <C> UniqueKey buildMultitonKey(final Class<C> clazz, final Object... keyPart) {
        return new MultitonKey<C>(clazz, keyPart);
    }
}
