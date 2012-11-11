/**
 * Copyright JRebirth.org © 2011-2012 
 * Contact : sebastien.bordes@jrebirth.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.core.application;

import java.util.List;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.jrebirth.core.concurrent.AbstractJrbRunnable;
import org.jrebirth.core.concurrent.JRebirth;
import org.jrebirth.core.concurrent.JRebirthThread;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.exception.JRebirthThreadException;
import org.jrebirth.core.exception.handler.DefaultUncaughtExceptionHandler;
import org.jrebirth.core.exception.handler.JatUncaughtExceptionHandler;
import org.jrebirth.core.exception.handler.JitUncaughtExceptionHandler;
import org.jrebirth.core.exception.handler.PoolUncaughtExceptionHandler;
import org.jrebirth.core.facade.GlobalFacade;
import org.jrebirth.core.resource.font.FontEnum;
import org.jrebirth.core.util.ClassUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * The abstract class <strong>AbstractApplication</strong>.
 * 
 * The class to extend if you want to build an application using JRebirth CSM MVC (Command-Service-Message-Model-View Controller).
 * 
 * @author Sébastien Bordes
 * 
 * @param <P> The root node of the stage, must extends Pane
 */
public abstract class AbstractApplication<P extends Pane> extends Application implements JRebirthApplication {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractApplication.class);

    /** The application primary stage. */
    private transient Stage stage;

    /** The application scene. */
    private transient Scene scene;

    /** The logger status. */
    private boolean loggerEnabled = true;

    /** The eventTracker status. */
    private boolean eventTrackerEnabled = true;

    /**
     * {@inheritDoc}
     */
    // @Override
    @Override
    public final void init() throws CoreException {
        try {
            super.init();
        } catch (final Exception e) {
            LOGGER.error("Error while application init phase : ", e);
            throw new CoreException(e);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void start(final Stage primaryStage) {

        try {

            // Attach the primary stage for later customization
            this.stage = primaryStage;
            // Customize the primary stage
            initializeStage();

            this.scene = buildScene();
            // Customize the default scene previously created
            initializeScene();

            // Attach exception handler (JRebirth thread will be created)
            initializeExceptionHandler();

            // Start the JRebirthThread
            JRebirthThread.getThread().launch(this);

            // Attach the first view and run pre and post command
            // JRebirthThread.getThread().bootUp();

            // Attach the scene
            primaryStage.setScene(this.scene);
            primaryStage.show();

            // JRebirthThread.getThread().stageShown();

            // JRebirth.runIntoJIT(new AbstractJrbRunnable() {
            //
            // @Override
            // protected void runInto() throws JRebirthThreadException {
            // bootup();
            // }
            // });

        } catch (final CoreException ce) {
            LOGGER.error("Error while starting application : ", ce);
        }
    }

    /**
     * Launch custom BootUp action.<br />
     * 
     * In Example read Application parameters and perform something.
     */
    // protected abstract void bootup();

    /**
     * {@inheritDoc}
     */
    // @Override
    @Override
    public final void stop() throws CoreException {
        try {
            super.stop();

            // Be Careful done into the JAT
            // Should create a progress bar to control the closure process

            // Stop the JRebirthThread
            JRebirthThread.getThread().close();

        } catch (final Exception e) {
            // getFacade().getLogger().error("Error while stopping application : "
            // + e.getMessage());
            throw new CoreException(e);
        }
    }

    /**
     * Customize the primary Stage.
     */
    private void initializeStage() {

        this.stage.setTitle(getApplicationTitle());

        customizeStage(this.stage);
    }

    /**
     * Customize the primary stage.
     * 
     * @param stage the primary stage to customize
     */
    protected abstract void customizeStage(final Stage stage);

    /**
     * Initialize the default scene.
     */
    private void initializeScene() {

        final Stage currentStage = this.stage;

        // Manage F11 button to switch full screen
        this.scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

            @Override
            public void handle(final KeyEvent event) {
                if (event.getCode() == getFullScreenKeyCode()) {
                    currentStage.setFullScreen(!currentStage.isFullScreen());
                    event.consume();
                } else if (event.getCode() == getIconifiedKeyCode()) {
                    currentStage.setIconified(!currentStage.isIconified());
                    event.consume();
                }

            }
        });

        // Preload fonts to allow them to be used by CSS
        preloadFonts();

        // The call customize method to allow extension by sub class
        customizeScene(this.scene);
    }

    /**
     * Preload fonts to allow them to be used by CSS.
     */
    private void preloadFonts() {
        for (final FontEnum font : getFontToPreload()) {
            // Access the font to load it and allow it to be used by CSS
            font.get();
        }

    }

    /**
     * Return the list of FontEnum to load for CSS.
     * 
     * @return the list of fontEnum to load
     */
    protected abstract List<FontEnum> getFontToPreload();

    /**
     * Customize the default scene.
     * 
     * @param scene the scene to customize
     */
    protected abstract void customizeScene(final Scene scene);

    /**
     * Return the application title.
     * 
     * @return the application title
     */
    protected abstract String getApplicationTitle();

    /**
     * Initialize the properties of the scene.
     * 
     * 800x600 with transparent background and a Region as Parent Node
     * 
     * @return the scene built
     * 
     * @throws CoreException if build fails
     */
    protected final Scene buildScene() throws CoreException {
        return SceneBuilder.create()
                .root(buildRootPane())
                .width(JRebirthApplication.DEFAULT_SCENE_WIDTH)
                .height(JRebirthApplication.DEFAULT_SCENE_HEIGHT)
                .fill(JRebirthApplication.DEFAULT_SCENE_BG_COLOR)
                .build();
    }

    /**
     * Build dynamically the root pane.
     * 
     * @return the root pane
     * @throws CoreException if build fails
     */
    @SuppressWarnings("unchecked")
    protected P buildRootPane() throws CoreException {
        return (P) ClassUtility.buildGenericType(this.getClass(), 0);
    }

    /**
     * @param loggerEnabled The loggerEnabled to set.
     */
    public final void setLoggerEnabled(final boolean loggerEnabled) {
        this.loggerEnabled = loggerEnabled;
    }

    /**
     * Initialize all Uncaught Exception Handler.
     */
    protected void initializeExceptionHandler() {

        final GlobalFacade gf = JRebirthThread.getThread().getFacade();

        // Initialize the default uncaught exception handler for all other threads
        Thread.setDefaultUncaughtExceptionHandler(getDefaultUncaughtExceptionHandler(gf));

        // Initialize the uncaught exception handler for JavaFX Application Thread
        JRebirth.runIntoJAT(new AbstractJrbRunnable("Attach JAT Uncaught Exception Handler") {
            @Override
            public void runInto() throws JRebirthThreadException {
                Thread.currentThread().setUncaughtExceptionHandler(getJatUncaughtExceptionHandler(gf));
            }
        });

        // Initialize the uncaught exception handler for JRebirth Internal Thread
        JRebirth.runIntoJIT(new AbstractJrbRunnable("Attach JIT Uncaught Exception Handler") {
            @Override
            public void runInto() throws JRebirthThreadException {
                Thread.currentThread().setUncaughtExceptionHandler(getJitUncaughtExceptionHandler(gf));
            }
        });

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isLoggerEnabled() {
        return this.loggerEnabled;
    }

    /**
     * @param eventTrackerEnabled The eventTrackerEnabled to set.
     */
    public final void setEventTrackerEnabled(final boolean eventTrackerEnabled) {
        this.eventTrackerEnabled = eventTrackerEnabled;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isEventTrackerEnabled() {
        return this.eventTrackerEnabled;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stage getStage() {
        return this.stage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Scene getScene() {
        return this.scene;
    }

    /**
     * Return the #KeyCode used to put the application in full screen mode.<br />
     * Can be overridden<br />
     * Default is F11<br />
     * 
     * @return the full screen shortcut
     */
    protected KeyCode getFullScreenKeyCode() {
        return KeyCode.F11;
    }

    /**
     * Return the #KeyCode used to iconify the application.<br />
     * Can be overridden<br />
     * Default is F10<br />
     * 
     * @return the iconify shortcut
     */
    protected KeyCode getIconifiedKeyCode() {
        return KeyCode.F10;
    }

    /**
     * Build and return the Default Uncaught Exception Handler for All threads which don't have any handler.
     * 
     * @param gf the JRebirth global facade
     * 
     * @return the uncaught exception handler for All threads which don't have any handler.
     */
    protected DefaultUncaughtExceptionHandler getDefaultUncaughtExceptionHandler(final GlobalFacade gf) {
        return new DefaultUncaughtExceptionHandler(gf);
    }

    /**
     * Build and return the Uncaught Exception Handler for JavaFX Application Thread.
     * 
     * @param gf the JRebirth global facade
     * 
     * @return the uncaught exception handler for JavaFX Application Thread
     */
    protected JatUncaughtExceptionHandler getJatUncaughtExceptionHandler(final GlobalFacade gf) {
        return new JatUncaughtExceptionHandler(gf);
    }

    /**
     * Build and return the Uncaught Exception Handler for JRebirth Internal Thread.
     * 
     * @param gf the JRebirth global facade
     * 
     * @return the uncaught exception handler for JRebirth Internal Thread
     */
    protected JitUncaughtExceptionHandler getJitUncaughtExceptionHandler(final GlobalFacade gf) {
        return new JitUncaughtExceptionHandler(gf);
    }

    /**
     * Build and return the Uncaught Exception Handler for JRebirth Thread Pool.
     * 
     * @param gf the JRebirth global facade
     * 
     * @return the uncaught exception handler for JRebirth Thread Pool
     */
    public PoolUncaughtExceptionHandler getPoolUncaughtExceptionHandler(final GlobalFacade gf) {
        return new PoolUncaughtExceptionHandler(gf);
    }
}
