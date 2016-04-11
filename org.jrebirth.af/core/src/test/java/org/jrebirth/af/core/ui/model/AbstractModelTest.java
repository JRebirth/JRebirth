package org.jrebirth.af.core.ui.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.jrebirth.af.core.test.AbstractTest;
import org.jrebirth.af.core.ui.AbstractModel;
import org.jrebirth.af.core.ui.object.AbstractObjectModel;

import org.junit.After;
import org.junit.Before;

/**
 * The class <strong>FxmlTest</strong>.
 *
 * @author Sébastien Bordes
 */
public abstract class AbstractModelTest extends AbstractTest {

    /**
     * TODO To complete.
     *
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

    }

    /**
     * TODO To complete.
     */
    protected <M extends AbstractModel> void basicModel(final Class<M> modelClass) {

        final M msm1 = globalFacade.uiFacade().retrieve(modelClass);
        final M msm1bis = globalFacade.uiFacade().retrieve(modelClass);

        final M msm2 = globalFacade.uiFacade().retrieve(modelClass, "keypart");
        final M msm2bis = globalFacade.uiFacade().retrieve(modelClass, "keypart");

        final M msm3 = globalFacade.uiFacade().retrieve(modelClass, "key", "part", 18);
        final M msm3bis = globalFacade.uiFacade().retrieve(modelClass, "key", "part", 18);

        final M msm4 = globalFacade.uiFacade().retrieve(modelClass, "key", new ModelBean("bean"));
        final M msm4bis = globalFacade.uiFacade().retrieve(modelClass, "key", new ModelBean("bean"));

        final ModelBean bean5 = new ModelBean("bean5");
        final M msm5 = globalFacade.uiFacade().retrieve(modelClass, bean5);
        final M msm5bis = globalFacade.uiFacade().retrieve(modelClass, new ModelBean("bean5"));

        final M msm6 = globalFacade.uiFacade().retrieve(modelClass, new ModelBean2("bean", 1));
        final M msm7 = globalFacade.uiFacade().retrieve(modelClass, new ModelBean2("bean", 2));

        assertSame(msm1, msm1bis);
        assertNotSame(msm1, msm2);
        assertEquals(msm1.getFirstKeyPart(), modelClass);

        assertSame(msm2, msm2bis);
        assertNotSame(msm2, msm3);
        assertEquals(msm2.getFirstKeyPart(), "keypart");

        assertSame(msm3, msm3bis);
        assertNotSame(msm3, msm4);
        assertEquals(msm3.getFirstKeyPart(), "key");
        assertEquals(msm3.getListKeyPart().get(0), "key");
        assertEquals(msm3.getListKeyPart().get(1), "part");
        assertEquals(msm3.getListKeyPart().get(2), 18);

        assertSame(msm4, msm4bis);
        assertNotSame(msm4, msm5);
        assertEquals(msm4.getFirstKeyPart(), "key");
        assertEquals(msm4.getListKeyPart().get(0), "key");
        assertEquals(msm4.getListKeyPart().get(1), msm4bis.getListKeyPart().get(1));
        assertEquals(((ModelBean) msm4.getListKeyPart().get(1)).getName(), "bean");

        assertSame(msm5, msm5bis);
        assertNotSame(msm5, msm1);
        assertEquals(msm5.getFirstKeyPart(), bean5);
        assertEquals(msm5.getListKeyPart().get(0), bean5);
        assertEquals(msm5.getFirstKeyPart(), msm5bis.getFirstKeyPart());
        assertEquals(((ModelBean) msm5.getListKeyPart().get(0)).getName(), "bean5");

        assertNotSame(msm6, msm7);
        if (msm6 instanceof AbstractObjectModel) {
            assertNull(((AbstractObjectModel) msm6).object());
        }
        if (msm7 instanceof AbstractObjectModel) {
            assertNull(((AbstractObjectModel) msm7).object());
        }
        assertNotSame(msm6.getFirstKeyPart(), msm7.getFirstKeyPart());
        assertNotSame(msm6.getListKeyPart().get(0), msm7.getListKeyPart().get(0));
    }

    /**
     * TODO To complete.
     */
    protected <M extends AbstractObjectModel, M2 extends AbstractObjectModel> void objectModel(final Class<M> modelClass, final Class<M2> modelClass2) {

        final M msom1 = globalFacade.uiFacade().retrieve(modelClass);
        final M msom1bis = globalFacade.uiFacade().retrieve(modelClass);

        final M msom2 = globalFacade.uiFacade().retrieve(modelClass, "keypart");
        final M msom2bis = globalFacade.uiFacade().retrieve(modelClass, "keypart");

        final M msom3 = globalFacade.uiFacade().retrieve(modelClass, "key", "part", 18);
        final M msom3bis = globalFacade.uiFacade().retrieve(modelClass, "key", "part", 18);

        final M msom4 = globalFacade.uiFacade().retrieve(modelClass, "key", new ModelBean("bean"));
        final M msom4bis = globalFacade.uiFacade().retrieve(modelClass, "key", new ModelBean("bean"));

        final ModelBean bean5 = new ModelBean("bean5");
        final M msom5 = globalFacade.uiFacade().retrieve(modelClass, bean5);
        final M msom5bis = globalFacade.uiFacade().retrieve(modelClass, new ModelBean("bean5"));
        final M msom5ter = globalFacade.uiFacade().retrieve(modelClass, new ModelBean("beanTER"));

        final M2 msom6 = globalFacade.uiFacade().retrieve(modelClass2, new ModelBean2("bean", 1));
        final M2 msom7 = globalFacade.uiFacade().retrieve(modelClass2, new ModelBean2("bean", 2));

        assertSame(msom1, msom1bis);
        assertNotSame(msom1, msom2);
        assertEquals(msom1.getFirstKeyPart(), modelClass);
        assertNotNull(msom1.object());

        assertSame(msom2, msom2bis);
        assertNotSame(msom2, msom3);
        assertEquals(msom2.getFirstKeyPart(), "keypart");

        assertSame(msom3, msom3bis);
        assertNotSame(msom3, msom4);
        assertEquals(msom3.getFirstKeyPart(), "key");
        assertEquals(msom3.getListKeyPart().get(0), "key");
        assertEquals(msom3.getListKeyPart().get(1), "part");
        assertEquals(msom3.getListKeyPart().get(2), 18);

        assertSame(msom4, msom4bis);
        assertNotSame(msom4, msom5);
        assertEquals(msom4.getFirstKeyPart(), "key");
        assertEquals(msom4.getListKeyPart().get(0), "key");
        assertEquals(msom4.getListKeyPart().get(1), msom4bis.getListKeyPart().get(1));
        assertEquals(((ModelBean) msom4.object()).getName(), "bean");

        assertSame(msom5, msom5bis);
        assertNotSame(msom5, msom1);
        assertSame(msom5.object(), msom5bis.object());
        assertNotSame(msom5bis, msom5ter);
        assertNotSame(msom5bis.object(), msom5ter.object());

        assertEquals(msom5.getFirstKeyPart(), bean5);
        assertEquals(msom5.getListKeyPart().get(0), bean5);
        assertEquals(msom5.getFirstKeyPart(), msom5bis.getFirstKeyPart());
        assertEquals(((ModelBean) msom5.object()).getName(), "bean5");

        assertNotSame(msom6, msom7);
        assertNotSame(msom6.object(), msom7.object());
        assertNotSame(msom6.getFirstKeyPart(), msom7.getFirstKeyPart());
        assertNotSame(msom6.getListKeyPart().get(0), msom7.getListKeyPart().get(0));
    }

    /**
     * TODO To complete.
     *
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

}
