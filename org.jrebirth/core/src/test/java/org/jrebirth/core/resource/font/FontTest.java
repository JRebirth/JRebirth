package org.jrebirth.core.resource.font;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import junit.framework.Assert;

import org.jrebirth.core.resource.ResourceBuilders;

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
public class FontTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

    }

    @Before
    public void setUp() throws Exception {
        System.out.println("2 << 1 = " + (1 << 1));
        System.out.println("2 << 2 = " + (1 << 2));
        System.out.println("2 << 3 = " + (1 << 3));
        System.out.println("2 << 4 = " + (1 << 4));
        System.out.println("2 << 5 = " + (1 << 5));
        System.out.println("2 << 6 = " + (1 << 6));
    }

    @Test
    public void realFont() {

        checkRealFont(TestFonts.TEST_REAL_FONT_1);
        checkRealFont(TestFonts.TEST_REAL_FONT_2);
    }

    private void checkRealFont(final FontItem fontItem) {
        final Font font = fontItem.get();
        final AbstractBaseFont rf = (AbstractBaseFont) ResourceBuilders.FONT_BUILDER.getParam(fontItem);

        assertNotNull(font);
        assertEquals(font.getName(), rf.name().name());
        assertEquals(font.getSize(), rf.size(), 0.0);
    }

    @Test
    public void familyFont() {

        checkFamilyFont(TestFonts.TEST_FAMILY_FONT_1);
        checkFamilyFont(TestFonts.TEST_FAMILY_FONT_2);
        checkFamilyFont(TestFonts.TEST_FAMILY_FONT_3);
        checkFamilyFont(TestFonts.TEST_FAMILY_FONT_4);
    }

    private void checkFamilyFont(final FontItem fontItem) {
        final Font font = fontItem.get();
        final FamilyFont ff = (FamilyFont) ResourceBuilders.FONT_BUILDER.getParam(fontItem);

        Assert.assertNotNull(font);

        final String[] names = font.getName().split(" ");
        int i = 0;
        assertThat(names[i++]).isEqualToIgnoringCase(ff.family());

        if (FontWeight.NORMAL != ff.weight()) {
            assertThat(names[i++]).isEqualToIgnoringCase(ff.weight().name());
        }

        if (FontPosture.REGULAR != ff.posture()) {
            assertThat(names[i++]).isEqualToIgnoringCase(ff.posture().name());
        }
        assertEquals(font.getSize(), ff.size(), 0.0);
    }

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

}
