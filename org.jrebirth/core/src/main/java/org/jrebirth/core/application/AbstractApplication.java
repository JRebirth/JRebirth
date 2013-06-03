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
import java.net.URL;
import java.util.List;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
import org.jrebirth.core.resource.ResourceBuilders;
import org.jrebirth.core.resource.font.FontItem;
import org.jrebirth.core.resource.provided.JRebirthParameters;
import org.jrebirth.core.util.ClassUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * The abstract class <strong>AbstractApplication</strong> is the base class of a JRebirth Application.
 * 
 * This the class to extend if you want to build an application using JRebirth WCS-MVC (Wave-Command-Service-Model-View-Controller).
 * 
 * @author Sébastien Bordes
 * 
 * @param <P> The root node of the stage, must extends Pane to allow children management
 */
@Configuration(".*jrebirth")
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

            // Load configurationrationFiles
            loadConfigurationFiles();

            // Attach the primary stage for later customization
            this.stage = primaryStage;

            // Customize the primary stage
            initializeStage();

            // Build and customize the default scene
            this.scene = buildScene();
            initializeScene();

            // Build the JRebirth Thread before attaching uncaught Exception Handler
            final JRebirthThread jrt = JRebirthThread.getThread();

            // Attach exception handlers
            initializeExceptionHandler();

            // Start the JRebirthThread, if an error occurred it will be processed by predefined handler
            // It will create all facades and trigger the pre and post boot waves and will alost attach the first model view
            jrt.launch(this);

            // Attach the scene
            primaryStage.setScene(this.scene);

            // Let the stage visible for users
            primaryStage.show();

            // Preload fonts to allow them to be used by CSS
            preloadFonts();

            LOGGER.trace("{} has started successfully", this.getClass().getSimpleName());

        } catch (final CoreException ce) {
            LOGGER.error("Error while starting the application : ", ce);
            throw new CoreException(ce);
        }
    }

    /**
     * Load all configuration files before showing anything.
     */
    private void loadConfigurationFiles() {

        // Parse the first annotation found (manage overriding)
        final Configuration conf = ClassUtility.extractAnnotation(this.getClass(), Configuration.class);

        // Conf variable cannot be null because it was defined in this class
        // It's possible to discard default behaviour by setting an empty string to the value.

        // launch the configuration search engine
        ResourceBuilders.PARAMETER_BUILDER.searchConfigurationFiles(conf.value(), conf.extension());
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

                // Wait parameterized delay before retrying to close if the thread is still alive
                Thread.sleep(firstTime ? JRebirthParameters.CLOSE_RETRY_DELAY_FIRST.get() : JRebirthParameters.CLOSE_RETRY_DELAY_OTHER.get());

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

        // The call customize method to allow extension by sub class
        customizeScene(this.scene);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void preloadFonts() {
        final List<FontItem> fontList = getFontToPreload();
        if (fontList != null) {
            for (final FontItem font : fontList) {
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
    protected abstract List<FontItem> getFontToPreload();

    /**
     * Customize the default scene.
     * 
     * @param scene the scene to customize
     */
    protected abstract void customizeScene(final Scene scene);

    /**
     * Return the external form of the css file by using the default classloader.
     * 
     * @param cssFileName the razw path to css file
     * 
     * @return the external form of the css file
     */
    private String loadCSS(final String cssFileName) {
        final URL cssResource = Thread.currentThread().getContextClassLoader().getResource(cssFileName);
        return cssResource == null ? null : cssResource.toExternalForm();
    }

    /**
     * Attach a new CSS file to the scene using the default classloader.
     * 
     * @param scene the scene that will hold this new CSS file
     * @param cssFileName the raw path to css file
     */
    protected void addCSS(final Scene scene, final String cssFileName) {

        final String cssPath = loadCSS(cssFileName);
        if (cssPath != null) {
            scene.getStylesheets().add(cssPath);
        } else {
            LOGGER.error("Impossible to load CSS: " + cssFileName);
        }
    }

    /**
     * Return the application title.
     * 
     * This method could be overridden.
     * 
     * By default it will will return {@link JRebirthParameters.APPLICATION_NAME} {@link JRebirthParameters.APPLICATION_VERSION} string.
     * 
     * The default application is: ApplicationClass powered by JRebirth <br />
     * If version is equals to "0.0.0", it will not be appended
     * 
     * @return the application title
     */
    protected String getApplicationTitle() {
        // Add application Name
        String name = JRebirthParameters.APPLICATION_NAME.get();
        if (name.contains("{}")) {
            name = name.replace("{}", this.getClass().getSimpleName());
        }
        // Add version with a space before
        String version = JRebirthParameters.APPLICATION_VERSION.get();
        if (!"0.0.0".equals(version)) {
            name += " " + version;
        }
        return name;
    }

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
                .width(JRebirthParameters.APPLICATION_STAGE_WIDTH.get())
                .height(JRebirthParameters.APPLICATION_STAGE_HEIGHT.get())
                .fill(Color.TRANSPARENT/* JRebirthApplication.DEFAULT_SCENE_BG_COLOR */)
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

        // Initialize the default uncaught exception handler for all other threads
        Thread.setDefaultUncaughtExceptionHandler(getDefaultUncaughtExceptionHandler());

        // Initialize the uncaught exception handler for JavaFX Application Thread
        JRebirth.runIntoJAT(new AbstractJrbRunnable("Attach JAT Uncaught Exception Handler") {
            @Override
            public void runInto() throws JRebirthThreadException {
                Thread.currentThread().setUncaughtExceptionHandler(getJatUncaughtExceptionHandler());
            }
        });

        // Initialize the uncaught exception handler for JRebirth Internal Thread
        JRebirth.runIntoJIT(new AbstractJrbRunnable("Attach JIT Uncaught Exception Handler") {
            @Override
            public void runInto() throws JRebirthThreadException {
                Thread.currentThread().setUncaughtExceptionHandler(getJitUncaughtExceptionHandler());
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
     * @return the uncaught exception handler for All threads which don't have any handler.
     */
    protected UncaughtExceptionHandler getDefaultUncaughtExceptionHandler() {
        return new DefaultUncaughtExceptionHandler();
    }

    /**
     * Build and return the Uncaught Exception Handler for JavaFX Application Thread.
     * 
     * @return the uncaught exception handler for JavaFX Application Thread
     */
    protected UncaughtExceptionHandler getJatUncaughtExceptionHandler() {
        return new JatUncaughtExceptionHandler();
    }

    /**
     * Build and return the Uncaught Exception Handler for JRebirth Internal Thread.
     * 
     * @return the uncaught exception handler for JRebirth Internal Thread
     */
    protected UncaughtExceptionHandler getJitUncaughtExceptionHandler() {
        return new JitUncaughtExceptionHandler();
    }

    /**
     * Build and return the Uncaught Exception Handler for JRebirth Thread Pool.
     * 
     * @return the uncaught exception handler for JRebirth Thread Pool
     */
    public UncaughtExceptionHandler getPoolUncaughtExceptionHandler() {
        return new PoolUncaughtExceptionHandler();
    }
}
