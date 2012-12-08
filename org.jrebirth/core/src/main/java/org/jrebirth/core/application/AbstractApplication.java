/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
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

import java.lang.Thread.UncaughtExceptionHandler;
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
public abstract class AbstractApplication<P extends Pane> extends Application implements JRebirthApplication<P> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractApplication.class);

    /** The application primary stage. */
    private transient Stage stage;

    /** The application scene. */
    private transient Scene scene;

    /** The root node of the scene built by reflection. */
    private transient P rootNode;

    /**
     * {@inheritDoc}
     */
    // @Override
    @Override
    public final void init() throws CoreException {
        try {
            super.init();
        } catch (final Exception e) {
            LOGGER.error("Error while initializing the application  : ", e);
            throw new CoreException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void start(final Stage primaryStage) throws CoreException {

        try {
            LOGGER.trace("Starting {}", this.getClass().getSimpleName());

            // Attach the primary stage for later customization
            this.stage = primaryStage;

            // Customize the primary stage
            initializeStage();

            // Build and customize the default scene
            this.scene = buildScene();
            initializeScene();

            // Attach exception handler (JRebirth thread will be created)
            initializeExceptionHandler();

            // Start the JRebirthThread
            JRebirthThread.getThread().launch(this);

            // Attach the scene
            primaryStage.setScene(this.scene);

            // Let the stage visible for users
            primaryStage.show();

            LOGGER.trace("{} has started successfully", this.getClass().getSimpleName());

        } catch (final CoreException ce) {
            LOGGER.error("Error while starting the application : ", ce);
            throw new CoreException(ce);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void stop() throws CoreException {
        try {
            LOGGER.trace("Stopping {}", this.getClass().getSimpleName());

            super.stop();

            // Hide the stage is this method wasn't call by user
            if (getStage().isShowing()) {
                getStage().hide();
            }

            // Now nothing is visible by users, Let's kill and release all JRebirth folks
            // without loosing or corrupting something

            // Be Careful done into the JAT
            // Should create a progress bar to control the closure process

            // Flag used to have 2 different waiting times
            boolean firstTime = true;
            do {
                // Try to stop the JRebirth Thread
                JRebirthThread.getThread().close();

                // Wait 2s before retrying to close if the thread is still alive
                Thread.sleep(firstTime ? 4000 : 1000); // TO BE PARAMETRIZED

                if (firstTime) {
                    firstTime = false;
                }
            } while (JRebirthThread.getThread().isAlive());

            LOGGER.trace("{} has stopped successfully", this.getClass().getSimpleName());

        } catch (final Exception e) {
            LOGGER.error("Error while stopping the application : ", e);
            throw new CoreException(e);
        }
    }

    /**
     * Customize the primary Stage.
     */
    private void initializeStage() {
        // Define the stage title
        this.stage.setTitle(getApplicationTitle());
        // and allow customization
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

        final KeyCode fullKeyCode = getFullScreenKeyCode();
        final KeyCode iconKeyCode = getIconifiedKeyCode();

        // Attach the handler only if necessary, these 2 method can be overridden to return null
        if (fullKeyCode != null && iconKeyCode != null) {

            this.scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

                @Override
                public void handle(final KeyEvent event) {
                    // Manage F11 button to switch full screen
                    if (fullKeyCode != null && fullKeyCode == event.getCode()) {
                        currentStage.setFullScreen(!currentStage.isFullScreen());
                        event.consume();
                        // Manage F10 button to iconify
                    } else if (iconKeyCode != null && iconKeyCode == event.getCode()) {
                        currentStage.setIconified(!currentStage.isIconified());
                        event.consume();
                    }

                }
            });
        }

        // Preload fonts to allow them to be used by CSS
        preloadFonts();

        // The call customize method to allow extension by sub class
        customizeScene(this.scene);
    }

    /**
     * Preload fonts to allow them to be used by CSS.
     */
    private void preloadFonts() {
        final List<FontEnum> fontList = getFontToPreload();
        if (fontList != null) {
            for (final FontEnum font : fontList) {
                // Access the font to load it and allow it to be used by CSS
                font.get();
            }
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
        this.rootNode = (P) ClassUtility.buildGenericType(this.getClass(), 0);
        return this.rootNode;
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
     * {@inheritDoc}
     */
    @Override
    public P getRootNode() {
        return this.rootNode;
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
    protected UncaughtExceptionHandler getDefaultUncaughtExceptionHandler(final GlobalFacade gf) {
        return new DefaultUncaughtExceptionHandler(gf);
    }

    /**
     * Build and return the Uncaught Exception Handler for JavaFX Application Thread.
     * 
     * @param gf the JRebirth global facade
     * 
     * @return the uncaught exception handler for JavaFX Application Thread
     */
    protected UncaughtExceptionHandler getJatUncaughtExceptionHandler(final GlobalFacade gf) {
        return new JatUncaughtExceptionHandler(gf);
    }

    /**
     * Build and return the Uncaught Exception Handler for JRebirth Internal Thread.
     * 
     * @param gf the JRebirth global facade
     * 
     * @return the uncaught exception handler for JRebirth Internal Thread
     */
    protected UncaughtExceptionHandler getJitUncaughtExceptionHandler(final GlobalFacade gf) {
        return new JitUncaughtExceptionHandler(gf);
    }

    /**
     * Build and return the Uncaught Exception Handler for JRebirth Thread Pool.
     * 
     * @param gf the JRebirth global facade
     * 
     * @return the uncaught exception handler for JRebirth Thread Pool
     */
    public UncaughtExceptionHandler getPoolUncaughtExceptionHandler(final GlobalFacade gf) {
        return new PoolUncaughtExceptionHandler(gf);
    }
}
