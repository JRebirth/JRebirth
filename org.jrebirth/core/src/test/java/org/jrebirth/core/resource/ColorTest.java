package org.jrebirth.core.resource;

import javafx.scene.paint.Color;
import junit.framework.Assert;

import org.jrebirth.core.resource.color.ColorItem;
import org.jrebirth.core.resource.color.GrayColor;
import org.jrebirth.core.resource.color.HSBColor;
import org.jrebirth.core.resource.color.RGB01Color;
import org.jrebirth.core.resource.color.RGB255Color;
import org.jrebirth.core.resource.color.WebColor;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
    }

    private void checkGrayColor(final ColorItem colorItem) {
        final Color color = colorItem.get();
        final GrayColor wc = (GrayColor) ResourceBuilders.COLOR_BUILDER.getParam(colorItem);

        // compareRoundedValues(color.getRed(), convert255To1(wc.hex().substring(0, 2)));
        // compareRoundedValues(color.getGreen(), convert255To1(wc.hex().substring(2, 4)));
        // compareRoundedValues(color.getBlue(), convert255To1(wc.hex().substring(4, 6)));

        Assert.assertEquals(color.getOpacity(), wc.opacity());

    }

    @Test
    public void hsbColor() {

        checkHsbColor(TestColors.TEST_COLOR_HSB_1);
        checkHsbColor(TestColors.TEST_COLOR_HSB_2);
    }

    private void checkHsbColor(final ColorItem colorItem) {
        final Color color = colorItem.get();
        final HSBColor wc = (HSBColor) ResourceBuilders.COLOR_BUILDER.getParam(colorItem);

        compareRoundedValues(color.getHue(), wc.hue());
        compareRoundedValues(color.getSaturation(), wc.saturation());
        compareRoundedValues(color.getBrightness(), wc.brightness());

        Assert.assertEquals(color.getOpacity(), wc.opacity());
    }

    @Test
    public void webColor() {

        checkWebColor(TestColors.TEST_COLOR_WEB_1);
        checkWebColor(TestColors.TEST_COLOR_WEB_1);
    }

    private void checkWebColor(final ColorItem colorItem) {
        final Color color = colorItem.get();
        final WebColor wc = (WebColor) ResourceBuilders.COLOR_BUILDER.getParam(colorItem);

        compareRoundedValues(color.getRed(), convertHexToDouble(wc.hex().substring(0, 2)));
        compareRoundedValues(color.getGreen(), convertHexToDouble(wc.hex().substring(2, 4)));
        compareRoundedValues(color.getBlue(), convertHexToDouble(wc.hex().substring(4, 6)));

        Assert.assertEquals(color.getOpacity(), wc.opacity());

    }

    @Test
    public void rgb01Color() {

        checkRgb01Color(TestColors.TEST_COLOR_RGB01_1);
        checkRgb01Color(TestColors.TEST_COLOR_RGB01_2);
    }

    private void checkRgb01Color(final ColorItem colorItem) {
        final Color color = colorItem.get();
        final RGB01Color wc = (RGB01Color) ResourceBuilders.COLOR_BUILDER.getParam(colorItem);

        compareRoundedValues(color.getRed(), wc.red());
        compareRoundedValues(color.getGreen(), wc.green());
        compareRoundedValues(color.getBlue(), wc.blue());

        Assert.assertEquals(color.getOpacity(), wc.opacity());
    }

    @Test
    public void rgb255Color() {

        checkRgb255Color(TestColors.TEST_COLOR_RGB255_1);
        checkRgb255Color(TestColors.TEST_COLOR_RGB255_2);
    }

    private void checkRgb255Color(final ColorItem colorItem) {
        final Color color = colorItem.get();
        final RGB255Color wc = (RGB255Color) ResourceBuilders.COLOR_BUILDER.getParam(colorItem);

        compareRoundedValues(color.getRed(), convert255To1(wc.red()));
        compareRoundedValues(color.getGreen(), convert255To1(wc.green()));
        compareRoundedValues(color.getBlue(), convert255To1(wc.blue()));

        Assert.assertEquals(color.getOpacity(), wc.opacity());
    }

    private void compareRoundedValues(final double colorComponent, final double paramValue) {
        Assert.assertEquals(Math.round(colorComponent * 1_000_000), Math.round(paramValue * 1_000_000));
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
