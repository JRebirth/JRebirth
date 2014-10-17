package org.jrebirth.af.core.key;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Cursor;
import javafx.scene.layout.BorderPane;

import junit.framework.Assert;

import org.jrebirth.af.core.application.TestApplication;
import org.jrebirth.af.core.command.Command;
import org.jrebirth.af.core.command.basic.SwitchFullScreenCommand;
import org.jrebirth.af.core.command.basic.UpdateCursorCommand;
import org.jrebirth.af.core.facade.CommandFacade;
import org.jrebirth.af.core.facade.GlobalFacadeBase;

import org.junit.After;
import org.junit.AfterClass;
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
        globalFacade = new GlobalFacadeBase(new TestApplication());
    }

    /**
     * TODO To complete.
     *
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        // new TestApplication().start(new Stage());
        this.commandFacade = globalFacade.getCommandFacade();
    }

    @SuppressWarnings("unused")
    @Test
    public void registerCommandWithKey() {

        final List<Command> strongList = new ArrayList<>();

        for (int i = 0; i < 200_000; i++) {
            strongList.add(this.commandFacade.retrieve(SwitchFullScreenCommand.class, new Integer(i)));
            if (i % 20_000 == 0) {
                System.out.println(i + " added");
            }
        }

        final UpdateCursorCommand kc0 = this.commandFacade.retrieve(UpdateCursorCommand.class);
        final UpdateCursorCommand kc1 = this.commandFacade.retrieve(UpdateCursorCommand.class, Cursor.WAIT);
        final UpdateCursorCommand kc2 = this.commandFacade.retrieve(UpdateCursorCommand.class, Cursor.WAIT, new BorderPane());

        final long begin = System.currentTimeMillis();

        final List<UpdateCursorCommand> kcList = this.commandFacade.retrieveAll(Key.create(UpdateCursorCommand.class));

        System.out.println(System.currentTimeMillis() - begin + " ms");

        Assert.assertEquals(3, kcList.size());
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
