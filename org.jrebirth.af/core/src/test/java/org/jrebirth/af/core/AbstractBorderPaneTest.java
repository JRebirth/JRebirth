package org.jrebirth.af.core.test;

import javafx.scene.layout.BorderPane;

import org.jrebirth.af.api.facade.GlobalFacade;
import org.jrebirth.af.core.application.JRebirthApplicationTest;
import org.jrebirth.af.core.application.apps.BorderPaneModel;
import org.jrebirth.af.core.application.apps.BorderPaneTestApplication;
import org.jrebirth.af.core.concurrent.JRebirthThread;

import org.junit.BeforeClass;
import org.testfx.framework.junit.ApplicationTest;

/**
 * The class <strong>AbstractBorderPaneTest</strong>.
 *
 * @author Sébastien Bordes
 */
public abstract class AbstractBorderPaneTest extends JRebirthApplicationTest<BorderPaneTestApplication> {

    protected static GlobalFacade globalFacade;

    @BeforeClass
    public static void startUp() throws Exception {
        ApplicationTest.launch(BorderPaneTestApplication.class);

        globalFacade = JRebirthThread.getThread().getFacade();

    }

    public BorderPaneModel getBorderPaneModel() {

        return globalFacade.uiFacade().retrieve(BorderPaneModel.class);
    }

    public BorderPane getBorderPane() {
        return getBorderPaneModel().node();
    }

}