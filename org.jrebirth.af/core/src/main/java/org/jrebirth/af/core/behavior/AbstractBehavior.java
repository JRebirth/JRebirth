package org.jrebirth.af.core.behavior;

import org.jrebirth.af.core.exception.CoreException;
import org.jrebirth.af.core.facade.WaveReady;
import org.jrebirth.af.core.link.AbstractWaveReady;
import org.jrebirth.af.core.wave.Wave;

public abstract class AbstractBehavior<D extends BehaviorData> extends AbstractWaveReady<Behavior<?>> implements Behavior<D> {

    private D data;

    private WaveReady<?> component;

    @Override
    public D getData() {
        return this.data;
    }

    public WaveReady<?> getComponent() {
        return this.component;
    }

    @Override
    protected void processWave(final Wave wave) {

    }

    @SuppressWarnings("unchecked")
    @Override
    protected void ready() throws CoreException {

        // FIXME fix leaks
        this.data = (D) getKey().getOptionalData().stream().filter(d -> d instanceof BehaviorData).findFirst().get();
        this.component = (WaveReady<?>) getKey().getOptionalData().stream().filter(d -> d instanceof WaveReady<?>).findFirst().get();

        initBehavior();

    }

    /**
     *
     */
    public abstract void initBehavior();

}
