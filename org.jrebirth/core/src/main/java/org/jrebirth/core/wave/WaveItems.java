package org.jrebirth.core.wave;

/**
 * The class <strong>WaveItems</strong>.
 * 
 * @author Sébastien Bordes
 */
public enum WaveItems implements IWaveItem {

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
     * 
     * @param wi
     */
    WaveItems(final WaveItem<?> wi) {
        WaveItem.init(this, wi);
    }

}
