package org.jrebirth.af.core.application;

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
public class FullApplicationConfigurationTest extends ApplicationTest<FullConfApplication> {

    public FullApplicationConfigurationTest() {
        super(FullConfApplication.class);
    }

    @Test
    public void checkFullConf() {

        // Check that default value is used not those of properties file
        Assert.assertEquals(new Integer(1024), JRebirthParameters.APPLICATION_SCENE_WIDTH.get());
        Assert.assertEquals(new Integer(768), JRebirthParameters.APPLICATION_SCENE_HEIGHT.get());

    }

}
