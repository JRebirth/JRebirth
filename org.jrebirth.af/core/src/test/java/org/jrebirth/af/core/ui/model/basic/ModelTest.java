package org.jrebirth.af.core.ui.model.basic;

import org.jrebirth.af.core.ui.model.AbstractModelTest;
import org.junit.Test;

/**
 * The class <strong>FxmlTest</strong>.
 *
 * @author Sébastien Bordes
 */
public class ModelTest extends AbstractModelTest {

    @Test
    public void basicModel() {

        basicModel(MyModel.class);
    }

    @Test
    public void basicObjectModel() {

        objectModel(MyObjectModel.class, MyObjectModel2.class);
    }

}
