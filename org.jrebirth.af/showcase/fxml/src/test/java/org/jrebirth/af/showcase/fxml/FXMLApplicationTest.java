package org.jrebirth.af.showcase.fxml;

import java.util.concurrent.TimeUnit;

import javafx.scene.input.MouseButton;

import org.jrebirth.af.core.application.JRebirthApplicationTest;
import org.jrebirth.af.core.concurrent.JRebirthThread;
import org.jrebirth.af.showcase.fxml.ui.main.FXMLShowCaseModel;

import org.junit.BeforeClass;
import org.testfx.framework.junit.ApplicationTest;

/**
 * The class <strong>StageTest</strong>.
 *
 * @author Sébastien Bordes
 */
public class FXMLApplicationTest extends JRebirthApplicationTest<FXMLApplication> {

    @BeforeClass
    public static void startUp() throws Exception {
        ApplicationTest.launch(FXMLApplication.class);
    }

    // @Test
    public void fxmlApp() {

        sleep(500, TimeUnit.MILLISECONDS);

        final FXMLShowCaseModel model = JRebirthThread.getThread().getFacade().uiFacade().retrieve(FXMLShowCaseModel.class);

        clickOn(grabInternalNode(model, "showStandalone"), MouseButton.PRIMARY);

        sleep(500, TimeUnit.MILLISECONDS);

        clickOn(grabInternalNode(model, "showHybrid"), MouseButton.PRIMARY);

        sleep(500, TimeUnit.MILLISECONDS);

        clickOn(grabInternalNode(model, "showIncluded"), MouseButton.PRIMARY);

        sleep(500, TimeUnit.MILLISECONDS);

        clickOn(grabInternalNode(model, "showEmbedded"), MouseButton.PRIMARY);

        sleep(500, TimeUnit.MILLISECONDS);

    }

}
