package org.jrebirth.af.core.util;

import static org.jrebirth.af.core.wave.Builders.wave;
import static org.jrebirth.af.core.wave.Builders.waveType;

import org.jrebirth.af.api.exception.CoreRuntimeException;
import org.jrebirth.af.api.ui.NullView;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.contract.WaveType;
import org.jrebirth.af.core.resource.provided.parameter.CoreParameters;
import org.jrebirth.af.core.ui.DefaultModel;
import org.jrebirth.af.core.wave.Builders;
import org.jrebirth.af.core.wave.WaveItemBase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CheckerTest {

    WaveItemBase<String> StringItem = new WaveItemBase<String>() {
    };

    WaveItemBase<Integer> IntegerItem = new WaveItemBase<Integer>() {
    };

    WaveItemBase<Object> ObjectItem = new WaveItemBase<Object>() {
    };

    WaveType TYPE_0 = waveType("TYPE_0");

    WaveType TYPE_1 = waveType("TYPE_1").items(this.StringItem);

    WaveType TYPE_2 = waveType("TYPE_2").items(this.IntegerItem, this.StringItem);

    WaveType TYPE_3 = waveType("TYPE_3").items(this.ObjectItem, this.IntegerItem, this.StringItem);

    WaveType TYPE_4 = waveType("TYPE_4").items(this.IntegerItem, this.IntegerItem, this.IntegerItem);

    WaveType TYPE_5 = waveType("TYPE_5").items(this.StringItem, this.IntegerItem, this.ObjectItem);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        CoreParameters.DEVELOPER_MODE.define(true);
    }

    @Test()
    public void checkWaveTypes() {

        checkWaveType(this.TYPE_0, null);
        checkWaveType(this.TYPE_1, null);
        checkWaveType(this.TYPE_2, null);
        checkWaveType(this.TYPE_3, null);
        checkWaveType(this.TYPE_4, null);

        checkWaveType(this.TYPE_5, CoreRuntimeException.class);

    }

    private void checkWaveType(final WaveType type, final Class<? extends Throwable> exceptionClass) {

        if (exceptionClass != null) {
            this.thrown.expect(exceptionClass);
        }

        // Check the Wave Type
        CheckerUtility.checkWaveTypeContract(CheckerTestModel.class, type);

        // ONly called for successful checked wave type
        // Other will throw a CoreRuntimeException and use the Junit rule
        Assert.assertNull(exceptionClass);
    }

    public class CheckerTestModel extends DefaultModel<CheckerTestModel, NullView> {

        /**
         * {@inheritDoc}
         */
        @Override
        protected void initModel() {

            listen(CheckerTest.this.TYPE_0);
            listen(CheckerTest.this.TYPE_1);
            listen(CheckerTest.this.TYPE_2);
            listen(CheckerTest.this.TYPE_3);
            listen(CheckerTest.this.TYPE_4);

        }

        public void doType0(final Wave wave) {
        }

        public void doType1(final String item1, final Wave wave) {
        }

        public void doType2(final Integer item2, final String item1, final Wave wave) {
        }

        public void doType3(final Object item3, final Integer item2, final String item1, final Wave wave) {
        }

        public void doType4(final Object item3, final Object item2, final Object item1, final Wave wave) {
        }

    }

    @Test()
    public void checkWave0() {

        checkWave(wave().waveType(this.TYPE_0), null);
    }

    @Test()
    public void checkWave1() {

        // Right wave item
        checkWave(wave().waveType(this.TYPE_1)
                        .addDatas(Builders.waveData(this.StringItem, "string")),
                  null);

        // Right wave item + extra one
        checkWave(wave().waveType(this.TYPE_1)
                        .addDatas(Builders.waveData(this.StringItem, "string"), Builders.waveData(this.StringItem, "string")),
                  null);

        // No WaveItem instead of one
        checkWave(wave().waveType(this.TYPE_1), CoreRuntimeException.class);

        // Bad WaveItem
        checkWave(wave().waveType(this.TYPE_1)
                        .addDatas(Builders.waveData(this.IntegerItem, 0)),
                  CoreRuntimeException.class);

        // Bad WaveItem + Right one
        checkWave(wave().waveType(this.TYPE_1)
                        .addDatas(Builders.waveData(this.IntegerItem, 0), Builders.waveData(this.StringItem, "string")),
                  null);

    }

    @Test()
    public void checkWave2() {

        // Right wave item
        checkWave(wave().waveType(this.TYPE_2)
                        .addDatas(Builders.waveData(this.StringItem, "string"), Builders.waveData(this.IntegerItem, Integer.MIN_VALUE)),
                  null);

        // Right wave item + extra one
        checkWave(wave().waveType(this.TYPE_2)
                        .addDatas(Builders.waveData(this.StringItem, "string"), Builders.waveData(this.IntegerItem, Integer.MIN_VALUE), Builders.waveData(this.ObjectItem, new Object())),
                  null);

        // No WaveItem instead of two
        checkWave(wave().waveType(this.TYPE_2), CoreRuntimeException.class);

        // Only one WaveItem instead of two
        checkWave(wave().waveType(this.TYPE_2)
                        .addDatas(Builders.waveData(this.StringItem, "string")),
                  CoreRuntimeException.class);

        // Bad WaveItem
        checkWave(wave().waveType(this.TYPE_1)
                        .addDatas(Builders.waveData(this.IntegerItem, 0), Builders.waveData(this.ObjectItem, new Object())),
                  CoreRuntimeException.class);

        // Bad WaveItem + Right one (2)
        checkWave(wave().waveType(this.TYPE_1)
                        .addDatas(Builders.waveData(this.ObjectItem, new Object()), Builders.waveData(this.IntegerItem, 0), Builders.waveData(this.StringItem, "string")),
                  null);
    }

    @Test()
    public void checkWave3() {

        checkWave(wave().waveType(this.TYPE_3)
                        .addDatas(Builders.waveData(this.StringItem, "string"), Builders.waveData(this.IntegerItem, Integer.MIN_VALUE), Builders.waveData(this.ObjectItem, new Object())),
                  null);
    }

    @Test()
    public void checkWave4() {

        checkWave(wave().waveType(this.TYPE_4)
                        .addDatas(Builders.waveData(this.IntegerItem, 42), Builders.waveData(this.IntegerItem, Integer.MIN_VALUE), Builders.waveData(this.IntegerItem, 12)),
                  null);
    }

    @Test()
    public void checkWave5() {

        checkWave(wave().waveType(this.TYPE_5)
                        .addDatas(Builders.waveData(this.StringItem, "string"), Builders.waveData(this.IntegerItem, Integer.MIN_VALUE), Builders.waveData(this.ObjectItem, new Object())),
                  null);
    }

    private void checkWave(final Wave wave, final Class<? extends Throwable> exceptionClass) {
        if (exceptionClass != null) {
            this.thrown.expect(exceptionClass);
        }

        // Check that wave respect its WaveType contract (if any)
        CheckerUtility.checkWave(wave);

        // ONly called for successful checked wave type
        // Other will throw a CoreRuntimeException and use the Junit rule
        Assert.assertNull(exceptionClass);
    }
}
