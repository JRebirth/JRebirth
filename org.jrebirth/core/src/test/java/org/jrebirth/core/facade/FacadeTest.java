package org.jrebirth.core.facade;

import org.jrebirth.core.application.TestApplication;
import org.jrebirth.core.command.basic.SwitchFullScreenCommand;
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
public class FacadeTest {

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

    @Test
    public void registerCommandWithKey() {

        final long key = System.currentTimeMillis();
        final SwitchFullScreenCommand command = new SwitchFullScreenCommand();
        this.commandFacade.register(command, key);
        Assert.assertTrue(this.commandFacade.exists(SwitchFullScreenCommand.class, key));
    }

    @Test
    public void registerCommandWithOutKey() {

        final SwitchFullScreenCommand command = new SwitchFullScreenCommand();
        this.commandFacade.register(command);
        Assert.assertTrue(this.commandFacade.exists(SwitchFullScreenCommand.class));
    }

    @Test
    public void retrieveCommand() {

        final long key = System.currentTimeMillis();
        final SwitchFullScreenCommand command = this.commandFacade.retrieve(SwitchFullScreenCommand.class, key);
        Assert.assertNotNull(command);
    }

    @Test
    public void unregisterCommandWithOutKey() {

        final SwitchFullScreenCommand command = this.commandFacade.retrieve(SwitchFullScreenCommand.class);
        this.commandFacade.unregister(command);
        Assert.assertNotNull(this.commandFacade.exists(SwitchFullScreenCommand.class));
    }

    @Test
    public void unregisterCommandWithKey() {

        final long key = System.currentTimeMillis();
        final SwitchFullScreenCommand command = this.commandFacade.retrieve(SwitchFullScreenCommand.class, key);
        this.commandFacade.unregister(command, key);
        Assert.assertNotNull(this.commandFacade.exists(SwitchFullScreenCommand.class, key));
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

}
