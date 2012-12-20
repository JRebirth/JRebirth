package org.jrebirth.core.ui.fxml;

import javafx.scene.Node;
import javafx.scene.text.Text;

import org.jrebirth.core.application.TestApplication;
import org.jrebirth.core.concurrent.JRebirthThread;
import org.jrebirth.core.facade.GlobalFacade;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * The class <strong>FxmlTest</strong>. TODO To complete
 * 
 * @author Sébastien Bordes
 */
public class FxmlTest {

    private static GlobalFacade globalFacade;

    private TestFXMLModel model;

    /**
     * TODO To complete.
     * 
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        // new TestApplication().start(new Stage());
        JRebirthThread.getThread().launch(new TestApplication());
        globalFacade = JRebirthThread.getThread().getFacade();
    }

    /**
     * TODO To complete.
     * 
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void loadFXML1() {

        this.model = FxmlTest.globalFacade.getUiFacade().retrieve(TestFXMLModel.class);

        final FXMLComponent comp = FXMLUtils.loadFXML(this.model, "org/jrebirth/core/ui/fxml/Test1.fxml");
        checkFxmlNode(comp.getNode());
    }

    @Test
    public void loadFXML2() {

        this.model = FxmlTest.globalFacade.getUiFacade().retrieve(TestFXMLModel.class);

        final FXMLComponent comp = FXMLUtils.loadFXML(this.model, "Test1.fxml");
        checkFxmlNode(comp.getNode());
    }

    @Test
    public void loadFXML3() {

        this.model = FxmlTest.globalFacade.getUiFacade().retrieve(TestFXMLModel.class);

        final FXMLComponent comp = FXMLUtils.loadFXML(this.model, "org/jrebirth/core/ui/fxml/Test1.fxml", "org.jrebirth.core.ui.fxml.Test1");
        checkFxmlNode(comp.getNode());

    }

    @Test
    public void loadFXML4() {

        this.model = FxmlTest.globalFacade.getUiFacade().retrieve(TestFXMLModel.class);

        final FXMLComponent comp = FXMLUtils.loadFXML(this.model, "Test1.fxml", "org.jrebirth.core.ui.fxml.Test1");
        checkFxmlNode(comp.getNode());
    }

    @Test
    public void loadFXMLModel1() {

        this.model = FxmlTest.globalFacade.getUiFacade().retrieve(TestFXMLModel.class, "Test1.fxml");

        checkFxmlNode(this.model.getRootNode());
    }

    @Test
    public void loadFXMLModel2() {

        this.model = FxmlTest.globalFacade.getUiFacade().retrieve(TestFXMLModel.class, "org/jrebirth/core/ui/fxml/Test1.fxml");

        checkFxmlNode(this.model.getRootNode());
    }

    @Test
    public void loadFXMLModel3() {

        this.model = FxmlTest.globalFacade.getUiFacade().retrieve(TestFXMLModel.class, "Test1.fxml", "Test1");

        checkFxmlNode(this.model.getRootNode());
    }

    @Test
    public void loadFXMLModel4() {

        this.model = FxmlTest.globalFacade.getUiFacade().retrieve(TestFXMLModel.class, "org/jrebirth/core/ui/fxml/Test1.fxml", "Test1");

        checkFxmlNode(this.model.getRootNode());
    }

    /**
     * TODO To complete.
     * 
     * @param node
     */
    private void checkFxmlNode(final Node node) {
        if (node == null) {
            Assert.fail("The node is null");
        } else if (node instanceof Text) {
            if (((Text) node).getText().startsWith("FXML Error : ")) {
                Assert.fail("The FXMLnode has not been load");
            }
        }

    }

    /**
     * TODO To complete.
     * 
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * TODO To complete.
     * 
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        JRebirthThread.getThread().close();
    }

}
