package org.jrebirth.core.resource.parameter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * The class <strong>ParameterTest</strong>.
 * 
 * @author Sébastien Bordes
 */
public class ParameterTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void stringParameter() {

        checkStringParameter(TestParameters.TEST_STRING_PARAM_1);
    }

    private void checkStringParameter(final ParameterItemBase<String> parammeterItem) {
        // final Color color = colorItem.get();
        //
        // final GrayColor wc = (GrayColor) ResourceBuilders.COLOR_BUILDER.getParam(colorItem);
        //
        // assertEquals(color.getOpacity(), wc.opacity(), 0.1);

    }

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

}
