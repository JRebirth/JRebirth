package org.jrebirth.af.core.application;

import org.jrebirth.af.core.application.apps.FullConfApplication;
import org.jrebirth.af.core.resource.provided.parameter.StageParameters;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

/**
 * The class <strong>FullApplicationConfigurationTest</strong>.
 *
 * @author Sébastien Bordes
 */
public class FullApplicationConfigurationTest extends JRebirthApplicationTest<FullConfApplication> {

    @BeforeClass
    public static void startUp() throws Exception {
        ApplicationTest.launch(FullConfApplication.class);
    }

    @Test
    public void checkFullConf() {

        // Check that default value is used not those of properties file
        Assert.assertEquals(new Integer(1024), StageParameters.APPLICATION_SCENE_WIDTH.get());
        Assert.assertEquals(new Integer(768), StageParameters.APPLICATION_SCENE_HEIGHT.get());

    }

}
