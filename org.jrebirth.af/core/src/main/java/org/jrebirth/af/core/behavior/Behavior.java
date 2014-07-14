package org.jrebirth.af.core.behavior;

import org.jrebirth.af.core.facade.WaveReady;

public interface Behavior<D extends BehaviorData> extends WaveReady<Behavior<?>> {

    D getData();

    WaveReady<?> getComponent();

}
