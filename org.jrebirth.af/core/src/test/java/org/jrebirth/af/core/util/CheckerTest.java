package org.jrebirth.af.core.util;

import junit.framework.Assert;

import org.jrebirth.af.core.exception.CoreRuntimeException;
import org.jrebirth.af.core.resource.provided.JRebirthParameters;
import org.jrebirth.af.core.ui.DefaultModel;
import org.jrebirth.af.core.ui.NullView;
import org.jrebirth.af.core.wave.Wave;
import org.jrebirth.af.core.wave.WaveBase;
import org.jrebirth.af.core.wave.WaveData;
import org.jrebirth.af.core.wave.WaveItem;
import org.jrebirth.af.core.wave.WaveType;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CheckerTest {

    WaveItem<String> StringItem = new WaveItem<String>() {
    };

    WaveItem<Integer> IntegerItem = new WaveItem<Integer>() {
    };

    WaveItem<Object> ObjectItem = new WaveItem<Object>() {
    };

    WaveType TYPE_0 = WaveType.create("TYPE_0");

    WaveType TYPE_1 = WaveType.create("TYPE_1").items(this.StringItem);

    WaveType TYPE_2 = WaveType.create("TYPE_2").items(this.IntegerItem, this.StringItem);

    WaveType TYPE_3 = WaveType.create("TYPE_3").items(this.ObjectItem, this.IntegerItem, this.StringItem);

    WaveType TYPE_4 = WaveType.create("TYPE_4").items(this.IntegerItem, this.IntegerItem, this.IntegerItem);

    WaveType TYPE_5 = WaveType.create("TYPE_5").items(this.StringItem, this.IntegerItem, this.ObjectItem);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        JRebirthParameters.DEVELOPER_MODE.define(true);
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

        checkWave(WaveBase.create().waveType(this.TYPE_0)
                , null);
    }

    @Test()
    public void checkWave1() {

        // Right wave item
        checkWave(WaveBase.create().waveType(this.TYPE_1)
                .addDatas(WaveData.build(this.StringItem, "string"))
                , null);

        // Right wave item + extra one
        checkWave(WaveBase.create().waveType(this.TYPE_1)
                .addDatas(WaveData.build(this.StringItem, "string"), WaveData.build(this.StringItem, "string"))
                , null);

        // No WaveItem instead of one
        checkWave(WaveBase.create().waveType(this.TYPE_1)
                , CoreRuntimeException.class);

        // Bad WaveItem
        checkWave(WaveBase.create().waveType(this.TYPE_1)
                .addDatas(WaveData.build(this.IntegerItem, 0))
                , CoreRuntimeException.class);

        // Bad WaveItem + Right one
        checkWave(WaveBase.create().waveType(this.TYPE_1)
                .addDatas(WaveData.build(this.IntegerItem, 0), WaveData.build(this.StringItem, "string"))
                , null);

    }

    @Test()
    public void checkWave2() {

        // Right wave item
        checkWave(WaveBase.create().waveType(this.TYPE_2)
                .addDatas(WaveData.build(this.StringItem, "string"), WaveData.build(this.IntegerItem, Integer.MIN_VALUE))
                , null);

        // Right wave item + extra one
        checkWave(WaveBase.create().waveType(this.TYPE_2)
                .addDatas(WaveData.build(this.StringItem, "string"), WaveData.build(this.IntegerItem, Integer.MIN_VALUE), WaveData.build(this.ObjectItem, new Object()))
                , null);

        // No WaveItem instead of two
        checkWave(WaveBase.create().waveType(this.TYPE_2)
                , CoreRuntimeException.class);

        // Only one WaveItem instead of two
        checkWave(WaveBase.create().waveType(this.TYPE_2)
                .addDatas(WaveData.build(this.StringItem, "string"))
                , CoreRuntimeException.class);

        // Bad WaveItem
        checkWave(WaveBase.create().waveType(this.TYPE_1)
                .addDatas(WaveData.build(this.IntegerItem, 0), WaveData.build(this.ObjectItem, new Object()))
                , CoreRuntimeException.class);

        // Bad WaveItem + Right one (2)
        checkWave(WaveBase.create().waveType(this.TYPE_1)
                .addDatas(WaveData.build(this.ObjectItem, new Object()), WaveData.build(this.IntegerItem, 0), WaveData.build(this.StringItem, "string"))
                , null);
    }

    @Test()
    public void checkWave3() {

        checkWave(WaveBase.create().waveType(this.TYPE_3)
                .addDatas(WaveData.build(this.StringItem, "string"), WaveData.build(this.IntegerItem, Integer.MIN_VALUE), WaveData.build(this.ObjectItem, new Object()))
                , null);
    }

    @Test()
    public void checkWave4() {

        checkWave(WaveBase.create().waveType(this.TYPE_4)
                .addDatas(WaveData.build(this.IntegerItem, 42), WaveData.build(this.IntegerItem, Integer.MIN_VALUE), WaveData.build(this.IntegerItem, 12))
                , null);
    }

    @Test()
    public void checkWave5() {

        checkWave(WaveBase.create().waveType(this.TYPE_5)
                .addDatas(WaveData.build(this.StringItem, "string"), WaveData.build(this.IntegerItem, Integer.MIN_VALUE), WaveData.build(this.ObjectItem, new Object()))
                , null);
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
