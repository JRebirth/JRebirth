package org.jrebirth.af.core.wave;

/**
 * The class <strong>WaveItems</strong>.
 * 
 * @author Sébastien Bordes
 */
public enum WaveItems implements WaveItemEnum {

    /** . */
    resourceName(new WaveItem<String>() {
    }),

    /** . */
    fieldName(new WaveItem<String>() {
    }),

    /** . */
    name(new WaveItem<String>() {
    });

    /**
     * Private constructor.
     * 
     * @param wi the wave item to initialize
     */
    private WaveItems(final WaveItem<?> wi) {
        WaveItem.initEnum(this, wi);
    }

}
