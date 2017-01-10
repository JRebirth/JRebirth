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
package org.jrebirth.af.core.wave;

import org.jrebirth.af.api.wave.contract.WaveItem;
import org.jrebirth.af.api.wave.contract.WaveType;

import org.junit.Test;

import org.junit.Assert;

public class WaveTypeTest {

    @Test
    public void testTheReturnItemOfReturnWaveType() {
        final WaveType RE_FLUSH = WBuilder.waveType("RE_FLUSH");
        final WaveType FLUSH = WBuilder.waveType("FLUSH").returnWaveType(RE_FLUSH);

        Assert.assertTrue(FLUSH.returnWaveType().items().isEmpty());
        Assert.assertNull(FLUSH.returnItem());
    }

    @Test
    public void testHandlerMethodName() {
        final WaveItem<Object> RESULT = new WaveItemBase<Object>() {
        };
        final WaveType RE_FLUSH = WBuilder.waveType("RE_FLUSH").items(RESULT);
        final WaveType FLUSH = WBuilder.waveType("FLUSH").returnWaveType(RE_FLUSH).returnItem(RESULT);

        Assert.assertEquals("FLUSH", FLUSH.action());
        Assert.assertEquals("DO_FLUSH", FLUSH.toString());

        Assert.assertEquals("RE_FLUSH", RE_FLUSH.action());
        Assert.assertEquals("DO_RE_FLUSH", RE_FLUSH.toString());

        Assert.assertEquals("RE_FLUSH", FLUSH.returnWaveType().action());
        Assert.assertEquals("DO_RE_FLUSH", FLUSH.returnWaveType().toString());
    }
}
