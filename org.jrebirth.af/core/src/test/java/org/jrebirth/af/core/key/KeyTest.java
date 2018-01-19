package org.jrebirth.af.core.key;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.scene.Cursor;
import javafx.scene.layout.BorderPane;

import org.jrebirth.af.api.command.Command;
import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.core.application.apps.EmptyTestApplication;
import org.jrebirth.af.core.command.basic.SwitchFullScreenCommand;
import org.jrebirth.af.core.command.basic.UpdateCursorCommand;
import org.jrebirth.af.core.facade.CommandFacade;
import org.jrebirth.af.core.facade.GlobalFacadeBase;
import org.jrebirth.af.core.ui.DefaultModel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * The class <strong>FacadeTest</strong>.
 *
 * @author Sébastien Bordes
 */
public class KeyTest {

    private static GlobalFacadeBase globalFacade;

    private CommandFacade commandFacade;

    /**
     * TODO To complete.
     *
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        globalFacade = new GlobalFacadeBase(new EmptyTestApplication());
    }

    /**
     * TODO To complete.
     *
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        // new EmptyTestApplication().start(new Stage());
        this.commandFacade = globalFacade.commandFacade();
    }

    @Test
    public void testCrossKeyInequality() {
        final UniqueKey<DefaultModel> key1 = Key.create(DefaultModel.class);
        final UniqueKey<DefaultModel> key2 = Key.create(DefaultModel.class, new Object());

        Assert.assertFalse(key1.equals(key2));
        Assert.assertFalse(key2.equals(key1));
    }

    @Test
    public void testClassKeyEquality() {
        final Object sameObject = new Object();

        final UniqueKey<DefaultModel> key1 = Key.create(DefaultModel.class);
        final UniqueKey<DefaultModel> key2 = Key.create(DefaultModel.class);

        Assert.assertTrue(key1.equals(key2));
        Assert.assertTrue(key2.equals(key1));
    }

    @Test
    public void testMultitonKeyEquality1() {
        final Object sameObject = new Object();

        final UniqueKey<DefaultModel> key1 = Key.create(DefaultModel.class, sameObject);
        final UniqueKey<DefaultModel> key2 = Key.create(DefaultModel.class, sameObject);

        Assert.assertTrue(key1.equals(key2));
        Assert.assertTrue(key2.equals(key1));
    }

    @Test
    public void testMultitonKeyInequality1() {

        final Object sameObject = new Object();

        final UniqueKey<DefaultModel> key1 = Key.create(DefaultModel.class, sameObject, new Object());
        final UniqueKey<DefaultModel> key2 = Key.create(DefaultModel.class, sameObject);

        Assert.assertFalse(key1.equals(key2));
        Assert.assertFalse(key2.equals(key1));
    }

    @Test
    public void testMultitonKeyInequality2() {

        final UniqueKey<DefaultModel> key1 = Key.create(DefaultModel.class, new Object());
        final UniqueKey<DefaultModel> key2 = Key.create(DefaultModel.class, new Object());

        Assert.assertFalse(key1.equals(key2));
        Assert.assertFalse(key2.equals(key1));
    }

    @SuppressWarnings("unused")
    @Test
    public void registerCommandWithKey() {

        final Set<Command> strongSet = new HashSet<>();

        for (int i = 0; i < 10_000; i++) {
            strongSet.add(this.commandFacade.retrieve(SwitchFullScreenCommand.class, new Integer(i)));
            if (i % 1_000 == 0) {
                System.out.println(i + " added");
            }
        }

        strongSet.add(this.commandFacade.retrieve(UpdateCursorCommand.class));
        strongSet.add(this.commandFacade.retrieve(UpdateCursorCommand.class));
        strongSet.add(this.commandFacade.retrieve(UpdateCursorCommand.class));
        strongSet.add(this.commandFacade.retrieve(UpdateCursorCommand.class));
        strongSet.add(this.commandFacade.retrieve(UpdateCursorCommand.class));
        strongSet.add(this.commandFacade.retrieve(UpdateCursorCommand.class, Cursor.WAIT));
        strongSet.add(this.commandFacade.retrieve(UpdateCursorCommand.class, Cursor.WAIT, new BorderPane()));

        checkComponentCount(UpdateCursorCommand.class, 3);

        checkComponentCount(SwitchFullScreenCommand.class, 10_000);

        Assert.assertEquals(strongSet.size(), 10_003);

        // retain the strong list even method check to avoid compiler optimization that will release item too early
        System.out.println(strongSet.size() + " items strongly retained");

        // Break all strong references to release all components
        strongSet.clear();

        System.gc();

        checkComponentCount(UpdateCursorCommand.class, 0);

        checkComponentCount(SwitchFullScreenCommand.class, 0);

    }

    private <C extends Command> void checkComponentCount(final Class<C> componentClass, final int nb) {
        final long begin = System.currentTimeMillis();

        final List<?> kcList = this.commandFacade.retrieveFilter(Key.create(componentClass));

        System.out.println(kcList.size() + " components of " + nb + " retrieved in " + (System.currentTimeMillis() - begin) + " ms");

        Assert.assertEquals(nb, kcList.size());
    }

    /**
     * TODO To complete.
     *
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.commandFacade = null;
    }

    /**
     * TODO To complete.
     *
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        globalFacade.stop();
        globalFacade = null;
    }

    // public class KeyCommand extends DefaultCommand {
    //
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public void perform(final Wave wave) {
    //
    // }
    // }

}
