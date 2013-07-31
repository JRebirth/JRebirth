package org.jrebirth.core.wave;

public enum WaveItems implements IWaveItem {

    resourceName(new WaveItem<String>() {
    }),

    fieldName(new WaveItem<String>() {
    }),

    name(new WaveItem<String>() {
    });

    WaveItems(WaveItem<?> wi) {
        WaveItem.init(this, wi);
    }

}
