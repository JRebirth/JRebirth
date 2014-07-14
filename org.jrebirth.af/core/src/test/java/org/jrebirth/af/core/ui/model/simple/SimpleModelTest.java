package org.jrebirth.af.core.ui.model.simple;

import org.jrebirth.af.core.ui.model.AbstractModelTest;

import org.junit.Test;

/**
 * The class <strong>FxmlTest</strong>.
 *
 * @author Sébastien Bordes
 */
public class SimpleModelTest extends AbstractModelTest {

    @Test
    public void simpleModel() {

        basicModel(MySimpleModel.class);

    }

    @Test
    public void simpleObjectModel() {

        objectModel(MySimpleObjectModel.class, MySimpleObjectModel2.class);
    }

}
