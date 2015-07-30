package org.jrebirth.af.core.resource.fxml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.jrebirth.af.api.resource.fxml.FXMLItem;
import org.jrebirth.af.api.ui.fxml.FXMLComponent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * The class <strong>ColorTest</strong>.
 *
 * @author Sébastien Bordes
 */
@Ignore("Must launch the Toolkit before")
public class FxmlTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void singletonEnumFxmlClone() {

        checkSingletonFXML(SingletonEnumFxmls.FIRST_FXML);
        checkSingletonFXML(SingletonEnumFxmls.SECOND_FXML);

    }

    @Test
    public void multitonEnumFxmlClone() {

        checkMultitonFXML(MultitonEnumFxmls.FIRST_FXML);
        checkMultitonFXML(MultitonEnumFxmls.SECOND_FXML);

    }

    @Test
    public void singletonFxmlClone() {

        checkSingletonFXML(TestFxmls.FIRST_FXML);
        checkSingletonFXML(TestFxmls.SECOND_FXML);

    }

    @Test
    public void multitonFxmlClone() {

        checkMultitonFXML(TestFxmls.THIRD_FXML);
        checkMultitonFXML(TestFxmls.FOURTH_FXML);

    }

    private void checkSingletonFXML(final FXMLItem singletonFxmlItem) {

        final FXMLComponent fx1 = singletonFxmlItem.get();
        final FXMLComponent fx2 = singletonFxmlItem.get();

        final FXMLComponent fx3 = singletonFxmlItem.getNew();
        final FXMLComponent fx4 = singletonFxmlItem.getNew();

        assertEquals(fx1, fx2);
        assertNotEquals(fx1, fx3);
        assertNotEquals(fx1, fx4);
        assertNotEquals(fx3, fx4);

    }

    private void checkMultitonFXML(final FXMLItem multitonFxmlItem) {

        final FXMLComponent fx1 = multitonFxmlItem.get();
        final FXMLComponent fx2 = multitonFxmlItem.get();

        final FXMLComponent fx3 = multitonFxmlItem.getNew();
        final FXMLComponent fx4 = multitonFxmlItem.getNew();

        assertNotEquals(fx1, fx2);
        assertNotEquals(fx1, fx3);
        assertNotEquals(fx1, fx4);
        assertNotEquals(fx2, fx3);
        assertNotEquals(fx2, fx4);
        assertNotEquals(fx3, fx4);

    }

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

}
