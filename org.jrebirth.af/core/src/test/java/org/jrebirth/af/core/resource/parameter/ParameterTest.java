package org.jrebirth.af.core.resource.parameter;

import org.jrebirth.af.api.exception.CoreRuntimeException;
import org.jrebirth.af.api.resource.i18n.JRLevel;
import org.jrebirth.af.api.resource.parameter.ParameterItem;
import org.jrebirth.af.core.log.JRebirthMarkers;
import org.jrebirth.af.core.resource.ResourceBuilders;
import org.jrebirth.af.core.resource.i18n.MessageResourceBase;
import org.jrebirth.af.core.resource.provided.parameter.CoreParameters;
import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * The class <strong>ParameterTest</strong>.
 *
 * @author Sébastien Bordes
 */
public class ParameterTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        ResourceBuilders.MESSAGE_BUILDER.searchMessagesFiles(".*_rb");
        ResourceBuilders.PARAMETER_BUILDER.searchConfigurationFiles(".*test-jrebirth", "properties");
        ParameterMessages.UNDEFINED_ENV_VAR.define(new MessageResourceBase("Fatal Error ! environment variable {0} not found ", JRebirthMarkers.PARAMETER, JRLevel.Exception));
        CoreParameters.DEVELOPER_MODE.define(true);
    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void stringParameter() {

        checkStringParameter(TestParameters.TEST_STRING_PARAM_1, "fonts");
        checkStringParameter(TestParameters.TEST_STRING_PARAM_2, "fontsFolder");
        checkStringParameter(TestParameters.TEST_STRING_PARAM_3, "font");// Default value
        checkStringParameter(TestParameters.TEST_STRING_PARAM_4, "fonts");// properties value, default one is ignored

        checkStringParameter(EnumParameters.param1, "astring");
        checkStringParameter(EnumParameters.param2, "to");
        checkStringParameter(EnumParameters.param3, "test");

    }

    private void checkStringParameter(final ParameterItem<String> parameterItem, final String checkedName) {
        final String param = parameterItem.get();
        assertEquals("Check String", checkedName, param);

    }

    @Test
    public void integerParameter() {

        checkIntegerParameter(TestParameters.TEST_INTEGER_WIDTH, 800);

    }

    @Test
    public void objectParameter() {

        checkObjectParameter(NumberParameters.number1, 1);

        checkObjectParameter(NumberParameters.number2, 3.333f);

        checkObjectParameter(NumberParameters.number3, 1_000_000_000_000L);

    }

    private void checkIntegerParameter(final ParameterItem<? extends Integer> parameterItem, final Integer checkedValue) {
        final Integer param = parameterItem.get();
        assertEquals("Check Integer", checkedValue, param);

    }

    private <O extends Object> void checkObjectParameter(final ParameterItem<O> parameterItem, final O checkedValue) {
        final O param = parameterItem.get();
        assertEquals("Check Integer", checkedValue, param);

    }

    @Test
    public void overridableParameter() {

        TestParameters.OVERRIDABLE_PARAM.define(2000);

        assertEquals(2000, TestParameters.OVERRIDABLE_PARAM.get().intValue());

        try {
            System.gc();
            Thread.sleep(500);
            System.gc();
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(2000, TestParameters.OVERRIDABLE_PARAM.get().intValue());

    }

    //@Test
    public void varenvParameter() {

        final String tmp = System.getenv("TMP");

        // Test properties redefined into file
        // System.out.println(TestParameters.VARENV_PARAM0.get());
        Assert.assertEquals(tmp, TestParameters.VARENV_PARAM0.get());

        // System.out.println(TestParameters.VARENV_PARAM1.get());
        Assert.assertEquals(tmp + "/first", TestParameters.VARENV_PARAM1.get());
        // System.out.println(TestParameters.VARENV_PARAM2.get());
        Assert.assertEquals(tmp + "/second", TestParameters.VARENV_PARAM2.get());

        // Test default values
        // System.out.println(TestParameters.VARENV_PARAM3.get());
        Assert.assertEquals(tmp + "/third", TestParameters.VARENV_PARAM3.get());
        // System.out.println(TestParameters.VARENV_PARAM4.get());
        Assert.assertEquals(tmp + "/fourth", TestParameters.VARENV_PARAM4.get());

        // Test not found envvar
        this.thrown.expect(CoreRuntimeException.class);
        // System.out.println(TestParameters.VARENV_PARAM3.get());
        Assert.assertEquals(tmp + "/fifth", TestParameters.VARENV_PARAM5.get());

    }

    @Test(expected = CoreRuntimeException.class)
    public void varenvParameterException() {
        // System.out.println(TestParameters.VARENV_PARAM4.get());
        Assert.assertEquals("$TMP2/sixth", TestParameters.VARENV_PARAM6.get());
    }

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

}
