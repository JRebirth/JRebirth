package org.jrebirth.core.util;

import junit.framework.Assert;

import org.jrebirth.core.exception.CoreRuntimeException;
import org.jrebirth.core.resource.provided.JRebirthParameters;
import org.jrebirth.core.ui.DefaultModel;
import org.jrebirth.core.ui.NullView;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveItem;
import org.jrebirth.core.wave.WaveType;
import org.jrebirth.core.wave.WaveTypeBase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CheckerTest {

    WaveItem<String> ITEM_1 = new WaveItem<String>() {
    };

    WaveItem<Integer> ITEM_2 = new WaveItem<Integer>() {
    };

    WaveItem<Object> ITEM_3 = new WaveItem<Object>() {
    };

    WaveType TYPE_0 = WaveTypeBase.build("TYPE_0");

    WaveType TYPE_1 = WaveTypeBase.build("TYPE_1", this.ITEM_1);

    WaveType TYPE_2 = WaveTypeBase.build("TYPE_2", this.ITEM_2, this.ITEM_1);

    WaveType TYPE_3 = WaveTypeBase.build("TYPE_3", this.ITEM_3, this.ITEM_2, this.ITEM_1);

    WaveType TYPE_4 = WaveTypeBase.build("TYPE_4", this.ITEM_2, this.ITEM_2, this.ITEM_2);

    WaveType TYPE_5 = WaveTypeBase.build("TYPE_5", this.ITEM_1, this.ITEM_2, this.ITEM_3);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        JRebirthParameters.DEVELOPER_MODE.define(true);
    }

    @Test()
    public void checkWaveTypes() {

        checkWaveType(TYPE_0, null);
        checkWaveType(TYPE_1, null);
        checkWaveType(TYPE_2, null);
        checkWaveType(TYPE_3, null);
        checkWaveType(TYPE_4, null);

        checkWaveType(TYPE_5, CoreRuntimeException.class);

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
}
