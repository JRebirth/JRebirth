package org.jrebirth.core.resource.color;

import javafx.scene.paint.Color;

import org.jrebirth.af.core.resource.ResourceBuilders;
import org.jrebirth.af.core.resource.color.ColorItem;
import org.jrebirth.af.core.resource.color.GrayColor;
import org.jrebirth.af.core.resource.color.HSBColor;
import org.jrebirth.af.core.resource.color.RGB01Color;
import org.jrebirth.af.core.resource.color.RGB255Color;
import org.jrebirth.af.core.resource.color.WebColor;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The class <strong>ColorTest</strong>.
 * 
 * @author Sébastien Bordes
 */
public class ColorTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void grayColor() {

        checkGrayColor(TestColors.TEST_COLOR_GRAY_1);
        checkGrayColor(TestColors.TEST_COLOR_GRAY_2);
        checkGrayColor(TestColors.TEST_COLOR_GRAY_3);
    }

    private void checkGrayColor(final ColorItem colorItem) {
        final Color color = colorItem.get();
        final GrayColor wc = (GrayColor) ResourceBuilders.COLOR_BUILDER.getParam(colorItem);

        // compareRoundedValues(color.getRed(), convert255To1(wc.hex().substring(0, 2)));
        // compareRoundedValues(color.getGreen(), convert255To1(wc.hex().substring(2, 4)));
        // compareRoundedValues(color.getBlue(), convert255To1(wc.hex().substring(4, 6)));

        assertEquals(color.getOpacity(), wc.opacity(), 0.1);

    }

    @Test
    public void hsbColor() {

        checkHsbColor(TestColors.TEST_COLOR_HSB_1);
        checkHsbColor(TestColors.TEST_COLOR_HSB_2);
        checkHsbColor(TestColors.TEST_COLOR_HSB_3);
    }

    private void checkHsbColor(final ColorItem colorItem) {
        final Color color = colorItem.get();
        final HSBColor wc = (HSBColor) ResourceBuilders.COLOR_BUILDER.getParam(colorItem);

        assertEquals(color.getHue(), wc.hue(), 1.0);
        assertEquals(color.getSaturation(), wc.saturation(), 0.000001);
        assertEquals(color.getBrightness(), wc.brightness(), 0.000001);

        assertEquals(color.getOpacity(), wc.opacity(), 0.1);
    }

    @Test
    public void webColor() {

        checkWebColor(TestColors.TEST_COLOR_WEB_1);
        checkWebColor(TestColors.TEST_COLOR_WEB_2);
        checkWebColor(TestColors.TEST_COLOR_WEB_3);
    }

    private void checkWebColor(final ColorItem colorItem) {
        final Color color = colorItem.get();
        final WebColor wc = (WebColor) ResourceBuilders.COLOR_BUILDER.getParam(colorItem);

        assertEquals(color.getRed(), convertHexToDouble(wc.hex().substring(0, 2)), 0.000001);
        assertEquals(color.getGreen(), convertHexToDouble(wc.hex().substring(2, 4)), 0.000001);
        assertEquals(color.getBlue(), convertHexToDouble(wc.hex().substring(4, 6)), 0.000001);

        assertEquals(color.getOpacity(), wc.opacity(), 0.1);

    }

    @Test
    public void rgb01Color() {

        checkRgb01Color(TestColors.TEST_COLOR_RGB01_1);
        checkRgb01Color(TestColors.TEST_COLOR_RGB01_2);
        checkRgb01Color(TestColors.TEST_COLOR_RGB01_3);
    }

    private void checkRgb01Color(final ColorItem colorItem) {
        final Color color = colorItem.get();
        final RGB01Color wc = (RGB01Color) ResourceBuilders.COLOR_BUILDER.getParam(colorItem);

        assertEquals(color.getRed(), wc.red(), 0.000001);
        assertEquals(color.getGreen(), wc.green(), 0.000001);
        assertEquals(color.getBlue(), wc.blue(), 0.000001);

        assertEquals(color.getOpacity(), wc.opacity(), 0.1);
    }

    @Test
    public void rgb255Color() {

        checkRgb255Color(TestColors.TEST_COLOR_RGB255_1);
        checkRgb255Color(TestColors.TEST_COLOR_RGB255_2);
        checkRgb255Color(TestColors.TEST_COLOR_RGB255_3);
    }

    private void checkRgb255Color(final ColorItem colorItem) {
        final Color color = colorItem.get();
        final RGB255Color wc = (RGB255Color) ResourceBuilders.COLOR_BUILDER.getParam(colorItem);

        assertEquals(color.getRed(), convert255To1(wc.red()), 0.000001);
        assertEquals(color.getGreen(), convert255To1(wc.green()), 0.000001);
        assertEquals(color.getBlue(), convert255To1(wc.blue()), 0.000001);

        assertEquals(color.getOpacity(), wc.opacity(), 0.1);
    }

    private double convertHexToDouble(final String hexaSingleColor) {
        final int i = Integer.parseInt(hexaSingleColor, 16);
        return convert255To1(i);
    }

    private double convert255To1(final int i) {
        return i == 0 ? i : i / 255.0;
    }

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

}
