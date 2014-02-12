package org.jrebirth.core.application;

import org.jrebirth.af.core.resource.provided.JRebirthParameters;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * The class <strong>StageTest</strong>.
 * 
 * @author Sébastien Bordes
 */
@Ignore("JavaFX can't be run in headless mode yet")
public class NullApplicationConfigurationTest extends ApplicationTest<NullConfApplication> {

    public NullApplicationConfigurationTest() {
        super(NullConfApplication.class);
    }

    @Test
    public void checkNullConf() {

        // Check that default value is used not those of properties file
        Assert.assertEquals(new Integer(800), JRebirthParameters.APPLICATION_SCENE_WIDTH.get());
        Assert.assertEquals(new Integer(600), JRebirthParameters.APPLICATION_SCENE_HEIGHT.get());

    }

}
