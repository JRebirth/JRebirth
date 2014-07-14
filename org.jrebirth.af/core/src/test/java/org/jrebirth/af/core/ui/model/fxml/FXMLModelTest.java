package org.jrebirth.af.core.ui.model.fxml;

import org.jrebirth.af.core.ui.model.AbstractModelTest;

import org.junit.Test;

/**
 * The class <strong>FxmlTest</strong>.
 *
 * @author Sébastien Bordes
 */
public class FXMLModelTest extends AbstractModelTest {

    @Test
    public void fxmlModel() {

        basicModel(MyFXMLModel.class);
    }

    @Test
    public void fxmlObjectModel() {

        objectModel(MyFXMLObjectModel.class, MyFXMLObjectModel2.class);
    }

}
