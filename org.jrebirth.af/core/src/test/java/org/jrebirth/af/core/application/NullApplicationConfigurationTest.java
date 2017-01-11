package org.jrebirth.af.core.application;

import org.jrebirth.af.core.application.apps.NullConfApplication;
import org.jrebirth.af.core.resource.provided.parameter.StageParameters;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

/**
 * The class <strong>StageTest</strong>.
 *
 * @author Sébastien Bordes
 */
public class NullApplicationConfigurationTest extends JRebirthApplicationTest<NullConfApplication> {

    public NullApplicationConfigurationTest() {
        super(NullConfApplication.class);
    }

    @BeforeClass
    public static void startUp() throws Exception {
        ApplicationTest.launch(NullConfApplication.class);
    }

    @Test
    public void checkNullConf() {

        // Check that default value is used not those of properties file
        Assert.assertEquals(new Integer(800), StageParameters.APPLICATION_SCENE_WIDTH.get());
        Assert.assertEquals(new Integer(600), StageParameters.APPLICATION_SCENE_HEIGHT.get());

    }

}
