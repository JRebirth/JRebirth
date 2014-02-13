package org.jrebirth.af.core.util;

import junit.framework.Assert;

import org.jrebirth.af.core.exception.CoreRuntimeException;
import org.jrebirth.af.core.resource.provided.JRebirthParameters;
import org.jrebirth.af.core.ui.DefaultModel;
import org.jrebirth.af.core.ui.NullView;
import org.jrebirth.af.core.util.CheckerUtility;
import org.jrebirth.af.core.wave.Wave;
import org.jrebirth.af.core.wave.WaveBuilder;
import org.jrebirth.af.core.wave.WaveData;
import org.jrebirth.af.core.wave.WaveItem;
import org.jrebirth.af.core.wave.WaveType;
import org.jrebirth.af.core.wave.WaveTypeBase;

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

    WaveType TYPE_0 = WaveTypeBase.build("TYPE_0");

    WaveType TYPE_1 = WaveTypeBase.build("TYPE_1", this.StringItem);

    WaveType TYPE_2 = WaveTypeBase.build("TYPE_2", this.IntegerItem, this.StringItem);

    WaveType TYPE_3 = WaveTypeBase.build("TYPE_3", this.ObjectItem, this.IntegerItem, this.StringItem);

    WaveType TYPE_4 = WaveTypeBase.build("TYPE_4", this.IntegerItem, this.IntegerItem, this.IntegerItem);

    WaveType TYPE_5 = WaveTypeBase.build("TYPE_5", this.StringItem, this.IntegerItem, this.ObjectItem);

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

        checkWave(WaveBuilder.create().waveType(TYPE_0)
                .build(), null);
    }

    @Test()
    public void checkWave1() {

        // Right wave item
        checkWave(WaveBuilder.create().waveType(TYPE_1)
                .data(WaveData.build(StringItem, "string"))
                .build(), null);

        // Right wave item + extra one
        checkWave(WaveBuilder.create().waveType(TYPE_1)
                .data(WaveData.build(StringItem, "string"), WaveData.build(StringItem, "string"))
                .build(), null);

        // No WaveItem instead of one
        checkWave(WaveBuilder.create().waveType(TYPE_1)
                .build(), CoreRuntimeException.class);

        // Bad WaveItem
        checkWave(WaveBuilder.create().waveType(TYPE_1)
                .data(WaveData.build(IntegerItem, 0))
                .build(), CoreRuntimeException.class);

        // Bad WaveItem + Right one
        checkWave(WaveBuilder.create().waveType(TYPE_1)
                .data(WaveData.build(IntegerItem, 0), WaveData.build(StringItem, "string"))
                .build(), null);

    }

    @Test()
    public void checkWave2() {

        // Right wave item
        checkWave(WaveBuilder.create().waveType(TYPE_2)
                .data(WaveData.build(StringItem, "string"), WaveData.build(IntegerItem, Integer.MIN_VALUE))
                .build(), null);

        // Right wave item + extra one
        checkWave(WaveBuilder.create().waveType(TYPE_2)
                .data(WaveData.build(StringItem, "string"), WaveData.build(IntegerItem, Integer.MIN_VALUE), WaveData.build(ObjectItem, new Object()))
                .build(), null);

        // No WaveItem instead of two
        checkWave(WaveBuilder.create().waveType(TYPE_2)
                .build(), CoreRuntimeException.class);

        // Only one WaveItem instead of two
        checkWave(WaveBuilder.create().waveType(TYPE_2)
                .data(WaveData.build(StringItem, "string"))
                .build(), CoreRuntimeException.class);

        // Bad WaveItem
        checkWave(WaveBuilder.create().waveType(TYPE_1)
                .data(WaveData.build(IntegerItem, 0), WaveData.build(ObjectItem, new Object()))
                .build(), CoreRuntimeException.class);

        // Bad WaveItem + Right one (2)
        checkWave(WaveBuilder.create().waveType(TYPE_1)
                .data(WaveData.build(ObjectItem, new Object()), WaveData.build(IntegerItem, 0), WaveData.build(StringItem, "string"))
                .build(), null);
    }

    @Test()
    public void checkWave3() {

        checkWave(WaveBuilder.create().waveType(TYPE_3)
                .data(WaveData.build(StringItem, "string"), WaveData.build(IntegerItem, Integer.MIN_VALUE), WaveData.build(ObjectItem, new Object()))
                .build(), null);
    }

    @Test()
    public void checkWave4() {

        checkWave(WaveBuilder.create().waveType(TYPE_4)
                .data(WaveData.build(IntegerItem, 42), WaveData.build(IntegerItem, Integer.MIN_VALUE), WaveData.build(IntegerItem, 12))
                .build(), null);
    }

    @Test()
    public void checkWave5() {

        checkWave(WaveBuilder.create().waveType(TYPE_5)
                .data(WaveData.build(StringItem, "string"), WaveData.build(IntegerItem, Integer.MIN_VALUE), WaveData.build(ObjectItem, new Object()))
                .build(), null);
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
