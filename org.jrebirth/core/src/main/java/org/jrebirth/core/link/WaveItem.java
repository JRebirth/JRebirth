package org.jrebirth.core.link;

/**
 * The class <strong>WaveItem</strong>.
 * 
 * Wave item is used to identify an object into a wave.
 * 
 * @author Sébastien Bordes
 */
public interface WaveItem {

    /**
     * Return the type of the data class managed.
     * 
     * @return the type of the data class managed
     */
    Class<?> dataClass();

}
