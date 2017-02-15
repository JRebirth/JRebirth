package org.jrebirth.af.core.application;

import java.lang.reflect.Field;

import javafx.scene.Node;

import org.jrebirth.af.api.command.Command;
import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.service.Service;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.concurrent.JRebirth;
import org.jrebirth.af.core.concurrent.JRebirthThread;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

public class JRebirthApplicationTest<A extends DefaultApplication<?>> extends FxRobot {

    protected A application;

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws Exception {
        if (this.application == null) {
            // ApplicationTest.launch(this.appClass);

            this.application = (A) JRebirthThread.getThread().getApplication();
        }
    }

    @After
    public void cleanup() throws Exception {
        FxToolkit.cleanupStages();
    }

    @AfterClass
    public static void close() throws InterruptedException {

        JRebirth.runIntoJAT(() -> {
            try {
                JRebirthThread.getThread().getApplication().stop();
            } catch (final CoreException e) {
                e.printStackTrace();
            }
        });

        while (JRebirthThread.getThread() != null && JRebirthThread.getThread().isAlive()) {
            Thread.sleep(250);
        }
    }

    protected void hold(final long... time) {
        try {
            Thread.sleep(time.length > 0 ? time[0] : 100);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param wave
     */
    protected void waitWave(final Wave wave) {
        while (wave.status() != Wave.Status.Handled) {
            try {
                Thread.sleep(10);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param model
     * @throws IllegalAccessException
     */
    protected Node grabInternalNode(final Model model, final String nodeName) {
        try {
            final Field f = model.view().getClass().getDeclaredField(nodeName);
            f.setAccessible(true);
            return (Node) f.get(model.view());

        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param model
     * @throws IllegalAccessException
     */
    protected <M extends Model> M model(final Class<M> modelClass) {
        return JRebirthThread.getThread().getFacade().uiFacade().retrieve(modelClass);
    }

    protected <S extends Service> S service(final Class<S> serviceClass) {
        return JRebirthThread.getThread().getFacade().serviceFacade().retrieve(serviceClass);
    }

    protected <C extends Command> C command(final Class<C> commandClass) {
        return JRebirthThread.getThread().getFacade().commandFacade().retrieve(commandClass);
    }

}
