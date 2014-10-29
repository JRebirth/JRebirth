package org.jrebirth.af.core.wave;

/**
 * The class <strong>WaveItems</strong>.
 *
 * @author Sébastien Bordes
 */
public enum WaveItems implements WaveItemEnum {

    /** . */
    resourceName(new WaveItemBase<String>() {
    }),

    /** . */
    fieldName(new WaveItemBase<String>() {
    }),

    /** . */
    name(new WaveItemBase<String>() {
    });

    /**
     * Private constructor.
     *
     * @param wi the wave item to initialize
     */
    private WaveItems(final WaveItemBase<?> wi) {
        WaveItemBase.initEnum(this, wi);
    }

}
