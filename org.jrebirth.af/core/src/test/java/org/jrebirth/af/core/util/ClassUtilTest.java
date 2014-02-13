package org.jrebirth.af.core.util;

import org.jrebirth.af.core.util.ClassUtility;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>UtilTest</strong>.
 * 
 * @author Sébastien Bordes
 */
public class ClassUtilTest {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtilTest.class);

    @Test
    public void toCamelCase() {

        Assert.assertEquals("", "atest", ClassUtility.underscoreToCamelCase("ATEST"));
        Assert.assertEquals("", "aTest", ClassUtility.underscoreToCamelCase("A_TEST"));
        Assert.assertEquals("", "aLotOfTest", ClassUtility.underscoreToCamelCase("A_LOT_OF_TEST"));

    }

    @Test
    public void toUnderscore() {

        Assert.assertEquals("", "ATEST", ClassUtility.camelCaseToUnderscore("atest"));
        Assert.assertEquals("", "A_TEST", ClassUtility.camelCaseToUnderscore("aTest"));
        Assert.assertEquals("", "A_LOT_OF_TEST", ClassUtility.camelCaseToUnderscore("aLotOfTest"));
        // Assert.assertEquals("", "A_LOT_OFTEST", ClassUtility.camelCaseToUnderscore("aLotOFTest"));

    }

}
