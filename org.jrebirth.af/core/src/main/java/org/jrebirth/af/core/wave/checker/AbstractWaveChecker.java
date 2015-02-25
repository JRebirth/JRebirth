/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2015
 * Contact : sebastien.bordes@jrebirth.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.af.core.wave.checker;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.checker.WaveChecker;
import org.jrebirth.af.core.wave.WaveItemBase;

/**
 * The class <strong>AbstractWaveChecker</strong> is the base class for checker that use a WaveData.
 *
 * @param <I> the generic type of the WaveItem to check
 * @param <V> the value type of the reference object to compare to apply the filter
 *
 * @author Sébastien Bordes
 */
public abstract class AbstractWaveChecker<I extends Object, V> implements WaveChecker {

    /** The wave item used to store the value into a WaveData. */
    private final WaveItemBase<I> waveItem;

    /** The matching value used as a filter by the call method. */
    private final V matchingValue;

    /**
     * Default Constructor.
     *
     * @param waveItem the wave item
     * @param matchingValue the matching value
     */
    public AbstractWaveChecker(final WaveItemBase<I> waveItem, final V matchingValue) {
        super();
        this.waveItem = waveItem;
        this.matchingValue = matchingValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract Boolean call(final Wave wave);

    /**
     * @return Returns the waveItem.
     */
    protected WaveItemBase<I> getWaveItem() {
        return this.waveItem;
    }

    /**
     * @return Returns the matchingValue.
     */
    protected V getMatchingValue() {
        return this.matchingValue;
    }

}
