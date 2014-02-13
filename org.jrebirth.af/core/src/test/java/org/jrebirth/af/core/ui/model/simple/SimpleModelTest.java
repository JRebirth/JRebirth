package org.jrebirth.af.core.ui.model.simple;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertSame;

import org.jrebirth.af.core.ui.model.AbstractModelTest;
import org.jrebirth.af.core.ui.model.ModelBean;
import org.jrebirth.af.core.ui.model.ModelBean2;
import org.jrebirth.af.core.ui.object.AbstractObjectModel;

import org.junit.After;
import org.junit.Before;
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
