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
    public void fxmlClone() {

        checkFXML(EnumFxmls.FIRST_FXML);
        checkFXML(EnumFxmls.SECOND_FXML);

        checkFXML(TestFxmls.FIRST_FXML);
        checkFXML(TestFxmls.SECOND_FXML);

    }

    private void checkFXML(final FXMLItem fxmlItem) {

        final FXMLComponent fx1 = fxmlItem.get();
        final FXMLComponent fx2 = fxmlItem.get();

        final FXMLComponent fx3 = fxmlItem.getNew();
        final FXMLComponent fx4 = fxmlItem.getNew();

        assertEquals(fx1, fx2);
        assertNotEquals(fx1, fx3);
        assertNotEquals(fx1, fx4);
        assertNotEquals(fx3, fx4);

    }

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

}
