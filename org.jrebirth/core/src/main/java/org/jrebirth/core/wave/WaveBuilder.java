package org.jrebirth.core.wave;

import javafx.util.Builder;

/**
 * The class <strong>WaveBuilder</strong>.
 * 
 * Base builder used to build a custom wave.
 * 
 * @author Sébastien Bordes
 */
public class WaveBuilder<B extends WaveBuilder<B>> implements Builder<WaveBase> {

    private int setMask;
    private WaveGroup waveGroup;
    private WaveType waveType;
    private Class<?> relatedClass;
    private Class<? extends WaveBean> waveBeanClass;

    public static WaveBuilder<?> create()
    {
        return new WaveBuilder();
    }

    public void applyTo(final WaveBase paramWave)
    {
        // super.applyTo(paramWave);
        final int i = this.setMask;
        if ((i & 0x1) != 0) {
            paramWave.setWaveGroup(this.waveGroup);
        }
        if ((i & 0x2) != 0) {
            paramWave.setWaveType(this.waveType);
        }
        if ((i & 0x4) != 0) {
            paramWave.setRelatedClass(this.relatedClass);
        }
        if ((i & 0x8) != 0) {
            paramWave.setWaveBeanClass(this.waveBeanClass);
        }

    }

    public B waveGroup(final WaveGroup waveGroup)
    {
        this.waveGroup = waveGroup;
        this.setMask |= 1;
        return (B) this;
    }

    public B waveType(final WaveType waveType)
    {
        this.waveType = waveType;
        this.setMask |= 2;
        return (B) this;
    }

    public B relatedClass(final Class<?> relatedClass)
    {
        this.relatedClass = relatedClass;
        this.setMask |= 4;
        return (B) this;
    }

    public B waveBeanClass(final Class<? extends WaveBean> waveBeanClass)
    {
        this.waveBeanClass = waveBeanClass;
        this.setMask |= 8;
        return (B) this;
    }

    @Override
    public WaveBase build()
    {
        final WaveBase localWave = new WaveBase();
        applyTo(localWave);
        return localWave;
    }

}
