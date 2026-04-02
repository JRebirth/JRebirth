package org.jrebirth.af.core.application;

import org.jrebirth.af.core.application.apps.NullConfApplication;
import org.jrebirth.af.core.resource.ResourceBuilders;
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

    @BeforeClass
    public static void startUp() throws Exception {
        // Other tests may have loaded properties into the shared builder; @Configuration("") skips reload.
        ResourceBuilders.PARAMETER_BUILDER.define(StageParameters.APPLICATION_SCENE_WIDTH, 800);
        ResourceBuilders.PARAMETER_BUILDER.define(StageParameters.APPLICATION_SCENE_HEIGHT, 600);
        ApplicationTest.launch(NullConfApplication.class);
    }

    @Test
    public void checkNullConf() {

        // Check that default value is used not those of properties file
        Assert.assertEquals(new Integer(800), StageParameters.APPLICATION_SCENE_WIDTH.get());
        Assert.assertEquals(new Integer(600), StageParameters.APPLICATION_SCENE_HEIGHT.get());

    }

}
